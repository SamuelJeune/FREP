<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tags:layout>
    <jsp:attribute name="title">
      PermisPiste - Liste des actions
    </jsp:attribute>
    <jsp:body>

        <div class="container-fluid">
            <h1>Liste des actions : </h1>

            <c:if test="${not empty msg}">
                <div class="alert" style="background-color: #f39c12; color:whitesmoke;"> ${msg} </div>
            </c:if>

            <div class="jumbotron">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>NUMERO</th>
                            <th>ACTION PARENT</th>
                            <th>NOM DE L'ACTION</th>
                            <th>SCORE MINIMUM<th>
                            <th>ACTIONS</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${actions}" var="action">
                        <spring:url value="/actions/${action.numaction}" var="detailsURL" />
                        <spring:url value="/actions/${action.numaction}/modifier" var="editURL" />
                        <spring:url value="/actions/${action.numaction}/supprimer" var="deleteURL" />
                        <tr>
                            <td>${action.numaction}</td>
                            <td><a href="/actions/${action.actNumaction}">${action.actNumaction}</a></td>
                            <td>${action.libaction}</td>
                            <td>${action.scoremin}</td>
                            <td>
                                <a href="${detailsURL}"><button  class="btn" type="submit" style="background-color: #18bc9c; color:whitesmoke;">Détails</button></a>
                                <a href="${editURL}"><button  class="btn" type="submit" style="background-color: #2c3e50; color:whitesmoke;">Modifier</button></a>
                                <form method="post" action="${deleteURL}" style="display: inline"><button  class="btn" type="submit" style="background-color: #e74c3c; color: whitesmoke;">Supprimer</button></form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <a href="/actions/ajouter">
                    <button  class="btn" type="submit" style="background-color: dodgerblue; color:whitesmoke;">
                        <span class="glyphicon glyphicon-plus"></span> Ajouter une action
                    </button>
                 </a>

            </div>
        </div>
    </jsp:body>
</tags:layout>