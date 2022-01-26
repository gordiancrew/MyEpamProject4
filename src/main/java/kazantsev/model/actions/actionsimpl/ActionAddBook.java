package kazantsev.model.actions.actionsimpl;

import kazantsev.model.actions.Action;
import kazantsev.service.Service;
import kazantsev.service.ServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ActionAddBook implements Action {

    @Override
    public void executeGet(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("pagetype", "addbook");
        servletContext.getRequestDispatcher("/jspfiles/admin.jsp").forward(request, response);
    }

    @Override
    public void executePost(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String tittle=req.getParameter("tittle");
        String author=req.getParameter("author");
        String yearString=req.getParameter("year");
        String numberString=req.getParameter("number");
        String description=req.getParameter("description");
        String res="Некоррекные данные ";
        Service service= new ServiceImpl();

        if(yearString.matches("[0-9]+")&&numberString.matches("[0-9]+")) {
            int year =Integer.parseInt(yearString);
            int number=Integer.parseInt(numberString);


           res=service.addBook(tittle, author,year,number,description);

        }
        req.getSession().setAttribute("resultaddbook",res);
        req.getSession().setAttribute("pagetype","addbookres");
        servletContext.getRequestDispatcher("/jspfiles/admin.jsp" ).forward(req,resp);
    }
}
