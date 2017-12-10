<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="header.jsp"%>

	<form:form method="POST" commandName="person" >
		<table>
			<tr>
				<td>Email :</td>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<td>Mot de passe :</td>
				<td><form:password path="password" /></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" class="btn btn-success" value="Connection"/></td>
				<td colspan="3"> 
					<a href="inscription" class="btn btn-info" >Inscription</a>
				</td>
			</tr>
		</table>
	</form:form>


</body>
</html>