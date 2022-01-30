package kazantsev.controller.filters;

import kazantsev.controller.LibraryServlet;
import kazantsev.dao.impl.UsersDaoImpl;
import kazantsev.entity.User;
import kazantsev.entity.UserRole;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/readers", "/addbook", "/deletebook", "/adduser", "/deleteuser",
        "/nonconfirmreaders", "/confirmreader"})
public class FilterAdmin implements Filter {

    private static final Logger log = Logger.getLogger(FilterAdmin.class);


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
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)  {

        HttpServletRequest req = (HttpServletRequest) request;
        User user = null;
        user = (User) req.getSession().getAttribute("user");
        if (user.getRole() == UserRole.LIBRARIAN) {
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
            RequestDispatcher dispatcher = ctx.getRequestDispatcher("/index.jsp");
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
