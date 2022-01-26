package kazantsev.model.actions.actionsimpl;

import kazantsev.dao.impl.BooksDaoImpl;
import kazantsev.entity.Book;
import kazantsev.entity.Operation;
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

public class ActionUser implements Action {

    @Override
    public void executeGet(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String userId = req.getParameter("id");
        if (userId != null) {
            int id = Integer.parseInt(userId);
            Service service = new ServiceImpl();
            User user = service.getUserById(id);
            List<Operation>listUsersBook=service.getActiveOperations(id);


            req.getSession().setAttribute("reader", user);
            req.getSession().setAttribute("listusersbook", listUsersBook);
            req.getSession().setAttribute("pagetype", "user");
            servletContext.getRequestDispatcher("/jspfiles/admin.jsp").forward(req, resp);
        }
    }

    @Override
    public void executePost(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

    }
}
