package kazantsev.model.actions.actionsimpl;

import kazantsev.model.actions.Action;
import kazantsev.service.Service;
import kazantsev.service.ServiceImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActionAddBook implements Action {
    private static final Logger log = Logger.getLogger(ActionAddBook.class);

    @Override
    public void executeGet(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("pagetype", "addbook");
        try {
            servletContext.getRequestDispatcher("/jspfiles/admin.jsp").forward(request, response);
        } catch (ServletException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        } catch (IOException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
    }

    @Override
    public void executePost(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp)  {
        String tittle=req.getParameter("tittle");
        String author=req.getParameter("author");
        String yearString=req.getParameter("year");
        String numberString=req.getParameter("number");
        String description=req.getParameter("description");
        String res="incorrect enter ";
        Service service= new ServiceImpl();

        if(yearString.matches("[0-9]+")&&numberString.matches("[0-9]+")) {
            int year =Integer.parseInt(yearString);
            int number=Integer.parseInt(numberString);
            res=service.addBook(tittle, author,year,number,description);
            log.log(Level.INFO, "Book: \""+tittle+"\" \" "+author+"\" is added");
        }
        else{
            log.log(Level.WARN, "incorrect data entry when adding a book");
        }
        req.getSession().setAttribute("resultaddbook",res);
        req.getSession().setAttribute("pagetype","addbookres");
        try {
            servletContext.getRequestDispatcher("/jspfiles/admin.jsp" ).forward(req,resp);
        } catch (ServletException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        } catch (IOException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
    }
}
