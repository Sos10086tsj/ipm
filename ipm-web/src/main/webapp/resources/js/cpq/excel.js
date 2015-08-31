ipm.cpq = {
	excel : {
		grid : null,
		init : function(){
		var uploadedExcelStore = ipm.cpq.excel.store.uploadedExcelStore();
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
                    						//ipm.cpq.excel.reloadStore(response.data.excelIds,response.data.clothingType);
    										uploadedExcelStore.reload();
    										ipm.warningResult('操作提示','上传成功！');
                    					}else{
                    						ipm.warningResult('提示','系统异常，请重试');
                    					}
    								}
    							});
    						}
    					}else{
    						ipm.warningResult('提示','请先选择工厂和服装类型');
    					}
    				}
    			}
    		]
		});
		//2. Grid
		var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
		        saveBtnText: '保存', 
            	cancelBtnText: "取消", 
           	 	autoCancel: false, 
            	clicksToEdit:2 ,
		        listeners : {
		        	edit : function( editor, context, eOpts){
		        		var progress = ipm.progressBar('正在修改，请稍后...');
		        		progress.show();
		        		
		        		var record = context.record;
		        		Ext.Ajax.request({
		        			url : ctx + '/cpq/updateExcelRow',
		        			method : 'post', 
		        			params : {
		        				order : record.get('order'),
	            				style:record.get('style'),
	            				fromNo:record.get('fromNo'),
	            				toNo:record.get('toNo'),
	            				colour:record.get('colour'),
	            				sizeS:record.get('sizeS'),
	            				sizeM:record.get('sizeM'),
	            				sizeL:record.get('sizeL'),
	            				sizeXL:record.get('sizeXl'),
	            				sizeXXL:record.get('sizeXxl'),
	            				sizeP:record.get('sizeP'),
	            				size1:record.get('size1'),
	            				size2:record.get('size2'),
	            				size3:record.get('size3'),
	            				size4:record.get('size4'),
	            				size6:record.get('size6'),
	            				size8:record.get('size8'),
	            				size10:record.get('size10'),
	            				size12:record.get('size12'),
	            				size14:record.get('size14'),
	            				size16:record.get('size16'),
	            				size16:record.get('sizeUNI1'),
	            				size16:record.get('sizeUNI2'),
	            				size16:record.get('sizeUNI3'),
	            				size16:record.get('sizeUNI4'),
	            				size16:record.get('sizeUNI5'),
	            				size16:record.get('sizeUNI6'),
	            				boxQty:record.get('boxQty'),
	            				pcs:record.get('pcs'),
	            				grossWeight:record.get('grossWeight'),
	            				netWeight:record.get('netWeight'),
	            				volume:record.get('volume'),
	            				remark:record.get('remark'),
	            				country:record.get('country')
		        			},
		        			success : function(response){
		        				progress.hide();
		        				var response = Ext.decode(o.response.responseText);
		        				if(result.success){
		        					//ipm.cpq.pdf.reloadStore(response.data.excelIds,response.data.clothingType);
		        					//ipm.warningResult('操作提示','上传成功！');
		        				}else{
		        					ipm.warningResult('操作提示',result.errorMessage);
		        				}
		        			},
		        			failure : function(response, opts){
		        				progress.hide();
		        				ipm.warningResult('操作提示','网络异常，保存失败！');
		        			}
		        		});
		        	}
		        }
		    });
		    ipm.cpq.excel.grid = Ext.create('Ext.grid.Panel', {
		        store: ipm.cpq.excel.store.getExcelItemStore(),
		        columns: [{
		            header: 'order',
		            dataIndex: 'order',
		            width: 160,
		            //flex: 1,
		            editor: {
		                // defaults to textfield if no xtype is supplied
		                allowBlank: false
		            }
		        }, {
		            header: 'style',
		            dataIndex: 'style',
		            width: 160,
		            editor: {
		                allowBlank: false
		            }
		        }, {
		            header: 'fromNo',
		            dataIndex: 'fromNo',
		            width: 60,
		            editor: {
		                allowBlank: false
		            }
		        },{
		            header: 'toNo',
		            dataIndex: 'toNo',
		            width: 60,
		            editor: {
		                allowBlank: false
		            }
		        },
		        {
		            header: 'colour',
		            dataIndex: 'colour',
		            width: 60,
		            editor: {
		                allowBlank: false
		            }
		        },{
		            xtype: 'numbercolumn',
		            header: 'Size S',
		            dataIndex: 'sizeS',
		            format:'0',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        }, {
		            xtype: 'numbercolumn',
		            header: 'Size M',
		            dataIndex: 'sizeM',
		            format:'0',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },{
		            xtype: 'numbercolumn',
		            header: 'Size L',
		            dataIndex: 'sizeL',
		            format:'0',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },{
		            xtype: 'numbercolumn',
		            header: 'Size XL',
		            dataIndex: 'sizeXl',
		            format:'0',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },{
		            xtype: 'numbercolumn',
		            header: 'Size XXL',
		            dataIndex: 'sizeXxl',
		            format:'0',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },
		        {
		            xtype: 'numbercolumn',
		            header: 'Size P',
		            dataIndex: 'sizeP',
		            format:'0',
		            width: 60,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },
		        {
		            xtype: 'numbercolumn',
		            header: 'Size 1',
		            dataIndex: 'size1',
		            format:'0',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },
		        {
		            xtype: 'numbercolumn',
		            header: 'Size 2',
		            dataIndex: 'size2',
		            format:'0',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },
		        {
		            xtype: 'numbercolumn',
		            header: 'Size 3',
		            dataIndex: 'size3',
		            format:'0',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },
		        {
		            xtype: 'numbercolumn',
		            header: 'Size 4',
		            dataIndex: 'size4',
		            format:'0',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },
		        {
		            xtype: 'numbercolumn',
		            header: 'Size 6',
		            dataIndex: 'size6',
		            format:'0',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },
		        {
		            xtype: 'numbercolumn',
		            header: 'Size 8',
		            dataIndex: 'size8',
		            format:'0',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },
		        {
		            xtype: 'numbercolumn',
		            header: 'Size 10',
		            dataIndex: 'size10',
		            format:'0',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },
		        {
		            xtype: 'numbercolumn',
		            header: 'Size 12',
		            dataIndex: 'size12',
		            format:'0',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },
		        {
		            xtype: 'numbercolumn',
		            header: 'Size 14',
		            dataIndex: 'size14',
		            format:'0',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },
		        {
		            xtype: 'numbercolumn',
		            header: 'Size 16',
		            dataIndex: 'size16',
		            format:'0',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },
		        {
		            xtype: 'numbercolumn',
		            header: 'Size UNI1',
		            dataIndex: 'sizeUNI1',
		            format:'0',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },
		        {
		            xtype: 'numbercolumn',
		            header: 'Size UNI2',
		            dataIndex: 'sizeUNI2',
		            format:'0',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },
		        {
		            xtype: 'numbercolumn',
		            header: 'Size UNI3',
		            dataIndex: 'sizeUNI3',
		            format:'0',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },
		        {
		            xtype: 'numbercolumn',
		            header: 'Size UNI4',
		            dataIndex: 'sizeUNI4',
		            format:'0',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },
		        {
		            xtype: 'numbercolumn',
		            header: 'Size UNI5',
		            dataIndex: 'sizeUNI5',
		            format:'0',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },
		        {
		            xtype: 'numbercolumn',
		            header: 'Size UNI6',
		            dataIndex: 'sizeUNI6',
		            format:'0',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },
		        {
		            xtype: 'numbercolumn',
		            header: 'boxQty',
		            dataIndex: 'boxQty',
		            format:'0',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },{
		            xtype: 'numbercolumn',
		            header: 'pcs',
		            dataIndex: 'pcs',
		            format:'0',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },{
		            xtype: 'numbercolumn',
		            header: 'grossWeight',
		            dataIndex: 'grossWeight',
		            format:'0',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },{
		            xtype: 'numbercolumn',
		            header: 'netWeight',
		            dataIndex: 'netWeight',
		            format:'0',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },
		        {
		            xtype: 'numbercolumn',
		            header: 'volume',
		            dataIndex: 'volume',
		            format:'0.000',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },{
		            header: 'remark',
		            dataIndex: 'remark',
		            width: 60,
		            editor: {
		                allowBlank: false
		            }
		        },{
		            header: 'country',
		            dataIndex: 'country',
		            width: 60,
		            editor: {
		                allowBlank: false
		            }
		        }
		        ],
		        renderTo: 'excel_grid',
		        width: 1000,
		        height: 600,
		        title: 'Manufacotry Orders',
		        frame: true,
		        tbar: [{
		            text: 'Add item',
		            iconCls: 'employee-add',
		            disabled: true,
		            handler : function() {
		                rowEditing.cancelEdit();

		                // Create a model instance
		                var r = Ext.create('pdfModel', {
		                	'order': '',
		                	'style':'',
		                	'fromNo':'',
		                	'toNo':'',
		                	'colour':'',
		                	'sizeS':'',
		                	'sizeM':'',
		                	'sizeL':'',
		                	'sizeXl':'',
		                	'sizeXxl':'',
		                	'sizeP':'',
		                	'size1':'',
		                	'size2':'',
		                	'size3':'',
		                	'size4':'',
		                	'size6':'',
		                	'size8':'',
		                	'size10':'',
		                	'size12':'',
		                	'size14':'',
		                	'size16':'',
		                	'sizeUNI1':'',
		                	'sizeUNI2':'',
		                	'sizeUNI3':'',
		                	'sizeUNI4':'',
		                	'sizeUNI5':'',
		                	'sizeUNI6':'',
		                	'boxQty':'',
	            			'pcs':'',
	            			'grossWeight':'',
	            			'netWeight':'',
	            			'volume':'',
	            			'remark':'',
	            			'country':''
		                });

		                store.insert(0, r);
		                rowEditing.startEdit(0, 0);
		            }
		        }, {
		            itemId: 'removeExcelOrder',
		            text: 'Remove Order',
		            iconCls: 'employee-remove',
		            handler: function() {
		                var sm = grid.getSelectionModel();
		                rowEditing.cancelEdit();
		                store.remove(sm.getSelection());
		                if (store.getCount() > 0) {
		                    sm.select(0);
		                }
		            },
		            disabled: true
		        },
		        "->",
		        {
        			id:'js_item_manufactory_id',
    				xtype: 'combobox',
    				allowBlank : false,
    				name: 'itemManufactory',
    				fieldLabel: '选择工厂',
    				store: ipm.cpq.excel.store.getManufactorySotre(),
    				labelWidth:60,
    				queryMode: 'remote',
    				displayField: 'label',
    				valueField: 'value',
    				listeners:{
    					select:function(combo,record,opts) {
    						var manufactory = record[0].get("value");
    						if(manufactory && manufactory.length > 0){
    							uploadedExcelStore.proxy.url = ctx + '/cpq/getUploadedExcelStore/?manufactory=' + manufactory;
    							uploadedExcelStore.reload();
    						}else{
    							ipm.warningResult('提示','请先选择工厂');
    						}
   						}
    				}
        		},
        		"->",
		        {
        			xtype: 'combobox',
        			name: 'excelId',
        			fieldLabel: '已经上传的Excel',
        			width:300,
        			labelWidth:120,
    				store: uploadedExcelStore,
    				queryMode: 'remote',
    				displayField: 'label',
    				region:'east',
    				valueField: 'value',
    				listeners:{
    					select:function(combo,record,opts) {
    						var excelId = record[0].get("value");
    						var clothingType = record[0].get("clothingType");
    						var manufactory = Ext.getCmp('js_item_manufactory_id').getValue();
    						if(manufactory && manufactory.length > 0){
    							ipm.cpq.excel.reloadStore(excelId,clothingType,manufactory);
    						}else{
    							ipm.warningResult('提示','请先选择工厂');
    						}
   						}
    				}
        		}
		        ],
		        plugins: [rowEditing]
//		        ,
//		        listeners: {
//		            'selectionchange': function(view, records) {
//		                grid.down('#removeExcelOrder').setDisabled(!records.length);
//		            }
//		        }
		    });
	},
	
	reloadStore : function(excelId, clothingType,manufactory){
			var grid = ipm.cpq.excel.grid;
			var store = grid.store;
			store.proxy.url = ctx + '/cpq/getExcelItemStore/' + excelId + "?manufactory=" + manufactory;
            //TODO 隐藏不需要的列 grid.columns[i].setVisible(false/true); grid.columns[i].hide()/show()
			//1. 全部隐藏
			for(var i = 5; i < 21 + 6 ; i++){
				grid.columns[i].hide();
			}
			//2. 开发部分
			if(clothingType == 'MALE'){//男 Size S,Size M,Size L,Size XL,Size XXL    5~9
				for(var i = 5; i < 10 ; i++){
					grid.columns[i].show();
				}
			}else if(clothingType == 'FEMALE'){//女 Size P.Size 1.Size 2.Size 3.Size 4
				for(var i = 10; i < 15 ; i++){
					grid.columns[i].show();
				}
			}else if(clothingType == 'BOY' || clothingType == 'GIRL'){//男童 Size 4,Size 6,Size 8,Size 10,Size 12,Size 14,Size 16
				for(var i = 14; i < 21 ; i++){
					grid.columns[i].show();
				}
				grid.columns[12].show();
			}else if(clothingType == 'FAMILY'){//亲子  Size UNI1, Size UNI2, Size UNI3,Size UNI4,Size UNI5,Size UNI6
				for(var i = 21; i < 21 + 6 ; i++){
					grid.columns[i].show();
				}
			}else{
				return;
			}
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
	             'Ext.form.*'
	         ]);
	Ext.onReady(function () {
		Ext.QuickTips.init();
		ipm.cpq.excel.init();
	});
});