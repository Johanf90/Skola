package se.johan.communitysitev2.persistence.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Created by Johan on 10-Feb-16.
 */
public abstract class AbstractDao<PK extends Serializable, T> {

    private final Class<T> persistentClass;

    // Returns the class the hibernate model extens.
    @SuppressWarnings("unchecked")
    protected AbstractDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Autowired
    private SessionFactory sessionFactory;

    // Returns the session from the sessionfactory
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    // Saves an entity in DB.
    public void persist(T entity) {
        getSession().persist(entity);
    }

    // Returns a Critera for the specific class.
    protected Criteria createEntityCriteria() {
        return getSession().createCriteria(persistentClass);
    }

}
