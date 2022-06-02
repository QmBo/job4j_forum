<%--suppress JSUnresolvedLibraryURL --%>
<%--
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
    <title>Регистрация</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${URL}/">Форум job4j</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
</nav>
<div class="container mt-3">
    <%--@elvariable id="errorMessage" type="java.lang.String"--%>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger" role="alert">
                ${errorMessage}
        </div>
    </c:if>
    <form name="login" action="<c:url value='/reg'/>" method="POST">
        <div class="form-group" >
            <label for="name">Имя пользователя</label>
            <input type="text" class="form-control" name="username" id="name">
            <div class="alert alert-danger sr-only" role="alert" id="loginAlert">
                Такой пользователь уже существует.
            </div>
        </div>
        <div class="form-group">
            <label for="password">Пароль</label>
            <input type="password"  name="password" class="form-control" id="password">
        </div>
        <div class="form-group">
            <label for="password2">Подтверждение пароля</label>
            <input type="password"  name="password2" class="form-control" id="password2">
            <div class="alert alert-danger sr-only" role="alert" id="passwordAlert">
                Пароль не совзадает.
            </div>
        </div>
        <button type="submit" id="button" class="btn btn-primary" onclick="return check()" disabled="disabled">Зарегистрироваться</button>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.js"
        integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script>
    var host = 'localhost';
    var port = '8080';
    var protocol = 'http';

    function loginAction(data) {
        if (data !== "true") {
            $('#button').attr('disabled', 'disabled');
            $('#loginAlert').removeClass('sr-only');
        } else {
            $('#button').removeAttr('disabled');
            $('#loginAlert').addClass('sr-only');
        }
    }

    $('#name').focusout(function (){
        var inputLogin = $("#name").val();
        if (inputLogin.length !== 0) {
            $.ajax({
                url: protocol + '://' + host + ':' + port + '/reg/' +  inputLogin,
                type: 'GET',
                dataType: 'text'
            }).done(function(data) {
                loginAction(data);
            }).fail(function(err){
                alert(err.statusText);
            })
        }
    });

    function check() {
        var pass = $('#password').val();
        var pass2 = $('#password2').val();
        var result = true;
        if (pass.length === 0 || pass !== pass2) {
            result = false;
            $('#passwordAlert').removeClass('sr-only');
        }
        return result;
    }
</script>
</body>
</html>
