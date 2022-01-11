package kazantsev.controller;

import kazantsev.dao.impl.DaoBooks;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addbook")
public class ServletAddBook extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        String author=req.getParameter("author");
        String yearString=req.getParameter("year");
        String numberString=req.getParameter("number");
        String res="Некоррекные данные ";

        if(yearString.matches("[0-9]+")&&numberString.matches("[0-9]+")) {
            int year =Integer.parseInt(yearString);
            int number=Integer.parseInt(numberString);

            DaoBooks db=new DaoBooks();
            try {
                res=db.addBook(name,author,year,number);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        req.getSession().setAttribute("result",res);
        getServletContext().getRequestDispatcher("/addbook.jsp" ).forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("result","");
        getServletContext().getRequestDispatcher("/addbook.jsp" ).forward(req,resp);
    }
}
