 package br.edu.ifba.eunapolis.mandouchegou.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Matheus Thales - mtxthales@hotmail.com
 */
@Entity
public class CupomDesconto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double porcetagemDesconto;
    private String nome;
    private String descricao;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the porcetagemDesconto
     */
    public Double getPorcetagemDesconto() {
        return porcetagemDesconto;
    }

    /**
     * @param porcetagemDesconto the porcetagemDesconto to set
     */
    public void setPorcetagemDesconto(Double porcetagemDesconto) {
        this.porcetagemDesconto = porcetagemDesconto;
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
}
