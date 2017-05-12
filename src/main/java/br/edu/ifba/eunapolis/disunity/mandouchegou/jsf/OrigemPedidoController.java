package br.edu.ifba.eunapolis.disunity.mandouchegou.jsf;

import br.edu.ifba.eunapolis.disunity.mandouchegou.model.OrigemPedido;
import br.edu.ifba.eunapolis.disunity.mandouchegou.jsf.util.JsfUtil;
import br.edu.ifba.eunapolis.disunity.mandouchegou.jsf.util.JsfUtil.PersistAction;
import br.edu.ifba.eunapolis.disunity.mandouchegou.jpa.session.OrigemPedidoFacade;

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

@Named("origemPedidoController")
@SessionScoped
public class OrigemPedidoController implements Serializable {

    @EJB
    private br.edu.ifba.eunapolis.disunity.mandouchegou.jpa.session.OrigemPedidoFacade ejbFacade;
    private List<OrigemPedido> items = null;
    private OrigemPedido selected;

    public OrigemPedidoController() {
    }

    public OrigemPedido getSelected() {
        return selected;
    }

    public void setSelected(OrigemPedido selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private OrigemPedidoFacade getFacade() {
        return ejbFacade;
    }

    public OrigemPedido prepareCreate() {
        selected = new OrigemPedido();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/resources/Bundle").getString("OrigemPedidoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/resources/Bundle").getString("OrigemPedidoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/resources/Bundle").getString("OrigemPedidoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<OrigemPedido> getItems() {
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

    public OrigemPedido getOrigemPedido(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<OrigemPedido> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<OrigemPedido> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = OrigemPedido.class)
    public static class OrigemPedidoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OrigemPedidoController controller = (OrigemPedidoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "origemPedidoController");
            return controller.getOrigemPedido(getKey(value));
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
            if (object instanceof OrigemPedido) {
                OrigemPedido o = (OrigemPedido) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), OrigemPedido.class.getName()});
                return null;
            }
        }

    }

}
