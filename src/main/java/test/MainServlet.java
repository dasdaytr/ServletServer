package test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import test.Model.Person;
import test.helper.FactoryProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/hello")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String sql = "select * from Person ";
        Session s = FactoryProvider.currentSession();
        Transaction tr = s.beginTransaction();
        List<Person> users = s.createNativeQuery(sql,Person.class).list();
        req.setAttribute("name", users.size());
        req.getRequestDispatcher("test.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
