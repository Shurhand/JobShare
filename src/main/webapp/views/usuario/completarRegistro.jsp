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
            <h2 style="text-align: center"><spring:message code="usuario.completarRegistro"/></h2>
            <div class="well">
                <c:if test="${!erroresCheck.isEmpty() && erroresCheck != null}">
                    <div class="alert alert-danger alert-dismissable alert-link oaerror danger-conjunto">
                        <button class="close" type="button" data-dismiss="alert" aria-hidden="true">×</button>
                        <c:forEach var="e" items="${erroresCheck}">
                            <spring:message code="${e}"/> <br>
                        </c:forEach>
                    </div>
                </c:if>
                <form:form action="usuario/registroGoogle.do" modelAttribute="googleForm" acceptCharset="UTF-8">
                    <form:hidden path="subject"/>
                    <form:hidden path="givenName"/>
                    <form:hidden path="familyName"/>
                    <form:hidden path="pictureUrl"/>
                    <form:hidden path="email"/>
                    <form:hidden path="emailVerified"/>
                    <form:hidden path="idTokenString"/>

                    <div
                        class="form-group ${errores.contains('DNI') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="usuario.dni" var="dni"/>
                        <form:label class="control-label" path="DNI">${dni}</form:label>
                        <form:input class="form-control" path="DNI" placeholder="${dni}"/>
                        <form:errors class="help-block" path="DNI"/>
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
                    <div
                        class="form-group ${errores.contains('descripcion') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="usuario.descripcion" var="descripcion"/>
                        <form:label class="control-label" path="descripcion">${descripcion}</form:label>
                        <form:input class="form-control" path="descripcion" placeholder="${descripcion}"/>
                        <form:errors class="help-block" path="descripcion"/>
                    </div>

                    <div class="form-group text-center">
                        <br>
                        <a href="/" class="btn btn-primary"><i class="fa fa-arrow-left"></i> <spring:message
                            code="volver"/></a>
                        <button type="submit" name="googleForm" class="btn btn-primary"><i class="fa fa-check"></i>
                            <spring:message code="guardar"/>
                        </button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
