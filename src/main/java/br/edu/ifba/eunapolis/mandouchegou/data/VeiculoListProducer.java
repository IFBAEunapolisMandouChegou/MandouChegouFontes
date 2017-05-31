package br.edu.ifba.eunapolis.mandouchegou.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;


import br.edu.ifba.eunapolis.mandouchegou.model.Veiculo;

@RequestScoped
public class VeiculoListProducer {
	
	@Inject
    private VeiculoRepository veiculoRepository;
	
	private List<Veiculo> veiculos;
	
	@Produces
    @Named
    public List<Veiculo> getVeiculo() {
        return veiculos;
    }
	
	public void onVeiculoListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Veiculo veiculo) {
        retrieveAllVeiculosOrderedByModelo();
    }

    @PostConstruct
    public void retrieveAllVeiculosOrderedByModelo() {
        veiculos = veiculoRepository.findAllOrderedByMarca();
    }


}
