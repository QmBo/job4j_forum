<%--suppress JSUnresolvedLibraryURL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${URL}/">Форум job4j</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${URL}/create">Новая тема</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${URL}/logout">Выйти</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container mt-3">
    <div class="card">
        <h5 class="card-header">${post.name}</h5>
        <div class="card-body">
            <h5 class="card-title">${post.author.name}</h5>
            <p class="card-text">${post.description}</p>
            <p class="card-text"><small class="text-muted"><fmt:formatDate value="${post.created.time}" pattern="HH:mm:ss dd/MM/yyyy" /></small>
            <%--@elvariable id="login" type="java.lang.String"--%>
            <c:if test="${login == post.author.name}">
                <a href="${URL}/create?id=${post.id}" class="btn btn-primary">Редактировать</a>
            </c:if>
            </p>
        </div>
    </div>
    <c:forEach items="${post.answers}" var="answer">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">${answer.author.name}</h5>
                <p class="card-text">${answer.description}</p>
                <p class="card-text"><small class="text-muted"><fmt:formatDate value="${answer.created.time}" pattern="HH:mm:ss dd/MM/yyyy" /></small>
                <c:if test="${login == answer.author.name}">
                    <a href="${URL}/create?id=${answer.id}" class="btn btn-primary">Редактировать</a>
                </c:if>
                </p>
            </div>
        </div>
    </c:forEach>

    <div class="row">
        <div class="col-2">
            <a href="${URL}/create?answerFor=${post.id}" class="btn btn-success">Ответить</a>
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
