package kazantsev.model.actions.actionsimpl;

import kazantsev.model.actions.Action;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActionLanguage implements Action {

    @Override
    public void executeGet(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("pagetype", "language");
        servletContext.getRequestDispatcher("/jspfiles/books.jsp").forward(request, response);
    }

    @Override
    public void executePost(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lang=request.getParameter("lang");
        request.getSession().setAttribute("language", lang);
        request.getSession().setAttribute("pagetype", "language");
        servletContext.getRequestDispatcher("/jspfiles/books.jsp").forward(request, response);
    }
}
