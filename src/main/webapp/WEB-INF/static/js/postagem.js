$(document).ready(function() {
	$(document).on('click', 'button[id*="button_"]', function() {
		var pageNumber = $(this).val();

		tbody(pageNumber);
	});
	
	$('#search').keyup(function() {
		var value = $(this).val();
		
		var exp = new RegExp('^[a-zA-Z0-9]');
		if(exp.test(value)) {
			search(value);
		} else {
			tbody(1);
		}
	});
	
	$("#save-ajax").submit(function(event) {
		event.preventDefault();
		var token = $("input[name='_csrf']").attr("value");
		
		$.post('/blog/postagem/ajax?_csrf=' + token, $(this).serialize())
			.done(function(result) {
				if(result.status != 'FAIL') {
					$('#info').empty().append(
							"<p>Postagem salva com sucesso!</p>" +
							"<p>Abrir postagem: <a href='/blog/'" + result.postagem.permalink + "'>" + result.postagem.titulo + "</a></p>"
					);
					
					$('#save-ajax').each(function() {
						this.reset();
					});
					
					$('#titulo-error').empty();
					$('#texto-error').empty();
				} else {
					$('#titulo-error').empty().append(result.tituloError);
					$('#texto-error').empty().append(result.textoError);
				}
			})
			.fail(function(error) {
				$('#info').empty().append(
					"<p>Error: status " + error.status + ", " + error.statusText + "</p>"
				);
			});
	});
});

function tbody(page) {
	var url = "/blog/postagem/ajax/page/" + page;
	var autorId = $("#table-ajax").attr("title");
	
	var titulo = $('#search').val();
	
	if(autorId > 0 && titulo.length > 0) {
		url = "/blog/postagem/ajax/autor/" + autorId + "/titulo/" + titulo + "/page/" + page;
	} else if(titulo.length > 0) {
		url = "/blog/postagem/ajax/titulo/" + titulo + "/page/" + page;
	} else if (autorId > 0) {
		url = "/blog/postagem/ajax/autor/" + autorId + "/page/" + page;
	}

	$("#table-ajax").load(url, function(response, status, xhr) {
		if (status == "error") {
			var msg = "Sorry but there was an error: ";
			$("#info").html(msg + xhr.status + " " + xhr.statusText);
		}
		
		// Isn't necessary anymore, it's here just for future reference in studies.
//		if(status == "success") {
//			$('button').each(function() {
//				var id = '#' + $(this).attr('id');
//				
//				if($(id).attr('disabled') == 'disabled') {
//					$(id).removeAttr('disabled');
//				}
//			});
//			
//			$('#button_' + page).attr('disabled', 'disabled');
//		}
	});
}

function search(value) {
	var url = "/blog/postagem/ajax/titulo/" + value + "/page/1";
	var autorId = $("#table-ajax").attr("title");
	
	if(autorId > 0) {
		url = "/blog/postagem/ajax/autor/" + autorId + "/titulo/" + value + "/page/1";
	}
	
	$('#table-ajax').load(url, function(response, status, xhr) {
		if(status == 'error') {
			var msg = "Sorry but there was an error: ";
			$("#info").html(msg + xhr.status + " " + xhr.statusText);
		}
	});
}