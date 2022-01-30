package kazantsev.model.actions.actionsimpl;

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
import java.util.ArrayList;
import java.util.List;

public class ActionBooksBox implements Action {
    private static final Logger log = Logger.getLogger(ActionBooksBox.class);
    @Override
    public void executeGet(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp)  {
        List<Operation> result = new ArrayList();
        Service service=new ServiceImpl();
        User user=(User)req.getSession().getAttribute("user");
        result=service.getActiveOperations(user.getId());
        req.getSession().setAttribute("pagetype", "booksbox");
        req.getSession().setAttribute("result", result);
        try {
            servletContext.getRequestDispatcher("/jspfiles/books.jsp").forward(req, resp);
        } catch (ServletException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        } catch (IOException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
    }

    @Override
    public void executePost(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
