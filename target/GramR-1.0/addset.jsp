<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="includes/head.jsp"/>
    <h2>Add a set</h2>
    <p>Use the form below to add a set</p>
    <hr />
    <form method="post" action="#">
        <div class="form-group">
            <label for="name">Set name</label>
            <input type="text" name="name" class="form-control" id="name">
        </div>
        <div class="form-group">
            <label for="user">Username</label>
            <input type="text" class="form-control" name="user" id="user">
        </div>
        <div class="form-group">
            <label for="photos">Foto's</label>
            <input type="text" class="form-control" name="photos" id="photos">
        </div>
        <div  class="form-group">
            <label for="privates">Photo's which are private</label>
            <input type="text" class="form-control" name="privates" id="privates">
        </div>
        <button type="submit" name="submit" class="btn btn-success">Opslaan</button>
    </form>

<jsp:include page="includes/footer.jsp"/>