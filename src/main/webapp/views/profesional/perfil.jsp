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
            <a href="/profesional/modificarPerfil.do" class="btn btn-info" role="button"> <spring:message
                code="actor.modificarPerfil"/> </a></h2>

        <br>
        <div class="col-md-4 col-sm-4 col-xs-4 col-md-offset-2 col-sm-offset-2 col-xs-offset-2">
            <c:if test="${profesional.foto == null}">
                <br>
                <h2><c:out value="Sin foto"></c:out></h2>
            </c:if>
            <c:if test="${profesional.foto != null}">
                <IMG src="${profesional.foto}" class="img-circle col-md-offset-1 col-xs-offset-1 col-sm-offset-1"
                     width="125"
                     height="125">
            </c:if>
            <security:authorize access="hasAuthority('PROFESIONAL')">
                <br>
                <br>

                <input id="ratingPersonal" value="${profesional.getValoracionTotal()}" class="rating-loading">

            </security:authorize>
        </div>
        <div class="col-md-5 col-sm-4 col-xs-4 col-md-offset-1 col-sm-offset-1 col-xs-offset-1">
            <p>${profesional.nombre} ${profesional.apellidos} ${profesional.email}</p>
            <p>${profesional.cp} ${profesional.provincia}</p>
            <p>${profesional.DNI}</p>
            <p><spring:message code="usuario.peticionesRealizadas"/> ${profesional.peticiones.size()}<p>
            <p><spring:message code="profesional.ofertasRealizadas"/> ${profesional.ofertas.size()}<p>
        </div>
        <div class="col-md-offset-1 col-sm-offset-1">
            <p><spring:message code="usuario.descripcion"/>:
            <p>
            <h5>${profesional.descripcion}</h5>
        </div>
        <br>
        <h4 style="text-align: center"><spring:message code="profesional.estudios"/>
            <a href="/estudio/profesional/crear.do" class="btn btn-info" role="button"> <spring:message
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
            <a href="/trabajo/profesional/crear.do" class="btn btn-info" role="button"> <spring:message
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
        <div class="col-md-8 col-sm-10 col-md-offset-2 col-sm-offset-1 text-center">
            <h4><spring:message code="profesional.tusValoraciones"/> (${profesional.valoraciones.size()})</h4>
            <div class="col-md-2">
                <c:if test="${profesional.foto == null}">
                    <br>
                    <h2><c:out value="Sin foto"></c:out></h2>
                </c:if>
                <c:if test="${profesional.foto != null}">
                    <IMG src="${profesional.foto}" class="img-circle col-md-offset-1 col-xs-offset-1 col-sm-offset-1"
                         width="125" height="125">
                </c:if>
            </div>
        </div>
        <hr/>

    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
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
                }]

        });
        // Rating de estrellas
        $('#ratingPersonal').rating({
            size: 'sm',
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
                }]
        })
    });
</script>