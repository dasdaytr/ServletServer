package test.filters;


import test.DAO.DataBase;
import test.Model.Active;
import test.Model.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter( urlPatterns ={
        "/file/upload",
        "/users/roles"
} )
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) req).getSession();
        String active = (String) session.getAttribute("active");
        String role = (String) session.getAttribute("role");
        if((active == null && role == null) || active.equals(Active.OFF.name()))
            ((HttpServletResponse) res).sendRedirect("/login");
        if(active.equals(Active.ON.name()) && role.equals(Role.ADMIN.name()))
            chain.doFilter(req,res);
        else if(active.equals(Active.ON.name()) && role.equals(Role.USER.name()))
            ((HttpServletResponse) res).sendRedirect("/start");
      //  ((HttpServletResponse) res).sendRedirect("/login");

    }

    @Override
    public void destroy() {

    }
}
