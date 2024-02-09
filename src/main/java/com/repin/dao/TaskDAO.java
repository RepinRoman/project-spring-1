package com.repin.dao;

import com.repin.domain.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TaskDAO {
    private final SessionFactory sessionFactory;

    public TaskDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<Task> getAll(int offset, int limit) {
        return getSession().createQuery("SELECT t FROM Task t", Task.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public int getAllCount() {
        return Math.toIntExact(getSession().createQuery("SELECT count(t) FROM Task t", Long.class).uniqueResult());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Task getById(int id) {
        return getSession().createQuery("SELECT t FROM Task t WHERE t.id = :ID", Task.class)
                .setParameter("ID", id)
                .uniqueResult();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdate(Task task) {
        getSession().persist(task);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Task task) {
        getSession().remove(task);
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}