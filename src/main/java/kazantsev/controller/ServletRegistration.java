package kazantsev.controller;


import kazantsev.entity.User;
import kazantsev.service.Service;
import kazantsev.service.ServiceImpl;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/registration")
public class ServletRegistration extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("formtype", "registration");
        getServletContext().getRequestDispatcher("/forms.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String sureName = req.getParameter("surename");
        String phoneString = req.getParameter("phone");
        String login = req.getParameter("login");
        String passwordEter = req.getParameter("password");

        String password= BCrypt.hashpw(passwordEter, BCrypt.gensalt(12));
        Service service = new ServiceImpl();
        User user = null;

        if ( !phoneString.matches("[0-9]+") ) {
            req.getSession().setAttribute("forminfo", "Номер телефона должен содержать цифры");
            getServletContext().getRequestDispatcher("/forms.jsp").forward(req, resp);
        } else {
            try {
                if (service.getUserByLogin(login) != null) {
                    req.getSession().setAttribute("forminfo", "Данный логин занят!");
                    getServletContext().getRequestDispatcher("/forms.jsp").forward(req, resp);
                } else {
                    int phone = Integer.parseInt(phoneString);

                    // req.getSession().setAttribute("resultbook", result);
                    try {
                        user = service.addUser(name, sureName, phone, login, password);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    req.getSession().setAttribute("user", user);
                    getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}

