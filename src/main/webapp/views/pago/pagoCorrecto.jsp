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
        <div class="col-md-10 col-md-offset-2">
            <h2 style="text-align: center">
                <div class="error-notice caja-pago">
                    <div class="oaerror success">
                        <h2><spring:message
                            code="pago.pagoCorrecto"/></h2>

                        <h3>
                            <div id="counter">5</div>
                        </h3>
                        <script>
                            setInterval(function () {
                                var div = document.querySelector("#counter");
                                var count = div.textContent * 1 - 1;
                                div.textContent = count;
                                if (count <= 0) {
                                    window.location.href = "peticion/usuario/buscarMisPeticiones.do";
                                }
                            }, 1000);
                        </script>
                    </div>
                </div>
        </div>
    </div>
</div>

