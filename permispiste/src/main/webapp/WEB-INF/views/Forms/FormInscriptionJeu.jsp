<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:layout>
    <jsp:attribute name="title">
      F.R.E.P. - Inscription à un jeu
    </jsp:attribute>
    <jsp:body>
        <div class="container">
            <div class="jumbotron">
        <form:form method="post" modelAttribute="inscription" action="/apprenants/inscription" class="form-inline">
            <form:select path="numapprenant" items="${apprenants}" itemLabel="fullname" itemValue="numapprenant" class="form-control"/>
            <form:select path="numjeu" items="${jeux}" itemLabel="libellejeu" itemValue="numjeu" class="form-control"/>

            <button type="submit" class="btn btn-success">Inscrire</button>
        </form:form>
            </div>
        </div>
    </jsp:body>
</tags:layout>