ipm.cpq.rpt.store = {
	getRptStore : function(){
		var rptModel = Ext.define('rptModel', {
	        extend: 'Ext.data.Model',
	        fields: [
	            'orderNo'
	        ]
		});
		
		var rptStore = Ext.create('Ext.data.Store',{
			model: rptModel,
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
		return rptStore;
	},
	
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
	
	//获取荷兰、香港store
	getOrderTypeStore : function(){
		var store = Ext.create('Ext.data.Store', {
			fields: ['value', 'label'],
			data : [
				{"value":"CPQ_ORDER_TYPE_HK", "label":"香港"},
        		{"value":"CPQ_ORDER_TYPE_NETHERLANDS", "label":"荷兰"}
			]
		});
		return store;
	}
};