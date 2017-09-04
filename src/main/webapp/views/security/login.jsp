<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<%--<script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>--%>

<script type="text/javascript">


    function onSignIn(googleUser) {
        window.onbeforeunload = function (e) {
            gapi.auth2.getAuthInstance().signOut();
        };
        var id_token = googleUser.getAuthResponse().id_token;
        post('/usuario/googleToken.do', {idTokenString: id_token});
    }

</script>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-4 col-sm-8 col-sm-offset-2 col-lg-4">
            <h2 style="text-align: center"><spring:message code="login.iniciarSesion"/></h2>
            <div class="well">
                <div class="row">
                    <div class="col-md-12 text-center">
                        <div class="g-signin2 botonGoogle" data-onsuccess="onSignIn"></div>
                        <br><br>
                        <c:if test="${showError == true}">
                            <div class="alert alert-danger alert-dismissable alert-link oaerror danger-conjunto">
                                <button class="close" type="button" data-dismiss="alert" aria-hidden="true">×</button>
                                <spring:message code="security.login.failed"/>
                            </div>
                        </c:if>
                        <form:form action="login" modelAttribute="credenciales" acceptCharset="UTF-8">

                            <div
                                class="form-group ${errores.contains('username') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                                <spring:message code="usuario.usuario" var="usuario"/>
                                <form:label class="control-label" path="username">${usuario}</form:label>
                                <form:input class="form-control" path="username" placeholder="${usuario}"/>
                                <form:errors class="help-block" path="username"/>
                            </div>
                            <div
                                class="form-group ${errores.contains('password') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                                <spring:message code="usuario.contraseña" var="contraseña"/>
                                <form:label class="control-label" path="password">${contraseña}</form:label>
                                <form:password class="form-control" path="password" placeholder="${contraseña}"/>
                                <form:errors class="help-block" path="password"/>
                            </div>

                            <div class="form-group text-center">
                                <br>
                                <a href="/" class="btn btn-primary"><i class="fa fa-arrow-left"></i> <spring:message
                                    code="volver"/></a>
                                <button type="submit" name="login" class="btn btn-primary"><i class="fa fa-check"></i>
                                    <spring:message code="login.iniciarSesion"/>
                                </button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function post(path, params, method) {
        method = method || "post"; // Set method to post by default if not specified.

        // The rest of this code assumes you are not using a library.
        // It can be made less wordy if you use one.
        var form = document.createElement("form");
        form.setAttribute("method", method);
        form.setAttribute("action", path);

        for (var key in params) {
            if (params.hasOwnProperty(key)) {
                var hiddenField = document.createElement("input");
                hiddenField.setAttribute("type", "hidden");
                hiddenField.setAttribute("name", key);
                hiddenField.setAttribute("value", params[key]);

                form.appendChild(hiddenField);
            }
        }

        document.body.appendChild(form);
        form.submit();
    }
</script>

<%--<script type="text/javascript">--%>
<%--$(document).ready(function () {--%>
<%--//        alert($(".abcRioButtonContents span").html());--%>
<%--//        var idioma = document.getElementById('idioma').innerHTML;--%>
<%--if (idioma == 'es') {--%>
<%--var pepe = $("[id^=not_signed_]").html();--%>
<%--console.log(pepe);--%>
<%--} else {--%>
<%--document.body.innerHTML = document.body.innerHTML.replace(/Acceder/g, 'asdadsaada');--%>
<%--}--%>
<%--document.body.innerHTML = document.body.innerHTML.replace(/Acceder/g, 'asdadsaada');--%>
<%--});--%>
<%--</script>--%>
