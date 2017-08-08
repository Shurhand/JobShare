
<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags" %>


<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<p>
    <spring:message code="panic.text"/>
    <code>${name}</code>
    .
</p>

<h2>
    <spring:message code="panic.message"/>
</h2>

<p style="font-family: 'Courier New'">${exception}</p>

<h2>
    <spring:message code="panic.stack.trace"/>
</h2>

<p style="font-family: 'Courier New'">${stackTrace}</p>