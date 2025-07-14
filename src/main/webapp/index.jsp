<%@ page contentType="text/html; charset=UTF-8" language="java" %>
    <!doctype html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>Dictionary</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>
        <div class="container mt-5">
            <h1 class="text-center mb-4 text-danger">Dictionary App</h1>
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <form class="input-group mb-3" action="HelloServlet" method="post">
                        <input type="text" class="form-control shadow" name="word" placeholder="Enter a word">
                        <button type="submit" class="btn btn-primary">Search</button>
                    </form>
                </div>
            </div>

            <% String word=(String) request.getAttribute("word"); String definition=(String)
                request.getAttribute("definition"); if (word !=null && definition !=null) { %>
                <div class="row justify-content-center mt-3">
                    <div class="col-md-6">
                        <div class="card border-info mb-3">
                            <div class="card-body shadow">
                                <h4 class="card-title">
                                    <span class="text-danger">Word:</span>
                                    <span class="text-primary">
                                        <%= word %>
                                    </span>
                                </h4>
                                <p class="card-text fs-6">
                                    <%= definition %>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <% } %>
        </div>
    </body>

    </html>