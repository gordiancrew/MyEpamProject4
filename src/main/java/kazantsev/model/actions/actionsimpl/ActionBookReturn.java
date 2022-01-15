package kazantsev.model.actions.actionsimpl;

import kazantsev.dao.BooksDao;
import kazantsev.dao.OperationsDao;
import kazantsev.dao.impl.BooksDaoImpl;
import kazantsev.dao.impl.OperationsDaoImpl;
import kazantsev.entity.Book;
import kazantsev.entity.Operation;
import kazantsev.model.actions.Action;
import kazantsev.service.Service;
import kazantsev.service.ServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ActionBookReturn  implements Action {
    @Override
    public void executeGet(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idOperationString = req.getParameter("id");
        int idOperation = Integer.parseInt(idOperationString);
        BooksDao booksDao = new BooksDaoImpl();
        OperationsDao operationDao = new OperationsDaoImpl();
        Operation operation = null;

        try {
            operation = operationDao.getById(idOperation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Book book = null;
        try {
            book = booksDao.getById(operation.getBook().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getSession().setAttribute("pagetype", "book");
        req.getSession().setAttribute("booktype", "returnview");
        req.getSession().setAttribute("book", book);
        req.getSession().setAttribute("operation", operation);
        servletContext.getRequestDispatcher("/jspfiles/books.jsp").forward(req, resp);
    }

    @Override
    public void executePost(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idOperationString = req.getParameter("idoperation");
        String result = " no";
        Service service = new ServiceImpl();
        int idOperation = Integer.parseInt(idOperationString);
        try {
            result = service.returnBook(idOperation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getSession().setAttribute("pagetype", "book");
        req.getSession().setAttribute("booktype", "result");
        req.getSession().setAttribute("resultbook", result);
        servletContext.getRequestDispatcher("/jspfiles/books.jsp").forward(req, resp);
    }
}
