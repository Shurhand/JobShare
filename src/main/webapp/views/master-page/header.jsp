<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="styles/custom.css" type="text/css">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>

<!DOCTYPE html>

<header class="main-header">
    <div class="container navbar-custom">
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

                    <a class="title" href="/"> <img src="images/Logo.png" height="45px"/></a>
                </div>
                <ul class="nav navbar-nav navbar-left ">
                    <security:authorize access="hasAuthority('USUARIO')">
                        <li class="modal">
                            <a href="#" class="hidden-sm hidden-md hidden-lg" data-target="#modalProfesional"
                               data-toggle="modal"><spring:message code="master.page.convertirse"/>
                                <span class="caret hidden-sm hidden-md hidden-lg"></span></a>
                            <a class="hidden-xs" data-target="#modalProfesional" data-toggle="modal" href="#"><span
                                class="fa fa-user-md"></span> <spring:message code="master.page.convertirse"/></a>

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
                                                <h4 class="modal-title" id="myModalLabel2"><spring:message
                                                    code="master.page.convertirse"/></h4>
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
                            class="glyphicon glyphicon-globe"></span> <spring:message code="idioma.header"/></a>
                        <ul class="dropdown-menu box main-dp">
                            <li>
                                <a href="?language=en"><img src="images/icon_id_en.png"> <spring:message code="ingles"/></a>
                            </li>
                            <li>
                                <a href="?language=es"><img src="images/icon_id_es.png"> <spring:message
                                    code="español"/></a>
                            </li>
                        </ul>
                    </li>
                </ul>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <security:authorize access="!hasAuthority('ADMIN')">
                            <li><a href="etiqueta/lista.do"><i class="fa fa-tags" aria-hidden="true"></i>
                                <spring:message
                                    code="master.page.verEtiquetas"/></a>
                            </li>
                        </security:authorize>
                        <security:authorize access="hasAuthority('ADMIN')">
                            <li><a href="etiqueta/admin/lista.do"><i class="fa fa-tags" aria-hidden="true"></i>
                                <spring:message
                                code="master.page.verEtiquetas"/></a>
                            </li>
                        </security:authorize>
                        <security:authorize access="hasAuthority('USUARIO')">
                            <li><a href="peticion/usuario/misPeticiones.do"><i class="fa fa-book"
                                                                               aria-hidden="true"></i>
                                <spring:message code="master.page.misPeticiones"/></a></li>
                        </security:authorize>
                        <security:authorize access="hasAuthority('PROFESIONAL')">
                            <li><a href="peticion/usuario/misPeticiones.do"><i class="fa fa-book"
                                                                               aria-hidden="true"></i>
                                <spring:message code="master.page.misPeticiones"/></a></li>
                            <li><a href="oferta/profesional/misOfertas.do"><i class="fa fa-briefcase"
                                                                              aria-hidden="true"></i>
                                <spring:message code="master.page.misOfertas"/></a></li>
                        </security:authorize>
                        <security:authorize access="hasAuthority('ADMIN')">
                            <li><a href="usuario/admin/listaUsuarios.do"><i class="fa fa-users" aria-hidden="true"></i>
                                <spring:message code="master.page.listaUsuarios"/></a></li>
                        </security:authorize>
                        <security:authorize access="isAnonymous()">
                            <li><a href="usuario/registro.do"><i class="fa fa-user-plus" aria-hidden="true"></i>
                                <spring:message code="master.page.signUp"/></a></li>
                            <li><a href="security/login.do"><i class="fa fa-sign-in" aria-hidden="true"></i>
                                <spring:message code="master.page.signIn"/></a></li>

                        </security:authorize>
                        <security:authorize access="isAuthenticated()">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                    class="fa fa-user-circle-o" aria-hidden="true"></i>
                                    <spring:message code="master.page.salute"/>
                                    <security:authentication property="principal.username"/>!</a>
                                <ul class="dropdown-menu box main-dp">
                                    <security:authorize access="hasAuthority('ADMIN')">
                                        <li>
                                            <a href="admin/perfil.do"><i class="fa fa-user" aria-hidden="true">
                                            </i> <spring:message code="master.page.verPerfil"/></a>
                                        </li>
                                    </security:authorize>
                                    <security:authorize access="hasAuthority('PROFESIONAL')">
                                        <li>
                                            <a href="profesional/perfil.do"><i class="fa fa-user" aria-hidden="true">
                                            </i> <spring:message code="master.page.verPerfil"/></a>
                                        </li>
                                    </security:authorize>
                                    <security:authorize access="hasAuthority('USUARIO')">
                                        <li>
                                            <a href="usuario/perfil.do"><i class="fa fa-user" aria-hidden="true">
                                            </i> <spring:message code="master.page.verPerfil"/></a>
                                        </li>
                                    </security:authorize>
                                    <li>
                                        <a href="logout"><i class="fa fa-sign-out" aria-hidden="true"></i>
                                            <spring:message code="master.page.salir"/></a>
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
<a href="#" onclick="signOut();">SALIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIR</a>

<script type="text/javascript">
    function signOut() {
        var auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(function () {
            console.log('User signed out.');
        });
    }
</script>


