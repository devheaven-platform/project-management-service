package repositories;

import models.Deadline;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Stateless
public class DeadlineDAO {

    @PersistenceContext
    private EntityManager em;

    public void createDeadline(Deadline deadline){
        em.persist(deadline);
    }

    public void editDeadline(Deadline deadline){
        em.merge(deadline);
    }

    public void removeDeadline(Deadline deadline){
        em.remove(deadline);
    }

    public Deadline getDeadlineById(UUID id){
        return em.createNamedQuery("Deadline.getDeadlineById", Deadline.class)
                .setParameter("id", id)
                .getSingleResult();
    }

}
