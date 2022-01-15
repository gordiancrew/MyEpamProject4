package kazantsev.model.actions.actionsimpl;

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
import java.util.ArrayList;
import java.util.List;

public class ActionBooks implements Action {

    @Override
    public void executeGet(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("pagetype", "books");
        servletContext.getRequestDispatcher("/jspfiles/books.jsp").forward(request, response);

    }

    @Override
    public void executePost(ServletContext servletContext,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Service service=new ServiceImpl();
        List<Book> resultList=new ArrayList<>();
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        try {
            resultList=service.searchBook(name,author);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("pagetype", "books");
        request.getSession().setAttribute("resultlist", resultList);
        servletContext.getRequestDispatcher("/jspfiles/books.jsp").forward(request, response);
    }
}
