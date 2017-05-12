package br.edu.ifba.eunapolis.disunity.mandouchegou.controller;

import br.edu.ifba.eunapolis.disunity.mandouchegou.controller.exceptions.NonexistentEntityException;
import br.edu.ifba.eunapolis.disunity.mandouchegou.controller.exceptions.RollbackFailureException;
import br.edu.ifba.eunapolis.disunity.mandouchegou.model.Contratante;
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
public class ContratanteJpaController implements Serializable {

    public ContratanteJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Contratante contratante) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(contratante);
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

    public void edit(Contratante contratante) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            contratante = em.merge(contratante);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = contratante.getId();
                if (findContratante(id) == null) {
                    throw new NonexistentEntityException("The contratante with id " + id + " no longer exists.");
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
            Contratante contratante;
            try {
                contratante = em.getReference(Contratante.class, id);
                contratante.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contratante with id " + id + " no longer exists.", enfe);
            }
            em.remove(contratante);
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

    public List<Contratante> findContratanteEntities() {
        return findContratanteEntities(true, -1, -1);
    }

    public List<Contratante> findContratanteEntities(int maxResults, int firstResult) {
        return findContratanteEntities(false, maxResults, firstResult);
    }

    private List<Contratante> findContratanteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Contratante.class));
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

    public Contratante findContratante(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Contratante.class, id);
        } finally {
            em.close();
        }
    }

    public int getContratanteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Contratante> rt = cq.from(Contratante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
