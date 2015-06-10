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
		        width: 500,
		        height: 600,
		        title: 'Report Order Range',
		        frame: true,
		        tbar: [
		        	{
		        		id:'js_manufactory',
        				xtype: 'combobox',
        				name: 'manufactory',
        				fieldLabel: '选择工厂',
        				width:150,
        				labelWidth:60,
    					store: ipm.cpq.rpt.store.getManufactorySotre(),
    					queryMode: 'remote',
    					displayField: 'label',
    					region:'east',
    					valueField: 'value',
    					listeners:{
    						select:function(combo,record,opts) {
    							ipm.cpq.rpt.grid.down('#print').setDisabled(!record.length);
   							}
    					}
		        	},
		        	"->",
		        	{
		        		id:'js_order_no',
		        		xtype: 'textfield',
		        		name: 'orderNo',
		        		fieldLabel: '订单号',
		        		width:150,
        				labelWidth:60
		        	},
		        	"->",
		        	{
		        		itemId: 'orders',
		            	text: '选取order',
		            	handler: function() {
		                	ipm.cpq.rpt.reloadGrid();
		            	},
		            	width:60
		        	},
		        	//"->",
		        	{
		        		itemId: 'print',
		            	text: 'Print',
		            	//iconCls: 'employee-remove',
		            	handler: function(grid, rowIndex, colIndex) {
		                	var manufactory = Ext.getCmp('js_manufactory').getValue();
		                	var selectRows = ipm.cpq.rpt.grid.getSelectionModel().getSelection();
		                	if(selectRows.length <= 0){
		                		ipm.extjs.warningResult('提示','请选择需要打印的订单号');
		                	}else{
		                		var orderNos = "";
		                		for(var i =0; i < selectRows.length; i ++){
		                			var data = selectRows[i].data.orderNo;
		                			orderNos += data;
		                			if(i != selectRows.length - 1){
		                				orderNos += ",";
		                			}
		                		}
		                		window.location.href = ctx + '/cpq/print?manufactory=' + manufactory + "&orderNos=" + orderNos;
		                		//window.location.href = "javascript:$.post(ctx + '/cpq/print',{manufactory:" + manufactory + ",orderNos:" + orderNos + "})";
		                	}
		            	},
		            	disabled: true,
		            	width:60
		        	}
		        ]
			});
		},
		
		reloadGrid : function(){
			var store = ipm.cpq.rpt.grid.store;
			var orderNo = Ext.getCmp('js_order_no').getValue();
			store.proxy.url = ctx + '/cpq/getRptOrders?orderNo=' + orderNo;
			store.reload();
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