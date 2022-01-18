package test.helper;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class FactoryProvider {

    public static final SessionFactory factory;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        }catch (Throwable tx){
            throw new ExceptionInInitializerError(tx);
        }
    }
    public static final ThreadLocal session = new ThreadLocal();
    public static Session currentSession() throws HibernateException{
        Session s = (Session)session.get();
        if(s == null){
            s = factory.openSession();
            session.set(s);
        }
        return s;
    }
    public static void closeFactory(){
        session.set(null);
    }
}
