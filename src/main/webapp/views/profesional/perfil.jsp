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
                <c:if test="${!profesional.cuenta.isGoogle}">
                <a href="profesional/modificarPerfil.do" class="btn btn-info" role="button"> <spring:message
                    code="actor.modificarPerfil"/> </a></h2>
            </c:if>
            <c:if test="${profesional.cuenta.isGoogle}">
                <a href="profesional/modificarPerfilGoogle.do" class="btn btn-info" role="button"> <spring:message
                    code="actor.modificarPerfil"/> </a></h2>
            </c:if>
            <br>
            <div class="row">
                <div class="col-md-4 col-sm-6 col-md-offset-2">
                    <c:if test="${profesional.foto == null}">
                        <br>
                        <h2><spring:message code="actor.sinFoto"/></h2>
                    </c:if>
                    <c:if test="${profesional.foto != null}">
                        <IMG src="${profesional.foto}"
                             class="img-circle col-md-offset-1 col-xs-offset-1 col-sm-offset-1"
                             width="125"
                             height="125">
                    </c:if>
                    <security:authorize access="hasAuthority('PROFESIONAL')">
                        <br>
                        <br>

                        <input id="ratingPersonal" value="${profesional.getValoracionTotal()}" class="rating-animate"/>

                    </security:authorize>
                </div>

                <div class="col-md-6 col-sm-6">
                    <p>${profesional.nombre} ${profesional.apellidos} - ${profesional.email}</p>
                    <p>${profesional.cp} ${profesional.provincia}</p>
                    <c:if test="${profesional.telefono == ''}">
                        <p>${profesional.DNI} - <spring:message code="usuario.sinTelefono"/></p>
                    </c:if>
                    <c:if test="${profesional.telefono != ''}">
                        <p>${profesional.DNI} - ${profesional.telefono}</p>
                    </c:if>
                    <p><spring:message code="usuario.peticionesRealizadas"/> ${profesional.peticiones.size()}<p>
                    <p><spring:message code="profesional.ofertasRealizadas"/> ${profesional.ofertas.size()}<p>
                </div>
            </div>

            <div class="row">
                <div class="justificar-texto">
                    <p><spring:message code="usuario.descripcion"/>:</p>
                    <p> ${profesional.descripcion}</p>
                </div>
            </div>
            <br>
            <h4 style="text-align: center"><spring:message code="profesional.estudios"/>
                <a href="estudio/profesional/crear.do" class="btn btn-info" role="button"> <spring:message
                    code="estudio.nuevo"/> </a></h4>
            <table id="estudios" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0"
                   width="100%">

                <thead>
                <tr>
                    <th><spring:message code="estudio.titulacion"/></th>
                    <th><spring:message code="estudio.centro"/></th>
                    <th><spring:message code="estudio.fechaInicio"/></th>
                    <th><spring:message code="estudio.fechaFin"/></th>
                    <th><spring:message code="estudio.acciones"/></th>

                </tr>
                </thead>
            </table>
            <br>
            <h4 style="text-align: center"><spring:message code="profesional.trabajos"/>
                <a href="trabajo/profesional/crear.do" class="btn btn-info" role="button"> <spring:message
                    code="trabajo.nuevo"/> </a>
            </h4>
            <table id="trabajos" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0"
                   width="100%">

                <thead>
                <tr>
                    <th><spring:message code="trabajo.puesto"/></th>
                    <th><spring:message code="trabajo.empresa"/></th>
                    <th><spring:message code="trabajo.fechaInicio"/></th>
                    <th><spring:message code="trabajo.fechaFin"/></th>
                    <th><spring:message code="trabajo.acciones"/></th>

                </tr>
                </thead>
            </table>
            <br>

            <h4 style="text-align: center">
                <spring:message code="valoracion.valoraciones"/>
                <hr>

                <c:forEach var="valoracion" items="${valoraciones}">
                <div class="row">
                    <div class="col-xs-2 col-md-2 avatar-wrapper text-center">
                            <%--<spring:message code="valoracion.persona" />--%>
                        <c:if test="${valoracion.usuario.foto != null}">
                            <img alt="" height="70px" width="70px"
                                 class="img-circle center-block"
                                 src="${valoracion.usuario.foto}">
                        </c:if>
                        <c:if test="${valoracion.usuario.foto == null}">
                            <br>
                            <h3><spring:message code="peticion.sinFoto"/></h3>
                        </c:if>
                        <h6 style="margin: auto">${valoracion.usuario.nombre}</h6>
                            <%--<input value="${valoracion.profesional.getValoracionTotal()}"--%>
                            <%--class="rating-loading">--%>

                    </div>
                    <div class="col-xs-2 col-md-2 ">
                        <br><br>
                        <input value="${valoracion.getPuntuacion()}" class="rating-loading rating-xs"/>
                    </div>
                    <div class="col-xs-5 col-md-5 ">
                            <br>
                        <div class="text-left">
                            <h5>${valoracion.comentario}</h5>
                        </div>
                    </div>
                    <div class="col-xs-2 col-md-2 ">
                        <br>
                        <div class="text-left">
                            <h5><a href="peticion/ver.do?peticionID=${valoracion.oferta.item.peticion.id}">
                                <spring:message code="peticion.ver"/>
                            </a></h5>
                        </div>
                    </div>
                    <security:authorize access="hasAuthority('ADMIN')">
                        <div class="col-xs-2 col-md-1">
                            <br>
                            <a href="valoracion/admin/borrar.do?valoracionID=${valoracion.id}&actorID${profesional.id}"
                               class="btn btn-info btn-sm" role="button"><spring:message code="borrar"/></a>
                        </div>
                    </security:authorize>
                </div>
                <hr>
                </c:forEach>
        </div>
    </div>
    <hr/>
