ipm.cpq = {
	pdf : {
		grid : null,
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
        			id:'js_cloting_type_id',
        			xtype: 'combobox',
        			allowBlank : false,
        			name: 'clothingType',
        			fieldLabel: '服装类型',
    				store: clothingTypeStore,
    				labelWidth:60,
    				queryMode: 'remote',
    				displayField: 'label',
    				valueField: 'value'
        		},
        		{
        			xtype: 'filefield',
        			name: 'pdf',
        			fieldLabel: 'PDF',
        			allowBlank : false,
        			labelWidth: 60,
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
        				var clothingType = Ext.getCmp('js_cloting_type_id').getValue();
        				if(clothingType && clothingType.length > 0){
        					var form = this.up('form').getForm();
            				if(form.isValid()){
                				form.submit({
                    				url: '/ipm' + '/cpq/uploadPdf',
                    				waitMsg: 'Uploading...',
                    				timeout: 600000,
                    				success: function(fp, o) {
                    					var response = Ext.decode(o.response.responseText);
                    					if(response.success){
                    						ipm.cpq.pdf.reloadStore(response.data.pdfId,response.data.clothingType);
                    					}else{
                    						ipm.warningResult('提示','系统异常，请重试');
                    					}
                    				},
                    				failure : function(error){
                    					ipm.warningResult('提示','系统异常，请重试');
                    				}
                				});
            				}
        				}else{
        					ipm.warningResult('提示','请先选择服装类型');
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
		        		ipm.warningResult('操作提示','上传成功！');
		        		var progress = ipm.progressBar('正在修改，请稍后...');
		        		progress.show();
		        		
		        		var record = context.record;
		        		Ext.Ajax.request({
		        			url : '/ipm' + '/cpq/updatePdfRow',
		        			method : 'post', 
		        			params : {
		        				order : record.get('order'),
	            				style:record.get('style'),
	            				colour:record.get('colour'),
	            				sizeS:record.get('sizeS'),
	            				sizeM:record.get('sizeM'),
	            				sizeL:record.get('sizeL'),
	            				sizeXl:record.get('sizeXl'),
	            				sizeXxl:record.get('sizeXxl'),
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
	            				size16:record.get('sizeUNI6')
		        			},
		        			success : function(response){
		        				var result = Ext.decode(response.responseText);
		        				progress.hide();
		        				if(result.success){
		        					ipm.warningResult('操作提示','保存成功！');
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
			var store = ipm.cpq.pdf.store.grid();
		    ipm.cpq.pdf.grid = Ext.create('Ext.grid.Panel', {
		        store: store,
		        columns: [{
		            header: 'order',
		            dataIndex: 'order',
		            width: 160
		            //flex: 1
		        }, {
		            header: 'style',
		            dataIndex: 'style',
		            width: 160
		        },  {
		            header: 'colour',
		            dataIndex: 'colour',
		            width: 60
		        },{
		            xtype: 'numbercolumn',
		            header: 'Size S',
		            dataIndex: 'sizeS',
		            format:'0',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: true,
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
		                allowBlank: true,
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
		                allowBlank: true,
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
		                allowBlank: true,
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
		                allowBlank: true,
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
		                allowBlank: true,
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
		                allowBlank: true,
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
		                allowBlank: true,
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
		                allowBlank: true,
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
		                allowBlank: true,
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
		                allowBlank: true,
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
		                allowBlank: true,
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
		                allowBlank: true,
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
		                allowBlank: true,
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
		                allowBlank: true,
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
		                allowBlank: true,
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
		                allowBlank: true,
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
		                allowBlank: true,
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
		                allowBlank: true,
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
		                allowBlank: true,
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
		                allowBlank: true,
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
		                allowBlank: true,
		                minValue: 1,
		                maxValue: 150000
		            }
		        }
//		        {
//		            xtype: 'numbercolumn',
//		            header: 'ttl',
//		            dataIndex: 'ttl',
//		            format:'0',
//		            width: 90
//		        }
//		        ,
//		        {
//		            xtype: 'numbercolumn',
//		            header: 'totalAmount',
//		            dataIndex: 'totalAmount',
//		            format: '$0,0',
//		            width: 90
//		        }
		        ],
		        renderTo: 'pdf_grid',
		        width: 1000,
		        height: 600,
		        title: 'Customer Orders',
		        frame: true,
		        tbar: [{
		            text: 'Add Order',
		            iconCls: 'employee-add',
		            disabled: true,
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
		                	'sizeUNI6':''
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
		        },
		        "->",
		        //增加一个修改指定order的按钮
		        {
		        	xtype: 'combo',
		        	listConfig:{
		        		emptyText:'未找到匹配值',  
		        		loadingText: '正在查找...',
             			maxHeight:180
		        	},
		        	minChars:3,
             		queryDelay:200,
             		name:'orderNo',
             		fieldLabel:'订单号',
             		displayField:'label',
             		valueField:'value',
             		queryMode:'remote',
             		hideTrigger: true,
             		typeAhead:true,
             		forceSelection:true,
             		store : ipm.cpq.pdf.store.pdfOrderStore(),
             		listeners:{
             			select:function(combo,record,opts) {
             				var orderNo = record[0].get("value");
             				var clothingType = record[0].get("clothingType");
             				ipm.cpq.pdf.reloadStore(-1,clothingType,orderNo);
             			}
             		}
		        },
		        "->",
		        {
        			xtype: 'combobox',
        			name: 'pdfId',
        			fieldLabel: '已经上传的pdf',
        			width:350,
        			labelWidth:100,
    				store: ipm.cpq.pdf.store.uploadedPdf(),
    				queryMode: 'remote',
    				displayField: 'label',
    				region:'east',
    				valueField: 'value',
    				listeners:{
    					select:function(combo,record,opts) {
    						var pdfId = record[0].get("value");
    						var clothingType = record[0].get("clothingType");
    						ipm.cpq.pdf.reloadStore(pdfId,clothingType);
   						}
    				}
        		}
		        ],
		        plugins: [rowEditing],
		        listeners: {
//		            'selectionchange': function(view, records) {
//		                grid.down('#removePdfOrder').setDisabled(!records.length);
//		            }
		        	'beforeedit': function(editor, e) {
       					if (e.colIdx === 0 && e.record.get('status') == 4){
             				return false;
         				}
		        	}
		        }
		    });
		},
		
		reloadStore : function(pdfId, clothingType,orderNo){
			var grid = ipm.cpq.pdf.grid;
			var store = grid.store;
			var url = '/ipm' + '/cpq/getPdfStore/' + pdfId;
			var hasOrder = false;
			if(orderNo && orderNo.length > 0){
				url += '?orderNo=' + orderNo;
				hasOrder = true;
			}
			store.proxy.url = url;
            //TODO 隐藏不需要的列 grid.columns[i].setVisible(false/true); grid.columns[i].hide()/show()
			if(!hasOrder){
				//1. 全部隐藏
				for(var i = 3; i < 19 + 6 ; i++){
					grid.columns[i].hide();
				}
				//2. 开发部分
				if(clothingType == 'MALE'){//男 Size S,Size M,Size L,Size XL,Size XXL    3~7
					for(var i = 3; i < 8 ; i++){
						grid.columns[i].show();
					}
				}else if(clothingType == 'FEMALE'){//女 Size P.Size 1.Size 2.Size 3.Size 4
					for(var i = 8; i < 13 ; i++){
						grid.columns[i].show();
					}
				}else if(clothingType == 'BOY' || clothingType == 'GIRL'){//男童 Size 4,Size 6,Size 8,Size 10,Size 12,Size 14,Size 16
					for(var i = 12; i < 19 ; i++){
						grid.columns[i].show();
					}
					grid.columns[10].show();
				}else if(clothingType == 'FAMILY'){//亲子  Size UNI1, Size UNI2, Size UNI3,Size UNI4,Size UNI5,Size UNI6
					for(var i = 19; i < 19 + 6 ; i++){
						grid.columns[i].show();
					}
				}
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
	             'Ext.form.*',
    			 'Ext.window.MessageBox'
	         ]);
	Ext.onReady(function () {
		Ext.QuickTips.init();
		ipm.cpq.pdf.init();
	});
});