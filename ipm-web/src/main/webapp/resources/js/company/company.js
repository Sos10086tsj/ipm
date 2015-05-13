company = {
	//提交创建用户
	submitCreateUser : function(){
		var username = $("#js_username_id").val();
		var password = $("#js_password_id").val();
		var name = $("#js_name_id").val();
		var companyId = $("#js_company_id").val();
		if(company.submitCreateUserMandatoryCheck(username,password,name,companyId)){
			$.ajax({
				url : ctx + '/compnaymanage/createuser',
				type : 'post',
				data : {
					username : username,
					password : password,
					name : name,
					companyId : companyId
				},
				success : function(response){
					console.log(response.data);
					alert('User ' + response.data + ' created.');
				},
				failure : function(error){
					alert('Save failed, please retry or contact support team for help.');
				}
			});
		}else{
			alert("Mandatory field can not be empty!");
		}
	},
	submitCreateUserMandatoryCheck : function(username,password,name,companyId){
		var pass = true;
		if(!username || username.length <= 0){
			pass = false;
			return pass;
		}
		if(!password || password.length <= 0){
			pass = false;
			return pass;
		}
		if(!name || name.length <= 0){
			pass = false;
			return pass;
		}
		if(!companyId || companyId.length <= 0){
			pass = false;
			return pass;
		}
		return pass;
	}
	
};

