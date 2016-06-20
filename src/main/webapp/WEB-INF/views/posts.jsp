<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Home</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />" />
	</head>
	<body>
		<fieldset class="header">
			<h1>Blog do Curso de Spring Data JPA | Devmedia</h1>
		</fieldset>
		
		<c:import url="menu.jsp" />
		<br/>
		
		<fieldset>
			<c:forEach items="${postagens}" var="postagem">
				<div>
					<div>
						<h2>
							<a href='<c:url value="/${postagem.permalink}" />' title="${postagem.titulo}">${postagem.titulo}</a>
						</h2>
						<fmt:parseDate value="${postagem.dataPostagem}" var="date" pattern="yyyy-MM-dd'T'HH:mm:ss" />
						<p>
							Autor: <a href='<c:url value="/autor/${postagem.autor.nome}" />'>${postagem.autor.nome}</a> | Data: <fmt:formatDate value="${date}" pattern="dd/MM/yyyy HH:mm:ss" />
						</p>
					</div>
					<div>
						<p class="post-texto">
							<c:forTokens items="${postagem.texto}" var="resumo" delims="." begin="0" end="1">
								${resumo}
							</c:forTokens>
							<a href='<c:url value="/${postagem.permalink}" />'>[Leia Mais]</a>
						</p>
					</div>
					<div>
						<p class="post-categ">
							<span>Categorias: </span>
							<c:forEach items="${postagem.categorias}" var="categoria">
								<a href='<c:url value="/categoria/${categoria.permalink}"/>' title="${categoria.descricao}">${categoria.descricao}</a>
							</c:forEach>
						</p>
					</div>
				</div>
			</c:forEach>
		</fieldset>
	</body>
</html>