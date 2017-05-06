package br.edu.ifba.eunapolis.mandouchegou.model;

import javax.persistence.Entity;

/**
 * @author Matheus Thales - mtxthales@hotmail.com
 */
@Entity
public class PessoaJuridica extends Pessoa{
    
    private String cnpj;
    private String inscricaoEstadual;
    private String razoSocial;
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PessoaJuridica)) {
            return false;
        }
        PessoaJuridica other = (PessoaJuridica) object;
        if ((getId() == null && other.getId() != null) || (getId() != null && !getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.ifba.eunapolis.mandouchegou.model.PessoaJuridica[ id=" + getId() + " ]";
    }

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
