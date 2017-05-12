package br.edu.ifba.eunapolis.disunity.mandouchegou.controller;

import br.edu.ifba.eunapolis.disunity.mandouchegou.controller.exceptions.NonexistentEntityException;
import br.edu.ifba.eunapolis.disunity.mandouchegou.controller.exceptions.RollbackFailureException;
import br.edu.ifba.eunapolis.disunity.mandouchegou.model.FaturaContratante;
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
public class FaturaContratanteJpaController implements Serializable {

    public FaturaContratanteJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FaturaContratante faturaContratante) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(faturaContratante);
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

    public void edit(FaturaContratante faturaContratante) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            faturaContratante = em.merge(faturaContratante);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = faturaContratante.getId();
                if (findFaturaContratante(id) == null) {
                    throw new NonexistentEntityException("The faturaContratante with id " + id + " no longer exists.");
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
            FaturaContratante faturaContratante;
            try {
                faturaContratante = em.getReference(FaturaContratante.class, id);
                faturaContratante.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The faturaContratante with id " + id + " no longer exists.", enfe);
            }
            em.remove(faturaContratante);
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

    public List<FaturaContratante> findFaturaContratanteEntities() {
        return findFaturaContratanteEntities(true, -1, -1);
    }

    public List<FaturaContratante> findFaturaContratanteEntities(int maxResults, int firstResult) {
        return findFaturaContratanteEntities(false, maxResults, firstResult);
    }

    private List<FaturaContratante> findFaturaContratanteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FaturaContratante.class));
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

    public FaturaContratante findFaturaContratante(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FaturaContratante.class, id);
        } finally {
            em.close();
        }
    }

    public int getFaturaContratanteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FaturaContratante> rt = cq.from(FaturaContratante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
