package kazantsev.model.actions.actionsimpl;

import kazantsev.model.actions.Action;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ActionRules implements Action {
    @Override
    public void executeGet(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("pagetype", "rules");
        servletContext.getRequestDispatcher("/jspfiles/books.jsp").forward(request, response);
    }

    @Override
    public void executePost(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

    }
}
