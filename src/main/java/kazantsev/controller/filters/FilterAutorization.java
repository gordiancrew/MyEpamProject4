package kazantsev.controller.filters;


import kazantsev.dao.impl.BooksDaoImpl;
import kazantsev.entity.User;
import kazantsev.service.Service;
import kazantsev.service.ServiceImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(urlPatterns = {"/authorization","/book","/booksbox","/bookreturn"})
public class FilterAutorization implements Filter {
    private static final Logger log = Logger.getLogger(FilterAutorization.class);
    private FilterConfig filterConfig;

    @Override
    public void destroy() {
        filterConfig = null;
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        filterConfig = fConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain)  {

        HttpServletRequest req = (HttpServletRequest) request;
        String login = req.getParameter("login");
        String password=req.getParameter("password");
        Service service=new ServiceImpl();
        User userLogin=(User)req.getSession().getAttribute("user");
        User user=null;
        user= service.getUserByLogin(login);
        if (userLogin != null&&userLogin.isConfirm()) {
            try {
                filterChain.doFilter(request, response);
            } catch (IOException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            } catch (ServletException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            }
        } else if ( user != null&& BCrypt.checkpw(password,user.getPassword())&&user.isConfirm()) {
            req.getSession().setAttribute("user", user);
            log.log(Level.INFO, "user id:"+user.getId()+" is logged in");
            ServletContext ctx = filterConfig.getServletContext();
            RequestDispatcher dispatcher = ctx.getRequestDispatcher("/jspfiles/index.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            } catch (IOException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            }
            try {
                filterChain.doFilter(request, response);
            } catch (IOException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            } catch (ServletException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            }
        } else if ( user != null&& BCrypt.checkpw(password,user.getPassword())&&!user.isConfirm()) {
            req.setAttribute("confirm", "no");
            log.log(Level.INFO, "unauthorized user has entered");
            ServletContext ctx = filterConfig.getServletContext();
            RequestDispatcher dispatcher = ctx.getRequestDispatcher("/jspfiles/index.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            } catch (IOException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            }
            try {
                filterChain.doFilter(request, response);
            } catch (IOException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            } catch (ServletException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            }
        } else {
            ServletContext ctx = filterConfig.getServletContext();
            if(login!=null||password!=null) {
                log.log(Level.WARN, "unsuccessful authorization");
            }
            req.getSession().setAttribute("pagetype", "login");
            RequestDispatcher dispatcher = ctx.getRequestDispatcher("/jspfiles/books.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            } catch (IOException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            }
            return;
        }
    }
}
