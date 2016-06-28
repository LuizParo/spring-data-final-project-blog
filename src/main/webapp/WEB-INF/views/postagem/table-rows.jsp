<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:forEach items="${page.content}" var="postagem" varStatus="i">
	<tr bgcolor="${i.count % 2 != 0 ? '#f1f1f1' : white}">
		<td>${postagem.id}</td>
		<td>${postagem.titulo}</td>
		<td>${postagem.permalink}</td>
		<td>
			<fmt:parseDate value="${postagem.dataPostagem}" var="date" pattern="yyyy-MM-dd'T'HH:mm:ss" />
			<fmt:formatDate value="${date}" type="both"/>
		</td>
		<td>${postagem.autor.nome}</td>
		<td>
			<c:forEach items="${postagem.categorias}" var="categoria">
				[ ${categoria.descricao} ]
			</c:forEach>
		</td>
		<td>
			<c:url value="/postagem/update/${postagem.id}" var="update" />
			<a href="${update}" title="Editar">&#9445</a>
			<c:url value="/postagem/delete/${postagem.id}" var="delete" />
			<a href="${delete}" title="Excluir">&#9447</a>
		</td>
	</tr>
</c:forEach>