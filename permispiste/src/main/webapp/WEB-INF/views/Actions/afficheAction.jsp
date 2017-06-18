<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>

<tags:layout>
    <jsp:attribute name="title">
      PermisPiste - Action : ${action.libaction}
    </jsp:attribute>
    <jsp:body>

        <div class="container-fluid">
            <h1>Action : ${action.libaction}</h1>

            <c:if test="${not empty msg}">
                <div class="alert" style="background-color: #f39c12; color:whitesmoke;"> ${msg} </div>
            </c:if>

            <div class="jumbotron">

                <h2>Action parent :</h2>
                <c:choose>
                    <c:when test="${not empty action.actNumaction}">
                        <p><a href="/actions/${action.parent.numaction}"> ${action.parent.numaction} - ${action.parent.libaction}</a></p>
                    </c:when>
                    <c:otherwise>
                        <p>Cette action ne possède pas d'action parent.</p>

                        <a href="/actions/${action.numaction}/modifier">
                            <button  class="btn" type="submit" style="background-color: dodgerblue; color:whitesmoke;">
                                <span class="glyphicon glyphicon-plus"></span> Ajouter une action parent
                            </button>
                        </a>
                    </c:otherwise>
                </c:choose>

                <c:if test="${not empty actionsFilles}">
                    <h2>Compléter cette action est nécessaire pour valider les actions suivantes : </h2>
                    <ul>
                        <c:forEach items="${actionsFilles}" var="action">
                            <li><a href="/actions/${action.numaction}">${action.numaction} - ${action.libaction}</a></li>
                        </c:forEach>
                    </ul>
                </c:if>

                <c:choose>
                    <c:when test="${objectifs.size() > 0}">
                        <h2>Cette action est inclue dans les objectifs suivants : </h2>
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>NUMERO</th>
                                    <th>NOM DE L'OBJECTIF</th>
                                    <th>ACTION</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${objectifs}" var="objectif">
                                    <tr>
                                        <td><a href="/objectifs/${objectif.numobjectif}">Objectif n°${objectif.numobjectif}</a></td>
                                        <td>${objectif.libobjectif}</td>
                                        <td>
                                            <form method="post" action="/actions/${action.numaction}/retirer-objectif/${objectif.numobjectif}" style="display: inline">
                                                <button  class="btn" type="submit" style="background-color: #e74c3c; color: whitesmoke;">Supprimer </button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <p>Cette action n'est inclue dans aucun objectif.</p>
                    </c:otherwise>
                </c:choose>

                <a href="/actions/${action.numaction}/ajouter-objectif">
                    <button  class="btn" type="submit" style="background-color: dodgerblue; color:whitesmoke;">
                        <span class="glyphicon glyphicon-plus"></span> Ajouter un objectif
                    </button>
                </a>
            </div>
        </div>
    </jsp:body>
</tags:layout>