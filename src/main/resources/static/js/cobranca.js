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
