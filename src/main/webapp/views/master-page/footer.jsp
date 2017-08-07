<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="date" class="java.util.Date"/>

<%--<p class="text-center strong">Copyright &copy; <fmt:formatDate value="${date}" pattern="yyyy"/>--%>
<%--JobShare Co., Inc.</p>--%>

<footer class="footer">
    <ul>
        <li>
            JobShare S.L. ®2017 Todos los derechos reservados
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
            <a href="/contacto.do">Contacto</a> |
            <a href="/terminos.do">Términos y condiciones</a> |
            <a href="/about.do">Sobre nosotros</a>
        </li>
    </ul>
</footer>