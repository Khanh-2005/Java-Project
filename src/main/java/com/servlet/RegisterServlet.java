package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.UserDAO;
import com.entity.User;

@WebServlet("/add_user")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String qua = request.getParameter("qua");
            String email = request.getParameter("email");
            String ps = request.getParameter("ps");

            UserDAO dao = new UserDAO(DBConnect.getConn());

            User user = new User(name, email, ps, qua, "user");
            boolean f = dao.addUser(user);

            HttpSession session = request.getSession();
            if (f) {
                session.setAttribute("succMsg", "Registration Successfully!");
            } else {
                session.setAttribute("succMsg", "Something wrong on server!");
            }

            response.sendRedirect("signup.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
