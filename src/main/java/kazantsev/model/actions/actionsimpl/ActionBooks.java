package kazantsev.model.actions.actionsimpl;

import kazantsev.controller.LibraryServlet;
import kazantsev.entity.Book;
import kazantsev.model.actions.Action;
import kazantsev.service.Service;
import kazantsev.service.ServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActionBooks implements Action {
    private static final Logger log = Logger.getLogger(ActionBooks.class);

    @Override
    public void executeGet(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("resultlist", null);
        request.getSession().setAttribute("pagetype", "books");
        servletContext.getRequestDispatcher("/jspfiles/books.jsp").forward(request, response);

    }

    @Override
    public void executePost(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
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
        servletContext.getRequestDispatcher("/jspfiles/books.jsp").forward(request, response);
    }
}
