ipm.cpq.pdf.store = {
	init : function(){
		var pdfModel = Ext.define('Employee', {
	        extend: 'Ext.data.Model',
	        fields: [
	            'Order#',
	            'Style#',
	            'colour',
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
			autoLoad : true
		});
		provinceStore.sort('name','ASC');
		return provinceStore;
	}
};