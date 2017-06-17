<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:layout>
    <jsp:attribute name="title">
      F.R.E.P. - Associer une action à un objectif
    </jsp:attribute>
    <jsp:body>
        <div class="container">
            <div class="jumbotron">
                <c:if test="${association.numaction != 0}"><c:url value="/actions/associer" var="formURL" /></c:if>
                <c:if test="${association.numobjectif != 0}"><c:url value="/objectifs/associer" var="formURL" /></c:if>
                <form:form method="post" modelAttribute="association" action="${formURL}" class="form-inline">
                    <form:label path="numaction">Action : </form:label>
                    <form:select path="numaction" items="${actions}" itemLabel="libaction" itemValue="numaction" class="form-control" />
                    <br />
                    <form:label path="numobjectif">Objectif : </form:label>
                    <form:select path="numobjectif" items="${objectifs}" itemLabel="libobjectif" itemValue="numobjectif" class="form-control" />

                    <button type="submit" class="btn btn-success">Enregistrer</button>
                </form:form>
            </div>
        </div>
    </jsp:body>
</tags:layout>