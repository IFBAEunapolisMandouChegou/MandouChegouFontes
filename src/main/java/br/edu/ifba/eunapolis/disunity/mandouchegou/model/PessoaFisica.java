package br.edu.ifba.eunapolis.disunity.mandouchegou.model;

import br.edu.ifba.eunapolis.disunity.mandouchegou.model.Pessoa;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 * @author Matheus Thales - mtxthales@hotmail.com
 */
@Entity
public class PessoaFisica extends Pessoa{

	private static final long serialVersionUID = 1L;
	
	private String rg;
    private String cpf;
    private String sexo;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataNascimento;

    /**
     * @return the rg
     */
    public String getRg() {
        return rg;
    }

    /**
     * @param rg the rg to set
     */
    public void setRg(String rg) {
        this.rg = rg;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the dataNascimento
     */
    public Date getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @param dataNascimento the dataNascimento to set
     */
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
