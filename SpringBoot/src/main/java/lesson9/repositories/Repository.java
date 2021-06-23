package lesson9.repositories;

import lesson9.model.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Repository<T> {
    private SessionFactory factory = null;

    private Session session = null;

    private Class<T> ClassToManage = null;

    public SessionFactory getFactory() {
        return factory;
    }

    @Autowired
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    public Class<T> getClassToManage() {
        return ClassToManage;
    }

    public void setClassToManage(Class<T> classToManage) {
        ClassToManage = classToManage;
    }

    public Repository() {
    }

    public List<T> getAll() {
        List<T> items = new ArrayList<>();

        session = factory.getCurrentSession();

        try{
            session.beginTransaction();
            items = loadAll(ClassToManage, session);
        }
        catch (Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
        finally {
            if (session != null) {
                session.getTransaction().commit();
                session.close();
            }
        }

        return items;
    }

    public T get(long id){
        T newItem = null;
        session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            newItem = session.get(ClassToManage, id);
        }
        catch(Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
        finally {
            session.getTransaction().commit();
            if (session != null)
            {
                session.close();
            }
        }
        return newItem;
    }

    public void save(T obj){
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.saveOrUpdate(obj);
            session.getTransaction().commit();
        }
        catch(Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
        finally {
            if (session != null)
            {
                session.close();
            }
        }
    }

    private <T> List<T> loadAll(Class<T> cl, Session s) {
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(cl);
        criteriaQuery.from(cl);
        List<T> data = s.createQuery(criteriaQuery).getResultList();
        return data;
    }
}
