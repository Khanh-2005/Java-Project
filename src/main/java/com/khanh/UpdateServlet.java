package com.khanh;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.TodoDAO;
import com.DB.DBConnector;
import com.entity.Entity;

public class UpdateServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String todo = request.getParameter("todo");
        String status = request.getParameter("status");

        TodoDAO todoDAO = new TodoDAO(DBConnector.getConn());

        Entity en = new Entity();
        en.setId(id);
        en.setName(username);
        en.setTodo(todo);
        en.setStatus(status);

        boolean f = todoDAO.updateTodo(en);

        HttpSession session = request.getSession();
        if (f) {
            session.setAttribute("sucMsg", "Todo Updated successfully");
            response.sendRedirect("index.jsp");
        } else {
            session.setAttribute("failedMsg", "Something wrong on server");
            response.sendRedirect("index.jsp");
        }
    }

}
