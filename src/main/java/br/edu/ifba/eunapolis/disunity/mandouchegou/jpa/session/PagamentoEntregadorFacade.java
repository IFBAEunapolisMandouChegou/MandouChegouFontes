package br.edu.ifba.eunapolis.disunity.mandouchegou.jpa.session;

import br.edu.ifba.eunapolis.disunity.mandouchegou.model.PagamentoEntregador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Matheus Thales - mtxthales@hotmail.com
 */
@Stateless
public class PagamentoEntregadorFacade extends AbstractFacade<PagamentoEntregador> {

    @PersistenceContext(unitName = "br.edu.ifba.eunapolis.disunity.mandouchegou_pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagamentoEntregadorFacade() {
        super(PagamentoEntregador.class);
    }

}
