package kazantsev.controller;

import kazantsev.entity.Book;
import kazantsev.service.Service;
import kazantsev.service.ServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/books"})
public class ServletBooks extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        List<Book> res = new ArrayList();
//        req.getSession().setAttribute("result", res);
        getServletContext().getRequestDispatcher("/jspfiles/books.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Service service=new ServiceImpl();
        List<Book> resultList=new ArrayList<>();
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        try {
            resultList=service.searchBook(name,author);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.getSession().setAttribute("result", resultList);
        getServletContext().getRequestDispatcher("/jspfiles/books.jsp").forward(req, resp);
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);

    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
}
