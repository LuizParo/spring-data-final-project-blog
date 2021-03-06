<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${postagem.titulo}</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />" />
	</head>
	<body>
		<fieldset class="header">
			<h1>Blog do Curso de Spring Data JPA | Devmedia</h1>
		</fieldset>
		
		<c:import url="menu.jsp" />
		<br/>
		
		<fieldset>
			<div>
				<div>
					<h2>${postagem.titulo}</h2>
					<fmt:parseDate value="${postagem.dataPostagem}" var="date" pattern="yyyy-MM-dd'T'HH:mm:ss" />
					<p>
						Autor: <a href='<c:url value="/autor/${postagem.autor.id}/page/1" />'>${postagem.autor.nome}</a> | Data: <fmt:formatDate value="${date}" pattern="dd/MM/yyyy HH:mm:ss" />
					</p>
				</div>
				<div>
					<p class="post-texto">${postagem.texto}</p>
				</div>
				<div>
					<p class="post-categ">
						<span>Categorias: </span>
						<c:forEach items="${postagem.categorias}" var="categoria">
							<a href='<c:url value="/categoria/${categoria.permalink}/page/1"/>' title="${categoria.descricao}">${categoria.descricao}</a>
						</c:forEach>
					</p>
				</div>
				<div class="post-autor">
					<img src='<c:url value="/avatar/load/${postagem.autor.usuario.avatar.id}" />' class="post-avatar" />
					<p><strong>${postagem.autor.nome}</strong></p>
					<p>${postagem.autor.biografia}</p>
				</div>
			</div>
			<c:if test="${logado == null}">
				<p>Apenas usuários logados podem comentar neste post!</p>
			</c:if>
			<c:import url="comments.jsp" />
		</fieldset>
	</body>
</html>