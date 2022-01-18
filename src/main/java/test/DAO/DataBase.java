package test.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import test.Model.Active;
import test.Model.Files;
import test.Model.Person;
import test.Model.Role;
import test.helper.FactoryProvider;

import java.util.List;

public class DataBase {
    private String SQL_GET_ALL_FILES = "select * from Files";
    private String SQL_GET_FILES_USER = "select * from Files where extension = :ex";
    private static String SQL_GET_ROLE = "select * from person where name=:name and password=:password";
    private String SQL_GET_ROLE_USER = "select * from person where role = 'USER'";

    public void addNewFile(Files file){
        Session session = FactoryProvider.currentSession();
        Transaction tx = session.beginTransaction();
        session.save(file);
        tx.commit();
        FactoryProvider.closeFactory();
    }

    public List<Files> getFilesForUser(String active){
        Session session = FactoryProvider.currentSession();
        List<Files> filesList = null;
        if(active.equals(Active.ON.name()))
            filesList = session.createNativeQuery(SQL_GET_ALL_FILES,Files.class).getResultList();
        else
            filesList = session.createNativeQuery(SQL_GET_FILES_USER,Files.class)
                    .setParameter("ex","txt").getResultList();
        FactoryProvider.closeFactory();
        return filesList;
    }
    public void updateUserRole(String name, String password){
        Session session = FactoryProvider.currentSession();
        Person person = sqlQGetUser(session,name,password);
        person.setRole(Role.ADMIN.name());
        Transaction transaction = session.beginTransaction();
        session.update(person);
        transaction.commit();
        FactoryProvider.closeFactory();
    }

    public List<Person> getRoleUser(){
        Session session = FactoryProvider.currentSession();
        List<Person> personList= session.createNativeQuery(SQL_GET_ROLE_USER,Person.class).getResultList();
        FactoryProvider.closeFactory();
        return personList;
    }

    private  static Person sqlQGetUser(Session session,String name, String password){
        return session.createNativeQuery(SQL_GET_ROLE, Person.class)
                .setParameter("name",name)
                .setParameter("password",password).uniqueResult();
    }
}
