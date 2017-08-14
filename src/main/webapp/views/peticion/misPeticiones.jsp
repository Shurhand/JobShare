<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.15/js/dataTables.bootstrap.min.js"></script>
<script src="https://sdn.datatables.net/responsive/2.1.1/js/dataTables.responsive.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.1.1/js/responsive.bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.1.1/css/responsive.bootstrap.min.css">
<br><br>
<div class="container">
    <form:form action="${action}" method="get" modelAttribute="buscaForm" acceptCharset="UTF-8">
        <div class="row">
            <div class="col-md-3 search-filter" id="sidebar">
                <div class="col-md-12 well white">

                    <h2 class="text-center"><spring:message code="peticion.filtros"/></h2>
                    <hr/>
                    <h4><spring:message code="peticion.ordernarPor"/></h4>
                    <div class="radio">
                        <label>
                            <form:radiobutton onchange="this.form.submit();" path="opcionRadio" value="1"/>
                            <spring:message code="peticion.mayorPresupuesto"/>
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <form:radiobutton onchange="this.form.submit();" path="opcionRadio" value="2"/>
                            <spring:message code="peticion.menorPresupuesto"/>
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <form:radiobutton onchange="this.form.submit();" path="opcionRadio" value="3"/>
                            <spring:message code="peticion.mayorFechaCaducidad"/>
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <form:radiobutton onchange="this.form.submit();" path="opcionRadio" value="4"/>
                            <spring:message code="peticion.menorFechaCaducidad"/>
                        </label>
                    </div>
                    <hr/>
                    <h4><spring:message code="peticion.etiquetas"/></h4>
                    <div
                        class="form-group ${errores.contains('etiquetas') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="buscaForm.etiquetas" var="etiquetas"/>

                        <form:select path="etiquetas" class="form-control js-example-basic-multiple"
                                     multiple="multiple" onchange="this.form.submit();">
                            <form:options items="${todasEtiquetas}" itemLabel="nombre" itemValue="id"/>
                        </form:select>
                        <form:errors class="help-block" path="etiquetas"/>
                    </div>
                    <hr/>
                    <h4><spring:message code="peticion.fechaCaducidad"/></h4>
                    <div
                        class="form-group ${errores.contains('fechaCaducidad') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="buscaForm.fechaCaducidad" var="fechaCaducidad"/>

                        <div class="input-group date" id="datepicker1">
                            <form:input pattern="\d{1,2}/\d{1,2}/\d{4}" class="form-control"
                                        path="fechaCaducidad"
                                        placeholder="dd/MM/yyyy" onchange="this.form.submit();"/>
                            <div class="input-group-addon">
                                <span class="fa fa-calendar"></span>
                            </div>
                        </div>
                        <form:errors class="help-block" path="fechaCaducidad"/>
                    </div>
                    <hr/>
                    <h4><spring:message code="buscaForm.presupuestoMaximoPorItem"/></h4>
                    <div
                        class="form-group ${errores.contains('presupuesto') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="buscaForm.presupuesto" var="presupuesto"/>

                        <form:input type="number" min="1.0" max="10000.0" step="0.5" class="form-control"
                                    path="presupuesto" placeholder="${presupuesto}" onchange="this.form.submit();"/>
                        <form:errors class="help-block" path="presupuesto"/>
                    </div>
                    <hr/>
                    <h4><spring:message code="buscaForm.provincia"/></h4>
                    <div
                        class="form-group ${errores.contains('provincia') ? 'has-error has-feedback' : errores != null ? 'has-success has-feedback' : ''}">
                        <spring:message code="buscaForm.provincia" var="provincia"/>

                        <form:input class="form-control"
                                    path="provincia" placeholder="${provincia}"/>
                        <form:errors class="help-block" path="provincia"/>
                    </div>
                    <br>
                    <button onclick="clearRadio()" class="form-control"><span
                        class="glyphicon glyphicon-refresh"></span> <spring:message code="peticion.limpiarRadio"/>
                    </button>

                </div>
            </div>
                <%--<h4>${peticiones.size()} <spring:message code="peticion.peticionesRealizadas"/></h4>--%>

            <div class="col-md-9">
                <div class="row">
                    <div class="col-md-9">
                        <div class="input-group">
                            <spring:message code="buscaForm.search" var="search"/>
                            <form:input path="palabraClave" class="form-control input" placeholder="${search}"/>
                            <span class="input-group-btn">
                                <button type="submit" name="buscar" class="btn btn btn-primary"><i
                                    class="fa fa-search"></i>
                                    <spring:message code="buscar"/>
                              </button>
                            </span>
                        </div>
                        <a href="/peticion/usuario/misPeticiones.do"> <spring:message
                            code="peticiones.activas"/> </a>|
                        <a href="/peticion/usuario/misPeticionesCaducadas.do"> <spring:message
                            code="peticiones.caducadas"/> </a>
                    </div>
                    <div class="col-md-3 text-right">
                        <a href="/peticion/usuario/crear.do" class="btn btn-primary" role="button"> <spring:message
                            code="peticion.nueva"/> </a>
                    </div>
                </div>

                <c:forEach var="peticion" items="${peticiones}">

                    <div class="row">
                        <div class="col-md-12">
                            <div class="item-search well white">
                                <div class="row">
                                    <div class="col-xs-4 col-md-2 avatar-wrapper text-center">
                                        <c:if test="${peticion.foto != null}">
                                            <img alt="" height="100px" width="100px" class="img-circle center-block"
                                                 src="${peticion.foto}">
                                        </c:if>
                                        <c:if test="${peticion.foto == null}">
                                            <br>
                                            <h2><spring:message code="peticion.sinFoto"/></h2>
                                        </c:if>
                                        <c:if test="${peticion.items.isEmpty()}">
                                            <div class="error-notice">
                                                <div class="oaerror-small warning">
                                                    <spring:message code="peticion.sinItems"/>
                                                </div>
                                            </div>
                                        </c:if>
                                    </div>

                                    <div class="col-xs-8 col-md-8 info-wrapper">
                                        <a href="peticion/ver.do?peticionID=${peticion.id}"> <b><h4>${peticion.titulo}
                                            &nbsp;-&nbsp; ${peticion.provincia} </h4></b></a>
                                        <h5>${peticion.descripcion}</h5>
                                    </div>
                                    <div class="col-md-2 col-xs-12 buttons-wrapper text-center">
                                        <spring:message code="peticion.fechaCaducidad"/>
                                        <tags:localDate date="${peticion.fechaCaducidad}" pattern="dd/MM/yyyy"/>
                                        <h4><spring:message code="peticion.presupuesto"/></h4>
                                        <p>${peticion.getPresupuestoTotal()} €</p>

                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </form:form>
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

<script type="text/javascript">
    $('form input').change(function () {
        $(this).closest('form').submit();
    });
</script>
<script type="text/javascript">
    function clearRadio() {
        $('input[type=radio]').prop('checked', false);
    }
</script>