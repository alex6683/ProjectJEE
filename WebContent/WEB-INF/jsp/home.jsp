<%@ include file="/WEB-INF/jsp/head-bootstrap.jsp" %>

<html>

<body>
<div class="container">

    <legend><h1>Accueil</h1></legend>

    <p>Bienvenue dans le gestionnaire d'annuaire d'Aix Marseille universite.
        Cette annuaire offre la possibilite de rechercher des personnes et les groupes auquels ils appartiennents</p>
    <p>Voici les differentes fonctionnalites : </p>
    <ul class="table table-striped table-hover ">
        <li>Lister les personnes de l'annuaire</li>
        <li>Lister les groupes de l'annuaire</li>
        <li>Rechercher avec des mots clefs</li>
        <li>Acceder aux informations des personnes</li>
        <li>S'authentifer pour augmenter les privileges</li>
        <li>
            <ul>
                <li>D'acces aux informations detaillees des personnes</li>
                <li>D'ajout de nouveaux groupes dans l'annuaire</li>
                <li>De gestion du compte</li>
            </ul>
        </li>
    </ul>
    <p><a href="${pageContext.request.contextPath}/actions/connexion/registration">Inscrivez-vous </a> pour acceder aux privileges</p>

</div>
</body>


</html>