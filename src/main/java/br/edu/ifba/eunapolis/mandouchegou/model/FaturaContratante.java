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
public class FaturaContratante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<Pedido> pedidos;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataFechamento;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataVencimento;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataPagamento;
    
    private Double totalFatura;
    private Double valorPago;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * @return the pedidos
     */
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    /**
     * @param pedidos the pedidos to set
     */
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    /**
     * @return the dataFechamento
     */
    public Date getDataFechamento() {
        return dataFechamento;
    }

    /**
     * @param dataFechamento the dataFechamento to set
     */
    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    /**
     * @return the dataVencimento
     */
    public Date getDataVencimento() {
        return dataVencimento;
    }

    /**
     * @param dataVencimento the dataVencimento to set
     */
    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
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

    /**
     * @return the totalFatura
     */
    public Double getTotalFatura() {
        return totalFatura;
    }

    /**
     * @param totalFatura the totalFatura to set
     */
    public void setTotalFatura(Double totalFatura) {
        this.totalFatura = totalFatura;
    }

    /**
     * @return the valorPago
     */
    public Double getValorPago() {
        return valorPago;
    }

    /**
     * @param valorPago the valorPago to set
     */
    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }
}
