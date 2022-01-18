package test.Controllers;

import test.services.RegService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reg")
public class RegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("reg.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegService rs = new RegService();
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        if(rs.regUser(name,password,req).equals("login.jsp")){
            resp.sendRedirect("/login");
        }else{
            req.getRequestDispatcher("reg.jsp").forward(req,resp);
        }
    }
}
