<%-- 
    Document   : confirmDelete
    Created on : Apr 16, 2025, 9:28:55 AM
    Author     : beu29
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Delete</title>
        <link rel="stylesheet" href="base">
        <link rel="stylesheet" href="grid">
        <link rel="stylesheet" href="confirmDeleteStyle">
    </head>
    <body>
        <c:set var="dto" value="${sessionScope.SHOW_PROFILE}" />
        <div class="container grid">
            <c:if test="${not empty dto}">

                <div class="row">
                    <h1>Confirm Delete</h1>
                </div>

                <div class="profile row">
                    <div class="info">
                        <div>
                            Username: ${dto.username}
                        </div>
                        <div>
                            Password: ${dto.password} 
                        </div>
                        <div>
                            Full Name: ${dto.lastname}
                        </div>
                        <div>
                            Role: 
                            <c:if test="${dto.role eq true}">
                                Admin
                            </c:if>
                            <c:if test="${dto.role eq false}">
                                Not Admin
                            </c:if>
                        </div>
                    </div>
                    <div>
                        <img src="avatar" alt="Avatar">
                    </div>
                </div>

                <div class="button">
                    <form action="deleteAction">
                        <input type="submit" value="Confirm" class="btn" />
                        <input type="hidden" name="pk" value="${dto.username}" />
                        <input type="hidden" name="lastSearchValue" value="${param.lastSearchValue}" />
                    </form>
                    <c:url var="urlReWriting" value="searchAction">
                        <c:param name="txtSearchValue" value="${param.lastSearchValue}" />
                    </c:url>
                    <a href="${urlReWriting}">
                        <input type="submit" value="Cancel" class="cancel"/>
                    </a>
                </div>
            </c:if>
            <c:if test="${empty dto}">
                <div class="row">
                    <h1>No profile for Delete!</h1>
                </div>
                <div>
                    <a href="loginPage">Go Back To Login Page!</a>
                </div>
            </c:if>
        </div>
    </body>
</html>
