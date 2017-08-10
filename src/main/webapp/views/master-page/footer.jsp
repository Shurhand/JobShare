<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:useBean id="date" class="java.util.Date"/>

<%--<p class="text-center strong">Copyright &copy; <fmt:formatDate value="${date}" pattern="yyyy"/>--%>
<%--JobShare Co., Inc.</p>--%>


<footer class="footer">
    <ul>
        <li>
            <spring:message code="footer.derechos"/>
        </li>
        <li class="social">
            <a href="">
                <span class="fa-twitter fa"/>
            </a>
            <a href="">
                <span class="fa-facebook fa"/>
            </a>
            <a href="">
                <span class="fa-google-plus fa"/>
            </a>
        </li>
        <li>
            <a href="/contacto.do"><spring:message code="footer.contacto"/></a> |
            <a href="/terminos.do"><spring:message code="footer.terminosYcondiciones"/></a> |
            <a href="/about.do"><spring:message code="footer.sobreNosotros"/></a>
        </li>
    </ul>
</footer>
