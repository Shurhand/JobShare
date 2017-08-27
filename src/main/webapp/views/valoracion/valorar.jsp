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
            <h2 style="text-align: center"><spring:message code="valoracion.nueva"/></h2>
            <div class="well">
                <c:if test="${!erroresCheck.isEmpty() && erroresCheck != null}">
                    <div class="alert alert-danger alert-dismissable alert-link oaerror danger-conjunto">
                        <button class="close" type="button" data-dismiss="alert" aria-hidden="true">×</button>
                        <c:forEach var="e" items="${erroresCheck}">
                            <spring:message code="${e}"/> <br>
                        </c:forEach>
                    </div>
                </c:if>
                <form:form action="valoracion/usuario/valorar.do" modelAttribute="valoracion" acceptCharset="UTF-8">
                    <form:hidden path="id"/>
                    <form:hidden path="version"/>
                    <form:hidden path="fechaCreacion"/>
                    <form:hidden path="usuario"/>
                    <form:hidden path="oferta"/>
                    <div
                        class="form-group ${errores.contains('puntuacion') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="valoracion.puntuacion" var="puntuacion"/>
                        <form:label class="control-label" path="puntuacion">${puntuacion}</form:label>
                        <form:input type="number" max="5.0" min="1.0" step="0.5" class="form-control" path="puntuacion"
                                    placeholder="${puntuacion}"/>
                        <form:errors class="help-block" path="puntuacion"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('comentario') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="valoracion.comentario" var="comentario"/>
                        <form:label class="control-label" path="comentario">${comentario}</form:label>
                        <form:input class="form-control" path="comentario" placeholder="${comentario}"/>
                        <form:errors class="help-block" path="comentario"/>
                    </div>
                    <div class="form-group text-center">
                        <br>
                        <a href="peticion/ver.do?peticionID=${valoracion.oferta.item.peticion.id}"
                           class="btn btn-primary"><i class="fa fa-arrow-left"></i>
                            <spring:message
                                code="volver"/></a>
                        <button type="submit" name="save" class="btn btn-primary"><i class="fa fa-check"></i>
                            <spring:message code="guardar"/>
                        </button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
