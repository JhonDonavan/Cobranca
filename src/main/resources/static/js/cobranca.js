$('#confirmacaoExclusaoModalTitulo').on('show.bs.modal', function(event) {
	
	var button = $(event.relatedTarget);
	
	var idTitulo = button.data('codigo');
	var descricaoTitulo = button.data('descricao');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	
	if(!action.endsWith('/')){
		action += '/';
	}
	form.attr('action', action + idTitulo);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o titulo de <strong>' + descricaoTitulo + '<strong/>?');
	
});