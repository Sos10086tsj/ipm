ipm.cpq = {
	excel : {
		init : function(){
		    var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
		        saveBtnText: '保存', 
            	cancelBtnText: "取消", 
           	 	autoCancel: false, 
            	clicksToEdit:2 ,
		        listeners : {
		        	edit : function( editor, context, eOpts){
		        		var progress = ipm.extjs.progressBar('正在修改，请稍后...');
		        		progress.show();
		        		
		        		var record = context.record;
		        		Ext.Ajax.request({
		        			url : '/cpq/updatePdfRow',
		        			method : 'post', 
		        			params : {
		        				order: record.get('order'),
	            				style: record.get('style'),
	            				from: record.get('from'),
	            				to: record.get('to'),
	            				colour:record.get('colour'),
	            				sizeS: record.get('sizeS'),
	            				sizeM: record.get('sizeM'),
	            				sizeL: record.get('sizeL'),
	            				sizeXL: record.get('sizeXL'),
	            				sizeXXL: record.get('sizeXXL'),
	            				box: record.get('box'),
	            				qty: record.get('qty'),
	            				grossWeight: record.get('grossWeight'),
	            				netWeight: record.get('netWeight')
		        			},
		        			success : function(response){
		        				var result = Ext.decode(response.responseText);
		        				progress.hide();
		        				if(result.success){
		        					ipm.extjs.warningResult('操作提示','保存成功！');
		        				}else{
		        					ipm.extjs.warningResult('操作提示',result.errorMessage);
		        				}
		        			},
		        			failure : function(response, opts){
		        				progress.hide();
		        				ipm.extjs.warningResult('操作提示','网络异常，保存失败！');
		        			}
		        		});
		        	}
		        }
		    });
			var store = ipm.cpq.excel.store.init();
		    var grid = Ext.create('Ext.grid.Panel', {
		        store: store,
		        columns: [{
		            header: 'order',
		            dataIndex: 'order',
		            flex: 1,
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
		            header: 'from',
		            dataIndex: 'from',
		            width: 160,
		            editor: {
		                allowBlank: false
		            }
		        }, {
		            header: 'to',
		            dataIndex: 'to',
		            width: 160,
		            editor: {
		                allowBlank: false
		            }
		        },  {
		            header: 'colour',
		            dataIndex: 'colour',
		            width: 160,
		            editor: {
		                allowBlank: false
		            }
		        },{
		            xtype: 'numbercolumn',
		            header: 'sizeS',
		            dataIndex: 'sizeS',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        }, {
		            xtype: 'numbercolumn',
		            header: 'sizeM',
		            dataIndex: 'sizeM',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },{
		            xtype: 'numbercolumn',
		            header: 'sizeL',
		            dataIndex: 'sizeL',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },{
		            xtype: 'numbercolumn',
		            header: 'sizeXL',
		            dataIndex: 'sizeXL',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },{
		            xtype: 'numbercolumn',
		            header: 'sizeXXL',
		            dataIndex: 'sizeXXL',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },{
		            xtype: 'numbercolumn',
		            header: 'box',
		            dataIndex: 'box',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        },{
		            xtype: 'numbercolumn',
		            header: 'qty',
		            dataIndex: 'qty',
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
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        }
		        ],
		        renderTo: 'excel_grid',
		        width: 600,
		        height: 400,
		        title: 'Company Orders',
		        frame: true,
		        tbar: [{
		            text: 'Add Order',
		            iconCls: 'employee-add',
		            handler : function() {
		                rowEditing.cancelEdit();

		                // Create a model instance
		                var r = Ext.create('pdfModel', {
		                	'order': '',
	            			'style': '',
	            			'from': '',
	            			'to': '',
	            			'colour': '',
	            			'sizeS': '',
	            			'sizeM': '',
	            			'sizeL': '',
	            			'sizeXL': '',
	            			'sizeXXL': '',
	            			'box': '',
	            			'qty': '',
	            			'grossWeight': '',
	            			'netWeight': ''
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
		        }],
		        plugins: [rowEditing],
		        listeners: {
		            'selectionchange': function(view, records) {
		            	console.log('updated');
		                grid.down('#removeExcelOrder').setDisabled(!records.length);
		            }
		        }
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
	             'Ext.form.*'
	         ]);
	Ext.onReady(function () {
		Ext.QuickTips.init();
		ipm.cpq.excel.init();
	});
});