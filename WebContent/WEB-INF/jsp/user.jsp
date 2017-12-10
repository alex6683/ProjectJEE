<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="header.jsp"%>

	<h1>Bienvenue ${personLogged.surname} ${personLogged.name}</h1>

	<h3>Vos informations</h3>
	<form:form method="POST" commandName="person">
		<table>
			<tr>
				<td>ID : </td>
				<td>${personLogged.identifier}</td>
			</tr>
			
			<tr>
				<td>Groupe :</td>
				<td>${groupPersonLogged.name}</td>
			</tr>
			
			<tr>
				<td>Nom :</td>
				<td>${personLogged.name}</td>
			</tr>

			<tr>
				<td>Prenom :</td>
				<td>${personLogged.surname}</td>
			</tr>

			<tr>
				<td>Email :</td>
				<td>${personLogged.email}</td>
			</tr>

			<tr>
				<td>Site web :</td>
				<td><a target="_blank" href="http://www.${personLogged.webSite}">${personLogged.webSite}</a></td>
			</tr>


			<tr>
				<td>Date de Naissance :</td>
				<td>${personLogged.dateBirth}</td>
			</tr>

			<tr>
				<td>Mot de passe :</td>
				<td>${personLogged.password}</td>
			</tr>

			<tr>
				<td>Description :</td>
				<td>${personLogged.description}</td>
			</tr>

			<tr>
			<td><a href="editUser" class="btn btn-info">Editer votre profil</a></td>
			<td><a href="log_out" class="btn btn-warning">Deconnexion</a></td>
			<td>
					<a href="deleteAccount?id=${personLogged.identifier}" class="btn btn-danger">
						Supprimer le compte</a>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>
