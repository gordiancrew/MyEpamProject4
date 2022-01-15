package kazantsev.model.actions;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public interface Action {
    void executeGet(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void executePost(ServletContext servletContext,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException;
}
