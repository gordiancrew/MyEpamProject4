package kazantsev.controller;

import kazantsev.model.actions.Action;
import kazantsev.model.factory.ActionFactiryImpl;
import kazantsev.model.factory.ActionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/books","/book","/language","/logout","/registration","/booksbox","/bookreturn",
        "/admin","/deletebook"})
public class LibraryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getRequestURI());
        ActionFactory factory = new ActionFactiryImpl();
        Action action = factory.createAction(req.getRequestURI());
        action.executeGet(getServletContext(), req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ActionFactory factory = new ActionFactiryImpl();
        Action action = factory.createAction(req.getRequestURI());
        try {
            action.executePost(getServletContext(), req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
