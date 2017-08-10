<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:useBean id="date" class="java.util.Date"/>

<%--<p class="text-center strong">Copyright &copy; <fmt:formatDate value="${date}" pattern="yyyy"/>--%>
<%--JobShare Co., Inc.</p>--%>

<div class="row">
<footer class="footer">
    <div>
        <div style="margin-top: 0.2em" class="text-left col-md-4 col-lg-4 col-xs-4 col-sm-4">
            <spring:message code="footer.derechos"/>
        </div>
        <div class="social col-md-4 col-lg-4 col-xs-4 col-sm-4">
            <a href="">
                <span class="fa-twitter fa"/>
            </a>
            <a href="">
                <span class="fa-facebook fa"/>
            </a>
            <a href="">
                <span class="fa-google-plus fa"/>
            </a>
        </div>
        <div style="margin-top: 0.2em" class="text-right col-md-4 col-lg-4 col-xs-4 col-sm-4">
            <a href="/contacto.do"><spring:message code="footer.contacto"/></a> |
            <a href="/terminos.do"><spring:message code="footer.terminosYcondiciones"/></a> |
            <a href="/about.do"><spring:message code="footer.sobreNosotros"/></a>
        </div>
    </div>
</footer>
</div>
