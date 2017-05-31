package br.edu.ifba.eunapolis.mandouchegou.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;


import br.edu.ifba.eunapolis.mandouchegou.data.OrigemPedidoRepository;
import br.edu.ifba.eunapolis.mandouchegou.model.OrigemPedido;
import br.edu.ifba.eunapolis.mandouchegou.service.OrigemPedidoRegistration;

@Model
public class OrigemPedidoController {
	
	@Inject
	private FacesContext facesContext;

	@Inject
	private OrigemPedidoRegistration origemPedidoRegistration;

	@Inject
	private OrigemPedidoRepository origemPedidoRepository;

	@Produces
	@Named
	private OrigemPedido newOrigemPedido;

	@PostConstruct
	public void initNewOrigemPedido() {
		newOrigemPedido = new OrigemPedido();
	}

	public String abrirListarItem(Long id){
		this.newOrigemPedido = this.findById(id);
		return "listarOrigemPedido.jsf";
	}

	public String setSelected(Long id) {
		this.newOrigemPedido = this.findById(id);
		return "editarOrigemPedido.jsf";
	}

	public OrigemPedido findById(Long id) {
		return origemPedidoRepository.findById(id);
	}

	public void register() throws Exception {
		try {
			origemPedidoRegistration.register(newOrigemPedido);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
			facesContext.addMessage(null, m);
			initNewOrigemPedido();
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
			facesContext.addMessage(null, m);
		}
	}

	public void update() throws Exception {
		try {
			origemPedidoRegistration.update(newOrigemPedido);
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
			facesContext.addMessage(null, m);
			FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/MandouChegou/item/listarOrigemPedido.jsf");
		}
	}

	public void delete(Long id) throws Exception {
		try {
			origemPedidoRegistration.delete(this.findById(id));
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
