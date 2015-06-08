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
	//衣服类型store
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
	},
	//excel item store
	getExcelItemStore : function(){
		var model = Ext.define('excelItemModel', {
	        extend: 'Ext.data.Model',
	        fields: [
	            'order',
	            'style',
	            'fromNo',
	            'toNo',
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
	            'boxQty',
	            'pcs',
	            'grossWeight',
	            'netWeight',
	            'volume',
	            'remark',
	            'country'
	        ]
		});
		var store = Ext.create('Ext.data.Store',{
			model: model,
			proxy : {
				type: 'ajax',
				url : '',
				method : 'get',
				reader: {
		             type: 'json',
		             root : ''
		         }
			},
			autoLoad : false
		});
		return store;
	},
	
	//已上传的excel
	uploadedExcelStore : function(){
		var model = Ext.define('excelSelectModel', {
	        extend: 'Ext.data.Model',
	        fields: [
	            'value',
	            'label',
	            'clothingType'
	        ]
		});
		
		var store = Ext.create('Ext.data.Store',{
			model: model,
			proxy : {
				type: 'ajax',
				url : ctx + '/cpq/getUploadedExcelStore',
				method : 'get',
				reader: {
		             type: 'json',
		             root : ''
		         }
			},
			autoLoad : false
		});
		return store;
	}
};