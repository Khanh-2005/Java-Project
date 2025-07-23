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

public class AddServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String username = request.getParameter("username");
        String todo = request.getParameter("todo");
        String status = request.getParameter("status");

        Entity entity = new Entity();
        entity.setName(username);
        entity.setTodo(todo);
        entity.setStatus(status);

        TodoDAO todoDAO = new TodoDAO(DBConnector.getConn());
        boolean f = todoDAO.addTodo(entity.getName(), entity.getTodo(), entity.getStatus()); // Sửa lại dòng này

        HttpSession session = request.getSession();
        if (f) {
            session.setAttribute("sucMsg", "Todo added successfully"); // Sửa lại tên thuộc tính
            response.sendRedirect("index.jsp");
        } else {
            session.setAttribute("failedMsg", "Failed to add todo");
            response.sendRedirect("index.jsp");
        }
    }

}
