<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.15/js/dataTables.bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.1.1/js/dataTables.responsive.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.1.1/js/responsive.bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.1.1/css/responsive.bootstrap.min.css">

<div class="container-fluid">
    <div class="well">
        <h2 style="text-align: center"><spring:message code="etiqueta.lista"/></h2>
        <table id="etiquetas" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0"
               width="100%">

            <thead>
            <tr>
                <th>Nombre</th>
                <th>Activada</th>
                <th>Activar/Desactivar</th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        var data = ${etiquetas};
        var tabla = $('#etiquetas').DataTable({
            'responsive': true,
            'autoWidth': true,
            "aaData": data,
            "aoColumns": [
                {"mData": "nombre"},
                {"mData": "activada"}

            ],
            'columnDefs': [{
                'targets': 2,
                'searchable': false,
                'orderable': false,
                'className': 'dt-body-center',
                "mRender": function (data, type, full) {
                    var aux = full.id;
                    var elHTML = '<a href="etiqueta/admin/activar.do?etiquetaID=';
                    var final = elHTML + aux + '"><spring:message code="etiqueta.bloquear"/> </a>';
                    return final;
                }

            }]
        });

    });

</script>