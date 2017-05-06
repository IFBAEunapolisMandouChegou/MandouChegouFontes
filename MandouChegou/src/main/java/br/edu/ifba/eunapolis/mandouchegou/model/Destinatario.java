package br.edu.ifba.eunapolis.mandouchegou.model;

import javax.persistence.Entity;

/**
 * @author Matheus Thales - mtxthales@hotmail.com
 */
@Entity
public class Destinatario extends Pessoa{

    private String comentario;
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Destinatario)) {
            return false;
        }
        Destinatario other = (Destinatario) object;
        return !((getId() == null && other.getId() != null) || (getId() != null && !getId().equals(other.getId())));
    }

    @Override
    public String toString() {
        return "br.edu.ifba.eunapolis.mandouchegou.model.Destinatario[ id=" + getId() + " ]";
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

}
