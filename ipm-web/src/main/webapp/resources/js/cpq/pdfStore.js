ipm.cpq.pdf.store = {
	grid : function(){
		var pdfModel = Ext.define('pdfModel', {
	        extend: 'Ext.data.Model',
	        fields: [
	            'order',
	            'style',
	            'colour',
	            'sizeS',
	            'sizeM',
	            'sizeL',
	            'sizeXl',
	            'sizeXxl',
	            'sizeP',
	            'size1',
	            'size2',
	            'size3',
	            'size4',
	            'size6',
	            'size8',
	            'size10',
	            'size12',
	            'size14',
	            'size16',
	            'ttl',
	            'totalAmount'
	        ]
		});
		
		var pdfStore = Ext.create('Ext.data.Store',{
			model: pdfModel,
			proxy : {
				type: 'ajax',
				url : ctx + '/cpq/getPdfStore/',
				method : 'get',
				reader: {
		             type: 'json',
		             root : ''
		         }
			},
			autoLoad : false,
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
	},
	
	clothingType : function(){
		var clothingTypeModel = Ext.define('clothingTypeModel', {
	        extend: 'Ext.data.Model',
	        fields: [
	            'value',
	            'label'
	        ]
		});
		
		var clothingTypeStore = Ext.create('Ext.data.Store',{
			model: clothingTypeModel,
			proxy : {
				type: 'ajax',
				url : ctx + '/cpq/getClothingTypeStore/',
				method : 'get',
				reader: {
		             type: 'json',
		             root : ''
		         }
			},
			autoLoad : true
		});
		return clothingTypeStore;
	}
};