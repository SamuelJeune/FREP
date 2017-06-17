<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>

<tags:layout>
    <jsp:attribute name="title">
      F.R.E.P. - Formulaire des actions
    </jsp:attribute>
    <jsp:body>
        <div class="container">
            <div class="jumbotron">
                <form:form method="post" modelAttribute="action" action="/actions/creer" class="form-inline">
                    <form:hidden path="numaction" />

                    <form:label path="actNumaction"> Action parent : </form:label>
                    <form:select path="actNumaction">
                        <form:option value="${null}" label="Aucune" />
                        <form:options items="${actions}" itemValue="numaction" itemLabel="libaction" />
                    </form:select>

                    <form:label path="libaction"> Libellé : </form:label>
                    <form:input path="libaction" class="form-control"/>

                    <form:label path="scoremin"> Score minimum : </form:label>
                    <form:input path="scoremin" class="form-control"/>

                    <button type="submit" class="btn btn-success">Enregistrer</button>
                </form:form>
            </div>
        </div>
    </jsp:body>
</tags:layout>