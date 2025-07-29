<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Online Job Portal</title>
        <%@ include file="allcomponent/allcss.jsp" %>
            <style type="text/css">
                .back-img {
                    background-image: url("img/pic1.jpg");
                    width: 100%;
                    height: 88vh;
                    background-size: cover;
                    background-repeat: no-repeat;
                }
            </style>
    </head>

    <body>
        <%@ include file="allcomponent/navbar.jsp" %>

            <div class="container-fluid back-img">
                <div class="text-center">
                    <h1 class="tet-white p-4">
                        <i class="fa fa-book" aria-hiđen="true"></i>
                        Online Job Portal
                    </h1>

                </div>
            </div>

            <%@ include file="allcomponent/footer.jsp" %>
    </body>

    </html>