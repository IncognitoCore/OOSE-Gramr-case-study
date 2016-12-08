<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="includes/head.jsp"/>
    <h2>Welcome to GramR!</h2>
    <p>
        Below you'll find an overview of all your sets.
    </p>
    <hr />
    <table class = "table table-responsive table-bordered">
        <thead>
            <tr style = "font-weight:bold;">
                <th>Owner</th>
                <th>Name</th>
                <th>id</th>
                <th>photo's</th>
            </tr>
        </thead>
        <c:forEach items="${items}" var="current">
            <tr>
                <td>
                    <c:out value="${current.owner}"/>
                </td>
                <td>
                    <c:out value="${current.name}"/>
                </td>
                <td>
                    <c:out value="${current.id}"/>
                </td>
                <td align = "left">
                    <c:forEach items="${current.photos}" var="photo">
                        <img class = "private ${photo.getIsPrivate().isPrivacy()}" width = "100" src = "${photo.url}" alt = "private=${photo.getIsPrivate().isPrivacy()}"/>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
<jsp:include page="includes/footer.jsp"/>