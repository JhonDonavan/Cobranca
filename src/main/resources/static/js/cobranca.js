//exclusao de titulos
$('#confirmacaoExclusaoModalTitulo').on(
		'show.bs.modal',
		function(event) {

			var button = $(event.relatedTarget);

			var idTitulo = button.data('id');
			var descricaoTitulo = button.data('descricao');

			var modal = $(this);
			var form = modal.find('form');
			var action = form.data('url-base');

			if (!action.endsWith('/')) {
				action += '/';
			}
			form.attr('action', action + idTitulo);

			modal.find('.modal-body span').html(
					'Tem certeza que deseja excluir o titulo de <strong>'
							+ descricaoTitulo + '<strong/>?');
		});

// exclusao de clientes
$('#confirmacaoExclusaoModalCliente').on(
		'show.bs.modal',
		function(event) {

			var button = $(event.relatedTarget);

			var idCliente = button.data('id');

			var nomeCliente = button.data('nome');

			var modal = $(this);

			var form = modal.find('form');

			var action = form.data('url-base');

			if (!action.endsWith('/')) {
				action += '/';
			}

			form.attr('action', action + idCliente);

			modal.find('.modal-body span').html(
					'tem certeza que deseja ecluir o cliente <strong>'
							+ nomeCliente + '<strong>?');
		});
// tudo dentro desta function carrega logo apos o carregamento da pagina
$(function() {
	// função para exibir mensagens de incones
	$('[rel="tooltip"]').tooltip();

	// mascara de campo para valor (MOEDA)
	$('.js-maskMoney').maskMoney({
		decimal : ',',
		thousands : '.',
		allowZero : true
	});

	// função ajax para alterar status de titulo
	$('.js-atualizar-status').on(
			'click',
			function(event) {
				event.preventDefault();

				var botaoReceber = $(event.currentTarget);
				var urlReceber = botaoReceber.attr('href');

				var response = $.ajax({
					url : urlReceber,
					method : 'PUT'
				});

				response.done(function(e) {

					var codigoTitulo = botaoReceber.data('id');
					$('[data-role=' + codigoTitulo + ']')
							.html(
									'<span class="label label-success">' + e
											+ '<span>');
					botaoReceber.hide();
				});

				response.fail(function(e) {
					console.log(e);
					alert('Erro ao receber cobrança');
				});

			});
})

$(document).ready(function() {

	
	$(function() {
        $.mask.definitions['~'] = "[+-]";
        $("#date").mask("99/99/9999",{placeholder:"mm/dd/yyyy",completed:function(){alert("completed!");}});
        $(".phone").mask("(999) 999-9999");
        $(".cel").mask("(999) 99999-9999");
        $("#phoneExt").mask("(999) 999-9999? x99999");
        $("#iphone").mask("+33 999 999 999");
        $("#tin").mask("99-9999999");
        $("#ssn").mask("999-99-9999");
        $("#product").mask("a*-999-a999", { placeholder: " " });
        $("#eyescript").mask("~9.99 ~9.99 999");
        $("#po").mask("PO: aaa-999-***");
        $("#pct").mask("99%");
        $("#phoneAutoclearFalse").mask("(999) 999-9999", { autoclear: false, completed:function(){alert("completed autoclear!");} });
        $("#phoneExtAutoclearFalse").mask("(999) 999-9999? x99999", { autoclear: false });
        $("input").blur(function() {
            $("#info").html("Unmasked value: " + $(this).mask());
        }).dblclick(function() {
            $(this).unmask();
        });
    });

	$('#dataTable').dataTable({
		"language" : {
			"sEmptyTable" : "Nenhum registro encontrado",
			"sInfo" : "Mostrando de _START_ até _END_ de _TOTAL_ registros",
			"sInfoEmpty" : "Mostrando 0 até 0 de 0 registros",
			"sInfoFiltered" : "(Filtrados de _MAX_ registros)",
			"sInfoPostFix" : "",
			"sInfoThousands" : ".",
			"sLengthMenu" : "_MENU_ resultados por página",
			"sLoadingRecords" : "Carregando...",
			"sProcessing" : "Processando...",
			"sZeroRecords" : "Nenhum registro encontrado",
			"sSearch" : "Pesquisar",
			"oPaginate" : {
				"sNext" : "Próximo",
				"sPrevious" : "Anterior",
				"sFirst" : "Primeiro",
				"sLast" : "Último"
			},
			"oAria" : {
				"sSortAscending" : ": Ordenar colunas de forma ascendente",
				"sSortDescending" : ": Ordenar colunas de forma descendente"
			}

		}
	});

});

