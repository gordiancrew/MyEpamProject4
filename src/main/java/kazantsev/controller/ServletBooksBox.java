package kazantsev.controller;

import kazantsev.entity.Book;
import kazantsev.entity.Operation;
import kazantsev.entity.User;
import kazantsev.service.Service;
import kazantsev.service.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/booksbox")
public class ServletBooksBox extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Operation> result = new ArrayList();
        Service service=new ServiceImpl();
        User user=(User)req.getSession().getAttribute("user");
        try {
            result=service.getActiveOperations(user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getSession().setAttribute("result", result);
        getServletContext().getRequestDispatcher("/booksbox.jsp").forward(req, resp);
    }
}
