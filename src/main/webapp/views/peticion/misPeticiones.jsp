<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.15/js/dataTables.bootstrap.min.js"></script>
<script src="https://sdn.datatables.net/responsive/2.1.1/js/dataTables.responsive.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.1.1/js/responsive.bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.1.1/css/responsive.bootstrap.min.css">

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <br/>
            <form class="form-inline well white" style="text-align:center;">
                <div class="form-group">
                    <label style="font-weight: normal;" for="postcode">Resultados para: </label>
                    <input id="postcode" name="postcode" type="text" class="form-control input-md" value="41012"
                           required>
                </div>
                <button onclick="#" class="form-control"><span class="glyphicon glyphicon-search"></span> Buscar
                </button>
            </form>
        </div>
    </div>

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
        <c:forEach var="peticion" items="${peticiones}">
            <div class="col-md-9">
                <div id="map"></div>
                <div class="row">
                    <div class="col-md-12">
                        <h4>${peticiones.size()} <spring:message code="peticion.peticionesRealizadas"/></h4>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">

                        <div class="item-search well white">
                            <div class="row">
                                <div class="col-xs-6 col-md-2 avatar-wrapper text-center">

                                    <img alt="" height="100px" width="100px" class="img-circle center-block"
                                         src="${peticion.foto}">

                                </div>
                                <div class="col-xs-6 col-md-8 info-wrapper">
                                    <strong><h4>${peticion.titulo}</h4></strong>
                                    <p>
                                            ${peticion.descripcion}
                                    </p>

                                </div>
                                <div class="col-md-2 col-xs-12 buttons-wrapper text-center">
                                    <button onclick="location.replace('/seller/menu/1/');" class="form-control"><span
                                        class="glyphicon glyphicon-file"></span> Menu
                                    </button>
                                    <p class="brief-text">
                                        <span class="glyphicon glyphicon-time"></span> TODA LA SEMANA · 9-14:30
                                    </p>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>