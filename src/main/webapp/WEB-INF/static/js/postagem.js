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
});

function tbody(page) {
	var url = "/blog/postagem/ajax/page/" + page;
	
	var titulo = $('#search').val();
	if(titulo.length > 0) {
		url = "/blog/postagem/ajax/titulo/" + titulo + "/page/" + page;
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
	
	$('#table-ajax').load(url, function(response, status, xhr) {
		if(status == 'error') {
			var msg = "Sorry but there was an error: ";
			$("#info").html(msg + xhr.status + " " + xhr.statusText);
		}
	});
}