$(document).on('ready', function(event) {
	$('#temas a').on('click', function() {
		$('#temas a').removeClass('active');
		$(this).addClass('active');

		$('#eventos-principal').empty();

		$.ajax({
			url : '/eventos/tema/' + $(this).data('id'),
			type : 'GET',
			success : function(result) {
				var data = JSON.parse(result);
				var tableTemplate = $('#evento-template');
				
				if (data.length > 0){
					$('#visualizacao-nenhum-evento').hide();
					for (var i = 0; i < data.length; i++) {
						var table = tableTemplate.clone();
	
						table.find('tr').attr('data-info', JSON.stringify(data[i]));
	
						table.find('.titulo').html(data[i].titulo);
						table.find('.data').html(new Date(data[i].data).toLocaleDateString());
	
						$('#eventos-principal').append(table.find('tr'));
					}
				}else{
					$('#visualizacao-nenhum-evento').show();
				}
			}
		});
	});

	$('#temas a:first').trigger('click')

});