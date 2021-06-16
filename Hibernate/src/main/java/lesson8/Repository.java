package lesson8;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.Field;

public class Repository<T> {
    private SessionFactory factory = null;

    private Session session = null;

    private Class<T> ClassToManage = null;

    public Repository(SessionFactory f, Class<T> cl){
        factory = f;
        ClassToManage = cl;
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

    public void read(long id){
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            T readItem = session.get(ClassToManage, id);
            StringBuilder sb = new StringBuilder(ClassToManage.getSimpleName());
            sb.append(" - ");
            Field[] fields = readItem.getClass().getDeclaredFields();
            for(Field f: fields){
                sb.append(f.getName());
                sb.append(":");
                if (!f.isAccessible()) {
                    f.setAccessible(true);
                    sb.append(f.get(readItem));
                    f.setAccessible(false);
                }
                else{
                    sb.append(f.get(readItem));
                }
                sb.append("; ");
            }
            sb.setLength(sb.length() - 2);
            System.out.println(sb);
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
}
