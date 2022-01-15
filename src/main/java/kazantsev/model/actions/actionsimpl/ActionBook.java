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

public class ActionBook implements Action {
    @Override
    public void executeGet(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("id");
        System.out.println(req.getRequestURI());
        if (bookId != null) {
            int id = Integer.parseInt(bookId);
            BooksDaoImpl booksDao = new BooksDaoImpl();
            Book book = null;
            try {
                book = booksDao.getById(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
            req.getSession().setAttribute("resultbook", result);
            servletContext.getRequestDispatcher("/jspfiles/books.jsp").forward(req, resp);
        }
    }
}
