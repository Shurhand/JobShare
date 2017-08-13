<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.15/js/dataTables.bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.1.1/js/dataTables.responsive.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.1.1/js/responsive.bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.1.1/css/responsive.bootstrap.min.css">

<div class="container">
    <div class="row well white">
        <div class="col-md-10 col-md-offset-1">
            <h2 class="col-md-push-2" style="text-align: center">${peticion.titulo}
                <c:if test="${actorAutenticado.peticiones.contains(peticion)}">
                <a href="peticion/usuario/editar.do?peticionID=${peticion.id}" class="btn btn-info" role="button">
                    <spring:message
                        code="peticion.modificarPeticion"/> </a>
                <a href="item/usuario/crear.do?peticionID=${peticion.id}" class="btn btn-primary" role="button">
                    <spring:message
                        code="peticion.addItem"/> </a>
                </c:if></h2>
            <br>
            <div class="row">
                <div class="col-md-3">
                    <c:if test="${usuario.foto == null}">
                        <br>
                        <h2><spring:message code="actor.sinFoto"/></h2>
                    </c:if>
                    <c:if test="${usuario.foto != null}">
                        <IMG src="${usuario.foto}"
                             class="img-circle col-md-offset-1 col-xs-offset-1 col-sm-offset-1"
                             width="125"
                             height="125">
                    </c:if>
                </div>
                <div class="col-md-3">
                    <c:if test="${peticion.foto == null}">
                        <br>
                        <h2><spring:message code="actor.sinFoto"/></h2>
                    </c:if>
                    <c:if test="${peticion.foto != null}">
                        <IMG src="${peticion.foto}"
                             class="col-md-offset-1 col-xs-offset-1 col-sm-offset-1"
                             width="125"
                             height="125">
                    </c:if>
                </div>

                <div class="col-md-6">
                    <c:forEach var="etiqueta" items="${peticion.etiquetas}">
                        <b class="etiquetas">#<c:out value="${etiqueta.nombre}"/></b>
                    </c:forEach>
                    <p>${peticion.provincia}</p>
                    <p><spring:message code="peticion.presupuestoTotal"/>: ${peticion.getPresupuestoTotal()} €</p>
                    <p><spring:message code="peticion.fechaCaducidad"/>:
                        <tags:localDate date="${peticion.fechaCaducidad}" pattern="dd/MM/yyyy"/>

                </div>

            </div>
            <br>
            <div class="row">
                <div class="justificar-texto">
                    <p><spring:message code="usuario.descripcion"/>:</p>
                    <p> ${peticion.descripcion}</p>
                </div>
            </div>
            <br>
            <br>

            <c:forEach var="item" items="${peticion.items}">

                <div class="row">
                    <div class="col-md-12">
                        <div class="item-search well white">
                            <div class="row">
                                <div class="col-xs-4 col-md-2 avatar-wrapper text-center">
                                    <c:if test="${item.foto != null}">
                                        <img alt="" height="100px" width="100px" class="img-circle center-block"
                                             src="${item.foto}">
                                    </c:if>
                                    <c:if test="${item.foto == null}">
                                        <br>
                                        <h2><spring:message code="peticion.sinFoto"/></h2>
                                    </c:if>

                                </div>

                                <div class="col-xs-8 col-md-8 info-wrapper">
                                    <h4>${item.nombre}</h4>
                                    <h5>${item.descripcion}</h5>
                                </div>
                                <div class="col-md-2 col-xs-12 buttons-wrapper text-center">
                                    <h4><spring:message code="peticion.presupuesto"/></h4>
                                    <c:set var="presupuestoString" value="${String.valueOf(item.presupuesto)}"/>
                                    <c:if test="${presupuestoString.endsWith('0')}">
                                        <c:set value="${presupuestoString.substring(0, presupuestoString.length() - 2)}"
                                               var="prep"/>
                                    </c:if>
                                    <c:if test="${!presupuestoString.endsWith('0')}">
                                        <c:set value="${item.presupuesto}" var="prep"/>
                                    </c:if>
                                    <p>${prep} €</p>
                                    <br>
                                    <c:if test="${actorAutenticado.peticiones.contains(peticion)}">
                                        <a href="item/usuario/editar.do?peticionID=${peticion.id}&itemID=${item.id}"
                                           class="btn btn-info btn-sm" role="button"><spring:message code="editar"/></a>
                                        <a href="item/usuario/borrar.do?peticionID=${peticion.id}&itemID=${item.id}"
                                           class="btn btn-danger btn-sm" role="button"><spring:message
                                            code="borrar"/></a>
                                    </c:if>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</div>