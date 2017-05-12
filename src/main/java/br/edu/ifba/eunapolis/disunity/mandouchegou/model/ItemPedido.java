package br.edu.ifba.eunapolis.disunity.mandouchegou.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Matheus Thales - mtxthales@hotmail.com
 */
@Entity
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String descricao;
    private Double valorMaterial;
    private Boolean retornavel;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the valorMaterial
     */
    public Double getValorMaterial() {
        return valorMaterial;
    }

    /**
     * @param valorMaterial the valorMaterial to set
     */
    public void setValorMaterial(Double valorMaterial) {
        this.valorMaterial = valorMaterial;
    }

    /**
     * @return the retornavel
     */
    public Boolean getRetornavel() {
        return retornavel;
    }

    /**
     * @param retornavel the retornavel to set
     */
    public void setRetornavel(Boolean retornavel) {
        this.retornavel = retornavel;
    }
}
