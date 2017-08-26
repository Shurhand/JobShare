<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>

<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <c:if test="${oferta.id == 0}">
                <h2 style="text-align: center"><spring:message code="oferta.nuevaOferta"/></h2>
            </c:if>
            <c:if test="${oferta.id != 0}">
                <h2 style="text-align: center"><spring:message code="oferta.editarOferta"/></h2>
            </c:if>
            <div class="well">
                <c:if test="${!erroresCheck.isEmpty() && erroresCheck != null}">
                    <div class="alert alert-danger alert-dismissable alert-link oaerror danger-conjunto">
                        <button class="close" type="button" data-dismiss="alert" aria-hidden="true">×</button>
                        <c:forEach var="e" items="${erroresCheck}">
                            <spring:message code="${e}"/> <br>
                        </c:forEach>
                    </div>
                </c:if>
                <form:form action="oferta/profesional/editar.do" modelAttribute="oferta" acceptCharset="UTF-8">
                    <form:hidden path="id"/>
                    <form:hidden path="version"/>
                    <form:hidden path="profesional"/>
                    <form:hidden path="estado"/>
                    <form:hidden path="item"/>
                    <form:hidden path="fechaCreacion"/>

                    <div class="error-notice">
                        <div class="oaerror warning">
                            <strong><spring:message code="header.informacion"/>: </strong> <spring:message
                            code="oferta.noSobrepasarMaximo"/> ${item.presupuesto} €
                        </div>
                    </div>
                    <div
                        class="form-group ${errores.contains('precio') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="oferta.precio" var="precio"/>
                        <form:label class="control-label" path="precio">${precio}</form:label>
                        <form:input type="number" min="1.0" max="${item.presupuesto}" step="0.5" class="form-control"
                                    path="precio" placeholder="${precio}"/>
                        <form:errors class="help-block" path="precio"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('comentario') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="oferta.comentario" var="comentario"/>
                        <form:label class="control-label" path="comentario">${comentario}</form:label>
                        <form:input class="form-control" path="comentario" placeholder="${comentario}"/>
                        <form:errors class="help-block" path="comentario"/>
                    </div>
                    <div class="form-group text-center">
                        <br>
                        <a href="peticion/ver.do?peticionID=${item.peticion.id}" class="btn btn-primary"><i
                            class="fa fa-arrow-left"></i> <spring:message code="volver"/></a>
                        <button type="submit" name="save" class="btn btn-primary"><i class="fa fa-check"></i>
                            <spring:message code="guardar"/>
                        </button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>


