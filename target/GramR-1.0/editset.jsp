<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="includes/head.jsp"/>
    <h2>Change SetName</h2>
    <p>Use the form below to change a sets name</p>
    <hr />
    <form method="post" action="#">
        <div class="form-group">
            <label for="setId">Set id</label>
            <input type="text" name="setId" class="form-control" id="setId">
        </div>
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" class="form-control" name="name" id="name">
        </div>
        <button type="submit" name="submit" class="btn btn-success">Save</button>
    </form>
<jsp:include page="includes/footer.jsp"/>