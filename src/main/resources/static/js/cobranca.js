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

// tudo dentro deste Function é carregado assim que a pagina esta carregada.
$(function() {
	// mmensagem para icones com mause sobre icones
	$('[rel="tooltip"]').tooltip();

	// MaskMoney, mascara para campos de valor(moeda)
	$('.js-maskMoney').maskMoney({
		decimal : ',',
		thousands : '.',
		allowZero : true
	});

	// AJAX para alterar status do titulo
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

//Inicializa dataTable
$(document).ready(function() {
	$('#dataTable').dataTable();
});
