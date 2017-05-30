package br.edu.ifba.eunapolis.mandouchegou.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifba.eunapolis.mandouchegou.data.ItemRepository;
import br.edu.ifba.eunapolis.mandouchegou.model.Item;
import br.edu.ifba.eunapolis.mandouchegou.service.ItemRegistration;

@Model
public class ItemController {

	@Inject
	private FacesContext facesContext;

	@Inject
	private ItemRegistration itemRegistration;

	@Inject
	private ItemRepository itemRepository;

	@Produces
	@Named
	private Item newItem;

	@PostConstruct
	public void initNewItem() {
		newItem = new Item();
	}

	public String alterar() throws Exception{
		update();
		return "listarItem.jsf";
	}
	
	public String novoItem() {
		return "item.jsf";
	}

	public String setSelected(Long id) {
		this.newItem = this.findById(id);
		return "editarItem.jsf";
	}

	public Item findById(Long id) {
		return itemRepository.findById(id);
	}

	public void register() throws Exception {
		try {
			itemRegistration.register(newItem);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
			facesContext.addMessage(null, m);
			initNewItem();
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
			facesContext.addMessage(null, m);
		}
	}

	public void update() throws Exception {
		try {
			itemRegistration.update(newItem);
			            


		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
			facesContext.addMessage(null, m);
			FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/MandouChegou/item/listarItem.jsf");
		}
	}

	public void delete(Long id) throws Exception {
		try {
			itemRegistration.delete(this.findById(id));
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Deletado", "Deletation successful");
			facesContext.addMessage(null, m);
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Deletation unsuccessful");
			facesContext.addMessage(null, m);
		}
	}

	private String getRootErrorMessage(Exception e) {
		// Default to general error message that registration failed.
		String errorMessage = "Registration failed. See server log for more information";
		if (e == null) {
			// This shouldn't happen, but return the default messages
			return errorMessage;
		}

		// Start with the exception and recurse to find the root cause
		Throwable t = e;
		while (t != null) {
			// Get the message from the Throwable class instance
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		// This is the root cause message
		return errorMessage;
	}

}
