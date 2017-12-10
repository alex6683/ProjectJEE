<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="header.jsp"%>

<div>
	<h1>${groupCurrentlyViewed.name} </h1>

	<ul style="list-style-type: circle">
		<c:forEach items="${persInGroup}" var="persInGroup">
			<li>
				<a href="${pageContext.request.contextPath}/actions/person/showPerson?id=${persInGroup.identifier}">
						${persInGroup.name} ${persInGroup.surname}
				</a>
			</li>
		</c:forEach>
	</ul>
</div>
</body>