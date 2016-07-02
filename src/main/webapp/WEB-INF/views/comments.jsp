<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="comentarios">
	<c:url value="/comentario" var="save" />
	<form:form action="${save}" modelAttribute="comentario" method="post">
		<input type="hidden" value="${postagem.permalink}" name="permalink" />
		<div>
			<form:label path="texto">Digite seu comentário</form:label>
			<form:textarea path="texto" rows="5" cols="80" />
			<form:errors path="texto" cssClass="error" />
		</div>
		<br/>
		
		<div>
			<input type="submit" value="Salvar" />
			<input type="reset" value="Limpar" />
		</div>
	</form:form>
	<hr/>
	<c:forEach items="${postagem.comentarios}" var="comentario" >
		<div class="comentarios">
			<img class="comentarios-avatar" src="<c:url value="/avatar/load/${comentario.usuario.avatar.id}" />">
			<em>
				${comentario.usuario.nome} - 
				<fmt:parseDate value="${comentario.dataComentario}" var="date" pattern="yyyy-MM-dd'T'HH:mm:ss" />
				<fmt:formatDate value="${date}" type="both"/>
			</em>
			<p>${comentario.texto}</p>
		</div>
	</c:forEach>
</div>