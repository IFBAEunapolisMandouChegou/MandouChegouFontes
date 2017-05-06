package br.edu.ifba.eunapolis.mandouchegou.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 * @author Matheus Thales - mtxthales@hotmail.com
 */
@Entity
public class PagamentoEntregador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<Entrega> entregas;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataPagamento;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
