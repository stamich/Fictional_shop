package pl.multishop.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Klasa abstrakcyjna implementująca interfejs Serializable.
 * Dzięki temu może ona serializować obiekty - dane tabelaryczne.
 * Klasa ta wyjątkowo nie deklaruje metod abstrakcyjnych, jednak
 * definiuje metody konkretne, służące do zarządzania Fabryką Sesji
 * frameworka Hibernate. Dzięki temu może po niej dziedziczyć wiele
 * klas konkretnych, reprezentujących różne modele utrwalania (tutaj
 * dwa modele - umowa oraz system).
 * @author Michał Stawarski
 * @param <PK>
 * @param <T>
 */
public abstract class AbstractDao<PK extends Serializable, T> {

    private final Class<T> persistentClass;

    /**
     * Konstruktor klasy.
     */
    @SuppressWarnings("unchecked")
    public AbstractDao(){
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Metoda uruchamiająca fabrykę sesji.
     * @return
     */
    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    /**
     * Metoda pobierająca encje.
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public T getById(PK key) {
        return (T) getSession().get(persistentClass, key);
    }

    @SuppressWarnings("unchecked")
    public T getByKey(PK key){
        return (T) getSession().get(persistentClass, key);
    }

    /**
     * Metoda zachowująca encje.
     * @param entity
     */
    public void persistEntity(T entity) {
        getSession().persist(entity);
    }

    /**
     * This method updates entity
     * @param entity
     */
    public void updateEntity(T entity){
        getSession().update(entity);
    }

    /**
     * Metoda usuwająca encje.
     * @param entity
     */
    public void deleteEntity(T entity) {
        getSession().delete(entity);
    }

    /**
     * Metoda tworząca właściwości encji.
     * @return
     */
    protected Criteria createEntityCriteria(){
        return getSession().createCriteria(persistentClass);
    }
}
