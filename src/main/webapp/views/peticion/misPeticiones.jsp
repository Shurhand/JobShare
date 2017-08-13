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

<div class="container">
    <div class="row">
        <div class="col-md-3 search-filter" id="sidebar">
            <div class="col-md-12 well white">
                <form action="/seller/search/" method="GET">
                    <h2 class="text-center">Filtros</h2>
                    <hr/>
                    <h4>Ordenar por</h4>
                    <div class="radio">
                        <label>
                            <input type="radio" name="list_by" onchange="this.form.submit();" value="0">
                            Mejor puntuación media
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" name="list_by" onchange="this.form.submit();" value="1">
                            Nuevo
                        </label>
                    </div>
                    <hr/>
                    <h4>Cocina</h4>

                    <div class="radio">
                        <label>
                            <input type="radio" name="cuisine" onchange="this.form.submit();"
                                   value=Bocadillos/Sandwiches>
                            Bocadillos/Sandwiches
                        </label>
                    </div>

                    <div class="radio">
                        <label>
                            <input type="radio" name="cuisine" onchange="this.form.submit();" value=Pizzas>
                            Pizzas
                        </label>
                    </div>

                    <div class="radio">
                        <label>
                            <input type="radio" name="cuisine" onchange="this.form.submit();" value=Pasta>
                            Pasta
                        </label>
                    </div>

                    <div class="radio">
                        <label>
                            <input type="radio" name="cuisine" onchange="this.form.submit();" value=Kebab>
                            Kebab
                        </label>
                    </div>

                    <div class="radio">
                        <label>
                            <input type="radio" name="cuisine" onchange="this.form.submit();" value=Bolleria>
                            Bolleria
                        </label>
                    </div>

                    <input type="hidden" name="postcode" value="41012"/>
                    <button onclick="#" class="form-control"><span class="glyphicon glyphicon-refresh"></span>
                        Actualizar
                    </button>
                </form>
            </div>
        </div>
        <%--<h4>${peticiones.size()} <spring:message code="peticion.peticionesRealizadas"/></h4>--%>

        <div class="col-md-9">
            <div class="row">
                <div class="col-md-9">
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
</div>