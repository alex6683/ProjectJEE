<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="header.jsp"%>
<h1>Liste des groupes</h1>

<div>
	<table>
		<c:forEach items="${groupsList}" var="groupInList">
			<tr>
				<td>
					<a href="${pageContext.request.contextPath}/actions/person/showPersInGroup?id=${groupInList.identifier}">
							${groupInList.name}
					</a>
				</td>
				<c:if test="${personLogged != null}">

					<td>
							<%--EDIT ICI--%>
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/actions/lists/deleteGroup?id=${groupInList.identifier}">
							Supprimer</a>
					</td>
				</c:if>

			</tr>
		</c:forEach>
	</table>
	<%--<tr>--%>
	<%--<c:forEach items="${groupsList}" var="groupInList">--%>
	<%--<td><a--%>
	<%--href="${pageContext.request.contextPath}/actions/person/showPersInGroup?id=${groupInList.identifier}">--%>
	<%--${groupInList.name}</a>--%>
	<%--<c:if test="${personLogged != null}">--%>
	<%--<td>--%>
	<%--<a--%>
	<%--href="${pageContext.request.contextPath}/actions/lists/deleteGroup?id=${groupInList.identifier}">--%>
	<%--Supprimer</a>--%>
	<%--</c:if>--%>
	<%--</td>--%>
	<%--</td>--%>

	<%--</c:forEach>--%>
	<%--</tr>--%>
</div>

<c:if test="${personLogged != null}">
	<p>
		<a href="${pageContext.request.contextPath}/actions/group/addGroup">Ajouter un nouveau groupe</a>
	</p>
</c:if>

</body>