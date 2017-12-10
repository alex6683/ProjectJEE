<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="header.jsp"%>
<h1>Resultats de la recherche</h1>

<c:if test="${not empty resultSearchGroup}">
	<a href="#groupes" class="btn btn-default">Descendre vers les groupes
		trouves</a>
</c:if>

<c:if test="${not empty resultSearchPerson}">
	<h3 id="personnes">Personnes trouvees</h3>
</c:if>
<c:if test="${empty resultSearchPerson}">
	<h3>Aucunes personnes trouvees</h3>
</c:if>
<div class="container">
	<ul style="list-style-type: square">
		<c:forEach items="${resultSearchPerson}" var="personFound">
			<li><a
				href="${pageContext.request.contextPath}/actions/person/showPerson?id=${personFound.identifier}">
					${personFound.surname} ${personFound.name} </a></li>
		</c:forEach>
	</ul>
</div>

<c:if test="${not empty resultSearchPerson}">
	<a href="#personnes" class="btn btn-default">Remonter aux personnes
		trouvees</a>
</c:if>

<c:if test="${not empty resultSearchGroup}">
	<h3 id="groupes">Groupes trouves</h3>
</c:if>
<c:if test="${empty resultSearchGroup}">
	<h3>Aucuns groupes trouves</h3>
</c:if>
<div class="container">
	<ul style="list-style-type: square">
		<c:forEach items="${resultSearchGroup}" var="groupFound">
			<li><a
				href="${pageContext.request.contextPath}/actions/person/showPersInGroup?id=${groupFound.identifier}">
					${groupFound.name} </a></li>
		</c:forEach>
	</ul>
</div>

</body>
</html>