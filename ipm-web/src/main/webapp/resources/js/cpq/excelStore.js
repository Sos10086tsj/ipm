ipm.cpq.excel.store = {
	init : function(){
		var excelModel = Ext.define('excelModel', {
	        extend: 'Ext.data.Model',
	        fields: [
	            'order',
	            'style',
	            'from',
	            'to',
	            'colour',
	            'sizeS',
	            'sizeM',
	            'sizeL',
	            'sizeXL',
	            'sizeXXL',
	            'box',
	            'qty',
	            'grossWeight',
	            'netWeight'
	        ]
		});
		
		var excelStore = Ext.create('Ext.data.Store',{
			model: excelModel,
			proxy : {
				type: 'ajax',
				url : '/cpq/getExcelStore',
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
					property: 'from',
					direction: 'ASC'
				},
				{
					property: 'to',
					direction: 'ASC'
				},
				{
					property: 'colour',
					direction: 'ASC'
				}
				]
		});
		return excelStore;
	}
};