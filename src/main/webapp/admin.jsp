<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page isELIgnored="false" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Admin</title>
                <%@ include file="allcomponent/allcss.jsp" %>
                    <style type="text/css">
                        .back-img {
                            background-image: url("img/pic2.jpg");
                            width: 100%;
                            height: 88vh;
                            background-size: cover;
                            background-repeat: no-repeat;
                        }
                    </style>
            </head>

            <body>
                <c:if test="${userobj.role ne 'admin' }">
                    <redirect url="login.jsp"></redirect>
                </c:if>

                <%@ include file="allcomponent/navbar.jsp" %>
                    <div class="container-fluid back-img">
                        <div class="text-center">
                            <h1 class="tet-white p-4">
                                Welcome to the Admin Page
                            </h1>

                        </div>
                    </div>

                    <%@ include file="allcomponent/footer.jsp" %>
            </body>

            </html>