package kazantsev.model.actions.actionsimpl;

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
import java.util.ArrayList;
import java.util.List;

public class ActionBooksBox implements Action {
    @Override
    public void executeGet(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Operation> result = new ArrayList();
        Service service=new ServiceImpl();
        User user=(User)req.getSession().getAttribute("user");
        try {
            result=service.getActiveOperations(user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getSession().setAttribute("pagetype", "booksbox");
        req.getSession().setAttribute("result", result);
        servletContext.getRequestDispatcher("/jspfiles/books.jsp").forward(req, resp);
    }

    @Override
    public void executePost(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
