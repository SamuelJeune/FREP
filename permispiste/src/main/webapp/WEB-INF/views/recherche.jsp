<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:layout>
    <jsp:attribute name="title">
      PermisPiste
    </jsp:attribute>
    <jsp:body>
        <div class="container-fluid">

            <h1>Résultat de la recherche "${recherche}" : </h1>
            <ul>
                <c:forEach items="${apprenants}" var="apprenant">
                    <li><a href="/apprenants/${apprenant.numapprenant}">${apprenant.prenomapprenant} ${apprenant.nomapprenant}</a></li>
                </c:forEach>
            </ul>
        </div>
    </jsp:body>
</tags:layout>