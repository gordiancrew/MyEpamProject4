package kazantsev.garbage;

import kazantsev.dao.BooksDao;
import kazantsev.dao.OperationsDao;
import kazantsev.dao.impl.BooksDaoImpl;
import kazantsev.dao.impl.OperationsDaoImpl;
import kazantsev.entity.Book;
import kazantsev.entity.Operation;
import kazantsev.service.Service;
import kazantsev.service.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/bookreturna/*")
public class ServletBookReturn extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        getServletContext().getRequestDispatcher("/jspfiles/books.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        getServletContext().getRequestDispatcher("jspfiles/books.jsp").forward(req, resp);
    }
}
