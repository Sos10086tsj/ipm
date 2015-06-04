ipm.cpq = {
	excel : {
		init : function(){
		//1. 上传form
		var excelUpload = Ext.create('Ext.form.Panel',{
			renderTo: 'btn_excel_upload',
			title: '上传Excel',
			width: 400,
    		bodyPadding: 10,
    		frame: true,
    		items : [
    			{
    				id:'js_manufactory_id',
    				xtype: 'combobox',
    				allowBlank : false,
    				name: 'manufactory',
    				fieldLabel: '选择工厂',
    				store: ipm.cpq.excel.store.getManufactorySotre(),
    				labelWidth:60,
    				queryMode: 'remote',
    				displayField: 'label',
    				valueField: 'value'
    			},
    			{
        			id:'js_cloting_type_id',
        			xtype: 'combobox',
        			allowBlank : false,
        			name: 'clothingType',
        			fieldLabel: '服装类型',
    				store: ipm.cpq.excel.store.getClothingTypeStore(),
    				labelWidth:60,
    				queryMode: 'remote',
    				displayField: 'label',
    				valueField: 'value'
        		},
    			{
    				xtype: 'filefield',
        			name: 'excels',
        			fieldLabel: 'Excel',
        			allowBlank : false,
        			labelWidth: 60,
        			msgTarget: 'side',
        			allowBlank: false,
        			anchor: '100%',
        			buttonText: '浏览',
        			listeners:{
        				afterrender:function(cmp){
        					cmp.fileInputEl.set({
        						multiple:'multiple'
        					});
        				}
        			}
    			}
    		],
    		buttons : [
    			{
    				text: 'Upload',
    				handler: function() {
    					var manufactory = Ext.getCmp('js_manufactory_id').getValue();
    					var clothingType = Ext.getCmp('js_cloting_type_id').getValue();
    					if(manufactory && manufactory.length > 0 && clothingType && clothingType.length > 0){
    						var form = this.up('form').getForm();
    						if(form.isValid()){
    							form.submit({
    								url: ctx + '/cpq/uploadExcel',
    								waitMsg: 'Uploading...',
    								timeout: 600000,
    								success: function(fp, o) {
    									var response = Ext.decode(o.response.responseText);
    									if(response.success){
                    						ipm.cpq.excel.reloadStore(response.data.excelIds,response.data.clothingType);
                    					}else{
                    						ipm.extjs.warningResult('提示','系统异常，请重试');
                    					}
    								}
    							});
    						}
    					}else{
    						ipm.extjs.warningResult('提示','请先选择工厂和服装类型');
    					}
    				}
    			}
    		]
		});
		//2. Grid
	},
	
	reloadStore : function(pdfId, clothingType){
//			var grid = ipm.cpq.pdf.grid;
//			var store = grid.store;
//			store.proxy.url = ctx + '/cpq/getPdfStore/' + pdfId;
//            //TODO 隐藏不需要的列 grid.columns[i].setVisible(false/true); grid.columns[i].hide()/show()
//			//1. 全部隐藏
//			for(var i = 3; i < 19 ; i++){
//				grid.columns[i].hide();
//			}
//			//2. 开发部分
//			if(clothingType == 'MALE'){//男 Size S,Size M,Size L,Size XL,Size XXL    3~7
//				for(var i = 3; i < 8 ; i++){
//					grid.columns[i].show();
//				}
//			}else if(clothingType == 'FEMALE'){//女 Size P.Size 1.Size 2.Size 3.Size 4
//				for(var i = 8; i < 13 ; i++){
//					grid.columns[i].show();
//				}
//			}else if(clothingType == 'BOY' || clothingType == 'GIRL'){//男童 Size 4,Size 6,Size 8,Size 10,Size 12,Size 14,Size 16
//				for(var i = 11; i < 19 ; i++){
//					grid.columns[i].show();
//				}
//			}else{
//				return;
//			}
//            store.reload();
		}
	}
};

$(function(){
	Ext.require([
	             'Ext.grid.*',
	             'Ext.data.*',
	             'Ext.util.*',
	             'Ext.state.*',
	             'Ext.form.*'
	         ]);
	Ext.onReady(function () {
		Ext.QuickTips.init();
		ipm.cpq.excel.init();
	});
});