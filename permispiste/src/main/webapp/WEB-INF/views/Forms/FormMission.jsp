<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>

<tags:layout>
    <jsp:attribute name="title">
      PermisPiste - Formulaire des missions
    </jsp:attribute>
    <jsp:body>
        <div class="container">
            <h1 class="text-center">Formulaire des missions</h1>

            <div class="jumbotron">
                <form:form method="post" modelAttribute="mission" action="/missions/creer" class="form-inline">
                    <form:hidden path="nummission" />

                    <form:label path="libmission"> Libellé : </form:label>
                    <form:input path="libmission" class="form-control" />

                    <form:label path="numjeu"> Jeu associé : </form:label>
                    <form:select path="numjeu" items="${jeux}" itemLabel="libellejeu" itemValue="numjeu" />

                    <button type="submit" class="btn btn-success">Enregistrer</button>
                </form:form>
            </div>
        </div>
    </jsp:body>
</tags:layout>