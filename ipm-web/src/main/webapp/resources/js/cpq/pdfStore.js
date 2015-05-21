ipm.cpq.pdf.store = {
	init : function(){
		var pdfModel = Ext.define('pdfModel', {
	        extend: 'Ext.data.Model',
	        fields: [
	            'Order',
	            'Style',
	            'Colour',
	            'Size S',
	            'Size M',
	            'Size L',
	            'Size XL',
	            'Size XXL',
	            'TTL',
	            'Total Amount'
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
					property: 'Order#',
					direction: 'ASC'
				},
				{
					property: 'Style#',
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