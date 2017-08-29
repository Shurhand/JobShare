<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<div class="container">
    <div class="row well white">
        <div class="col-md-10 col-md-offset-1">
            <h2 class="col-md-push-2" style="text-align: center">
                <c:if test="${!ofertas.isEmpty()}">
                    <spring:message code="pago.completar"/>
                <hr>
                <c:forEach var="oferta" items="${ofertas}">
                <div class="row">
                    <div class="col-xs-2 col-md-2 avatar-wrapper text-center">
                        <c:if test="${oferta.profesional.foto != null}">
                            <a href="actor/verPerfil.do?actorID=${oferta.profesional.id}"><img
                                alt="" height="50px" width="50px"
                                class="img-circle center-block"
                                src="${oferta.profesional.foto}"></a>
                        </c:if>
                        <c:if test="${oferta.profesional.foto == null}">
                            <br>
                            <h2><a
                                href="actor/verPerfil.do?actorID=${oferta.profesional.id}"><spring:message
                                code="peticion.sinFoto"/></a></h2>
                        </c:if>
                        <c:if test="${!oferta.profesional.id.equals(actorAutenticado.id)}">
                            <h6 style="margin: auto">${oferta.profesional.nombre}</h6>
                        </c:if>
                        <c:if test="${oferta.profesional.id.equals(actorAutenticado.id)}">
                            <h6 style="margin: auto"><spring:message code="peticion.tu"/></h6>
                        </c:if>
                        <input value="${oferta.profesional.getValoracionTotal()}"
                               class="rating-loading">
                        <br>
                    </div>
                    <div class="col-xs-2 col-md-2">
                        <br>
                        <c:set var="precioString" value="${String.valueOf(oferta.precio)}"/>
                        <c:if test="${precioString.endsWith('0')}">
                            <c:set
                                value="${precioString.substring(0, precioString.length() - 2)}"
                                var="precio"/>
                        </c:if>
                        <c:if test="${!precioString.endsWith('0')}">
                            <c:set value="${oferta.precio}" var="precio"/>
                        </c:if>
                        <h4 style="font-size: 2rem; ">${precio} €</h4>
                    </div>

                    <div class="col-xs-6 col-md-6">
                        <br>
                        <div class="justificar-texto">
                            <h5>${oferta.comentario}</h5>
                        </div>
                    </div>
                </div>
                </c:forEach>
                <hr>
                <p><spring:message code="pago.presupuestoTotal"/> ${precioTotal} €</p>

                <form action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post">

                    <!-- Identify your business so that you can collect the payments. -->
                    <input type="hidden" name="business" value="equecrates-facilitator@gmail.com">
                    <input type="hidden" name="currency_code" value="EUR">

                    <!-- Specify a Buy Now button. -->
                    <input type="hidden" name="cmd" value="_cart">
                    <input type="hidden" name="upload" value="1">
                    <c:set var="indice" value="1"/>
                        <%--<c:set var="parametros" value="?"/>--%>
                    <c:forEach var="oferta" items="${ofertas}">
                        <input type="hidden" name="item_name_${indice}" value="${oferta.id}">
                        <input type="hidden" name="amount_${indice}" value="${oferta.precio}">
                        <c:set var="indice" value="${indice + 1}"/>
                    </c:forEach>

                    <input type="hidden" name="return" value="${urlReturn}"/>
                    <input type="hidden" name="cancel_return"
                           value="http://localhost:8080/peticion/usuario/buscarMisPeticiones.do">

                    <!-- Display the payment button. -->
                    <input type="image" name="submit" border="0"
                           src="https://www.paypalobjects.com/webstatic/en_US/i/btn/png/blue-rect-paypal-60px.png"
                           alt="PayPal Checkout">

                </form>
                </c:if>
                <c:if test="${ofertas.isEmpty()}">
                <div class="error-notice">
                    <div class="oaerror danger">
                        <strong><spring:message code="pago.sinOfertas"/></strong>
                    </div>
                </div>
                </c:if>
                <br><br>
                <a href="peticion/ver.do?peticionID=${peticionID}" class="btn btn-primary"><i
                    class="fa fa-arrow-left"></i> <spring:message code="volver"/></a>
        </div>
    </div>
</div>

<script type="text/javascript">
    //    // Rating de estrellas
    $('.rating-loading').rating({
        size: 'xxs',
        readonly: true,
        showClear: false,
        showCaption: false,
    });

</script>