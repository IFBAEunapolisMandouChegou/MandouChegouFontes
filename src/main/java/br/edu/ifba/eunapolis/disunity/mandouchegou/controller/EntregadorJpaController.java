package br.edu.ifba.eunapolis.disunity.mandouchegou.controller;

import br.edu.ifba.eunapolis.disunity.mandouchegou.controller.exceptions.NonexistentEntityException;
import br.edu.ifba.eunapolis.disunity.mandouchegou.controller.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import br.edu.ifba.eunapolis.disunity.mandouchegou.model.Entrega;
import br.edu.ifba.eunapolis.disunity.mandouchegou.model.Entregador;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 * @author Matheus Thales - mtxthales@hotmail.com
 */
public class EntregadorJpaController implements Serializable {

    public EntregadorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Entregador entregador) throws RollbackFailureException, Exception {
        if (entregador.getEntregas() == null) {
            entregador.setEntregas(new ArrayList<Entrega>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Entrega> attachedEntregas = new ArrayList<Entrega>();
            for (Entrega entregasEntregaToAttach : entregador.getEntregas()) {
                entregasEntregaToAttach = em.getReference(entregasEntregaToAttach.getClass(), entregasEntregaToAttach.getId());
                attachedEntregas.add(entregasEntregaToAttach);
            }
            entregador.setEntregas(attachedEntregas);
            em.persist(entregador);
            for (Entrega entregasEntrega : entregador.getEntregas()) {
                Entregador oldEntregadorOfEntregasEntrega = entregasEntrega.getEntregador();
                entregasEntrega.setEntregador(entregador);
                entregasEntrega = em.merge(entregasEntrega);
                if (oldEntregadorOfEntregasEntrega != null) {
                    oldEntregadorOfEntregasEntrega.getEntregas().remove(entregasEntrega);
                    oldEntregadorOfEntregasEntrega = em.merge(oldEntregadorOfEntregasEntrega);
                }
            }
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

    public void edit(Entregador entregador) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Entregador persistentEntregador = em.find(Entregador.class, entregador.getId());
            List<Entrega> entregasOld = persistentEntregador.getEntregas();
            List<Entrega> entregasNew = entregador.getEntregas();
            List<Entrega> attachedEntregasNew = new ArrayList<Entrega>();
            for (Entrega entregasNewEntregaToAttach : entregasNew) {
                entregasNewEntregaToAttach = em.getReference(entregasNewEntregaToAttach.getClass(), entregasNewEntregaToAttach.getId());
                attachedEntregasNew.add(entregasNewEntregaToAttach);
            }
            entregasNew = attachedEntregasNew;
            entregador.setEntregas(entregasNew);
            entregador = em.merge(entregador);
            for (Entrega entregasOldEntrega : entregasOld) {
                if (!entregasNew.contains(entregasOldEntrega)) {
                    entregasOldEntrega.setEntregador(null);
                    entregasOldEntrega = em.merge(entregasOldEntrega);
                }
            }
            for (Entrega entregasNewEntrega : entregasNew) {
                if (!entregasOld.contains(entregasNewEntrega)) {
                    Entregador oldEntregadorOfEntregasNewEntrega = entregasNewEntrega.getEntregador();
                    entregasNewEntrega.setEntregador(entregador);
                    entregasNewEntrega = em.merge(entregasNewEntrega);
                    if (oldEntregadorOfEntregasNewEntrega != null && !oldEntregadorOfEntregasNewEntrega.equals(entregador)) {
                        oldEntregadorOfEntregasNewEntrega.getEntregas().remove(entregasNewEntrega);
                        oldEntregadorOfEntregasNewEntrega = em.merge(oldEntregadorOfEntregasNewEntrega);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = entregador.getId();
                if (findEntregador(id) == null) {
                    throw new NonexistentEntityException("The entregador with id " + id + " no longer exists.");
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
            Entregador entregador;
            try {
                entregador = em.getReference(Entregador.class, id);
                entregador.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entregador with id " + id + " no longer exists.", enfe);
            }
            List<Entrega> entregas = entregador.getEntregas();
            for (Entrega entregasEntrega : entregas) {
                entregasEntrega.setEntregador(null);
                entregasEntrega = em.merge(entregasEntrega);
            }
            em.remove(entregador);
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

    public List<Entregador> findEntregadorEntities() {
        return findEntregadorEntities(true, -1, -1);
    }

    public List<Entregador> findEntregadorEntities(int maxResults, int firstResult) {
        return findEntregadorEntities(false, maxResults, firstResult);
    }

    private List<Entregador> findEntregadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Entregador.class));
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

    public Entregador findEntregador(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Entregador.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntregadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Entregador> rt = cq.from(Entregador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
