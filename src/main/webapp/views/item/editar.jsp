<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <c:if test="${item.id == 0}">
                <h2 style="text-align: center"><spring:message code="item.nuevoItem"/></h2>
            </c:if>
            <c:if test="${item.id != 0}">
                <h2 style="text-align: center"><spring:message code="item.editarItem"/></h2>
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
                <form:form action="item/usuario/editar.do?peticionID=${peticion.id}" modelAttribute="item"
                           acceptCharset="UTF-8">
                    <form:hidden path="id"/>
                    <form:hidden path="version"/>
                    <form:hidden path="peticion"/>
                    <form:hidden path="ofertas"/>
                    <form:hidden path="estado"/>
                    <div
                        class="form-group ${errores.contains('nombre') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="item.nombre" var="nombre"/>
                        <form:label class="control-label" path="nombre">${nombre}</form:label>
                        <form:input class="form-control" path="nombre" placeholder="${nombre}"/>
                        <form:errors class="help-block" path="nombre"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('descripcion') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="item.descripcion" var="descripcion"/>
                        <form:label class="control-label" path="descripcion">${descripcion}</form:label>
                        <form:input class="form-control" path="descripcion" placeholder="${descripcion}"/>
                        <form:errors class="help-block" path="descripcion"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('presupuesto') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="item.presupuesto" var="presupuesto"/>
                        <form:label class="control-label" path="presupuesto">${presupuesto}</form:label>
                        <form:input type="number" min="1.0" max="10000.0" step="0.5" class="form-control"
                                    path="presupuesto" placeholder="${presupuesto}"/>
                        <form:errors class="help-block" path="presupuesto"/>
                    </div>
                    <div class="form-group ${errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="urlFotografia" var="urlFotografia"/>
                        <form:label class="control-label" path="foto">${urlFotografia}</form:label>
                        <form:input type="URL" class="form-control" path="foto" placeholder="URL"/>
                        <form:errors class="help-block" path="foto"/>
                    </div>
                    <div class="form-group text-center">
                        <br>
                        <a href="/peticion/ver.do?peticionID=${peticion.id}" class="btn btn-primary"><i
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
