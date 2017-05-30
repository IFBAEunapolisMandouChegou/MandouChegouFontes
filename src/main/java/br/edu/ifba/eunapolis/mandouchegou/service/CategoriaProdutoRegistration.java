package br.edu.ifba.eunapolis.mandouchegou.service;



import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.ifba.eunapolis.mandouchegou.model.CategoriaProduto;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class CategoriaProdutoRegistration {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	private Event<CategoriaProduto> categoriaProdutoEventSrc;

	public void register(CategoriaProduto categoriaProduto) throws Exception {
		log.info("Registrando " + categoriaProduto.getNome());
		em.persist(categoriaProduto);
		categoriaProdutoEventSrc.fire(categoriaProduto);
	}

	public void delete(CategoriaProduto categoriaProduto) throws Exception {
		log.info("Deletando " + categoriaProduto.getNome());
		em.remove(em.merge(categoriaProduto));
		categoriaProdutoEventSrc.fire(categoriaProduto);
	}

	public boolean updateCategoria(Long id, String nome, CategoriaProduto categoraProdutoPai) {
		try {
			em.createNamedQuery("updateCategoriaProduto", CategoriaProduto.class)
			.setParameter(1, nome)
			.setParameter(2, categoraProdutoPai).setParameter(3, id).executeUpdate();

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void update(CategoriaProduto categoriaProduto) throws Exception {
		log.info("Atualizando " + categoriaProduto.getNome());
		//CategoriaProduto c = em.find(CategoriaProduto.class, categoriaProduto.getId());
		//c.setNome(categoriaProduto.getNome());
		em.merge(categoriaProduto);

		categoriaProdutoEventSrc.fire(categoriaProduto);
	}
}
