ipm.cpq.excel.store = {
	//excel工厂列表
	getManufactorySotre : function(){
		var manufactoryModel = Ext.define('manufactoryModel', {
	        extend: 'Ext.data.Model',
	        fields: [
	            'value',
	            'label'
	        ]
		});
		
		var manufactorySotre = Ext.create('Ext.data.Store',{
			model: manufactoryModel,
			proxy : {
				type: 'ajax',
				url : ctx + '/cpq/excel/getManufactoryStore',
				method : 'get',
				reader: {
		             type: 'json',
		             root : ''
		         }
			},
			autoLoad : true
		});
		return manufactorySotre;
	},
	
	getClothingTypeStore : function(){
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