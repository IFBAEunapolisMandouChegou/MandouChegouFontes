package br.edu.ifba.eunapolis.disunity.mandouchegou.jpa.session;

import br.edu.ifba.eunapolis.disunity.mandouchegou.model.StatusPedido;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Matheus Thales - mtxthales@hotmail.com
 */
@Stateless
public class StatusPedidoFacade extends AbstractFacade<StatusPedido> {

    @PersistenceContext(unitName = "br.edu.ifba.eunapolis.desunity.mandouchegou_pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatusPedidoFacade() {
        super(StatusPedido.class);
    }

}
