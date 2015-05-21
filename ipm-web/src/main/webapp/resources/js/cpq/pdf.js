ipm.cpq = {
	pdf : {
		init : function(){
			
		    var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
		        clicksToMoveEditor: 1,
		        autoCancel: false
		    });

		    var grid = Ext.create('Ext.grid.Panel', {
		        store: ipm.cpq.pdf.store.init(),
		        columns: [{
		            header: 'Order',
		            dataIndex: 'Order',
		            flex: 1,
		            editor: {
		                // defaults to textfield if no xtype is supplied
		                allowBlank: false
		            }
		        }, {
		            header: 'Style',
		            dataIndex: 'Style',
		            width: 160,
		            editor: {
		                allowBlank: false,
		            }
		        },  {
		            header: 'Colour',
		            dataIndex: 'Colour',
		            width: 160,
		            editor: {
		                allowBlank: false,
		            }
		        },{
		            xtype: 'numbercolumn',
		            header: 'Size S',
		            dataIndex: 'Size S',
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
		            dataIndex: 'Size M',
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
		            dataIndex: 'Size L',
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
		            dataIndex: 'Size XL',
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
		            dataIndex: 'Size XXL',
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
		            header: 'TTL',
		            dataIndex: 'TTL',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 990000
		            }
		        },
		        {
		            xtype: 'numbercolumn',
		            header: 'Total Amount',
		            dataIndex: 'Total Amount',
		            format: '$0,0',
		            width: 90,
		            editor: {
		                xtype: 'numberfield',
		                allowBlank: false,
		                minValue: 1,
		                maxValue: 150000
		            }
		        }],
		        renderTo: 'pdf_grid',
		        width: 600,
		        height: 400,
		        title: 'Customer Orders',
		        frame: true,
		        tbar: [{
		            text: 'Add Order',
		            iconCls: 'employee-add',
		            handler : function() {
		                rowEditing.cancelEdit();

		                // Create a model instance
		                var r = Ext.create('pdfModel', {
		                	'Order': '',
		                	'Style':'',
		                	'Colour':'',
		                	'Size S':'',
		                	'Size M':'',
		                	'Size L':'',
		                	'Size XL':'',
		                	'Size XXL':'',
		                	'TTL':'',
		                	'Total Amount':'0'
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
	             'Ext.form.*'
	         ]);
	Ext.onReady(function () {
		Ext.QuickTips.init();
		ipm.cpq.pdf.init();
	});
});