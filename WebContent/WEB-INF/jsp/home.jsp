<%@ include file="/WEB-INF/jsp/head-bootstrap.jsp" %><html>

<head>
    <title>index</title>
</head>
<body>
<h1>Accueil</h1>

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
<a href="${pageContext.request.contextPath}/actions/connexion/registration">Vous pouvez vous inscrire ici</a>

</body>


</html>