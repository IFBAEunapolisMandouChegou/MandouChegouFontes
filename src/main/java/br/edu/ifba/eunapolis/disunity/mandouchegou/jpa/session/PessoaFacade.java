package br.edu.ifba.eunapolis.disunity.mandouchegou.jpa.session;

import br.edu.ifba.eunapolis.disunity.mandouchegou.model.Pessoa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Matheus Thales - mtxthales@hotmail.com
 */
@Stateless
public class PessoaFacade extends AbstractFacade<Pessoa> {

    @PersistenceContext(unitName = "br.edu.ifba.eunapolis.disunity.mandouchegou_pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PessoaFacade() {
        super(Pessoa.class);
    }

}
