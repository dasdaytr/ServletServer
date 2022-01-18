package test.Controllers;

import test.Model.Active;
import test.services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService loginService = new LoginService();
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        if(loginService.loginCheck(name,password,req).equals("login.jsp")){
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
        else{
            resp.sendRedirect("/start");
        }
    }
}
