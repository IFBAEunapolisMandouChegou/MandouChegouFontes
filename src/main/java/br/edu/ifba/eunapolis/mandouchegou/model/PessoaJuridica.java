package br.edu.ifba.eunapolis.mandouchegou.model;


import javax.persistence.Entity;

/**
 * @author
 */
@Entity
public class PessoaJuridica extends Pessoa{
    
	private static final long serialVersionUID = 1L;
	
	private String cnpj;
    private String inscricaoEstadual;
    private String razoSocial;
    
    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * @return the inscricaoEstadual
     */
    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    /**
     * @param inscricaoEstadual the inscricaoEstadual to set
     */
    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    /**
     * @return the razoSocial
     */
    public String getRazoSocial() {
        return razoSocial;
    }

    /**
     * @param razoSocial the razoSocial to set
     */
    public void setRazoSocial(String razoSocial) {
        this.razoSocial = razoSocial;
    }
}
