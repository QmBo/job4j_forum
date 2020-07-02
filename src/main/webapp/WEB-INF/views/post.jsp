<%--suppress JSUnresolvedLibraryURL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%--@elvariable id="post" type="ru.job4j.forum.model.Post"--%>
<c:set var="post" value="${post}"/>
<c:set var="URL" value="${pageContext.servletContext.contextPath}"/>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Topic - ${post.name}</title>
</head>
<body>
<div class="container mt-3">
    <div class="row">
        <div class="col-8">
            <a href="${URL}/" class="stretched-link"><h4>Форум job4j</h4></a>
        </div>
        <div class="col-2">
            <a href="${URL}/create" class="btn btn-success">New Topic</a>
        </div>
        <div class="col-2">
            <a href="${URL}/logout" class="btn btn-danger">Logout</a>
        </div>
    </div>
    <div class="card">
        <h5 class="card-header">${post.name}</h5>
        <div class="card-body">
            <h5 class="card-title">${post.author.name}</h5>
            <p class="card-text">${post.description}</p>
            <p class="card-text"><small class="text-muted">${post.created.time}</small></p>
            <%--@elvariable id="login" type="java.lang.String"--%>
            <c:if test="${login == post.author.name}">
                <a href="${URL}/create?id=${post.id}" class="btn btn-primary">Edit</a>
            </c:if>
        </div>
    </div>
    <c:forEach items="${post.answers}" var="answer">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">${answer.author.name}</h5>
                <p class="card-text">${answer.description}</p>
                <p class="card-text"><small class="text-muted">${answer.created.time}</small></p>
                <c:if test="${login == answer.author.name}">
                    <a href="${URL}/create?id=${answer.id}" class="btn btn-primary">Edit</a>
                </c:if>
            </div>
        </div>
    </c:forEach>

    <div class="row">
        <div class="col-2">
            <a href="${URL}/create?answerFor=${post.id}" class="btn btn-success">Comment</a>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>
