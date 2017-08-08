<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags" %>

<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.15/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css">
<div class="col-md-8 col-md-push-2 well dataTable-custom">
    <h2 style="text-align: center"><spring:message code="usuarios.lista"/></h2>
    <table id="usuarios" class="table table-striped table-bordered" width="100%">

        <thead>
        <tr>
            <th>Nombre</th>
            <th>Apellidos</th>
            <th>Código Postal</th>
            <th>DNI</th>
            <th>Email</th>
            <th>Provincia</th>
            <th>Usuario</th>
        </tr>
        </thead>
    </table>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        var data = ${usuarios};
        $('#usuarios').DataTable({
            "aaData": data,
            "aoColumns": [
                {"mData": "nombre"},
                {"mData": "apellidos"},
                {"mData": "cp"},
                {"mData": "dni"},
                {"mData": "email"},
                {"mData": "provincia"},
                {"mData": "cuenta.username"}

            ]
        });

    });
</script>