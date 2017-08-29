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
                <th><spring:message code="usuario.cuentaGoogle"/></th>
                <th><spring:message code="usuario.bloquear"/></th>
                <th><spring:message code="usuario.verPerfil"/></th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {

        var idiomas = ["<spring:message code="dataTable.sProcessing"/>",
            "<spring:message code="dataTable.sLengthMenu"/>",
            "<spring:message code="dataTable.sZeroRecords"/>",
            "<spring:message code="dataTable.sEmptyTable"/>",
            "<spring:message code="dataTable.sInfo"/>",
            "<spring:message code="dataTable.sInfoEmpty"/>",
            "<spring:message code="dataTable.sInfoFiltered"/>",
            "<spring:message code="dataTable.sInfoPostFix"/>",
            "<spring:message code="dataTable.sSearch"/>",
            "<spring:message code="dataTable.sUrl"/>",
            "<spring:message code="dataTable.sInfoThousands"/>",
            "<spring:message code="dataTable.sLoadingRecords"/>",
            "<spring:message code="dataTable.sFirst"/>",
            "<spring:message code="dataTable.sLast"/>",
            "<spring:message code="dataTable.sNext"/>",
            "<spring:message code="dataTable.sPrevious"/>",
            "<spring:message code="dataTable.sSortAscending"/>",
            "<spring:message code="dataTable.sSortDescending"/>"
        ];
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
                {
                    "mData": "cuenta.isActivated",
                    "mRender": function (data, type, full) {
                        var texto;
                        if (full.cuenta.isActivated == true) {
                            texto = '<spring:message code="frase.si"/>';
                        }
                        else {
                            texto = '<spring:message code="frase.no"/>';
                        }
                        return texto;
                    }
                },
                {
                    "mData": "cuenta.isGoogle",
                    "mRender": function (data, type, full) {
                        var texto;
                        if (full.cuenta.isGoogle == true) {
                            texto = '<spring:message code="frase.si"/>';
                        }
                        else {
                            texto = '<spring:message code="frase.no"/>';
                        }
                        return texto;
                    }
                }

            ],
            'columnDefs': [{"className": "dt-body-center", "targets": [7, 8]},
                {
                'targets': 9,
                'searchable': false,
                'orderable': false,
                'className': 'dt-body-center',
                "mRender": function (data, type, full) {
                    var aux = full.id;
                    var elHTML = '<a href="usuario/admin/bloquear.do?usuarioID=';
                    var final = elHTML + aux + '" class="btn btn-danger btn-xs" role="button"><spring:message code="usuario.bloquear"/> </a>';
                    return final;
                }
            },
                {
                    'targets': 10,
                    'searchable': false,
                    'orderable': false,
                    'className': 'dt-body-center',
                    "mRender": function (data, type, full) {
                        var aux = full.id;
                        var elHTML = '<a href="actor/verPerfil.do?actorID=';
                        var final = elHTML + aux + '" class="btn btn-info btn-xs" role="button"><spring:message code="usuario.ver"/> </a>';
                        return final;
                    }

                }],
            "language": {
                "sProcessing": idiomas[0],
                "sLengthMenu": idiomas[1],
                "sZeroRecords": idiomas[2],
                "sEmptyTable": idiomas[3],
                "sInfo": idiomas[4],
                "sInfoEmpty": idiomas[5],
                "sInfoFiltered": idiomas[6],
                "sInfoPostFix": idiomas[7],
                "sSearch": idiomas[8],
                "sUrl": idiomas[9],
                "sInfoThousands": idiomas[10],
                "sLoadingRecords": idiomas[11],
                "oPaginate": {
                    "sFirst": idiomas[12],
                    "sLast": idiomas[13],
                    "sNext": idiomas[14],
                    "sPrevious": idiomas[15],
                },
                "oAria": {
                    "sSortAscending": idiomas[16],
                    "sSortDescending": idiomas[17]
                }
            }

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
