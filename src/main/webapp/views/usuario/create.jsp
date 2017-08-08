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
            <h2 style="text-align: center">Registro de nuevo usuario</h2>
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
                        <form:label class="control-label" path="username">Usuario</form:label>
                        <form:input class="form-control" path="username" placeholder="Usuario"/>
                        <form:errors class="help-block" path="username"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('password') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <form:label class="control-label" path="password">Contraseña</form:label>
                        <form:password class="form-control" path="password" placeholder="Contraseña"/>
                        <form:errors class="help-block" path="password"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('confirmarPassword') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <form:label class="control-label" path="confirmarPassword">Confirmar contraseña</form:label>
                        <form:password class="form-control" path="confirmarPassword"
                                       placeholder="Confirmar contraseña"/>
                        <form:errors class="help-block" path="confirmarPassword"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('nombre') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <form:label class="control-label" path="nombre">Nombre</form:label>
                        <form:input class="form-control" path="nombre" placeholder="Nombre"/>
                        <form:errors class="help-block" path="nombre"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('apellidos') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <form:label class="control-label" path="apellidos">Apellidos</form:label>
                        <form:input class="form-control" path="apellidos" placeholder="Apellidos"/>
                        <form:errors class="help-block" path="apellidos"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('email') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <form:label class="control-label" path="email">Email</form:label>
                        <form:input type="email" class="form-control" path="email" placeholder="Email"/>
                        <form:errors class="help-block" path="email"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('DNI') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <form:label class="control-label" path="DNI">DNI</form:label>
                        <form:input class="form-control" path="DNI" placeholder="DNI"/>
                        <form:errors class="help-block" path="DNI"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('provincia') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <form:label class="control-label" path="provincia">Provincia</form:label>
                        <form:input class="form-control" path="provincia" placeholder="Provincia"/>
                        <form:errors class="help-block" path="provincia"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('cp') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <form:label class="control-label" path="cp">Código postal</form:label>
                        <form:input class="form-control" path="cp" placeholder="Código postal"/>
                        <form:errors class="help-block" path="cp"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('telefono') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <form:label class="control-label" path="telefono">Teléfono</form:label>
                        <form:input class="form-control" path="telefono" placeholder="Teléfono"/>
                        <form:errors class="help-block" path="telefono"/>
                    </div>
                    <div class="form-group">
                        <form:label class="control-label" path="foto">URL de fotografía</form:label>
                        <form:input type="URL" class="form-control" path="foto" placeholder="URL"/>
                        <form:errors class="help-block" path="foto"/>
                    </div>

                    <div
                        class="form-group ${errores.contains('checkTerminos') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <div class="checkbox">
                            <form:label path="checkTerminos">
                                <form:checkbox path="checkTerminos"/> Acepto los términos y condiciones
                            </form:label>
                        </div>
                    </div>
                    <div class="form-group text-center">
                        <br>
                        <a href="/" class="btn btn-primary"><i class="fa fa-arrow-left"></i> Atrás</a>
                        <button type="submit" name="saveForm" class="btn btn-primary"><i class="fa fa-check"></i>
                            Guardar
                        </button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
