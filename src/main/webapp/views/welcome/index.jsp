<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="styles/common.css" type="text/css">

<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>

        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img class="first-slide" src="images/Banner1.jpg" alt="First slide">
            <div class="container">
                <div class="carousel-caption">
                    <div class="carousel-p">
                        <h1><spring:message code="index.banner1_1"/></h1>
                        <p><spring:message code="index.banner1_2"/></p>
                        <p><a class="btn btn-lg btn-primary" href="customer/create.do" role="button"><spring:message
                            code="index.button_banner1"/></a></p>
                    </div>
                </div>
            </div>
        </div>
        <div class="item">
            <img class="second-slide" src="images/Banner6.jpg" alt="Second slide">
            <div class="container">
                <div class="carousel-caption">
                    <div class="carousel-p">
                        <h1><spring:message code="index.banner2_1"/></h1>
                        <p><spring:message code="index.banner2_2"/></p>
                        <p><a class="btn btn-lg btn-primary" href="activity/list.do" role="button"><spring:message
                            code="index.button_banner2"/></a></p>
                    </div>
                </div>
            </div>
        </div>
        <div class="item">
            <img class="third-slide" src="images/Banner7.jpg" alt="Third slide">
            <div class="container">
                <div class="carousel-caption">
                    <div class="carousel-p">
                        <h1><spring:message code="index.banner3_1"/></h1>
                        <p><spring:message code="index.banner3_2"/></p>
                        <p><a class="btn btn-lg btn-primary" href="#" role="button"><spring:message
                            code="index.button_banner3"/></a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
<!-- /.carousel -->

<%--<div class="container">--%>

<%--<!-- Main component for a primary marketing message or call to action -->--%>
<%--<div class="jumbotron">--%>
<%--<h1 align="center"><spring:message code="index.message_trip"/></h1>--%>
<%--<p align="center"><spring:message code="index.message_trip2"/></p>--%>

<%--<p align="center">--%>
<%--<a class="btn btn-lg btn-primary " href="route/create.do" role="button"><spring:message--%>
<%--code="index.trip"/></a>--%>
<%--</p>--%>
<%--</div>--%>

<%--</div>--%>
<!-- /container -->
