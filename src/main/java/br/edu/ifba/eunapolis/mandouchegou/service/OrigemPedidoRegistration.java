package br.edu.ifba.eunapolis.mandouchegou.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.ifba.eunapolis.mandouchegou.model.OrigemPedido;

@Stateless
public class OrigemPedidoRegistration {
	@Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<OrigemPedido> origemPedidoEventSrc;

    public void register(OrigemPedido origemPedido) throws Exception {
        log.info("Registering " + origemPedido.getEndereco());
        em.persist(origemPedido);
        origemPedidoEventSrc.fire(origemPedido);
    }
    
    public void delete(OrigemPedido origemPedido) throws Exception {
		log.info("Deletando " + origemPedido.getEndereco());
		em.remove(em.merge(origemPedido));
		origemPedidoEventSrc.fire(origemPedido);
	}

    
    
	public void update(OrigemPedido origemPedido) throws Exception {
		log.info("Atualizando " + origemPedido.getEndereco());
		em.merge(origemPedido);
		origemPedidoEventSrc.fire(origemPedido);
	}
	
	

}


