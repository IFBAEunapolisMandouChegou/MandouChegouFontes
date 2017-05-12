package br.edu.ifba.eunapolis.disunity.mandouchegou.controller;

import br.edu.ifba.eunapolis.disunity.mandouchegou.controller.exceptions.NonexistentEntityException;
import br.edu.ifba.eunapolis.disunity.mandouchegou.controller.exceptions.RollbackFailureException;
import br.edu.ifba.eunapolis.disunity.mandouchegou.model.StatusPedido;
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
public class StatusPedidoJpaController implements Serializable {

    public StatusPedidoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(StatusPedido statusPedido) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(statusPedido);
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

    public void edit(StatusPedido statusPedido) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            statusPedido = em.merge(statusPedido);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = statusPedido.getId();
                if (findStatusPedido(id) == null) {
                    throw new NonexistentEntityException("The statusPedido with id " + id + " no longer exists.");
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
            StatusPedido statusPedido;
            try {
                statusPedido = em.getReference(StatusPedido.class, id);
                statusPedido.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The statusPedido with id " + id + " no longer exists.", enfe);
            }
            em.remove(statusPedido);
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

    public List<StatusPedido> findStatusPedidoEntities() {
        return findStatusPedidoEntities(true, -1, -1);
    }

    public List<StatusPedido> findStatusPedidoEntities(int maxResults, int firstResult) {
        return findStatusPedidoEntities(false, maxResults, firstResult);
    }

    private List<StatusPedido> findStatusPedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StatusPedido.class));
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

    public StatusPedido findStatusPedido(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(StatusPedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getStatusPedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StatusPedido> rt = cq.from(StatusPedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
