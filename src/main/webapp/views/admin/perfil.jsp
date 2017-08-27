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
<script src="https://cdn.datatables.net/responsive/2.1.1/js/dataTables.responsive.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.1.1/js/responsive.bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.1.1/css/responsive.bootstrap.min.css">

<div class="container">
    <div class="row well white ">
        <div class="col-md-10 col-sm-12 col-sx-12 col-md-offset-1">
            <h2 class="col-md-push-2" style="text-align: center"><spring:message code="actor.perfil"/>
                <a href="/admin/modificarPerfil.do" class="btn btn-info" role="button"> <spring:message
                    code="actor.modificarPerfil"/> </a></h2>

            <br>
            <div class="row">
                <div class="col-md-4 col-sm-6 col-md-offset-2">
                    <c:if test="${admin.foto == null}">
                        <br>
                        <h2><spring:message code="actor.sinFoto"/></h2>
                    </c:if>
                    <c:if test="${admin.foto != null}">
                        <IMG src="${admin.foto}"
                             class="img-circle col-md-offset-1 col-xs-offset-1 col-sm-offset-1"
                             width="125"
                             height="125">
                    </c:if>
                </div>

                <div class="col-md-6 col-sm-6">
                    <p>${admin.nombre} ${admin.apellidos} - ${admin.email}</p>
                    <p>${admin.cp} ${admin.provincia}</p>
                    <p>${admin.DNI} - ${admin.telefono}</p>
                </div>
            </div>
            <div class="row">
                <div class="justificar-texto">
                    <p><spring:message code="usuario.descripcion"/>:</p>
                    <p> ${admin.descripcion}</p>
                </div>
            </div>
        </div>
    </div>
</div>
