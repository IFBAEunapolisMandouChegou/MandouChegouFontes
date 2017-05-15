package br.edu.ifba.eunapolis.disunity.mandouchegou.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * @author Matheus Thales - mtxthales@hotmail.com
 */
@Entity
public class Contratante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    private Pessoa pessoa;
    
    @OneToMany
    private List<Pedido> pedidos;
    
    @OneToMany
    private List<FaturaContratante> faturas;

    public Contratante(){
        this.faturas = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    public boolean addFatura(FaturaContratante fatura){
        return this.faturas.add(fatura);
    }
    
    public boolean removeFatura(FaturaContratante fatura){
        return this.faturas.remove(fatura);
    }
    
    public boolean addPedido(Pedido pedido){
        return this.pedidos.add(pedido);
    }
    
    public boolean removePediatdo(Pedido pedido){
        return this.pedidos.remove(pedido);
    }
    
    
    // Getters and Setters......................................................
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the pessoa
     */
    public Pessoa getPessoa() {
        return pessoa;
    }

    /**
     * @param pessoa the pessoa to set
     */
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    /**
     * @return the pedidos
     */
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    /**
     * @return the faturas
     */
    public List<FaturaContratante> getFaturas() {
        return faturas;
    }
}
