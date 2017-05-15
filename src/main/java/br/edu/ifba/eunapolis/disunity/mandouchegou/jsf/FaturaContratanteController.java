package br.edu.ifba.eunapolis.disunity.mandouchegou.jsf;

import br.edu.ifba.eunapolis.disunity.mandouchegou.model.FaturaContratante;
import br.edu.ifba.eunapolis.disunity.mandouchegou.jsf.util.JsfUtil;
import br.edu.ifba.eunapolis.disunity.mandouchegou.jsf.util.JsfUtil.PersistAction;
import br.edu.ifba.eunapolis.disunity.mandouchegou.jpa.session.FaturaContratanteFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("faturaContratanteController")
@SessionScoped
public class FaturaContratanteController implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @EJB
    private br.edu.ifba.eunapolis.disunity.mandouchegou.jpa.session.FaturaContratanteFacade ejbFacade;
    private List<FaturaContratante> items = null;
    private FaturaContratante selected;

    public FaturaContratanteController() {
    }

    public FaturaContratante getSelected() {
        return selected;
    }

    public void setSelected(FaturaContratante selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private FaturaContratanteFacade getFacade() {
        return ejbFacade;
    }

    public FaturaContratante prepareCreate() {
        selected = new FaturaContratante();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/resources/Bundle").getString("FaturaContratanteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/resources/Bundle").getString("FaturaContratanteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/resources/Bundle").getString("FaturaContratanteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<FaturaContratante> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public FaturaContratante getFaturaContratante(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<FaturaContratante> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<FaturaContratante> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = FaturaContratante.class)
    public static class FaturaContratanteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FaturaContratanteController controller = (FaturaContratanteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "faturaContratanteController");
            return controller.getFaturaContratante(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof FaturaContratante) {
                FaturaContratante o = (FaturaContratante) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), FaturaContratante.class.getName()});
                return null;
            }
        }

    }

}
