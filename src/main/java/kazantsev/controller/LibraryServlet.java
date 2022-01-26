package kazantsev.controller;

import kazantsev.model.actions.Action;
import kazantsev.model.factory.ActionFactiryImpl;
import kazantsev.model.factory.ActionFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = {"/books", "/book", "/language", "/logout", "/registration", "/booksbox", "/bookreturn",
        "/admin", "/deletebook", "/addbook", "/rules", "/nonconfirmreaders", "/deleteuser", "/user",
        "/confirmreader", "/readers"})
public class LibraryServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(LibraryServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        String url = req.getRequestURI();
        ActionFactory factory = new ActionFactiryImpl();
        Action action = factory.createAction(url);
        log.log(Level.INFO, "method doGet, url: " + url);
        try {
            action.executeGet(getServletContext(), req, resp);
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        } catch (ServletException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        } catch (IOException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        String url = req.getRequestURI();
        ActionFactory factory = new ActionFactiryImpl();
        Action action = factory.createAction(url);
        log.log(Level.INFO, "method doPost, url: " + url);
        try {
            action.executePost(getServletContext(), req, resp);
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        } catch (ServletException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        } catch (IOException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
    }
}
