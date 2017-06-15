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
        <form:form method="post" modelAttribute="inscription" action="/apprenants/inscription">
            <form:select path="numapprenant" items="${apprenants}" itemLabel="fullname" itemValue="numapprenant" />
            <form:select path="numjeu" items="${jeux}" itemLabel="libellejeu" itemValue="numjeu" />

            <button type="submit">Inscrire</button>
        </form:form>
    </jsp:body>
</tags:layout>