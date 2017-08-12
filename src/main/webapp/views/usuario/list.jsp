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
<div class="container-fluid">
    <div class="well">
        <h2 style="text-align: center"><spring:message code="usuarios.lista"/></h2>
        <table id="usuarios" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0"
               width="100%">

            <thead>
            <tr>
                <th><spring:message code="usuario.nombre"/></th>
                <th><spring:message code="usuario.apellidos"/></th>
                <th><spring:message code="usuario.cp"/></th>
                <th><spring:message code="usuario.dni"/></th>
                <th><spring:message code="usuario.email"/></th>
                <th><spring:message code="usuario.provincia"/></th>
                <th><spring:message code="usuario.usuario"/></th>
                <th><spring:message code="usuario.cuentaActiva"/></th>
                <th><spring:message code="usuario.bloquear"/></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        var data = ${usuarios};
        var tabla = $('#usuarios').DataTable({
            'responsive': true,
            'autoWidth': true,
            "aaData": data,
            "aoColumns": [
                {"mData": "nombre"},
                {"mData": "apellidos"},
                {"mData": "cp"},
                {"mData": "dni"},
                {"mData": "email"},
                {"mData": "provincia"},
                {"mData": "cuenta.username"},
                {"mData": "cuenta.isActivated"}

            ],
            'columnDefs': [{
                'targets': 8,
                'searchable': false,
                'orderable': false,
                'className': 'dt-body-center',
                "mRender": function (data, type, full) {
                    var aux = full.id;
                    var elHTML = '<a href="usuario/admin/bloquear.do?usuarioID=';
                    var final = elHTML + aux + '"><spring:message code="usuario.bloquear"/> </a>';
                    return final;
                }

            }]
        });
    });
</script>

<%--<script type="text/javascript">--%>
<%--$(document).ready(function () {--%>
<%--var idioma = document.getElementById('idioma').innerHTML;--%>
<%--if (idioma == 'es') {--%>
<%--document.body.innerHTML = document.body.innerHTML.replace(/true/g, 'Sí');--%>
<%--} else {--%>
<%--document.body.innerHTML = document.body.innerHTML.replace(/true/g, 'Yes');--%>
<%--}--%>
<%--document.body.innerHTML = document.body.innerHTML.replace(/false/g, 'No');--%>
<%--});--%>
<%--</script>--%>
