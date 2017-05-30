package br.edu.ifba.eunapolis.mandouchegou.model;



import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * @author
 */
@Entity
public class Entregador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne(cascade=CascadeType.ALL)
    private PessoaFisica pessoa;
    
    
    @OneToOne(cascade=CascadeType.ALL)
    private CarteiraHabilitacao cnh;
    
    @OneToMany
    private List<Veiculo> veiculos;
    
    @OneToMany
    private List<Entrega> entregas;
    
    @OneToMany
    private List<PagamentoEntregador> pagamentos;
    public Entregador(){
    	pessoa=new PessoaFisica();
    	cnh = new CarteiraHabilitacao();
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * @return the pessoa
     */
    
    /**
     * @param pessoa the pessoa to set
     */
    

    /**
     * @return the cnh
     */
    public CarteiraHabilitacao getCnh() {
        return cnh;
    }

    public PessoaFisica getPessoa() {
		return pessoa;
	}



	public void setPessoa(PessoaFisica pessoa) {
		this.pessoa = pessoa;
	}



	/**
     * @param cnh the cnh to set
     */
    public void setCnh(CarteiraHabilitacao cnh) {
        this.cnh = cnh;
    }

    /**
     * @return the veiculos
     */
    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    /**
     * @param veiculos the veiculos to set
     */
    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    /**
     * @return the entregas
     */
    public List<Entrega> getEntregas() {
        return entregas;
    }

    /**
     * @param entregas the entregas to set
     */
    public void setEntregas(List<Entrega> entregas) {
        this.entregas = entregas;
    }

    /**
     * @return the pagamentos
     */
    public List<PagamentoEntregador> getPagamentos() {
        return pagamentos;
    }

    /**
     * @param pagamentos the pagamentos to set
     */
    public void setPagamentos(List<PagamentoEntregador> pagamentos) {
        this.pagamentos = pagamentos;
    }
    

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entregador other = (Entregador) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
    
}
