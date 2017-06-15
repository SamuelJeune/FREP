<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:layout>
    <jsp:attribute name="title">
      F.R.E.P.
    </jsp:attribute>
    <jsp:body>
        <div class="container">


            <h2 class="text-center">F.R.E.P.</h2>
            <br>
            <div class="jumbotron">
                <div class="container">

                    <div class="row">
                        <div class="col-6">
                            <p>Bienvenu dans l'application F.R.E.P</p>
                        </div>
                        <div class="col-4">
                        </div>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-md-5">
                                <a class="btn btn-primary btn-lg btn-block" href="apprenants">Accéder aux éleves</a>
                            </div>
                            <div class="col-md-2"></div>
                            <div class="col-md-5">
                                <a class="btn btn-primary btn-lg btn-block" href="jeux">Accéder aux jeux</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </jsp:body>
</tags:layout>