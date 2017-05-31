package br.edu.ifba.eunapolis.mandouchegou.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.edu.ifba.eunapolis.mandouchegou.model.Veiculo;

@ApplicationScoped
public class VeiculoRepository {

	@Inject
	private EntityManager em;
	
	public Veiculo findById (Long id){
		return em.find(Veiculo.class,id);
	}
	
	public Veiculo findByPlaca (String placa){
		return em.find(Veiculo.class,placa);
	}
	
	public Veiculo findByRenavan (String renavan){
		return em.find(Veiculo.class,renavan);
	}
	
	public Veiculo findByTipo(String tipo){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Veiculo> criteria = cb.createQuery(Veiculo.class);
		Root<Veiculo> veiculo = criteria.from(Veiculo.class);
		criteria.select(veiculo).where(cb.equal(veiculo.get("tipo"),tipo));
		return em.createQuery(criteria).getSingleResult();
		
	}
	
	public List<Veiculo> findAllOrderedByModelo(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Veiculo> criteria = cb.createQuery(Veiculo.class);
		Root<Veiculo> veiculo = criteria.from(Veiculo.class);
		criteria.select(veiculo).orderBy(cb.asc(veiculo.get("modelo")));
		return em.createQuery(criteria).getResultList();
		
	}
	
	public List <Veiculo> findAllOrderedByMarca(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Veiculo> criteria = cb.createQuery(Veiculo.class);
		Root<Veiculo> veiculo = criteria.from(Veiculo.class);
		criteria.select(veiculo).orderBy(cb.asc(veiculo.get("marca")));
		return em.createQuery(criteria).getResultList();
		
	}
	
	

	
}
