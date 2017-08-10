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
            <h2 style="text-align: center"><spring:message code="peticion.nueva" /></h2>
            <div class="well">
                <c:if test="${!erroresCheck.isEmpty()}">
                    <div class="alert alert-danger alert-dismissable alert-link">
                        <button class="close" type="button" data-dismiss="alert" aria-hidden="true">×</button>
                        <c:forEach var="e" items="${erroresCheck}">
                            <spring:message code="${e}"/> <br>
                        </c:forEach>
                    </div>
                </c:if>
                <form:form action="peticion/usuario/create.do" modelAttribute="peticion" acceptCharset="UTF-8">
                    <form:hidden path="id"/>
                    <form:hidden path="version"/>
                    <form:hidden path="usuario"/>
                    <form:hidden path="estado"/>
                    <form:hidden path="fechaCreacion"/>
                    <div
                        class="form-group ${errores.contains('titulo') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="peticion.titulo" var="peticion.titulo" />
                        <form:label class="control-label" path="titulo">${peticion.titulo}</form:label>
                        <form:input class="form-control" path="titulo" placeholder="${peticion.titulo}"/>
                        <form:errors class="help-block" path="titulo"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('descripcion') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="peticion.descripcion" var="peticion.descripcion" />
                        <form:label class="control-label" path="descripcion">${peticion.descripcion}</form:label>
                        <form:input class="form-control" path="descripcion" placeholder="${peticion.descripcion}"/>
                        <form:errors class="help-block" path="descripcion"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('fechaCaducidad') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="peticion.fechaCaducidad" var="peticion.fechaCaducidad" />
                        <form:label class="control-label" path="fechaCaducidad">${peticion.fechaCaducidad}</form:label>
                        <form:input class="form-control" path="fechaCaducidad" placeholder="${peticion.fechaCaducidad}"/>
                        <form:errors class="help-block" path="fechaCaducidad"/>
                    </div>
                    <div class="form-group ${errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="urlFotografia" var="urlFotografia" />
                        <form:label class="control-label" path="foto">${urlFotografia}</form:label>
                        <form:input type="URL" class="form-control" path="foto" placeholder="URL"/>
                        <form:errors class="help-block" path="foto"/>
                    </div>

                    <div class="form-group text-center">
                        <br>
                        <a href="peticion/usuario/misPeticiones.do" class="btn btn-primary"><i
                            class="fa fa-arrow-left"></i> <spring:message code="volver"/></a>
                        <button type="submit" name="save" class="btn btn-primary"><i class="fa fa-check"></i>
                            <spring:message code="guardar" />
                        </button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>