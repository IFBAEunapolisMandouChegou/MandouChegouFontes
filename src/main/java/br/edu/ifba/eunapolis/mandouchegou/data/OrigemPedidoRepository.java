package br.edu.ifba.eunapolis.mandouchegou.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.edu.ifba.eunapolis.mandouchegou.model.OrigemPedido;

@ApplicationScoped
public class OrigemPedidoRepository {
	
	@Inject
    private EntityManager em;

    public OrigemPedido findById(Long id) {
        return em.find(OrigemPedido.class, id);
    }

    public OrigemPedido findByEndereco(String endereco) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OrigemPedido> criteria = cb.createQuery(OrigemPedido.class);
        Root<OrigemPedido> origempedido = criteria.from(OrigemPedido.class);
        criteria.select(origempedido).where(cb.equal(origempedido.get("endereco"), endereco));
        return em.createQuery(criteria).getSingleResult();
    }
    
    public List<OrigemPedido> findAllOrderedByEndereco() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OrigemPedido> criteria = cb.createQuery(OrigemPedido.class);
        Root<OrigemPedido> origempedido = criteria.from(OrigemPedido.class);
        criteria.select(origempedido).orderBy(cb.asc(origempedido.get("endereco")));
        return em.createQuery(criteria).getResultList();
    }

}
