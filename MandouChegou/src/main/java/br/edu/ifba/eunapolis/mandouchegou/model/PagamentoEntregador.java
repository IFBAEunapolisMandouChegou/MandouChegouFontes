package br.edu.ifba.eunapolis.mandouchegou.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Matheus Thales - mtxthales@hotmail.com
 */
@Entity
public class PagamentoEntregador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private List<Entrega> entregas;
    private Date dataPagamento;
    
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
        if (!(object instanceof PagamentoEntregador)) {
            return false;
        }
        PagamentoEntregador other = (PagamentoEntregador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.ifba.eunapolis.mandouchegou.model.PagamentoEntregador[ id=" + id + " ]";
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
     * @return the dataPagamento
     */
    public Date getDataPagamento() {
        return dataPagamento;
    }

    /**
     * @param dataPagamento the dataPagamento to set
     */
    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

}