</div>
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

        // Tabla de estudios
        var data = ${estudios};
        $('#estudios').DataTable({
            'responsive': true,
            'paging': false,
            'searching': false,
            'autoWidth': true,
            'info': false,
            "aaData": data,
            "aoColumns": [
                {"mData": "titulacion"},
                {"mData": "centro"},
                {"mData": "fechaInicio"},
                {"mData": "fechaFin"}
            ],

            'columnDefs': [{"className": "dt-body-center", "targets": "_all"},
                {
                    'targets': 4,
                    'searchable': false,
                    'orderable': false,
                    "mRender": function (data, type, full) {
                        var elHTML = '<a href="estudio/profesional/editar.do?estudioID=';
                        var editar = elHTML + full.id + '" style="margin: 5px;" class="btn btn-info btn-sm role="button"><spring:message code="editar"/> </a>';

                        var elHTML2 = '<a href="estudio/profesional/borrar.do?estudioID=';
                        var borrar = elHTML2 + full.id + '" style="margin: 5px;" class="btn btn-danger btn-sm role="button"><spring:message code="borrar"/> </a>';

                        return editar + borrar;
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
        // Rating de estrellas
        $('.rating-animate').rating({
            size: 'sm',
            readonly: true,
            showClear: false,
            showCaption: false

        });

        $('.rating-xs').rating({
            size: 'xxs',
            readonly: true,
            showClear: false,
            showCaption: false

        });

        // Tabla de trabajos
        var data = ${trabajos};
        $('#trabajos').DataTable({
            'responsive': true,
            'paging': false,
            'searching': false,
            'autoWidth': true,
            'info': false,
            "aaData": data,
            "aoColumns": [
                {"mData": "puesto"},
                {"mData": "empresa"},
                {"mData": "fechaInicio"},
                {"mData": "fechaFin"}
            ],
            'columnDefs': [{"className": "dt-body-center", "targets": "_all"},
                {
                    'targets': 4,
                    'searchable': false,
                    'orderable': false,
                    "mRender": function (data, type, full) {
                        var elHTML = '<a href="trabajo/profesional/editar.do?trabajoID=';
                        var editar = elHTML + full.id + '" style="margin: 5px;" class="btn btn-info btn-sm role="button"><spring:message code="editar"/> </a>';

                        var elHTML2 = '<a href="trabajo/profesional/borrar.do?trabajoID=';
                        var borrar = elHTML2 + full.id + '" style="margin: 5px;" class="btn btn-danger btn-sm role="button"><spring:message code="borrar"/> </a>';

                        return editar + borrar;
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
        })
    });
</script>