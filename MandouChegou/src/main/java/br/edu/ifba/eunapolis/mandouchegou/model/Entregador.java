package br.edu.ifba.eunapolis.mandouchegou.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * @author Matheus Thales - mtxthales@hotmail.com
 */
@Entity
public class Entregador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    private PessoaFisica pessoa;
    
    @OneToOne
    private CarteiraHabilitacao cnh;
    
    @OneToMany
    private List<Veiculo> veiculos;
    
    @OneToMany
    private List<Entrega> entregas;
    
    @OneToMany
    private List<PagamentoEntregador> pagamentos;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * @return the pessoa
     */
    public PessoaFisica getPessoa() {
        return pessoa;
    }

    /**
     * @param pessoa the pessoa to set
     */
    public void setPessoa(PessoaFisica pessoa) {
        this.pessoa = pessoa;
    }

    /**
     * @return the cnh
     */
    public CarteiraHabilitacao getCnh() {
        return cnh;
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
}
