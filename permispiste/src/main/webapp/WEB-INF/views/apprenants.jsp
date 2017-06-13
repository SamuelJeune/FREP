<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:layout>
    <jsp:attribute name="title">
      F.R.E.P. - Liste des apprenants
    </jsp:attribute>
    <jsp:body>
        <table>
            <thead>
                <tr>
                    <th>Num. apprenant</th>
                    <th>Nom apprenant</th>
                    <th>Prénom apprenant</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${apprenants}" var="apprenant">
                <tr>
                    <td>${apprenant.numapprenant}</td>
                    <td>${apprenant.nomapprenant}</td>
                    <td>${apprenant.prenomapprenant}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </jsp:body>
</tags:layout>