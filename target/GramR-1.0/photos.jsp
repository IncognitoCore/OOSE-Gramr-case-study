<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="includes/head.jsp"/>
<div class="container">
    <h2>Your photo's</h2>
    <p>Below you'll find an overview of all your photo's</p>
    <hr />
    <table class = "table table-responsive table-bordered">
        <thead>
            <tr>
                <th>Id</th>
                <th>Titel</th>
                <th>Owner</th>
                <th>Image</th>
                <th>Filter properties</th>
                <th>New filter</th>
            </tr>
        </thead>
            <tbody>
            <c:forEach items="${photos}" var="current">
                <tr>
                    <td>
                        <c:out value="${current.id}"/>
                    </td>
                    <td>
                        <c:out value="${current.title}"/>
                    </td>
                    <td>
                        <c:out value="${current.creator}"/>
                    </td>
                    <td>
                        <img width = "120px" src="<c:out value="${current.url}"/>" class="<c:out value="${current.filter.getCssClass()}"/>"/>
                    </td>
                    <td>
                        <c:out value="${current.filter.toString()}"/>
                    </td>
                    <td>
                        <ul class ="overlay">
                            <li><a href="/applyfilter?filter=null&photoId=<c:out value="${current.id}"/>&user=<c:out value="${current.creator}"/>">none</a> </li>
                            <li><a href="/applyfilter?filter=Gray&photoId=<c:out value="${current.id}"/>&user=<c:out value="${current.creator}"/>">Gray</a> </li>
                            <li><a href="/applyfilter?filter=Vintage&photoId=<c:out value="${current.id}"/>&user=<c:out value="${current.creator}"/>">Vintage</a> </li>
                            <li><a href="/applyfilter?filter=Invert&photoId=<c:out value="${current.id}"/>&user=<c:out value="${current.creator}"/>">Invert</a> </li>
                        </ul>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
<jsp:include page="includes/footer.jsp"/>