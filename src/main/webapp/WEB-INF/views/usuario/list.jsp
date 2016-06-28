<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Lista de Usuários</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />" />
	</head>
	<body>
		<c:import url="../menu.jsp" />
		<br/>
		<fieldset>
			<legend>Lista de Usuários</legend>
			<table class="table">
				<tr>
					<th>Avatar</th>
					<th>
						<a href="<c:url value="/usuario/sort/asc/nome/page/1" />" >&darr;</a>
						Nome do Usuário
						<a href="<c:url value="/usuario/sort/desc/nome/page/1" />" >&uarr;</a>
					</th>
					<th>E-mail</th>
					<th>
						<a href="<c:url value="/usuario/sort/asc/dataCadastro/page/1" />" >&darr;</a>
						Data de Cadastro
						<a href="<c:url value="/usuario/sort/desc/dataCadastro/page/1" />" >&uarr;</a>
					</th>
					<th>
						<a href="<c:url value="/usuario/sort/asc/perfil/page/1" />" >&darr;</a>
						Perfil
						<a href="<c:url value="/usuario/sort/desc/perfil/page/1" />" >&uarr;</a>
					</th>
					<th>Ação</th>
				</tr>
				<c:forEach items="${page.content}" var="usuario" varStatus="i">
					<tr bgcolor="${i.count % 2 != 0 ? '#f1f1f1' : white}">
						<td>
							<a href='<c:url value="/avatar/update/${usuario.avatar.id}" />' title="Editar Avatar" >
								<img alt="avatar" src='<c:url value="/avatar/load/${usuario.avatar.id}" />' style="width: 25px; height: 25px;" />
							</a>
						</td>
						<td>${usuario.nome}</td>
						<td>${usuario.email}</td>
						<td>
							<fmt:parseDate value="${usuario.dataCadastro}" var="date" pattern="yyyy-MM-dd" />
							<fmt:formatDate value="${date}" type="date" />
						</td>
						<td>${usuario.perfil}</td>
						<td>
							<c:url value="/usuario/update/${usuario.id}" var="update" />
							<a href="${update}" title="Editar">&#9445</a>
							<a href="#" title="Excluir">&#9447</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<c:import url="../paginacao.jsp" />
		</fieldset>
	</body>
</html>