<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="head-bootstrap.jsp"%>

<h1>Liste des groupes</h1>

<div>
	<table>
		<c:forEach items="${groupsList}" var="groupInList">
			<tr>
				<td>
					<a href="${pageContext.request.contextPath}/actions/person/personInGroup?id=${groupInList.identifier}">
							${groupInList.name}
					</a>
				</td>
				<c:if test="${personLogged != null}">
					<td>
						<a href="${pageContext.request.contextPath}/actions/lists/deleteGroup?id=${groupInList.identifier}">
							Supprimer groupe vide
						</a>
					</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
</div>

<c:if test="${personLogged != null}">
	<tr>
		<td colspan="3">
			<a href="${pageContext.request.contextPath}/actions/group/addGroup" class="btn btn-info" >Ajouter un groupe</a>
		</td>
	</tr>
</c:if>