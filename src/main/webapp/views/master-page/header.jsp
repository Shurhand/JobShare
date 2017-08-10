<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="styles/custom.css" type="text/css">

<!DOCTYPE html>

<header class="main-header">
    <div class="container navbar-custom">
        <div class="navbar-collapse" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="title" href="/"> <img src="images/Logo.png" height="45px"/></a>
                </div>
                <ul class="nav navbar-nav navbar-left ">
                    <security:authorize access="hasAuthority('USUARIO')">
                        <%--<li><a href="usuario/convertirse.do"></a></li>--%>
                        <li class="modal">
                            <a href="#" class="hidden-sm hidden-md hidden-lg" data-target="#modalProfesional"
                               data-toggle="modal">¡Conviértete en profesional!
                                <span class="caret hidden-sm hidden-md hidden-lg"></span></a>
                            <a class="hidden-xs" data-target="#modalProfesional" data-toggle="modal" href="#"><span
                                class="fa fa-user-md"></span> ¡Conviértete en profesional!</a>

                            <!-- Modal -->
                            <div class="modal fade" id="modalProfesional" tabindex="-1" role="dialog"
                                 aria-labelledby="myModalLabel2">
                                <div class="modal-dialog" role="document">
                                    <div class="col-md-12">
                                        <div class="modal-content text-center">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close"><span aria-hidden="true">&times;</span>
                                                </button>
                                                <h4 class="modal-title" id="myModalLabel2">Convertirse en
                                                    profesional</h4>
                                            </div>
                                            <div class="modal-body">
                                                <p><spring:message code="usuario.convertirse"/></p>
                                            </div>
                                            <div class="modal-footer">
                                                <div class="form-group text-center">
                                                    <button type="button" class="btn btn-default"
                                                            data-dismiss="modal"><spring:message
                                                        code="cancelar"/></button>
                                                    <a class="btn btn-primary" href="usuario/convertirse.do"
                                                       role="button"><spring:message code="aceptar"/></a>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </li>
                    </security:authorize>

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
                </ul>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <security:authorize access="isAuthenticated()">
                            <li><a href="etiquetas.do"><i class="fa fa-tags" aria-hidden="true"></i> Ver etiquetas</a>
                            </li>
                        </security:authorize>
                        <security:authorize access="hasAuthority('USUARIO')">
                            <li><a href="usuario/MisPeticiones.do"><i class="fa fa-book" aria-hidden="true"></i> Mis
                                peticiones</a></li>
                        </security:authorize>
                        <security:authorize access="hasAuthority('PROFESIONAL')">
                            <li><a href="profesional/MisPeticiones.do"><i class="fa fa-book" aria-hidden="true"></i> Mis
                                peticiones</a></li>
                            <li><a href="profesional/MisOfertas.do"><i class="fa fa-briefcase" aria-hidden="true"></i>
                                Mis ofertas</a></li>
                        </security:authorize>
                        <security:authorize access="hasAuthority('ADMIN')">
                            <li><a href="usuario/admin/listaUsuarios.do"><i class="fa fa-users" aria-hidden="true"></i>
                                Lista de
                                usuarios</a></li>
                        </security:authorize>
                        <security:authorize access="isAnonymous()">
                            <li><a href="etiquetas.do"><i class="fa fa-tags" aria-hidden="true"></i> Ver etiquetas</a>
                            </li>
                            <li><a href="usuario/registro.do"><i class="fa fa-user-plus" aria-hidden="true"></i>
                                Registrarse</a></li>

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
                                                                        <input type="checkbox" name="remember-me">
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
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                    class="fa fa-user-circle-o" aria-hidden="true"></i>
                                    <spring:message code="master.page.salute"/>
                                    <security:authentication property="principal.username"/>!</a>
                                <ul class="dropdown-menu box main-dp">
                                    <li>
                                        <a href="actor/perfil.do"><i class="fa fa-user" aria-hidden="true"></i> Ver
                                            perfil</a>
                                    </li>
                                    <li>
                                        <a href="logout"><i class="fa fa-sign-out" aria-hidden="true"></i> Salir</a>
                                    </li>
                                </ul>
                            </li>

                        </security:authorize>
                    </ul>
                </div>
            </div>
        </div>


    </div>
</header>


