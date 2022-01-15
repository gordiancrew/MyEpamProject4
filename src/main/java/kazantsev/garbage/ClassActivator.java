package kazantsev.garbage;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClassActivator {
    HttpServletRequest request;
    HttpServletResponse response;
    ServletContext servletContext;
    public ClassActivator(ServletContext servletContext,HttpServletRequest request, HttpServletResponse response){
        this.request=request;
        this.response=response;
        this.servletContext=servletContext;
    }
    public void execut() throws ServletException, IOException {
        request.getSession().setAttribute("pagetype", "books");
        servletContext.getRequestDispatcher("/jspfiles/books.jsp").forward(request, response);
    }
}
