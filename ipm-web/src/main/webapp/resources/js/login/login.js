ipm.login = {
	loginForm : null,
	initPanel : function(){
		ipm.login.loginForm = Ext.create("Ext.form.Panel",{
			title : '<font style="font-size: 18px;">' + login.labels.title + '</font>',
			buttonAlign: 'center',
			renderTo : 'login_panel',
			layout:{    
	            type:'column'  
	            //columns:2 
	        }, 
	        border : 0,
			width : 300,
			height : 250,
			style : {
			    borderStyle: 'solid',
			    borderWidth: 1,
			    borderColor: '#dedede #dedede #dedede #dedede'
			},
			header : Ext.panel.Header({
				style : {
				    borderWidth: 1,
				    borderColor: '#dedede #dedede #dedede #dedede',
					borderStyle :'solid',
					padding : '10 10 10 10'
				}
			}),
			bodyStyle : {
			    borderStyle: 'solid',
			    borderWidth: 1,
			    borderColor: '#dedede #dedede #dedede #dedede',
			    padding : '10 10 10 10'
			},
		});
	}
};

$(function(){
	Ext.onReady(function () {
		Ext.QuickTips.init();
		ipm.login.initPanel();
	}); 
});