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
    <div class="row well white col-md-10 col-sm-12 col-sx-12 col-md-offset-1">

        <h2 class="col-md-push-2" style="text-align: center"><spring:message code="actor.perfil"/>
            <a href="/admin/modificarPerfil.do" class="btn btn-info" role="button"> <spring:message
                code="actor.modificarPerfil"/> </a></h2>

        <br>
        <div class="col-md-3 col-sm-4 col-xs-4 col-md-offset-2 col-sm-offset-2 col-xs-offset-2">
            <c:if test="${admin.foto == null}">
                <br>
                <h2><c:out value="Sin foto"></c:out></h2>
            </c:if>
            <c:if test="${admin.foto != null}">
                <IMG src="${admin.foto}" class="img-circle" width="100" height="100">
            </c:if>
        </div>
        <div class="col-md-5 col-sm-4 col-xs-4 col-md-offset-1 col-sm-offset-1 col-xs-offset-1">
            <p>${admin.nombre} ${admin.apellidos} - ${admin.email}</p>
            <p>${admin.cp} ${admin.provincia}</p>
            <p>${admin.DNI}</p>
        </div>
        <div class="col-md-offset-1 col-sm-offset-1">
            <p><spring:message code="usuario.descripcion"/>:
            <p>
            <h5>${admin.descripcion}</h5>
        </div>
    </div>
</div>
