package br.edu.ifba.eunapolis.mandouchegou.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Matheus Thales - mtxthales@hotmail.com
 */
@Entity
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private OrigemPedido origem;
    private Destinatario destinatario;
    private Entregador entregador;
    private Double frete;
    private List<ComplementoPedido> complementos;
    private Stack<StatusPedido> status;
    private String comentario;
    private Date dataAbertura;
    private Date dataConclusao;
    
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
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.ifba.eunapolis.mandouchegou.model.Pedido[ id=" + id + " ]";
    }

    /**
     * @return the origem
     */
    public OrigemPedido getOrigem() {
        return origem;
    }

    /**
     * @param origem the origem to set
     */
    public void setOrigem(OrigemPedido origem) {
        this.origem = origem;
    }

    /**
     * @return the destinatario
     */
    public Destinatario getDestinatario() {
        return destinatario;
    }

    /**
     * @param destinatario the destinatario to set
     */
    public void setDestinatario(Destinatario destinatario) {
        this.destinatario = destinatario;
    }

    /**
     * @return the entregador
     */
    public Entregador getEntregador() {
        return entregador;
    }

    /**
     * @param entregador the entregador to set
     */
    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    /**
     * @return the frete
     */
    public Double getFrete() {
        return frete;
    }

    /**
     * @param frete the frete to set
     */
    public void setFrete(Double frete) {
        this.frete = frete;
    }

    /**
     * @return the complementos
     */
    public List<ComplementoPedido> getComplementos() {
        return complementos;
    }

    /**
     * @param complementos the complementos to set
     */
    public void setComplementos(List<ComplementoPedido> complementos) {
        this.complementos = complementos;
    }

    /**
     * @return the status
     */
    public Stack<StatusPedido> getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Stack<StatusPedido> status) {
        this.status = status;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * @return the dataAbertura
     */
    public Date getDataAbertura() {
        return dataAbertura;
    }

    /**
     * @param dataAbertura the dataAbertura to set
     */
    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    /**
     * @return the dataConclusao
     */
    public Date getDataConclusao() {
        return dataConclusao;
    }

    /**
     * @param dataConclusao the dataConclusao to set
     */
    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

}
