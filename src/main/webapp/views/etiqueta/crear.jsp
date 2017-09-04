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
            <h2 style="text-align: center"><spring:message code="etiqueta.nueva"/></h2>
            <div class="well">
                <c:if test="${!erroresCheck.isEmpty() && erroresCheck != null}">
                    <div class="alert alert-danger alert-dismissable alert-link oaerror danger-conjunto">
                        <button class="close" type="button" data-dismiss="alert" aria-hidden="true">×</button>
                        <c:forEach var="e" items="${erroresCheck}">
                            <spring:message code="${e}"/> <br>
                        </c:forEach>
                    </div>
                </c:if>
                <form:form action="etiqueta/actor/crear.do" modelAttribute="etiqueta" acceptCharset="UTF-8">
                    <form:hidden path="peticiones"/>
                    <form:hidden path="activada"/>
                    <div
                        class="form-group ${errores.contains('nombre') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="etiqueta.nombre" var="nombre"/>
                        <form:label class="control-label" path="nombre">${nombre}</form:label>
                        <form:input class="form-control" path="nombre" placeholder="${nombre}"/>
                        <form:errors class="help-block" path="nombre"/>
                    </div>
                    <div class="form-group text-center">
                        <br>
                        <a href="etiqueta/lista.do" class="btn btn-primary"><i class="fa fa-arrow-left"></i>
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
