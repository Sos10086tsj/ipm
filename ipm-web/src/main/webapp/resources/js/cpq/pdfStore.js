ipm.cpq.pdf.store = {
	init : function(){
		var pdfModel = Ext.define('pdfModel', {
	        extend: 'Ext.data.Model',
	        fields: [
	            'order',
	            'style',
	            'colour',
	            'sizeS',
	            'sizeM',
	            'sizeL',
	            'sizeXL',
	            'sizeXXL',
	            'tTL',
	            'totalAmount'
	        ]
		});
		
		var pdfStore = Ext.create('Ext.data.Store',{
			model: pdfModel,
			proxy : {
				type: 'ajax',
				url : '/cpq/getPdfStore',
				method : 'get',
				reader: {
		             type: 'json',
		             root : ''
		         }
			},
			autoLoad : true,
			sorters: [
				{
					property: 'order',
					direction: 'ASC'
				},
				{
					property: 'style',
					direction: 'ASC'
				},
				{
					property: 'colour',
					direction: 'ASC'
				}
				]
		});
		return pdfStore;
	}
};