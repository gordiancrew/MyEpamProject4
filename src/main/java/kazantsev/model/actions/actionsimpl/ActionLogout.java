package kazantsev.model.actions.actionsimpl;

import kazantsev.model.actions.Action;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActionLogout implements Action {
    @Override
    public void executeGet(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("user", null);
        request.getSession().setAttribute("pagetype", "logout");
        servletContext.getRequestDispatcher("/jspfiles/index.jsp").forward(request, response);
    }

    @Override
    public void executePost(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
