package kazantsev.controller.filters;

import kazantsev.dao.impl.UsersDaoImpl;
import kazantsev.entity.User;
import kazantsev.entity.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/admin")
public class FilterAdmin implements Filter {

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
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
//        UsersDaoImpl usersDao = new UsersDaoImpl();
        User user = null;
        user = (User) req.getSession().getAttribute("user");
        if (user.getRole() == UserRole.LIBRARIAN) {
            filterChain.doFilter(request, response);
        } else {
            ServletContext ctx = filterConfig.getServletContext();
            RequestDispatcher dispatcher = ctx.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
            return;
        }
    }
}
