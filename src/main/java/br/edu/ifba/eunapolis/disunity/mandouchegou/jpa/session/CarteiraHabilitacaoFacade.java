package br.edu.ifba.eunapolis.disunity.mandouchegou.jpa.session;

import br.edu.ifba.eunapolis.disunity.mandouchegou.model.CarteiraHabilitacao;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Matheus Thales - mtxthales@hotmail.com
 */
@Stateless
public class CarteiraHabilitacaoFacade extends AbstractFacade<CarteiraHabilitacao> {

    @PersistenceContext(unitName = "br.edu.ifba.eunapolis.disunity.mandouchegou_pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarteiraHabilitacaoFacade() {
        super(CarteiraHabilitacao.class);
    }

}
