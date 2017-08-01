<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>



      <nav class="navbar-sinmargen navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
         
          
          <a href="${base}"><img  src="images/JobShareletras.png" width="90px"></a>
          <a href="?language=en"><img src="images/icon_id_en.png"></a>
          <a href="?language=es"><img src="images/icon_id_es.png"></a>
        </div>
      </div>
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li><a href="${base}"><spring:message code="master.page.home"/></a></li>
            <li><a href="about.do"><spring:message code="master.page.about"/></a></li>
            <li><a href="contact.do"><spring:message code="master.page.contact"/></a></li>
            <li><a href="tips.do"><spring:message code="master.page.tips"/></a></li>
            
            <security:authorize access="hasAuthority('CUSTOMER')">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><spring:message code="master.page.activities"/><span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="activity/create.do"><spring:message code="master.page.activity.create"/></a></li>
                <li><a href="schedule/create.do"><spring:message code="master.page.schedule.create"/></a></li>
                <li><a href="activity/list.do"><spring:message code="master.page.activity.list"/></a></li>
              </ul>
            </li>
            </security:authorize>
            
            <security:authorize access="hasAuthority('COMPANY')">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><spring:message code="master.page.activities"/><span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="activity/company/create.do"><spring:message code="master.page.activity.create"/></a></li>
                <li><a href="schedule/create.do"><spring:message code="master.page.schedule.create"/></a></li>
                <li><a href="activity/company/list.do"><spring:message code="master.page.activity.list"/></a></li>
              </ul>
            </li>
            </security:authorize>
            
            <security:authorize access="hasAuthority('CUSTOMER')">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><spring:message code="master.page.routes"/><span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="route/customer/create.do"><spring:message code="master.page.custom.route.create"/></a></li>
                <li><a href="route/customer/list2.do"><spring:message code="master.page.route.list"/></a></li>
                <li><a href="route/customer/listCustom.do"><spring:message code="master.page.custom.route.list"/></a></li>
                
              </ul>
            </li>
            </security:authorize>
            
            <security:authorize access="hasAuthority('ADMIN')">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><spring:message code="master.page.companies"/><span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="company/admin/create.do"><spring:message code="master.page.company.register"/></a></li>
                <li><a href="#"><spring:message code="master.page.company.list"/></a></li>
              </ul>
            </li>
            </security:authorize>
            
            <security:authorize access="hasAuthority('ADMIN')">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><spring:message code="master.page.category"/><span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="category/admin/create.do"><spring:message code="master.page.category.create"/></a></li>
                <li><a href="category/admin/list.do"><spring:message code="master.page.category.list"/></a></li>
              </ul>
            </li>
            </security:authorize>
            
            <security:authorize access="hasAuthority('ADMIN')">
            
                <li><a href="admin/dashboard.do"><spring:message code="master.page.admin.dashboard"/></a></li>
               
            </security:authorize>
            
            
          </ul>
          <security:authorize access="isAnonymous()">
          <div id="navbar" class="navbar-collapse collapse">
          <form:form class="navbar-form navbar-right" action="login" modelAttribute="credenciales">
            <div class="form-group">
             	<form:input path="username" class="form-control" />
				<form:errors class="error2" path="username" />
            </div>
            <div class="form-group">
             	<form:password path="password" class="form-control" />
				<form:errors class="error2" path="password" />
			</div>
			<div class="form-group">
			<jstl:if test="${showError == true}">
				<div class="error2">
					<spring:message code="security.login.failed" />
				</div>
			</jstl:if>
			</div>
			 <div class="form-group">
            <input type="submit" class="btn btn-primary" value="<spring:message code="master.page.login" />" />
            <a href="customer/create.do" class="btn btn-primary"> <spring:message code="master.page.register" /></a>
           </div>
           </form:form>
           </div>
         </security:authorize>
          <security:authorize access="isAuthenticated()">
          
			 <div id="navbar" class="navbar-collapse collapse">
			 <form:form class="navbar-form navbar-right">
			<div class="form-group">
				<p class="navbar-text navbar-right2"><spring:message code="master.page.salute" />&nbsp&nbsp  <security:authentication property="principal.username" /></p> 
			 	<a class="btn btn-primary" href="logout" ><spring:message code="master.page.logout"/></a>
			</div>
	</form:form>
	</div>
        </div>
      </div>
    </nav>
