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

public class ActionReaders implements Action {
    @Override
    public void executeGet(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        Service service = new ServiceImpl();
        List<User> confirmreaders = service.getConfirmUsers();
        req.getSession().setAttribute("confirmreaders", confirmreaders);
        req.getSession().setAttribute("pagetype", "confirmreaders");
        servletContext.getRequestDispatcher("/jspfiles/admin.jsp").forward(req, resp);
    }

    @Override
    public void executePost(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        Service service = new ServiceImpl();
        String name=req.getParameter("namereader");
        String sureName=req.getParameter("surename");

        List<User> confirmreaders = service.searchReader(name, sureName);
        req.getSession().setAttribute("confirmreaders", confirmreaders);
        req.getSession().setAttribute("pagetype", "confirmreaders");
        servletContext.getRequestDispatcher("/jspfiles/admin.jsp").forward(req, resp);
    }
}
