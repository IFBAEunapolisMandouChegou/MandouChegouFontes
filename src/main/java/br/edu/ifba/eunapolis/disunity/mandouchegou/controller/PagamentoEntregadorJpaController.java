package br.edu.ifba.eunapolis.disunity.mandouchegou.controller;

import br.edu.ifba.eunapolis.disunity.mandouchegou.controller.exceptions.NonexistentEntityException;
import br.edu.ifba.eunapolis.disunity.mandouchegou.controller.exceptions.RollbackFailureException;
import br.edu.ifba.eunapolis.disunity.mandouchegou.model.PagamentoEntregador;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 * @author Matheus Thales - mtxthales@hotmail.com
 */
public class PagamentoEntregadorJpaController implements Serializable {

    public PagamentoEntregadorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PagamentoEntregador pagamentoEntregador) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(pagamentoEntregador);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PagamentoEntregador pagamentoEntregador) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            pagamentoEntregador = em.merge(pagamentoEntregador);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pagamentoEntregador.getId();
                if (findPagamentoEntregador(id) == null) {
                    throw new NonexistentEntityException("The pagamentoEntregador with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            PagamentoEntregador pagamentoEntregador;
            try {
                pagamentoEntregador = em.getReference(PagamentoEntregador.class, id);
                pagamentoEntregador.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pagamentoEntregador with id " + id + " no longer exists.", enfe);
            }
            em.remove(pagamentoEntregador);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PagamentoEntregador> findPagamentoEntregadorEntities() {
        return findPagamentoEntregadorEntities(true, -1, -1);
    }

    public List<PagamentoEntregador> findPagamentoEntregadorEntities(int maxResults, int firstResult) {
        return findPagamentoEntregadorEntities(false, maxResults, firstResult);
    }

    private List<PagamentoEntregador> findPagamentoEntregadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PagamentoEntregador.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PagamentoEntregador findPagamentoEntregador(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PagamentoEntregador.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagamentoEntregadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PagamentoEntregador> rt = cq.from(PagamentoEntregador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
