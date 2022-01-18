package test.services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import test.Model.Active;
import test.Model.Person;
import test.helper.FactoryProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public class LoginService {
    private Session session;
    private String SQL = "select * from Person where name=:name and password=:password";
    public LoginService(){
    }
    public String loginCheck(String name, String password, HttpServletRequest req){
        session = FactoryProvider.currentSession();
        HttpSession httpSession = req.getSession();
        Transaction transaction =session.beginTransaction();
        List<Person> personList = session.createNativeQuery(SQL,Person.class)
                .setParameter("name",name)
                .setParameter("password",password).list();
        if(personList.isEmpty()){
            req.setAttribute("error","Логин или пароль не правильный");
            FactoryProvider.closeFactory();
            return "login.jsp";
        }else{
            req.setAttribute("error","");
            httpSession.setAttribute("active", Active.ON.name());
            httpSession.setAttribute("name",name);
            httpSession.setAttribute("password",password);
            httpSession.setAttribute("role", personList.get(0).getRole());
            FactoryProvider.closeFactory();
            return "start.jsp";
        }
    }
}
