package br.edu.ifba.eunapolis.mandouchegou.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifba.eunapolis.mandouchegou.data.EntregadorRepository;
import br.edu.ifba.eunapolis.mandouchegou.model.Entregador;
import br.edu.ifba.eunapolis.mandouchegou.service.EntregadorRegistration;

@Model
public class EntregadorController {

	@Inject
	private FacesContext facesContext;

	@Inject
	private EntregadorRegistration entregadorRegistration;

	@Inject
	private EntregadorRepository entregadorRepository;

	@Produces
	@Named
	private Entregador newEntregador;

	@PostConstruct
	public void initNewEntregador() {
		newEntregador = new Entregador();
	}

	public String alterar() throws Exception{
		update();
		return "listarEntregador.jsf";
	}
	
	public String setSelected(Long id) {
		this.newEntregador = this.findById(id);
		return "editarEntregador.jsf";
	}

	public Entregador findById(Long id) {
		return entregadorRepository.findById(id);
	}

	public void register() throws Exception {
		try {
			entregadorRegistration.register(newEntregador);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
			facesContext.addMessage(null, m);
			initNewEntregador();
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
			facesContext.addMessage(null, m);
		}
	}

	public void update() throws Exception {
		try {
			entregadorRegistration.update(newEntregador);
			            


		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
			facesContext.addMessage(null, m);
			
		}
	}

	public void delete(Long id) throws Exception {
		try {
			entregadorRegistration.delete(this.findById(id));
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
