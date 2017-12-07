<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 07/12/17
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/header.jsp"%>
<html>
<head>
  <title>$Title$</title>
</head>
<body>
<h1>Accueil</h1>

<%-- rediriger le contr�leur hello --%>
<%-- 	<c:redirect url="/actions/connexion/login" /> --%>

<p>Bienvenue sur la page d'accueil du gestionnaire d'annuaire des
  �tudiants de master informatique de l'universit� d'Aix-Marseille</p>
<p>Vous pouvez effectuer les actions suivantes :</p>
<ul class="table table-striped table-hover ">
  <li>S'inscrire au site</li>
  <li>Visualiser les personnes inscrites dans l'annuaire</li>
  <li>Visualiser les groupes disponibles dans l'annuaire</li>
  <li>Acceder � plus d'informations sur les personnes inscrites au
    site en vous connectant</li>
</ul>
<%--<a href="/gestionnaireAnnuaire/actions/connexion/inscription">Vous pouvez vous inscrire ici</a>--%>
<!--
    <a href="">Liste des personnes</a>

    <a href="">Liste des groupes</a>
    -->

</body>
</html>
