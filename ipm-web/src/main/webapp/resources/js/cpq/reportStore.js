ipm.cpq.rpt.store = {
	getRptStore : function(){
		var rptModel = Ext.define('rptModel', {
	        extend: 'Ext.data.Model',
	        fields: [
	            'orderNo',
				'selected'
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
	}
};