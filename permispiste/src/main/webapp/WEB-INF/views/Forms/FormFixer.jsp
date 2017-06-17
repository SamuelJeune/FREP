<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:layout>
    <jsp:attribute name="title">
      F.R.E.P. - Fixer un objectif
    </jsp:attribute>
    <jsp:body>
        <div class="container">
            <div class="jumbotron">
                <c:if test="${fixe.nummission != 0}"><c:url value="/missions/fixer" var="formURL" /></c:if>
                <c:if test="${fixe.numobjectif != 0}"><c:url value="/objectifs/fixer" var="formURL" /></c:if>
                <form:form method="post" modelAttribute="fixe" action="${formURL}" class="form-inline">
                    <form:label path="nummission">Mission : </form:label>
                    <form:label path="numobjectif">Objectif : </form:label>
                    <form:select path="nummission" items="${missions}" itemLabel="libmission" itemValue="nummission" class="form-control" />
                    <form:select path="numobjectif" items="${objectifs}" itemLabel="libobjectif" itemValue="numobjectif" class="form-control" />
                    
                    <button type="submit" class="btn btn-success">Enregistrer</button>
                </form:form>
            </div>
        </div>
    </jsp:body>
</tags:layout>