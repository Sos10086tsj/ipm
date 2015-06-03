ipm.cpq = {
	pdf : {
		init : function(){
			var clothingTypeStore = ipm.cpq.pdf.store.clothingType();
			//文件上传
			var fileUpload = Ext.create('Ext.form.Panel',{
				renderTo: 'btn_pdf_upload',
				title: '上传PDF',
    			width: 400,
    			bodyPadding: 10,
    			frame: true,
        		items : [
        		{
        			xtype: 'combobox',
        			name: 'clothingType',
        			fieldLabel: '服装类型',
    				store: clothingTypeStore,
    				queryMode: 'remote',
    				displayField: 'label',
    				valueField: 'value'
        		},
        		{
        			xtype: 'filefield',
        			name: 'pdf',
        			fieldLabel: 'PDF',
        			labelWidth: 50,
        			msgTarget: 'side',
        			allowBlank: false,
        			anchor: '100%',
        			buttonText: '浏览'
        		}
        		],
        		buttons : [
        		{
        			text: 'Upload',
        			handler: function() {
            			var form = this.up('form').getForm();
            			if(form.isValid()){
                			form.submit({
                    			url: ctx + '/cpq/uploadPdf',
                    			waitMsg: 'Uploading...',
                    			timeout: 300000,
                    			success: function(fp, o) {
                    				var response = Ext.decode(o.response.responseText);
                    				if(response.success){
                    					//store.proxy.conn.url = ctx + '/cpq/getPdfStore/' + response.data.pdfId;
                    					//store.proxy = new Ext.data.HttpProxy({url: ctx + '/cpq/getPdfStore/' + response.data.pdfId });
                    					store.proxy.url = ctx + '/cpq/getPdfStore/' + response.data.pdfId;
                    					//TODO 隐藏不需要的列 grid.columns[i].setVisible(false/true); grid.columns[i].hide()/show()
                    					store.reload();
                    				}else{
                    					ipm.extjs.warningResult('提示','系统异常，请重试');
                    				}
                    			},
                    			failure : function(error){
                    				ipm.extjs.warningResult('提示','系统异常，请重试');
                    			}
                			});
            			}
        			}
        		}
        		]
			});
			//结果展示grid
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
		        			url : ctx + '/cpq/updatePdfRow',
		        			method : 'post', 
		        			params : {
		        				order : record.get('order'),
	            				style:record.get('style'),
	            				colour:record.get('colour'),
	            				sizeS:record.get('sizeS'),
	            				sizeM:record.get('sizeM'),
	            				sizeL:record.get('sizeL'),
	            				sizeXL:record.get('sizeXl'),
	            				sizeXXL:record.get('sizeXxl'),
	            				tTL:record.get('ttl'),
	            				totalAmount:record.get('totalAmount')
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
			var store = ipm.cpq.pdf.store.grid();
		    var grid = Ext.create('Ext.grid.Panel', {
		        store: store,
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
		        },  {
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
		            width: 60,
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
		            width: 60,
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
		            width: 60,
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
		            width: 60,
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
		            header: 'Size 2',
		            dataIndex: 'size2',
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
		            header: 'Size 4',
		            dataIndex: 'size4',
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
		            header: 'Size 6',
		            dataIndex: 'size6',
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
		            header: 'Size 8',
		            dataIndex: 'size8',
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
		            header: 'Size 10',
		            dataIndex: 'size10',
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
		            header: 'Size 12',
		            dataIndex: 'size12',
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
		            header: 'Size 14',
		            dataIndex: 'size14',
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
		            header: 'Size 16',
		            dataIndex: 'size16',
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
		            header: 'ttl',
		            dataIndex: 'ttl',
		            format:'0',
		            width: 60
		        },
		        {
		            xtype: 'numbercolumn',
		            header: 'totalAmount',
		            dataIndex: 'totalAmount',
		            format: '$0,0',
		            width: 90
		        }],
		        renderTo: 'pdf_grid',
		        width: 870,
		        height: 600,
		        title: 'Customer Orders',
		        frame: true,
		        tbar: [{
		            text: 'Add Order',
		            iconCls: 'employee-add',
		            handler : function() {
		                rowEditing.cancelEdit();

		                // Create a model instance
		                var r = Ext.create('pdfModel', {
		                	'order': '',
		                	'style':'',
		                	'colour':'',
		                	'sizeS':'',
		                	'sizeM':'',
		                	'sizeL':'',
		                	'sizeXL':'',
		                	'sizeXXL':'',
		                	'tTL':'',
		                	'totalAmount':'0'
		                });

		                store.insert(0, r);
		                rowEditing.startEdit(0, 0);
		            }
		        }, {
		            itemId: 'removePdfOrder',
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
		                grid.down('#removePdfOrder').setDisabled(!records.length);
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
	             'Ext.form.*',
    			 'Ext.window.MessageBox'
	         ]);
	Ext.onReady(function () {
		Ext.QuickTips.init();
		ipm.cpq.pdf.init();
	});
});