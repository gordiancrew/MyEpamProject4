package kazantsev.model.actions.actionsimpl;

import kazantsev.entity.Book;
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
import java.util.List;

public class ActionBooks implements Action {

    private static final Logger log = Logger.getLogger(ActionBooks.class);

    @Override
    public void executeGet(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("resultlist", null);
        request.getSession().setAttribute("pagetype", "books");
        try {
            servletContext.getRequestDispatcher("/jspfiles/books.jsp").forward(request, response);
        } catch (ServletException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        } catch (IOException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
    }

    @Override
    public void executePost(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response)  {
        Service service = new ServiceImpl();
        String pageString = request.getParameter("page");
        int page = 0;
        List<Book> resultList = (List) request.getSession().getAttribute("resultlist");
        if (pageString == null) {
            String name = request.getParameter("name");
            String author = request.getParameter("author");
            resultList = service.searchBook(name, author);
        } else {
            page = Integer.parseInt(request.getParameter("page"));
        }
        request.setAttribute("page", page);
        request.getSession().setAttribute("pagetype", "books");
        request.getSession().setAttribute("resultlist", resultList);
        try {
            servletContext.getRequestDispatcher("/jspfiles/books.jsp").forward(request, response);
        } catch (ServletException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        } catch (IOException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
    }
}
