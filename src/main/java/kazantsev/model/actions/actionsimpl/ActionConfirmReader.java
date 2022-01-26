package kazantsev.model.actions.actionsimpl;

import kazantsev.model.actions.Action;
import kazantsev.service.Service;
import kazantsev.service.ServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ActionConfirmReader implements Action {
    @Override
    public void executeGet(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

    }

    @Override
    public void executePost(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {



        Service service=new ServiceImpl();
        int id=  Integer.parseInt(request.getParameter("id"));
        String info=service.confirmUser(id);
        request.getSession().setAttribute("info", info);
        request.getSession().setAttribute("pagetype", "confirmreader");
        servletContext.getRequestDispatcher("/jspfiles/admin.jsp").forward(request, response);
    }
}
