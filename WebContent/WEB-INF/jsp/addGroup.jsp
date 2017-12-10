<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="head-bootstrap.jsp"%>

<form:form method="POST" commandName="group">
    <table>
        <tr>
            <td>Nom du groupe : </td>
            <td><form:input path="name" /></td>
            <td><small class="errors help-block"><form:errors
                    path="name" cssClass="error" /></small></td>
            <td colspan="3"><input type="submit" value="Ajouter" class="btn btn-info" /></td>
        </tr>
    </table>
</form:form>
