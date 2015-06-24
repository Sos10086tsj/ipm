ipm.cpqMenu = {
	init : function(){
		//初始化菜单
		var menus = Ext.create('Ext.menu.Menu', {
			style: {
            	overflow: 'visible'     // For the Combo popup
        	},
        	items: [
        		{
        			text : '上传pdf',
        			handler : function(){  
                    	ipm.cpqMenu.menuBtnClick(ctx + '/cpq/pdf');
                	} 
        		},
        		{
        			text : '上传excel',
        			handler : function(){  
                    	ipm.cpqMenu.menuBtnClick(ctx + '/cpq/excel');
                	} 
        		},
        		{
        			text : '打印报表',
        			handler : function(){  
                    	ipm.cpqMenu.menuBtnClick(ctx + '/cpq/report');
                	} 
        		}
        	]
		});
		
		var menuToolbar = Ext.create('Ext.toolbar.Toolbar',{
//			width : 650,
			renderTo: 'js_menu',
			items : [
				{
					text:'打单',
            		menu: menus  // assign menu by instance
				}
			]
		});
		menuToolbar.resumeLayouts(true);
	},
	
	menuBtnClick : function(url){
		window.location.href = url;
	}
};

$(function(){
	Ext.require([
    	'Ext.tip.QuickTipManager',
    	'Ext.menu.*',
//    	'Ext.form.field.ComboBox',
    	'Ext.layout.container.Table',
    	'Ext.container.ButtonGroup'
	]);
	Ext.onReady(function () {
		Ext.QuickTips.init();
		ipm.cpqMenu.init();
	});
});