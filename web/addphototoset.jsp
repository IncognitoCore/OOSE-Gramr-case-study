<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="includes/head.jsp"/>
    <h2>Add your photo to a set!</h2>
    <p>Use the form below to add a photo to your sets</p>
    <hr />
    <form method="get" action="#">
        <div class="form-group">
            <label for="titel">Enter a search term</label>
            <input type="text" name="titel" class="form-control" id="titel">
            <input type="hidden" name="user" value="Mastermindzh" class="form-control" id="user">
        </div>
        <button type="submit" name="submit" class="btn btn-success">Zoek</button>
    </form>

    <table class = "table table-responsive table-bordered">
        <thead>
        <th>Id</th>
        <th>Title</th>
        <th>Owner</th>
        <th>Image</th>
        <th>Filter properties</th>
        <th>New filter</th>
        </thead>
        <tbody>
        <c:forEach items="${items}" var="current">
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
                    <img src="<c:out value="${current.url}"/>" class="<c:out value="${current.filter.getCssClass()}"/>"/>
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
    <br>
    <form method="post" action="#">
        <div class="form-group">
            <label for="photoID">Photo id</label>
            <input type="number" name="photoID" class="form-control" id="photoID">

        </div>
        <div class="form-group">
            <label for="setID">Set id</label>
            <input type="number" class="form-control" name="setID" id="setID">
        </div>
        <div class="checkbox">
            <label><input type="checkbox" name="isPrivate" value="true">Private?</label>
        </div>
        <button type="submit" name="submit" class="btn btn-success">Save</button>
    </form>
<jsp:include page="includes/footer.jsp"/>