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
            <c:if test="${estudio.id == 0}">
                <h2 style="text-align: center"><spring:message code="estudio.nuevo"/></h2>
            </c:if>
            <c:if test="${estudio.id != 0}">
                <h2 style="text-align: center"><spring:message code="estudio.editar"/></h2>
            </c:if>
            <div class="well">
                <c:if test="${!erroresCheck.isEmpty() && erroresCheck != null}">
                    <div class="alert alert-danger alert-dismissable alert-link">
                        <button class="close" type="button" data-dismiss="alert" aria-hidden="true">×</button>
                        <c:forEach var="e" items="${erroresCheck}">
                            <spring:message code="${e}"/> <br>
                        </c:forEach>
                    </div>
                </c:if>
                <form:form action="estudio/profesional/editar.do" modelAttribute="estudio" acceptCharset="UTF-8">
                    <form:hidden path="id"/>
                    <form:hidden path="version"/>
                    <form:hidden path="profesional"/>
                    <div
                        class="form-group ${errores.contains('titulacion') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="estudio.titulacion" var="titulacion"/>
                        <form:label class="control-label" path="titulacion">${titulacion}</form:label>
                        <form:input class="form-control" path="titulacion" placeholder="${titulacion}"/>
                        <form:errors class="help-block" path="titulacion"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('centro') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="estudio.centro" var="centro"/>
                        <form:label class="control-label" path="centro">${centro}</form:label>
                        <form:input class="form-control" path="centro" placeholder="${centro}"/>
                        <form:errors class="help-block" path="centro"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('fechaInicio') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="estudio.fechaInicio" var="fechaInicio"/>
                        <form:label class="control-label" path="fechaInicio">${fechaInicio}</form:label>
                        <div class="input-group date" id="datepicker1">
                            <form:input pattern="\d{1,2}/\d{1,2}/\d{4}" class="form-control" path="fechaInicio"
                                        placeholder="dd/MM/yyyy"/>
                            <div class="input-group-addon">
                                <span class="fa fa-calendar"></span>
                            </div>
                        </div>
                        <form:errors class="help-block" path="fechaInicio"/>
                    </div>
                    <div
                        class="form-group ${errores.contains('fechaFin') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="estudio.fechaFin" var="fechaFin"/>
                        <form:label class="control-label" path="fechaFin">${fechaFin}</form:label>
                        <div class="input-group date" id="datepicker2">
                            <form:input pattern="\d{1,2}/\d{1,2}/\d{4}" class="form-control" path="fechaFin"
                                        placeholder="dd/MM/yyyy"/>
                            <div class="input-group-addon">
                                <span class="fa fa-calendar"></span>
                            </div>
                        </div>
                        <form:errors class="help-block" path="fechaFin"/>
                    </div>

                    <div class="form-group text-center">
                        <br>
                        <a href="/profesional/perfil.do" class="btn btn-primary"><i
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
<script type="text/javascript">
    $(function () {
        $('#datepicker1').datepicker({
            format: "dd/mm/yyyy",
            clearBtn: true,
            endDate: "0d",
            todayHighlight: true,
            daysOfWeekHighlighted: "0",
        })
    });
    $(function () {
        $('#datepicker2').datepicker({
            format: "dd/mm/yyyy",
            clearBtn: true,
            endDate: "0d",
            todayHighlight: true,
            daysOfWeekHighlighted: "0",
        })
    });
</script>