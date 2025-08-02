package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import com.entity.User;
import com.dao.UserDAO;
import com.DB.DBConnect;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String em = request.getParameter("email");
            String ps = request.getParameter("password");
            User user = new User();
            HttpSession session = request.getSession();

            if ("admin@gmail.com".equals(em) && "admin123".equals(ps)) {
                session.setAttribute("userobj", user);
                user.setRole("admin");
                response.sendRedirect("admin.jsp");
            } else {
                UserDAO dao = new UserDAO(DBConnect.getConn());
                User users = dao.login(em, ps);

                if (users != null) {
                    session.setAttribute("userobj", user);
                    response.sendRedirect("home.jsp");
                } else {
                    session.setAttribute("succMsg", "Invalid Email or Password!");
                    response.sendRedirect("login.jsp");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
