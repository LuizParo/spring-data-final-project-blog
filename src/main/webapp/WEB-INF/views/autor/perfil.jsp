<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Perfil</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />" />
	</head>
	<body>
		<c:import url="../menu.jsp" />
		<br/>
		<fieldset>
			<legend>Perfil</legend>
			<table class="table">
				<tr>
					<th>Nome do Autor</th>
					<th>E-mail do Autor</th>
					<th>Data de Cadastro</th>
					<th>Biografia</th>
					<th>Ação</th>
				</tr>
				<c:forEach items="${page.content != null ? page.content : autores}" var="autor" varStatus="i" >
					<tr bgcolor="${i.count % 2 != 0 ? '#f1f1f1' : 'white'}">
						<td>${autor.nome}</td>
						<td>${autor.usuario.email}</td>
						<td>
							<fmt:parseDate value="${autor.usuario.dataCadastro}" var="date" pattern="yyyy-MM-dd" />
							<fmt:formatDate value="${date}" type="date" />
						</td>
						<td>${autor.biografia}</td>
						<td>
							<c:url value="/autor/update/${autor.id}" var="update" />
							<a href="${update}" title="Editar">&#9445</a>
							<c:url value="/autor/delete/${autor.id}" var="delete" />
							<a href="${delete}" title="Excluir">&#9447</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<c:if test="${page != null}">
				<c:import url="../paginacao.jsp" />
			</c:if>
		</fieldset>
	</body>
</html>