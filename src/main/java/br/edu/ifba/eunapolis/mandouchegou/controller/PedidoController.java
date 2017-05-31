package br.edu.ifba.eunapolis.mandouchegou.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifba.eunapolis.mandouchegou.data.PedidoRepository;
import br.edu.ifba.eunapolis.mandouchegou.model.Pedido;
import br.edu.ifba.eunapolis.mandouchegou.service.PedidoRegistration;

@Model
public class PedidoController {

	@Inject
	private FacesContext facesContext;

	@Inject
	private PedidoRegistration pedidoRegistration;

	@Inject
	private PedidoRepository pedidoRepository;

	@Produces
	@Named
	private Pedido newPedido;

	@PostConstruct
	public void initNewPedido() {
		newPedido = new Pedido();
	}

	public String setSelected(Long id) {
		this.newPedido = this.findById(id);
		return "editarPedido.jsf";
	}

	public Pedido findById(Long id) {
		return pedidoRepository.findById(id);
	}

	public void register() throws Exception {
		try {
			pedidoRegistration.register(newPedido);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
			facesContext.addMessage(null, m);
			initNewPedido();
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
			facesContext.addMessage(null, m);
		}
	}

	public void update() throws Exception {
		try {
			pedidoRegistration.update(newPedido);
			            


		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
			facesContext.addMessage(null, m);
			
		}
	}

	public void delete(Long id) throws Exception {
		try {
			pedidoRegistration.delete(this.findById(id));
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
