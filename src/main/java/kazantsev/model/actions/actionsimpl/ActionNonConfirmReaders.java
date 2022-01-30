package kazantsev.model.actions.actionsimpl;

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

public class ActionNonConfirmReaders implements Action {

    private static final Logger log = Logger.getLogger(ActionNonConfirmReaders.class);

    @Override
    public void executeGet(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp){
        Service service = new ServiceImpl();
        List<User> nonconfirmreaders = service.getNonConfirmUsers();
        req.getSession().setAttribute("nonconfirmreaders", nonconfirmreaders);
        req.getSession().setAttribute("pagetype", "nonconfirmreaders");
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

    @Override
    public void executePost(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp)  {
        req.getSession().setAttribute("pagetype", "nonconfirmreaders");
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
