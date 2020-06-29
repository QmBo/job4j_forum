<%--suppress ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="URL" value="${pageContext.servletContext.contextPath}"/>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Login</title>
</head>
<body>
<div class="container mt-3">
    <div class="row">
        <div class="col-10">
            <a href="${URL}/" class="stretched-link"><h4>Форум job4j</h4></a>
        </div>
        <div class="col-2">
            <a href="${URL}/reg" class="btn btn-success">Sing Up</a>
        </div>
    </div>
    <%--@elvariable id="errorMessage" type="java.lang.String"--%>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger" role="alert">
                ${errorMessage}
        </div>
    </c:if>
    <form name="login" action="<c:url value='/login'/>" method="POST">
        <div class="form-group">
            <label for="name">User Name</label>
            <input type="text" class="form-control" name="username" id="name" aria-describedby="emailHelp">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password"  name="password" class="form-control" id="password">
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
