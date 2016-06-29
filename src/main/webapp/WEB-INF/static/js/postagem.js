$(document).ready(function() {
	$('button[id*="button_"]').click(function() {
		var pageNumber = $(this).val();

		tbody(pageNumber);
	});
});

function tbody(page) {
	var url = "/blog/postagem/ajax/page/" + page;

	$("#tbody").load(url, function(response, status, xhr) {
		if (status == "error") {
			var msg = "Sorry but there was an error: ";
			$("#info").html(msg + xhr.status + " " + xhr.statusText);
		}
		
		if(status == "success") {
			$('button').each(function() {
				var id = '#' + $(this).attr('id');
				
				if($(id).attr('disabled') == 'disabled') {
					$(id).removeAttr('disabled');
				}
			});
			
			$('#button_' + page).attr('disabled', 'disabled');
		}
	});
}