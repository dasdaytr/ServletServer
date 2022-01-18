package test.services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import test.Model.Person;
import test.helper.FactoryProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RegService {
    private Session session;
    private String SQL = "select * from Person where name=:name";

    public String regUser(String name, String password, HttpServletRequest req){
        session = FactoryProvider.currentSession();
        Transaction tr = session.beginTransaction();
        List<Person> personList = session.createNativeQuery(SQL,Person.class)
                .setParameter("name",name).list();
        if(personList.isEmpty()){
            Person person = new Person();
            person.setName(name);
            person.setPassword(password);
            person.setRole("USER");
            session.save(person);
            tr.commit();
            FactoryProvider.closeFactory();
            req.setAttribute("error","");
            return "login.jsp";
        }
        else{
            req.setAttribute("error","Такой пользователь уже существует");
            return "reg.jsp";
        }
    }
}
