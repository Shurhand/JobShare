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
                <c:if test="${!erroresCheck.isEmpty()}">
                    <div class="alert alert-danger alert-dismissable alert-link">
                        <button class="close" type="button" data-dismiss="alert" aria-hidden="true">×</button>
                        <c:forEach var="e" items="${erroresCheck}">
                            <spring:message code="${e}"/> <br>
                        </c:forEach>
                    </div>
                </c:if>
                <form:form action="usuario/registro.do" modelAttribute="usuarioForm" acceptCharset="UTF-8">

                    <div
                        class="form-group ${errores.contains('username') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="usuario.usuario" var="usuario.usuario" />
                        <form:label class="control-label" path="username">${usuario.usuario}</form:label>
                        <form:input class="form-control" path="username" placeholder="${usuario.usuario}"/>
                        <form:errors class="help-block" path="username"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('password') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="usuario.contraseña" var="usuario.contraseña" />
                        <form:label class="control-label" path="password">${usuario.contraseña}</form:label>
                        <form:password class="form-control" path="password" placeholder="${usuario.contraseña}"/>
                        <form:errors class="help-block" path="password"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('confirmarPassword') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="usuario.confirmar" var="usuario.confirmar" />
                        <form:label class="control-label" path="confirmarPassword">${usuario.confirmar}</form:label>
                        <form:password class="form-control" path="confirmarPassword" placeholder="${usuario.confirmar}"/>
                        <form:errors class="help-block" path="confirmarPassword"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('nombre') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="usuario.nombre" var="usuario.nombre" />
                        <form:label class="control-label" path="nombre">${usuario.nombre}</form:label>
                        <form:input class="form-control" path="nombre" placeholder="${usuario.nombre}"/>
                        <form:errors class="help-block" path="nombre"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('apellidos') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="usuario.apellidos" var="usuario.apellidos" />
                        <form:label class="control-label" path="apellidos">${usuario.apellidos}</form:label>
                        <form:input class="form-control" path="apellidos" placeholder="${usuario.apellidos}"/>
                        <form:errors class="help-block" path="apellidos"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('email') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="usuario.email" var="usuario.email" />
                        <form:label class="control-label" path="email">${usuario.email}</form:label>
                        <form:input type="email" class="form-control" path="email" placeholder="${usuario.email}"/>
                        <form:errors class="help-block" path="email"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('DNI') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="usuario.dni" var="usuario.dni" />
                        <form:label class="control-label" path="DNI">${usuario.dni}</form:label>
                        <form:input class="form-control" path="DNI" placeholder="${usuario.dni}"/>
                        <form:errors class="help-block" path="DNI"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('provincia') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="usuario.provincia" var="usuario.provincia" />
                        <form:label class="control-label" path="provincia">${usuario.provincia}</form:label>
                        <form:input class="form-control" path="provincia" placeholder="${usuario.provincia}"/>
                        <form:errors class="help-block" path="provincia"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('cp') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="usuario.cp" var="usuario.cp" />
                        <form:label class="control-label" path="cp">${usuario.cp}</form:label>
                        <form:input class="form-control" path="cp" placeholder="${usuario.cp}"/>
                        <form:errors class="help-block" path="cp"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('telefono') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="usuario.telefono" var="usuario.telefono" />
                        <form:label class="control-label" path="telefono">${usuario.telefono}</form:label>
                        <form:input class="form-control" path="telefono" placeholder="${usuario.telefono}"/>
                        <form:errors class="help-block" path="telefono"/>
                    </div>
                    <div class="form-group ${errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="urlFotografia" var="urlFotografia" />
                        <form:label class="control-label" path="foto">${urlFotografia}</form:label>
                        <form:input type="URL" class="form-control" path="foto" placeholder="URL"/>
                        <form:errors class="help-block" path="foto"/>
                    </div>

                    <div
                        class="form-group ${errores.contains('checkTerminos') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <div class="checkbox">
                            <form:label path="checkTerminos">
                                <form:checkbox path="checkTerminos"/> <spring:message code="usuario.terminos" />
                            </form:label>
                        </div>
                    </div>
                    <div class="form-group text-center">
                        <br>
                        <a href="/" class="btn btn-primary"><i class="fa fa-arrow-left"></i> <spring:message code="volver" />"</a>
                        <button type="submit" name="save" class="btn btn-primary"><i class="fa fa-check"></i>
                            <spring:message code="guardar" />
                        </button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
