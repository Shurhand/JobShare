<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<header class="main-header">
    <div class="container">
        <div class="navbar-collapse" role="navigation">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">


                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                            data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="title" href="/"> <img src="/static/images/logo2.png" height="45px"/></a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">

                        <li><a href="/faq"><span class="glyphicon glyphicon-info-sign"></span> Ayuda</a></li>

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span
                                class="glyphicon glyphicon-globe"></span> Idioma</a>
                            <ul class="dropdown-menu box main-dp">
                                <li>
                                    <a href="?language=en"><img src="images/icon_id_en.png"></a>
                                </li>
                                <li>
                                    <a href="?language=es"><img src="images/icon_id_es.png"></a>
                                </li>
                            </ul>
                        </li>

                        <li class="dropdown">
                            <a href="#" class="hidden-sm hidden-md hidden-lg" data-toggle="dropdown">Iniciar sesión
                                <span class="caret hidden-sm hidden-md hidden-lg"></span></a>
                            <a class="hidden-xs" data-toggle="dropdown" href="#"><span
                                class="glyphicon glyphicon-log-in"></span> Iniciar sesión</a>
                            <ul id="login-dp" class="dropdown-menu box main-dp">
                                <li>
                                    <div class="row">
                                        <div class="col-md-12">

                                            <security:authorize access="isAnonymous()">
                                                <div id="navbar" class="navbar-collapse collapse">
                                                    <form:form class="navbar-form navbar-right" action="login"
                                                               modelAttribute="credenciales">
                                                        <div class="form-group">
                                                            <form:input path="username" class="form-control"/>
                                                            <form:errors class="error2" path="username"/>
                                                        </div>
                                                        <div class="form-group">
                                                            <form:password path="password" class="form-control"/>
                                                            <form:errors class="error2" path="password"/>
                                                        </div>
                                                        <div class="form-group">
                                                            <jstl:if test="${showError == true}">
                                                                <div class="error2">
                                                                    <spring:message code="security.login.failed"/>
                                                                </div>
                                                            </jstl:if>
                                                        </div>
                                                        <div class="form-group">
                                                            <input type="submit" class="btn btn-primary"
                                                                   value="<spring:message code="master.page.login" />"/>
                                                            <a href="customer/create.do" class="btn btn-primary">
                                                                <spring:message code="master.page.register"/></a>
                                                        </div>
                                                    </form:form>
                                                </div>
                                            </security:authorize>

                                        </div>
                                        <div class="bottom text-center">
                                            ¿No tienes cuenta todavía? <a href="/customer/register/"><b>Registro</b></a>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </li>

                    </ul>
                </div>
            </div>
        </div>


    </div>
</header>

</div>

</html>


