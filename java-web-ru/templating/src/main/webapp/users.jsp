<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Example application | Users</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
        <a href="/">Главная</a>
        <table>
            <c:forEach var="u" items="${users}">
                <tr>
                <td>${u.get("id")}</td>
                <td><a href='/users/show?id=${u.get("id")}'>${u.get("firstName")} ${u.get("lastName")}</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
<!-- END -->
