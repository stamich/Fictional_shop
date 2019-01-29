package pl.multishop.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * The abstract class implemented Serializable interface for
 * serializing Domain Access Objects(DAO).
 * @author Michał Stawarski
 * @param <PK>
 * @param <T>
 */
public abstract class AbstractDao<PK extends Serializable, T> implements Serializable{

    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDao(){
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * This method turns on session factory of Hibernate.
     * @return sessionFactory.getCurrentSession()
     */
    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    /**
     * This method gets the object (entity).
     * @param key
     * @return (T) getSession().get(persistentClass, key)
     */
    @SuppressWarnings("unchecked")
    public T getById(PK key) {
        return (T) getSession().get(persistentClass, key);
    }

    /**
     * This method gets the object (entity).
     * @param key
     * @return (T) getSession().get(persistentClass, key)
     */
    @SuppressWarnings("unchecked")
    public T getByKey(PK key){
        return (T) getSession().get(persistentClass, key);
    }

    /**
     * This method persists the object (entity).
     * @param entity
     */
    public void persistEntity(T entity) {
        getSession().persist(entity);
    }

    /**
     * This method updates the object (entity).
     * @param entity
     */
    public void updateEntity(T entity){
        getSession().update(entity);
    }

    /**
     * This method deletes the object (entity).
     * @param entity
     */
    public void deleteEntity(T entity) {
        getSession().delete(entity);
    }

    /**
     * This method creates properties of the object (entity).
     * @return getSession().createCriteria(persistentClass)
     */
    protected Criteria createEntityCriteria(){
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(persistentClass);
        return (Criteria) getSession().createQuery(query).getResultList();
    }

//    protected Criteria createEntityCriteria(){
//        return getSession().createCriteria(persistentClass);
//    }
}
