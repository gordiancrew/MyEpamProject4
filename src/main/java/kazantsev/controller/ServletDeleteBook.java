package kazantsev.controller;

import kazantsev.dao.impl.DaoBooks;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deletebook")
public class ServletDeleteBook extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString=req.getParameter("id");
        String res="Некорректный ввод";
        if(idString.matches("[0-9]+")){
            int id=Integer.parseInt(idString);
            DaoBooks db= new DaoBooks();
            try {
                res=db.deleteBookById(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        req.getSession().setAttribute("result",res);
        getServletContext().getRequestDispatcher("/deletebook.jsp" ).forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().setAttribute("result","");
        getServletContext().getRequestDispatcher("/deletebook.jsp" ).forward(req,resp);
    }
}
