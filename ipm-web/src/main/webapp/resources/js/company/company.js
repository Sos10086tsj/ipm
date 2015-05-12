company = {
	//提交创建用户
	submitCreateUser : function(){
		var username = $("#js_username_id");
		var passowrd = $("#js_passowrd_id");
		var name = $("#js_name_id");
		var companyId = $("#js_company_id");
		if(company.submitCreateUserMandatoryCheck(username,passowrd,name,companyId)){
			$.ajax({
				url : ctx + '/compnaymanag/createusere',
				type : 'post',
				data : {
					username : username,
					passowrd : passowrd,
					name : name,
					companyId : companyId
				},
				success : function(response){
					console.log(response.data);
				},
				failure : function(error){
					alert('Save failed, please retry or contact support team for help.');
				}
			});
		}else{
			alert("Mandatory field can not be empty!");
		}
	},
	submitCreateUserMandatoryCheck : function(username,passowrd,name,companyId){
		var pass = true;
		if(!username || username.length <= 0){
			pass = false;
			return pass;
		}
		if(!passowrd || passowrd.length <= 0){
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

