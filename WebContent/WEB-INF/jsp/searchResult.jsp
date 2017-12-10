<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="head-bootstrap.jsp"%>

<h1>Resultats de la recherche de "${personSearcher}"</h1>

<c:if test="${not empty resultSearchGroup}">
	<a href="#groupsFound" class="btn btn-info">Descendre vers les groupes
		trouves</a>
</c:if>

<c:if test="${not empty resultSearchPerson}">
	<h3 id="personsFound">Personnes trouvees</h3>
</c:if>
<c:if test="${empty resultSearchPerson}">
	<h3>Aucunes personnes trouvees</h3>
</c:if>
<div class="container">
	<ul style="list-style-type: circle">
		<c:forEach items="${resultSearchPerson}" var="personFound">
			<li><a
				href="${pageContext.request.contextPath}/actions/person/personData?id=${personFound.identifier}">
					${personFound.surname} ${personFound.name} </a></li>
		</c:forEach>
	</ul>
</div>

<c:if test="${not empty resultSearchPerson}">
	<a href="#personsFound" class="btn btn-info">Remonter aux personnes
		trouvees</a>
</c:if>

<c:if test="${not empty resultSearchGroup}">
	<h3 id="groupsFound">Groupes trouves</h3>
</c:if>
<c:if test="${empty resultSearchGroup}">
	<h3>Aucuns groupes trouves</h3>
</c:if>
<div class="container">
	<ul style="list-style-type: circle">
		<c:forEach items="${resultSearchGroup}" var="groupFound">
			<li><a
				href="${pageContext.request.contextPath}/actions/person/personInGroup?id=${groupFound.identifier}">
					${groupFound.name} </a></li>
		</c:forEach>
	</ul>
</div>