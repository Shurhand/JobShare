<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <h2 style="text-align: center"><spring:message code="login.iniciarSesion"/></h2>
            <div class="well">
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

<%--<div class="container"/>--%>
<%--<div class="row">--%>
<%--<div class="col-md-6 col-md-offset-3 ">--%>
<%--<form:form action="login" modelAttribute="credenciales">--%>
<%--<div class="input-group">--%>
<%--<span class="input-group-addon"><i--%>
<%--class="fa fa-user fa"--%>
<%--aria-hidden="true"></i></span>--%>
<%--<form:input placeholder="Usuario" path="username"--%>
<%--class="form-control"/>--%>
<%--<form:errors class="error2" path="username"/>--%>
<%--</div>--%>
<%--<div class="input-group">--%>
<%--<span class="input-group-addon"><i--%>
<%--class="fa fa-lock fa"--%>
<%--aria-hidden="true"></i></span>--%>
<%--<form:password placeholder="Contraseña"--%>
<%--path="password"--%>
<%--class="form-control"/>--%>
<%--<form:errors class="error2" path="password"/>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<jstl:if test="${showError == true}">--%>
<%--<div class="error2">--%>
<%--<spring:message--%>
<%--code="security.login.failed"/>--%>
<%--</div>--%>
<%--</jstl:if>--%>
<%--</div>--%>
<%--<div id="remember" class="checkbox">--%>
<%--<label>--%>
<%--<input type="checkbox" name="remember-me">--%>
<%--<spring:message code="master.page.rememberMe"/>--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<input type="submit"--%>
<%--class="btn btn-primary btn-block"--%>
<%--style="margin-bottom: 2%"--%>
<%--value="<spring:message code="master.page.login" />"/>--%>
<%--</div>--%>
<%--<a href="#" class="forgot-password">--%>
<%--<spring:message--%>
<%--code="master.page.passwordOlvidado"/>--%>
<%--</a>--%>
<%--</form:form>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>