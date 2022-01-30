package kazantsev.model.actions.actionsimpl;

import kazantsev.dao.impl.BooksDaoImpl;
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
import java.sql.SQLException;

public class ActionDeleteBook implements Action {

    private static final Logger log = Logger.getLogger(ActionDeleteBook.class);

    @Override
    public void executeGet(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) {
        String bookId = req.getParameter("id");
        Service service = new ServiceImpl();
        if (bookId != null) {
            int id = Integer.parseInt(bookId);
            Book book = null;
            book = service.getBookById(id);
            req.getSession().setAttribute("book", book);
            req.getSession().setAttribute("pagetype", "deletebookview");
            try {
                servletContext.getRequestDispatcher("/jspfiles/admin.jsp").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void executePost(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {

        Service service = new ServiceImpl();
        int id = Integer.parseInt(req.getParameter("idbook"));
        String info = service.deleteBook(id);
        if (service.getBookById(id) == null) {
            log.log(Level.INFO, "book:id:" + id + " is deleted");
        } else {
            log.log(Level.ERROR, "book:id:" + id + " is not deleted");
        }
        req.getSession().setAttribute("info", info);
        req.getSession().setAttribute("pagetype", "deletebook");
        servletContext.getRequestDispatcher("/jspfiles/admin.jsp").forward(req, resp);
    }
}
