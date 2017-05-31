package br.edu.ifba.eunapolis.mandouchegou.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.ifba.eunapolis.mandouchegou.model.Veiculo;

@Stateless
public class VeiculoRegistration {
	 @Inject
	    private Logger log;

	    @Inject
	    private EntityManager em;

	    @Inject
	    private Event<Veiculo> veiculoEvent;
	    
	    public void register(Veiculo veiculo) throws Exception {
	        log.info("Registrando" + veiculo.getModelo());
	        em.persist(veiculo);
	        veiculoEvent.fire(veiculo);
	    }
	    
	    public void delete(Veiculo veiculo) throws Exception {
			log.info("Deletando " + veiculo.getModelo());
			em.remove(em.merge(veiculo));
			veiculoEvent.fire(veiculo);
		}

	    
	    public void update(Veiculo veiculo) throws Exception {
			log.info("Deletando " + veiculo.getModelo());
			em.merge(veiculo);
			veiculoEvent.fire(veiculo);
		}

}
