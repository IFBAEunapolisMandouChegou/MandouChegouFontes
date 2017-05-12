package br.edu.ifba.eunapolis.disunity.mandouchegou.controller;

import br.edu.ifba.eunapolis.disunity.mandouchegou.controller.exceptions.NonexistentEntityException;
import br.edu.ifba.eunapolis.disunity.mandouchegou.controller.exceptions.RollbackFailureException;
import br.edu.ifba.eunapolis.disunity.mandouchegou.model.CarteiraHabilitacao;
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
public class CarteiraHabilitacaoJpaController implements Serializable {

    public CarteiraHabilitacaoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CarteiraHabilitacao carteiraHabilitacao) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(carteiraHabilitacao);
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

    public void edit(CarteiraHabilitacao carteiraHabilitacao) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            carteiraHabilitacao = em.merge(carteiraHabilitacao);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = carteiraHabilitacao.getId();
                if (findCarteiraHabilitacao(id) == null) {
                    throw new NonexistentEntityException("The carteiraHabilitacao with id " + id + " no longer exists.");
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
            CarteiraHabilitacao carteiraHabilitacao;
            try {
                carteiraHabilitacao = em.getReference(CarteiraHabilitacao.class, id);
                carteiraHabilitacao.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The carteiraHabilitacao with id " + id + " no longer exists.", enfe);
            }
            em.remove(carteiraHabilitacao);
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

    public List<CarteiraHabilitacao> findCarteiraHabilitacaoEntities() {
        return findCarteiraHabilitacaoEntities(true, -1, -1);
    }

    public List<CarteiraHabilitacao> findCarteiraHabilitacaoEntities(int maxResults, int firstResult) {
        return findCarteiraHabilitacaoEntities(false, maxResults, firstResult);
    }

    private List<CarteiraHabilitacao> findCarteiraHabilitacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CarteiraHabilitacao.class));
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

    public CarteiraHabilitacao findCarteiraHabilitacao(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CarteiraHabilitacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getCarteiraHabilitacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CarteiraHabilitacao> rt = cq.from(CarteiraHabilitacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
