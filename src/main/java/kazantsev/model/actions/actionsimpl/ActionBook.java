package kazantsev.model.actions.actionsimpl;

import kazantsev.entity.Book;
import kazantsev.entity.User;
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
import java.util.Locale;
import java.util.ResourceBundle;

public class ActionBook implements Action {

    private static final Logger log = Logger.getLogger(ActionBook.class);

    @Override
    public void executeGet(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) {
        String bookId = req.getParameter("id");
        Service service=new ServiceImpl();
        if (bookId != null) {
            int id = Integer.parseInt(bookId);
            Book book = null;
            book =service.getBookById(id);
            req.getSession().setAttribute("booktype", "view");
            req.getSession().setAttribute("book", book);
            req.getSession().setAttribute("pagetype", "book");
            try {
                servletContext.getRequestDispatcher("/jspfiles/books.jsp").forward(req, resp);
            } catch (ServletException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            } catch (IOException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void executePost(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) {
        String idBookString = req.getParameter("idbook");
        String result = " no";
        Service service = new ServiceImpl();
        User user = (User) req.getSession().getAttribute("user");
        Object languageObject = req.getSession().getAttribute("language");
        String language = "ru";
        if (languageObject != null) {
            language = languageObject.toString();
        }
        ResourceBundle bundle = ResourceBundle.getBundle("messages", new Locale(language));
        if (user != null && idBookString != null) {
            int idBook = Integer.parseInt(idBookString);
            int idReader = user.getId();
            log.log(Level.INFO, "reader:id:" + idReader + " get book:id:" + idBook);
            result = service.getBook(idBook, idReader);
            req.getSession().setAttribute("booktype", "result");
            req.getSession().setAttribute("pagetype", "book");
            req.getSession().setAttribute("resultbook", bundle.getString(result));
            try {
                servletContext.getRequestDispatcher("/jspfiles/books.jsp").forward(req, resp);
            } catch (ServletException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            } catch (IOException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            }
        }
    }
}
