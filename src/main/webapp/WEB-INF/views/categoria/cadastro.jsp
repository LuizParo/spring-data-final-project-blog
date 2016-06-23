<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Categoria</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />" />
	</head>
	<body>
		<c:import url="../menu.jsp" />
		<br/>
		
		<c:url var="save" value="/categoria" />
		<form:form modelAttribute="categoria" action="${save}" method="post">
			<fieldset class="grupo">
				<legend>Cadastro de Categoria</legend>
				<form:hidden path="id"/>
				<div class="campo">
					<form:label path="descricao">Descrição da Categoria</form:label><br/>
					<form:input type="text" path="descricao" title="Descrição da Categoria" required="true" size="60" />
				</div>
				<div>
					<input type="submit" value="Salvar" />
					<input type="reset" value="Limpar" />
				</div>
			</fieldset>
		</form:form>
		
		<fieldset class="grupo">
			<legend>Lista de Categorias</legend>
			<table class="table">
				<tr>
					<th>Código</th>
					<th>Descrição</th>
					<th>Permalink</th>
					<th>Ação</th>
				</tr>
				<c:forEach items="${page.content}" var="categoria" varStatus="i">
					<tr bgcolor="${i.count % 2 != 0 ? '#f1f1f1' : white}">
						<td>${categoria.id}</td>
						<td>${categoria.descricao}</td>
						<td>${categoria.permalink}</td>
						<td>
							<c:url value="/categoria/update/${categoria.id}" var="update" />
							<a href="${update}" title="Editar">&#9445</a>
							<c:url value="/categoria/delete/${categoria.id}" var="delete" />
							<a href="${delete}" title="Excluir">&#9447</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div align="center">
				[
					<c:forEach var="p" begin="1" end="${page.totalPages}">
						<c:choose>
							<c:when test="${p - 1 eq page.number}">
								<label style="font-size: 18pt;">${p}</label>
							</c:when>
							<c:otherwise>
								<a href='<c:url value="/categoria/page/${p}" />' title="Go to ${p}">${p}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				]
			</div>
		</fieldset>
	</body>
</html>