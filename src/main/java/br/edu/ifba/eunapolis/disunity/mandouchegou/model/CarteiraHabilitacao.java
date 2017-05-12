package br.edu.ifba.eunapolis.disunity.mandouchegou.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 * @author Matheus Thales - mtxthales@hotmail.com
 */
@Entity
public class CarteiraHabilitacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String numeracao;
    private String categoria;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date vencimento;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the numeracao
     */
    public String getNumeracao() {
        return numeracao;
    }

    /**
     * @param numeracao the numeracao to set
     */
    public void setNumeracao(String numeracao) {
        this.numeracao = numeracao;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the vencimento
     */
    public Date getVencimento() {
        return vencimento;
    }

    /**
     * @param vencimento the vencimento to set
     */
    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

}
