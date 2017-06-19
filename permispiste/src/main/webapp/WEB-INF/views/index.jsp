<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:layout>
    <jsp:attribute name="title">
      PermisPiste
    </jsp:attribute>
    <jsp:body>
        <div class="container-fluid">

            <h1>Accueil</h1>

            <div class="jumbotron">
                <table>
                    <tr>
                        <td>
                            <h2>Bienvenue dans l'application Permis Piste!</h2>
                            <div class="container">
                                <p> Cette application permettra de suivre pas à pas votre apprentissage</p>
                                <p>En vous souhaitant une agréable visite !</p>
                                <br/>
                                <h3>Pour commencer, vous pouvez vous :</h3>
                                <ol>
                                    <li> Enregistrer en tant qu'apprenant </li>
                                    <li> Inscrire à un jeu depuis la liste </li>
                                    <li> Accéder à la liste des missions pour valider des actions </li>
                                </ol>
                            </div>
                        </td>
                        <td>
                            <img class="img-responsive" src="<c:url value="/resources/images/polytech.png" />">
                        </td>
                    </tr>
                </table>
            </div>

        </div>
    </jsp:body>
</tags:layout>