package br.edu.ifba.eunapolis.disunity.mandouchegou.persistence;

import javax.persistence.Persistence;

/**
 * @author mtxth
 */
public class GeraTabelas {

    public static void main(String[] args) {
        Persistence.createEntityManagerFactory("br.edu.ifba.eunapolis.disunity.mandouchegou_pu");
    }
}
