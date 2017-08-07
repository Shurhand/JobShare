<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="forms" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="row">
        <h2>Registro de nuevo usuario</h2>
        <div class="contact-main well white">

            <forms:form modelAttribute="userForm" action="usuario/registro">

                <div class="form-group">
                    <label class="control-label" for="id_username">Usuario</label>
                    <input class="form-control" id="id_username" name="username" placeholder="Usuario"
                           required="required"
                           title="" type="text"/>
                </div>
                <div class="form-group">
                    <label class="control-label" for="id_first_name">Nombre</label>
                    <input
                        class="form-control" id="id_first_name" name="first_name" placeholder="Nombre"
                        required="required"
                        title="" type="text"/>
                </div>
                <div class="form-group">
                    <label class="control-label" for="id_last_name">Apellidos</label>
                    <input class="form-control" id="id_last_name" name="last_name" placeholder="Apellidos"
                           required="required"
                           title="" type="text"/>
                </div>
                <div class="form-group">
                    <label class="control-label" for="id_email">Correo</label>
                    <input class="form-control" id="id_email" name="email" placeholder="Correo" required="required"
                           title=""
                           type="email"/>
                </div>
                <div class="form-group">
                    <label class="control-label" for="id_password">Contraseña</label>
                    <input class="form-control" id="id_password" name="password" placeholder="Contraseña"
                           required="required"
                           title="" type="password"/>
                </div>
                <div class="form-group">
                    <label class="control-label" for="id_password2">Repetir contraseña</label>
                    <input class="form-control" id="id_password2" name="password2" placeholder="Repetir contraseña"
                           required="required" title="" type="password"/>
                </div>
                <div class="form-group">
                    <label class="control-label" for="id_phone">Teléfono</label>
                    <input class="form-control" id="id_phone" name="phone" placeholder="Teléfono" required="required"
                           title=""
                           type="text"/>
                </div>
                <div class="form-group">
                    <div class="checkbox"><label for="id_acceptation"><input class="" id="id_acceptation"
                                                                             name="acceptation" type="checkbox"/> Acepto
                        los términos y condiciones</label>
                    </div>
                </div>
                <div class="form-group">
                    <br>
                    <a href="/" class="btn btn-default"><i class="fa fa-arrow-left"></i> Atrás</a>
                    <button type="submit" name="saveForm" class="btn btn-primary"><i class="fa fa-check"></i>
                        Registrarse
                    </button>
                </div>
            </forms:form>
        </div>
    </div>
</div>
