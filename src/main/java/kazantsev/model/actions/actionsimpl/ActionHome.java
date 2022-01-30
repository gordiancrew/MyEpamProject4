package kazantsev.model.actions.actionsimpl;

import kazantsev.model.actions.Action;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActionHome implements Action {

    private static final Logger log = Logger.getLogger(ActionHome.class);
    @Override
    public void executeGet(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response)  {
        try {
            servletContext.getRequestDispatcher("/jspfiles/index.jsp").forward(request, response);
        } catch (ServletException e) {
            log.log(Level.ERROR, "exception :",e);
            e.printStackTrace();
        } catch (IOException e) {
            log.log(Level.ERROR, "exception :",e);
            e.printStackTrace();
        }
    }

    @Override
    public void executePost(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
