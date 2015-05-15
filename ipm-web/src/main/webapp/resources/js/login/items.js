login.items = {
		//init alert msg
		initAlertMsg:function(){
			var alertLabel = new Ext.form.Label({
				id : 'loing_item_alert_label_id',
				text : '',
				hidden : false,
				style : {
					color: '#EA0000',
					fontSize : 16
				},
				//colspan : 2,
				height : 35,
				padding : '10 0 10 50',
				columnWidth : 1
			});
			return alertLabel;
		},
		
		initUsernamePic:function(){
			var pic =  Ext.create('Ext.Img', {
			    src: '../../resources/images/login/username.jpg',
			    width : 28,
			    height : 25,
			    columnWidth : 0.12,
			    margin : '20 5 10 0',
			});
			return pic;
		},
		//user name text filed
		initUsername:function(){
			var username = new Ext.form.TextField({
				width : 145,
				height : 25,
				allowBlank : false,
				maxLength : 20,
				name : 'username',
				blankText : login.labels.userEmptyText,
				maxLengthText : login.labels.maxUserLengthText,
				margin : '20 0 10 0',
				columnWidth : 0.88
			});
			return username;
		},
		initPasswordPic:function(){
			var pic =  Ext.create('Ext.Img', {
			    src: '../../resources/images/login/password.jpg',
			    width : 28,
			    height : 25,
			    columnWidth : 0.12,
			    margin : '20 5 10 0',
			});
			return pic;
		},
		//password text field
		initPassword:function(){
			var pass = new Ext.form.TextField({
				width : 145,
				height : 25,
				allowBlank : false,
				maxLength : 20,
				name : 'password',
				blankText : login.labels.passEmptyText,
				blankText : login.labels.passEmptyText,
				inputType : 'password',
				margin : '20 0 10 0',
				columnWidth : 0.88
			});
			return pass;
		},
		//register button
		initRegister:function(){
			var register = new Ext.Button({
				width : 80,
				text: login.labels.registerText,
				handler: function(){
					Ext.Msg.alert("提示", "尚未实现");
				}
			});
			return register;
		},
		//login button
		initLogin:function(){
			var loginBtn = new Ext.Button({
				 text: login.labels.loginText,
				 width : 180,
				 height : 50,
				 handler: function(){
					var label = Ext.getCmp('loing_item_alert_label_id');
					label.setText(' ');
					if (login.loginForm.getForm().isValid()) {
						login.loginForm.getForm().submit({
							url : '/fastcontacts/login',
							method : 'POST',
							waitMsg : login.labels.waitingMsg,
							timeout : 60000,
							success : function(form, action){
								location.href = 'fastcontacts/mainBoard';
							},
							failure : function(form, action){
								label.setText( action.result.errMsg );
							}
						});
					}else{
						label.setText(login.labels.loginAlertMsg);
					}
				 }
			});
			return loginBtn;
		}
}