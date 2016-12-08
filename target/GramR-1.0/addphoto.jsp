<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="includes/head.jsp"/>

    <h2>Add a photo</h2>
    <p>Use the form below to add a photo</p>
    <hr />
    <form method="post" action="#">
        <div class="form-group">
            <label for="title">Title</label>
            <input type="text" name="title" class="form-control" id="title">
        </div>
        <div class="form-group">
            <label for="creator">Creator</label>
            <input type="text" class="form-control" name="creator" id="creator">
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <input type="text" class="form-control" name="description" id="description">
        </div>
        <div  class="form-group">
            <label for="url">url</label>
            <input type="text" class="form-control" name="url" id="url">
        </div>
        <button type="submit" name="submit" class="btn btn-success">Opslaan</button>
    </form>
<jsp:include page="includes/footer.jsp"/>