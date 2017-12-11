<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="head-bootstrap.jsp"%>

<div class="container">
    <legend><h1>${currentGroup.name}</h1></legend>

    <ul style="list-style-type: circle">
        <c:forEach items="${personsInGroup}" var="person">
            <li>
                <a href="${pageContext.request.contextPath}/actions/person/personData?id=${person.identifier}">
                        ${person.name} ${person.surname}
                </a>
            </li>
        </c:forEach>
    </ul>
</div>