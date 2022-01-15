package kazantsev.garbage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/languagea")
public class ServletLanguage extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.getSession().setAttribute("pagetype", "language");
        getServletContext().getRequestDispatcher("/jspfiles/books.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String lang=req.getParameter("lang");
        req.getSession().setAttribute("language", lang);
        req.getSession().setAttribute("pagetype", "language");
        getServletContext().getRequestDispatcher("/jspfiles/books.jsp").forward(req, resp);
    }
}
