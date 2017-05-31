package br.edu.ifba.eunapolis.mandouchegou.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifba.eunapolis.mandouchegou.model.OrigemPedido;

@RequestScoped
public class OrigemPedidoListProducer {
	@Inject
    private OrigemPedidoRepository origemPedidoRepository;

    private List<OrigemPedido> origempedidos;

    
    @Produces
    @Named
    public List<OrigemPedido> getOrigemPedido() {
        return origempedidos;
    }

    public void onItemListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final OrigemPedido origempedido) {
    	retrieveAllOrigemPedidoOrderedByEndereco();
    }

    @PostConstruct
    public void retrieveAllOrigemPedidoOrderedByEndereco() {
    	origempedidos = origemPedidoRepository.findAllOrderedByEndereco();
    }
}

