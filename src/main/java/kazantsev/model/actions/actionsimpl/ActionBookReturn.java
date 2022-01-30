package kazantsev.model.actions.actionsimpl;

import kazantsev.entity.Book;
import kazantsev.entity.Operation;
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
import java.util.Locale;
import java.util.ResourceBundle;

public class ActionBookReturn  implements Action {

    private static final Logger log = Logger.getLogger(ActionBookReturn.class);

    @Override
    public void executeGet(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) {
        String idOperationString = req.getParameter("id");
        int idOperation = Integer.parseInt(idOperationString);
        Service service= new ServiceImpl();
        Operation operation = null;
        operation=service.getOperationById(idOperation);
        Book book = null;
        book=service.getBookById(operation.getBook().getId());
        req.getSession().setAttribute("pagetype", "book");
        req.getSession().setAttribute("booktype", "returnview");
        req.getSession().setAttribute("book", book);
        req.getSession().setAttribute("operation", operation);
        try {
            servletContext.getRequestDispatcher("/jspfiles/books.jsp").forward(req, resp);
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
        Object languageObject=req.getSession().getAttribute("language");
        String language="ru";
        if(languageObject!=null){
            language=languageObject.toString();
        }
        ResourceBundle bundle=ResourceBundle.getBundle("messages",new Locale(language));
        String idOperationString = req.getParameter("idoperation");
        String result = " no";
        Operation operation=null;
        Service service = new ServiceImpl();
        int idOperation = Integer.parseInt(idOperationString);
        operation=service.getOperationById(idOperation);
        log.log(Level.INFO,"reader:id:"+operation.getReader().getId()+" return book:id:"+operation.getBook().getId());
        result = service.returnBook(idOperation);

        req.getSession().setAttribute("pagetype", "book");
        req.getSession().setAttribute("booktype", "result");
        req.getSession().setAttribute("resultbook", bundle.getString(result));
        try {
            servletContext.getRequestDispatcher("/jspfiles/books.jsp").forward(req, resp);
        } catch (ServletException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        } catch (IOException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
    }
}
