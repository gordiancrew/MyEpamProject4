package kazantsev.model.actions.actionsimpl;

import kazantsev.dao.impl.BooksDaoImpl;
import kazantsev.entity.Book;
import kazantsev.model.actions.Action;
import kazantsev.service.Service;
import kazantsev.service.ServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ActionDeleteBook implements Action {
    @Override
    public void executeGet(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("id");
        if (bookId != null) {
            int id = Integer.parseInt(bookId);
            BooksDaoImpl booksDao = new BooksDaoImpl();
            Book book = null;
            book = booksDao.getById(id);
            req.getSession().setAttribute("book", book);
            req.getSession().setAttribute("pagetype", "deletebookview");
            servletContext.getRequestDispatcher("/jspfiles/admin.jsp").forward(req, resp);
        }
    }

    @Override
    public void executePost(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {

        Service service = new ServiceImpl();
        int id = Integer.parseInt(req.getParameter("idbook"));
        String info = service.deleteBook(id);
        req.getSession().setAttribute("info", info);
        req.getSession().setAttribute("pagetype", "deletebook");
        servletContext.getRequestDispatcher("/jspfiles/admin.jsp").forward(req, resp);
    }
}
