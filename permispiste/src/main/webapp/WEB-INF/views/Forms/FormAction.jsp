<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>

<tags:layout>
    <jsp:attribute name="title">
      PermisPiste - Formulaire des actions
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
                    <form:input path="libaction" class="form-control" type="text" maxlength="20" size="20"/>

                    <form:label path="scoremin"> Score minimum : </form:label>
                    <form:input path="scoremin" class="form-control" type="number" step="1" value="0" min="0"/>

                    <button type="submit" class="btn btn-success">Enregistrer</button>
                </form:form>
            </div>
        </div>
    </jsp:body>
</tags:layout>