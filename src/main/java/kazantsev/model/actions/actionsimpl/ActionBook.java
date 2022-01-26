package kazantsev.model.actions.actionsimpl;

import kazantsev.dao.impl.BooksDaoImpl;
import kazantsev.entity.Book;
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
import java.util.Locale;
import java.util.ResourceBundle;

public class ActionBook implements Action {
    @Override
    public void executeGet(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("id");
        if (bookId != null) {
            int id = Integer.parseInt(bookId);
            BooksDaoImpl booksDao = new BooksDaoImpl();
            Book book = null;
            book = booksDao.getById(id);
            req.getSession().setAttribute("booktype", "view");
            req.getSession().setAttribute("book", book);
            req.getSession().setAttribute("pagetype", "book");
            servletContext.getRequestDispatcher("/jspfiles/books.jsp").forward(req, resp);
        }
    }

    @Override
    public void executePost(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idBookString = req.getParameter("idbook");
        String result = " no";
        Service service = new ServiceImpl();
        User user = (User) req.getSession().getAttribute("user");
        Object languageObject=req.getSession().getAttribute("language");
        String language="ru";
        if(languageObject!=null){
            language=languageObject.toString();
        }
        ResourceBundle bundle=ResourceBundle.getBundle("messages",new Locale(language));
        if (user != null && idBookString != null) {
            int idBook = Integer.parseInt(idBookString);
            int idReader = user.getId();
            try {
                result = service.getBook(idBook, idReader);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            req.getSession().setAttribute("booktype", "result");
            req.getSession().setAttribute("pagetype", "book");
            req.getSession().setAttribute("resultbook", bundle.getString(result));
            servletContext.getRequestDispatcher("/jspfiles/books.jsp").forward(req, resp);
        }
    }
}
