package kazantsev.model.actions.actionsimpl;

import kazantsev.entity.User;
import kazantsev.model.actions.Action;
import kazantsev.service.Service;
import kazantsev.service.ServiceImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ActionRegistration implements Action {

    private static final Logger log = Logger.getLogger(ActionRegistration.class);

    @Override
    public void executeGet(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response){

        request.getSession().setAttribute("pagetype", "registration");
        try {
            servletContext.getRequestDispatcher("/jspfiles/books.jsp").forward(request, response);
        } catch (ServletException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        } catch (IOException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
    }

    @Override
    public void executePost(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String sureName = req.getParameter("surename");
        String phoneString = req.getParameter("phone");
        String login = req.getParameter("login");
        String passwordEter = req.getParameter("password");
        String address=req.getParameter("address");

        String password = BCrypt.hashpw(passwordEter, BCrypt.gensalt(12));
        Service service = new ServiceImpl();
        User user = null;

        Object languageObject=req.getSession().getAttribute("language");
        String language="ru";
        if(languageObject!=null){
            language=languageObject.toString();
        }
        ResourceBundle bundle=ResourceBundle.getBundle("messages",new Locale(language));

        if (!phoneString.matches("[0-9]+")) {
            req.getSession().setAttribute("error", bundle.getString("registration.number"));
            try {
                servletContext.getRequestDispatcher("/jspfiles/books.jsp").forward(req, resp);
            } catch (ServletException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            } catch (IOException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            }
        } else {
            if (service.getUserByLogin(login) != null) {
                req.getSession().setAttribute("error",  bundle.getString("registration.loginreserved"));
                try {
                    servletContext.getRequestDispatcher("/jspfiles/books.jsp").forward(req, resp);
                } catch (ServletException e) {
                    log.log(Level.ERROR, "exception:", e);
                    e.printStackTrace();
                } catch (IOException e) {
                    log.log(Level.ERROR, "exception:", e);
                    e.printStackTrace();
                }
            } else {
                int phone = Integer.parseInt(phoneString);
                user = service.addUser(name, sureName, phone,address, login, password);
                req.setAttribute("confirm", "no");
                req.getSession().setAttribute("user", user);
                try {
                    servletContext.getRequestDispatcher("/jspfiles/index.jsp").forward(req, resp);
                } catch (ServletException e) {
                    log.log(Level.ERROR, "exception:", e);
                    e.printStackTrace();
                } catch (IOException e) {
                    log.log(Level.ERROR, "exception:", e);
                    e.printStackTrace();
                }
            }
        }
    }
}
