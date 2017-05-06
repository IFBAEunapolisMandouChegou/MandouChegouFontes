package br.edu.ifba.eunapolis.mandouchegou.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 * @author Matheus Thales - mtxthales@hotmail.com
 */
@Entity
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private OrigemPedido origem;

    @ManyToOne
    private Destinatario destinatario;
  
    @ManyToOne
    private Entregador entregador;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataAbertura;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataConclusao;
    
    @OneToMany
    private List<ItemPedido> complementos;
    
    @OneToMany
    private Stack<StatusPedido> status;
    
    private Double frete;
    private String comentario;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public List<ItemPedido> getComplementos() {
        return complementos;
    }

    /**
     * @param complementos the complementos to set
     */
    public void setComplementos(List<ItemPedido> complementos) {
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
