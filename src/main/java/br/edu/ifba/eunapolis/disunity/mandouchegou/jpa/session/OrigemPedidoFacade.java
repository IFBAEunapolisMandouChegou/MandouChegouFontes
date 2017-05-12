package br.edu.ifba.eunapolis.disunity.mandouchegou.jpa.session;

import br.edu.ifba.eunapolis.disunity.mandouchegou.model.OrigemPedido;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Matheus Thales - mtxthales@hotmail.com
 */
@Stateless
public class OrigemPedidoFacade extends AbstractFacade<OrigemPedido> {

    @PersistenceContext(unitName = "br.edu.ifba.eunapolis.desunity.mandouchegou_pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrigemPedidoFacade() {
        super(OrigemPedido.class);
    }

}
