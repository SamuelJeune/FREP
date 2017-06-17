<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>

<tags:layout>
    <jsp:attribute name="title">
      F.R.E.P. - Formulaire des objectifs
    </jsp:attribute>
    <jsp:body>
        <div class="container">
            <div class="jumbotron">
                <form:form method="post" modelAttribute="objectif" action="/objectifs/creer" class="form-inline">
                    <form:hidden path="numobjectif" />

                    <form:label path="libobjectif"> Libellé : </form:label>
                    <form:input path="libobjectif" class="form-control" />

                    <button type="submit" class="btn btn-success">Enregistrer</button>
                </form:form>
            </div>
        </div>
    </jsp:body>
</tags:layout>