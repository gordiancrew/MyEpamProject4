package kazantsev.model.actions.actionsimpl;

import kazantsev.entity.User;
import kazantsev.model.actions.Action;
import kazantsev.service.Service;
import kazantsev.service.ServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ActionNonConfirmReaders implements Action {
    @Override
    public void executeGet(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        Service service = new ServiceImpl();
        List<User> nonconfirmreaders = service.getNonConfirmUsers();
        req.getSession().setAttribute("nonconfirmreaders", nonconfirmreaders);
        req.getSession().setAttribute("pagetype", "nonconfirmreaders");
        servletContext.getRequestDispatcher("/jspfiles/admin.jsp").forward(req, resp);
    }

    @Override
    public void executePost(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int id=Integer.parseInt(req.getParameter("idreader"));
        Service service=new ServiceImpl();


        req.getSession().setAttribute("pagetype", "nonconfirmreaders");
        servletContext.getRequestDispatcher("/jspfiles/admin.jsp").forward(req, resp);
    }
}
