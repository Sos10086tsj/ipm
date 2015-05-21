ipm.cpq = {
	pdf : {
		init : function(){
			console.log("begin init");
		    var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
		        clicksToMoveEditor: 1,
		        autoCancel: false
		    });

		    var grid = Ext.create('Ext.grid.Panel', {
		        store: ipm.cpq.pdf.store.init(),
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
		                allowBlank: false,
		            }
		        },  {
		            header: 'colour',
		            dataIndex: 'colour',
		            width: 160,
		            editor: {
		                allowBlank: false,
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
		        },
		        {
		            xtype: 'numbercolumn',
		            header: 'tTL',
		            dataIndex: 'tTL',
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
		            header: 'totalAmount',
		            dataIndex: 'totalAmount',
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
	             'Ext.form.*'
	         ]);
	Ext.onReady(function () {
		Ext.QuickTips.init();
		ipm.cpq.pdf.init();
	});
});