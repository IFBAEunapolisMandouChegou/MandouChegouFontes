package br.edu.ifba.eunapolis.mandouchegou.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Matheus Thales - mtxthales@hotmail.com
 */
@Entity
public class Entregador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private PessoaFisica pessoa;
    private CarteiraHabilitacao cnh;
    private List<Veiculo> veiculos;
    private List<Entrega> entregas;
    private List<PagamentoEntregador> pagamentos;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entregador)) {
            return false;
        }
        Entregador other = (Entregador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.ifba.eunapolis.mandouchegou.model.Entregador[ id=" + id + " ]";
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
