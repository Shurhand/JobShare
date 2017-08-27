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
            </tr>
            </thead>
        </table>
    </div>
    <security:authorize access="isAuthenticated()">
        <div class="text-center">
            <h2>
                <a href="/etiqueta/actor/crear.do" class="btn btn-primary btn-lg" role="button"><spring:message
                    code="etiqueta.nueva"/> </a></h2>
        </div>
    </security:authorize>
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
        var data = ${etiquetas};
        var tabla = $('#etiquetas').DataTable({
            'responsive': true,
            'autoWidth': true,
            "aaData": data,
            'columnDefs': [{"className": "dt-body-center", "targets": 1}],
            "aoColumns": [
                {"mData": "nombre"},
                {
                    "mData": "activada",
                    "mRender": function (data, type, full) {
                        var texto;
                        if (full.activada == true) {
                            texto = '<spring:message code="frase.si"/>';
                        }
                        else {
                            texto = '<spring:message code="frase.no"/>';
                        }
                        return texto;
                    }
                }

            ],
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