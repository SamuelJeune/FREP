<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>

<tags:layout>
    <jsp:attribute name="title">
      F.R.E.P. - Liste des apprenants
    </jsp:attribute>
    <jsp:body>
        <div class="container">
            <div class="jumbotron">
                <form:form method="post" modelAttribute="apprenant" action="/apprenants/creer" class="form-inline">
                    <form:hidden path="numapprenant" />

                    <form:label path="nomapprenant"> Nom : </form:label>
                    <form:input path="nomapprenant" class="form-control"/>

                    <form:label path="prenomapprenant"> Prénom : </form:label>
                    <form:input path="prenomapprenant" class="form-control"/>

                    <button type="submit" class="btn btn-success">Enregistrer</button>
                </form:form>
            </div>
        </div>
    </jsp:body>
</tags:layout>