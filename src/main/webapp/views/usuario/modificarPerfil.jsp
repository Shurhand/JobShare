<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags" %>

<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <h2 style="text-align: center"><spring:message code="usuario.nuevo"/></h2>
            <div class="well">
                <c:if test="${!erroresCheck.isEmpty() && erroresCheck != null}">
                    <div class="alert alert-danger alert-dismissable alert-link oaerror danger-conjunto">
                        <button class="close" type="button" data-dismiss="alert" aria-hidden="true">×</button>
                        <c:forEach var="e" items="${erroresCheck}">
                            <spring:message code="${e}"/> <br>
                        </c:forEach>
                    </div>
                </c:if>
                <form:form action="usuario/modificarPerfil.do" modelAttribute="usuarioForm" acceptCharset="UTF-8">
                    <form:hidden path="DNI"/>
                    <form:hidden path="checkTerminos"/>
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
                    <div
                        class="form-group ${errores.contains('confirmarPassword') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="usuario.confirmar" var="confirmar"/>
                        <form:label class="control-label" path="confirmarPassword">${confirmar}</form:label>
                        <form:password class="form-control" path="confirmarPassword" placeholder="${confirmar}"/>
                        <form:errors class="help-block" path="confirmarPassword"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('nombre') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="usuario.nombre" var="nombre"/>
                        <form:label class="control-label" path="nombre">${nombre}</form:label>
                        <form:input class="form-control" path="nombre" placeholder="${nombre}"/>
                        <form:errors class="help-block" path="nombre"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('apellidos') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="usuario.apellidos" var="apellidos"/>
                        <form:label class="control-label" path="apellidos">${apellidos}</form:label>
                        <form:input class="form-control" path="apellidos" placeholder="${apellidos}"/>
                        <form:errors class="help-block" path="apellidos"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('email') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="usuario.email" var="email"/>
                        <form:label class="control-label" path="email">${email}</form:label>
                        <form:input type="email" class="form-control" path="email" placeholder="${email}"/>
                        <form:errors class="help-block" path="email"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('provincia') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="usuario.provincia" var="provincia"/>
                        <form:label class="control-label" path="provincia">${provincia}</form:label>
                        <form:input class="form-control" path="provincia" placeholder="${provincia}"/>
                        <form:errors class="help-block" path="provincia"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('cp') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="usuario.cp" var="cp"/>
                        <form:label class="control-label" path="cp">${cp}</form:label>
                        <form:input class="form-control" path="cp" placeholder="${cp}"/>
                        <form:errors class="help-block" path="cp"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('telefono') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="usuario.telefono" var="telefono"/>
                        <form:label class="control-label" path="telefono">${telefono}</form:label>
                        <form:input class="form-control" path="telefono" placeholder="${telefono}"/>
                        <form:errors class="help-block" path="telefono"/>
                    </div>
                    <div class="form-group ${errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="urlFotografia" var="urlFotografia"/>
                        <form:label class="control-label" path="foto">${urlFotografia}</form:label>
                        <form:input type="URL" class="form-control" path="foto" placeholder="URL"/>
                        <form:errors class="help-block" path="foto"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('descripcion') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="usuario.descripcion" var="descripcion"/>
                        <form:label class="control-label" path="descripcion">${descripcion}</form:label>
                        <form:input class="form-control" path="descripcion" placeholder="${descripcion}"/>
                        <form:errors class="help-block" path="descripcion"/>
                    </div>

                    <div class="form-group text-center">
                        <br>
                        <a href="/usuario/perfil.do" class="btn btn-primary"><i class="fa fa-arrow-left"></i>
                            <spring:message
                            code="volver"/></a>
                        <button type="submit" name="saveForm" class="btn btn-primary"><i class="fa fa-check"></i>
                            <spring:message code="guardar"/>
                        </button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
