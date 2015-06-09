ipm.cpq = {
	rpt : {
		grid : null,
		init : function(){
			ipm.cpq.rpt.grid = Ext.create('Ext.grid.Panel',{
				store: ipm.cpq.rpt.store.getRptStore(),
				selModel:Ext.create('Ext.selection.CheckboxModel'),
				columns: [{
		            header: 'orderNo',
		            dataIndex: 'orderNo',
		            width: 160
		        	}],
		       	renderTo: 'js_report_export',
		        width: 1000,
		        height: 600,
		        title: 'Report Order Range',
		        frame: true,
		        tbar: [
		        	{
		        		id:'js_manufactory',
        				xtype: 'combobox',
        				name: 'manufactory',
        				fieldLabel: '选择工厂',
        				width:550,
        				labelWidth:100,
    					store: ipm.cpq.rpt.store.getManufactorySotre(),
    					queryMode: 'remote',
    					displayField: 'label',
    					region:'east',
    					valueField: 'value',
    					listeners:{
    						select:function(combo,record,opts) {
    							ipm.cpq.rpt.grid.down('#print').setDisabled(!records.length);
   							}
    					}
		        	},
		        	"->",
		        	{
		        		itemId: 'print',
		            	text: 'Print',
		            	//iconCls: 'employee-remove',
		            	handler: function() {
		                	var manufactory = Ext.getCmp('js_manufactory').getValue();
		                	var selectRows = ipm.cpq.rpt.grid.getSelectionModel().getSelections();
		                	console.log("manufactory:" + manufactory);
		                	console.log("selectRows:" + selectRows);
		            	},
		            	disabled: true
		        	}
		        ]
			});
		}
	}
};

$(function(){
	Ext.require([
	             'Ext.grid.*',
	             'Ext.data.*',
	             'Ext.util.*',
	             'Ext.state.*',
	             'Ext.form.*',
    			 'Ext.window.MessageBox'
	         ]);
	Ext.onReady(function () {
		Ext.QuickTips.init();
		ipm.cpq.rpt.init();
	});
});