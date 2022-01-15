package kazantsev.controller.filters;

import kazantsev.dao.impl.UsersDaoImpl;
import kazantsev.entity.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter("/book")
public class FilterAutorization implements Filter {

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
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String login = req.getParameter("login");
        String password=req.getParameter("password");
        UsersDaoImpl usersDao = new UsersDaoImpl();
        User userLogin=(User)req.getSession().getAttribute("user");
        User user=null;
        try {
             user= usersDao.getUserByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (userLogin != null) {
            filterChain.doFilter(request, response);
        } else if ( user != null&& BCrypt.checkpw(password,user.getPassword())) {
            req.getSession().setAttribute("user", user);
            ServletContext ctx = filterConfig.getServletContext();
            RequestDispatcher dispatcher = ctx.getRequestDispatcher("/jspfiles/index.jsp");
            dispatcher.forward(request, response);
            filterChain.doFilter(request, response);
        } else {
            ServletContext ctx = filterConfig.getServletContext();
            req.getSession().setAttribute("pagetype", "login");
            RequestDispatcher dispatcher = ctx.getRequestDispatcher("/jspfiles/books.jsp");
            dispatcher.forward(request, response);
            return;
        }
    }
}
