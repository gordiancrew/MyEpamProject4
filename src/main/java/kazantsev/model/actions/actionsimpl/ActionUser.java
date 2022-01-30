package kazantsev.model.actions.actionsimpl;

import kazantsev.dao.impl.BooksDaoImpl;
import kazantsev.entity.Book;
import kazantsev.entity.Operation;
import kazantsev.entity.User;
import kazantsev.model.actions.Action;
import kazantsev.service.Service;
import kazantsev.service.ServiceImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ActionUser implements Action {

    private static final Logger log = Logger.getLogger(ActionUser.class);

    @Override
    public void executeGet(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp)  {
        String userId = req.getParameter("id");
        if (userId != null) {
            int id = Integer.parseInt(userId);
            Service service = new ServiceImpl();
            User user = service.getUserById(id);
            List<Operation>listUsersBook=service.getActiveOperations(id);


            req.getSession().setAttribute("reader", user);
            req.getSession().setAttribute("listusersbook", listUsersBook);
            req.getSession().setAttribute("pagetype", "user");
            try {
                servletContext.getRequestDispatcher("/jspfiles/admin.jsp").forward(req, resp);
            } catch (ServletException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            } catch (IOException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void executePost(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

    }
}
