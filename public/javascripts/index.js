$(document).on('ready', function(event) {
	
	createValidations();
	
	$('#principal-temas a').on('click', function() {
		$('#principal-temas a').removeClass('active');
		$(this).addClass('active');
		$($("#temas").find("[data-id='" + $(this).data('id') + "']")).trigger('click');
		mySwiper.swipeTo(1);
	});
	
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
						table.find('.total-participantes').html(data[i].totalDeParticipantes);
						$('#eventos-principal').append(table.find('tr'));
					}
				}else{
					$('#visualizacao-nenhum-evento').show();
				}
			}
		});
	});
	
	$('#form-novo-evento').on('submit', function(e){

		e.preventDefault();
		
		var tooltips = $('#criacao-temas .tooltip');
		
		$.each(tooltips, function(){
			$(this).css('left',parseInt($('.tooltip').css('left')) + 90 + 'px');
		});
		
		$.ajax({
			url : $(this).attr('action'),
			data: new FormData(this),
			type : 'POST',
			mimeType:"multipart/form-data",
		    contentType: false,
	        cache: false,
	        processData:false,
			success : function(result) {
				$('#temas .active').trigger('click');
				$('#form-novo-evento')[0].reset();
				
				$('.top-right').notify({
					 message : {
					 	text : 'Evento criado com sucesso'
					 },
					 type : "success"
				 }).show(); 
			},
			error: function (){
				 $('.top-right').notify({
					 message : {
					 	text : 'Erro ao criar o evento'
					 },
					 type : "danger"
				 }).show(); 
			}
		});
	});

	$('#temas a:first').trigger('click')

});

function createValidations(){
	
	jQuery.validator.addMethod("menorQueAtual", function(value, element, params) {
	    if (!/Invalid|NaN/.test(new Date(value))) {
	    	
	        return new Date(new Date(value).setHours(0,0,0,0)) >= new Date(new Date().setHours(0,0,0,0));
	    }

	    return isNaN(value) && isNaN($(params).val()) 
	        || (Number(value) > Number($(params).val())); 
	},'A data deve ser maior que a atual.');
	
	$('#form-novo-evento').validate({
        rules:{ 
            titulo:{ 
                required: true,
                minlength: 5
            },
            data:{ 
                required: true,
                menorQueAtual: true
            },
            descricao:{
            	required: true,
            	minlength: 10
            },
            'temas[]':{
            	required: true,
            	minlength: 1
            }
        },
        messages:{
        	titulo:{ 
                required: "É necessário um título.",
                minlength: "O título deve conter no mínimo 5 caracteres."
            },
            data:{ 
                required: "A data é obrigatoria."
            },
            descricao:{
            	required: "É necessário uma descrição",
                minlength: "A descrição deve conter no mínimo 10 caracteres."
            },
            'temas[]':{
            	required: "É necessário escolher pelo menos 1 tema.",
            	minlength: "É necessário escolher pelo menos 1 tema."
            }
            
        }
         
    });
}