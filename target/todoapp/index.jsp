<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Welcome my todo app</title>
        <%@ include file="component/all_css.jsp" %>
    </head>

    <body>
        <%@ include file="component/navbar.jsp" %>

            <h1 class="text-center text-success">TODO APP</h1>

            <div class="container">
                <table class="table table-striped" border="1px">
                    <thead class="bg-success text-white">
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Todo</th>
                            <th scope="col">Status</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row">1</th>
                            <td>Flutter tutorial</td>
                            <td>Pending</td>
                            <td>
                                <a href="" class="btn btn-sm btn-success">Edit</a>
                                <a href="" class="btn btn-sm btn-danger">Delete</a>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">2</th>
                            <td>Jacob</td>
                            <td>Thornton</td>
                            <td>@fat</td>
                        </tr>
                        <tr>
                            <th scope="row">3</th>
                            <td>Larry</td>
                            <td>the Bird</td>
                            <td>@twitter</td>
                        </tr>
                    </tbody>
                </table>
            </div>

    </body>

    </html>