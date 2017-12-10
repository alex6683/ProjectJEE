<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="header.jsp"%>

<form:form method="POST" commandName="group">
    <table>
        <tr>
            <td>Nom du groupe :</td>
            <td><form:input path="name" /></td>
            <td><small class="errors help-block"><form:errors
                    path="name" cssClass="error" /></small></td>
        </tr>
        <tr>
            <td colspan="3"><input type="submit" class="btn btn-warning" /></td>
        </tr>
    </table>


</form:form>
