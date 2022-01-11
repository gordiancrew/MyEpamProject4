package kazantsev.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/language")
public class ServletLanguage extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getSession().setAttribute("user", null);
        getServletContext().getRequestDispatcher("/jspfiles/language.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String lang=req.getParameter("lang");
        req.getSession().setAttribute("language", lang);
        getServletContext().getRequestDispatcher("/jspfiles/index.jsp").forward(req, resp);
    }
}
