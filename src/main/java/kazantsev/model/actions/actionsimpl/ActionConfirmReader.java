package kazantsev.model.actions.actionsimpl;

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

public class ActionConfirmReader implements Action {

    private static final Logger log = Logger.getLogger(ActionConfirmReader.class);

    @Override
    public void executeGet(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

    }

    @Override
    public void executePost(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response)  {
        Service service=new ServiceImpl();
        int id=  Integer.parseInt(request.getParameter("id"));
        String info=null;
        log.log(Level.INFO,"reader:id:"+id+" is confirm");
        info=service.confirmUser(id);
        request.getSession().setAttribute("info", info);
        request.getSession().setAttribute("pagetype", "confirmreader");
        try {
            servletContext.getRequestDispatcher("/jspfiles/admin.jsp").forward(request, response);
        } catch (ServletException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        } catch (IOException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
    }
}
