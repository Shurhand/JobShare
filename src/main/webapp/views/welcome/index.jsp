<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="styles/custom.css" type="text/css">

<div class="jumbotron jumbotron-fluid jumbo-custom" style="background-image: url(/images/Plaza-España.jpg);">

    <div class="container text-center">
        <div class="col-md-10 col-md-offset-1">
            <div id="jumbo-header" class="jumbotron jumbotron-fluid jumbo-header">
                <h2 class="header1"><spring:message code="index.colabora"/></h2>
                <p class="header1"><spring:message code="index.solicitaYColabora"/></p>
            </div>
        </div>
    </div>

    <div class="jumbotron jumbotron-fluid" style="background-color: rgba(0,0,0,0.3); margin-top: 1%">
        <div class="container" style="text-align: center">
            <form:form action="peticion/buscar.do" method="get" modelAttribute="buscaForm" acceptCharset="UTF-8">
                <div class="row">
                    <div class="col-md-12">
                        <div class="col-md-3">
                            <div
                                class="form-group ${errores.contains('fechaCaducidad') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                                <spring:message code="buscaForm.fechaCaducidad" var="fechaCaducidad"/>
                                <form:label class="control-label blanco"
                                            path="fechaCaducidad">${fechaCaducidad}</form:label>
                                <div class="input-group date" id="datepicker1">
                                    <form:input pattern="\d{1,2}/\d{1,2}/\d{4}" class="form-control"
                                                path="fechaCaducidad"
                                                placeholder="dd/MM/yyyy"/>
                                    <div class="input-group-addon">
                                        <span class="fa fa-calendar"></span>
                                    </div>
                                </div>
                                <form:errors class="help-block" path="fechaCaducidad"/>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div
                                class="form-group ${errores.contains('presupuesto') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                                <spring:message code="buscaForm.presupuesto" var="presupuesto"/>
                                <form:label class="control-label blanco" path="presupuesto">${presupuesto}</form:label>
                                <form:input type="number" min="1.0" max="10000.0" step="0.5" class="form-control"
                                            path="presupuesto" placeholder="${presupuesto}"/>
                                <form:errors class="help-block" path="presupuesto"/>
                            </div>

                        </div>
                        <div class="col-md-3">
                            <div
                                class="form-group ${errores.contains('etiquetas') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                                <spring:message code="buscaForm.etiquetas" var="etiquetas"/>
                                <form:label class="control-label blanco" path="etiquetas">${etiquetas}</form:label>
                                <form:select path="etiquetas" class="form-control js-example-basic-multiple"
                                             multiple="multiple">
                                    <form:options items="${todasEtiquetas}" itemLabel="nombre" itemValue="id"/>
                                </form:select>
                                <form:errors class="help-block" path="etiquetas"/>
                            </div>
                        </div>

                        <div class="col-md-3">
                            <div
                                class="form-group ${errores.contains('provincia') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                                <spring:message code="buscaForm.provincia" var="provincia"/>
                                <form:label class="control-label blanco" path="provincia">${provincia}</form:label>
                                <form:input class="form-control"
                                            path="provincia" placeholder="${provincia}" onchange="this.form.submit();"/>
                                <form:errors class="help-block" path="provincia"/>
                            </div>
                        </div>

                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-4 col-md-offset-4">
                        <div class="input-group">
                            <spring:message code="buscaForm.search" var="search"/>
                            <form:input path="palabraClave" class="form-control input-lg" placeholder="${search}"/>
                            <span class="input-group-btn">
            <button type="submit" name="buscar" class="btn btn-lg btn-primary"><i
                class="fa fa-search"></i>
            <spring:message code="buscar"/>
            </button>
            </span>
                        </div>

                    </div>
                </div>
            </form:form>
        </div>
    </div>

</div>
<script type="text/javascript">
    $(function () {
        $('#datepicker1').datepicker({
            format: "dd/mm/yyyy",
            clearBtn: true,
            startDate: "0d",
            language: "es",
            todayHighlight: true,
            daysOfWeekHighlighted: "0",
        })
    });
</script>

<script type="text/javascript">
    $('.js-example-basic-multiple').select2();
</script>

