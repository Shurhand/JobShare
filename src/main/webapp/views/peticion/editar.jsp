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
            <c:if test="${peticion.id == 0}">
                <h2 style="text-align: center"><spring:message code="peticion.nueva"/></h2>
            </c:if>
            <c:if test="${peticion.id != 0}">
                <h2 style="text-align: center"><spring:message code="peticion.editar"/></h2>
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
                <form:form action="peticion/usuario/editar.do" modelAttribute="peticion" acceptCharset="UTF-8">
                    <form:hidden path="id"/>
                    <form:hidden path="version"/>
                    <form:hidden path="usuario"/>
                    <form:hidden path="estado"/>
                    <form:hidden path="items"/>
                    <form:hidden path="etiquetas"/>
                    <form:hidden path="fechaCreacion"/>
                    <div
                        class="form-group ${errores.contains('titulo') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="peticion.titulo" var="titulo"/>
                        <form:label class="control-label" path="titulo">${titulo}</form:label>
                        <form:input class="form-control" path="titulo" placeholder="${titulo}"/>
                        <form:errors class="help-block" path="titulo"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('descripcion') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="peticion.descripcion" var="descripcion"/>
                        <form:label class="control-label" path="descripcion">${descripcion}</form:label>
                        <form:input class="form-control" path="descripcion" placeholder="${descripcion}"/>
                        <form:errors class="help-block" path="descripcion"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('provincia') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="peticion.provincia" var="provincia"/>
                        <form:label class="control-label" path="provincia">${provincia}</form:label>
                        <form:input class="form-control" path="provincia" placeholder="${provincia}"/>
                        <form:errors class="help-block" path="provincia"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('fechaCaducidad') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="peticion.fechaCaducidad" var="fechaCaducidad"/>
                        <form:label class="control-label" path="fechaCaducidad">${fechaCaducidad}</form:label>
                        <div class="input-group date" id="datepicker1">
                            <form:input pattern="\d{1,2}/\d{1,2}/\d{4}" class="form-control" path="fechaCaducidad"
                                        placeholder="dd/MM/yyyy"/>
                            <div class="input-group-addon">
                                <span class="fa fa-calendar"></span>
                            </div>
                        </div>
                        <form:errors class="help-block" path="fechaCaducidad"/>
                    </div>
                    <div class="form-group ${errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="urlFotografia" var="urlFotografia" />
                        <form:label class="control-label" path="foto">${urlFotografia}</form:label>
                        <form:input type="URL" class="form-control" path="foto" placeholder="URL"/>
                        <form:errors class="help-block" path="foto"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('etiquetas') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="peticion.etiquetas" var="etiquetas"/>
                        <form:label class="control-label" path="etiquetas">${etiquetas}</form:label>
                        <form:select path="etiquetas" class="form-control js-example-basic-multiple"
                                     multiple="multiple">
                            <form:options items="${todasEtiquetas}" itemLabel="nombre" itemValue="id"/>
                        </form:select>
                            <%--<form:input class="form-control" path="etiquetas" placeholder="${etiquetas}"/>--%>

                        <form:errors class="help-block" path="etiquetas"/>
                    </div>
                    <div class="error-notice">
                        <div class="oaerror warning">
                            <strong><spring:message code="header.informacion"/>: </strong> <spring:message
                            code="peticion.informacionItems"/>
                        </div>
                    </div>
                    <div class="form-group text-center">
                        <br>
                        <a href="peticion/usuario/buscarMisPeticiones.do" class="btn btn-primary"><i
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

<script type="text/javascript">
    $(function () {
        $('#datepicker1').datepicker({
            format: "dd/mm/yyyy",
            clearBtn: true,
            startDate: "0",
            todayHighlight: true,
            daysOfWeekHighlighted: "0",
        })
    });
</script>

<script type="text/javascript">
    $('.js-example-basic-multiple').select2(
        {width: '100%'})
    ;
</script>
