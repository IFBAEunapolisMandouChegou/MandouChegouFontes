package br.edu.ifba.eunapolis.disunity.mandouchegou.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date abertura;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date conclusao;
    
    @OneToMany
    private List<ItemPedido> complementos;
    
    @OneToMany
    private List<StatusPedido> status;
    
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
    public List<StatusPedido> getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(List<StatusPedido> status) {
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
     * @return the abertura
     */
    public Date getAbertura() {
        return abertura;
    }

    /**
     * @param abertura the abertura to set
     */
    public void setAbertura(Date abertura) {
        this.abertura = abertura;
    }

    /**
     * @return the conclusao
     */
    public Date getConclusao() {
        return conclusao;
    }

    /**
     * @param conclusao the conclusao to set
     */
    public void setConclusao(Date conclusao) {
        this.conclusao = conclusao;
    }

}
