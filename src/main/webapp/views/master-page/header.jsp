<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags" %>

<link rel="stylesheet" href="styles/custom.css" type="text/css">

<!DOCTYPE html>
<html>
<header class="main-header">
    <div class="container">
        <div class="navbar-collapse" role="navigation">
            <div class="container-fluid">
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

                        <security:authorize access="isAnonymous()">
                            <li class="modal">
                                <a href="#" class="hidden-sm hidden-md hidden-lg" data-target="#myModal"
                                   data-toggle="modal">Iniciar sesión
                                    <span class="caret hidden-sm hidden-md hidden-lg"></span></a>
                                <a class="hidden-xs" data-target="#myModal" data-toggle="modal" href="#"><span
                                    class="glyphicon glyphicon-log-in"></span> Iniciar sesión</a>

                                <!-- Modal -->
                                <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
                                     aria-labelledby="myModalLabel">
                                    <div class="modal-dialog" role="document">
                                        <div class="col-md-12">
                                            <div class="modal-content text-center">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal"
                                                            aria-label="Close"><span aria-hidden="true">&times;</span>
                                                    </button>
                                                    <h4 class="modal-title" id="myModalLabel">Inicio de sesión</h4>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="row">
                                                        <div class="col-md-6 col-md-offset-3">
                                                            <div class="row">
                                                                <button type="button"
                                                                        class="btn btn-primary btn-lg btn-block">
                                                                    Acceso con Google
                                                                </button>
                                                            </div>
                                                            <div class="row">
                                                                <button type="button"
                                                                        class="btn btn-primary btn-lg btn-block">
                                                                    Acceso con Facebook
                                                                </button>
                                                            </div>
                                                        </div>


                                                        <div class="col-md-6 col-md-offset-3 ">
                                                            <form:form action="login"
                                                                       modelAttribute="credenciales">
                                                                <div class="input-group">
                                                                    <span class="input-group-addon"><i
                                                                        class="fa fa-user fa"
                                                                        aria-hidden="true"></i></span>
                                                                    <form:input placeholder="Usuario" path="username"
                                                                                class="form-control"/>
                                                                    <form:errors class="error2" path="username"/>
                                                                </div>
                                                                <div class="input-group">
                                                                    <span class="input-group-addon"><i
                                                                        class="fa fa-lock fa"
                                                                        aria-hidden="true"></i></span>
                                                                    <form:password placeholder="Contraseña"
                                                                                   path="password"
                                                                                   class="form-control"/>
                                                                    <form:errors class="error2" path="password"/>
                                                                </div>
                                                                <div class="form-group">
                                                                    <jstl:if test="${showError == true}">
                                                                        <div class="error2">
                                                                            <spring:message
                                                                                code="security.login.failed"/>
                                                                        </div>
                                                                    </jstl:if>
                                                                </div>
                                                                <div id="remember" class="checkbox">
                                                                    <label>
                                                                        <input type="checkbox" value="remember-me">
                                                                        Remember
                                                                        me
                                                                    </label>
                                                                </div>
                                                                <div class="form-group">
                                                                    <input type="submit"
                                                                           class="btn btn-primary btn-block"
                                                                           style="margin-bottom: 2%"
                                                                           value="<spring:message code="master.page.login" />"/>
                                                                </div>
                                                                <a href="#" class="forgot-password">
                                                                    ¿Contraseña olvidada?
                                                                </a>
                                                            </form:form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </li>
                        </security:authorize>
                        <security:authorize access="isAuthenticated()">
                            <div>
                                <form:form>
                                    <div class="form-group">
                                        <p><spring:message code="master.page.salute"/>&nbsp&nbsp
                                            <security:authentication property="principal.username"/></p>
                                        <a class="btn btn-primary" href="logout"><spring:message
                                            code="master.page.logout"/></a>
                                    </div>
                                </form:form>
                            </div>
                        </security:authorize>
                    </ul>
                </div>
            </div>
        </div>


    </div>
</header>

</html>


