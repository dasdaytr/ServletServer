package test.Controllers;

import test.Model.Role;
import test.services.UsersAdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users/roles")
public class UsersAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersAdminService uds = new UsersAdminService();
        req.setAttribute("users",uds.getPersonRoleUser());
        req.setAttribute("roles", List.of(Role.USER.name(),Role.ADMIN.name()));
        req.getRequestDispatcher("roles.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = (String)req.getParameter("name");
        String password = (String) req.getParameter("password");
        new UsersAdminService().updateUserRole(name,password);
        resp.sendRedirect("/users/roles");
    }
}
