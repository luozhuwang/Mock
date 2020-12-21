$(document).ready(function(){
	$("#editPassWdModal input#hf-confirm-pwd").blur(function(){
        var newPwd=$("#editPassWdModal input#hf-new-pwd").val();
		var confirmPWD=$("#editPassWdModal input#hf-confirm-pwd").val();
	
			if(!(newPwd== confirmPWD)){
				alert("确认密码与新密码不一致，请重新输入");
				$("#editPassWdModal input#hf-new-pwd").val("");
				$("#editPassWdModal input#hf-confirm-pwd").val("");
			}else{
				console.log("aa");
			}
    });
});
function editPer(){
	var userId=$("#userId").val();
	editPersonal(userId);
}
function Loginout(){
	$.ajax({
			type : "post",  
			url : "/Mock/system/outLogin",  
			cache: false,
			async: false ,
			success : function(result) {
				window.location.replace("/Mock/system/login.html");
			}
	});

};
function editPWD(){
	var userId=$("#userId").val();
	var oldPassword=$("#editPassWdModal input#hf-old-pwd").val();
	var password=$("#editPassWdModal input#hf-new-pwd").val();
	var data3="{\"updateBy\": "+userId+",\"userId\":"+userId+",\"oldPassword\":\""+oldPassword+"\",\"password\":\""+password+"\"}"; 
	console.log(data3);
		$.ajax({  
			type : "post",  
			url : "/Mock/system/user/editPWD",  
			dataType: "json", 
			data:data3,		
			contentType : "application/json;charset=UTF-8",
			cache: false,
			async: false ,
			success : function(result) {
				console.log(result);
				var json=eval(result);
				var code=json.code;
				if(code!=0){
					alert(json.data);
				}else{
					alert("密码修改成功,请重新登陆");
					Loginout();
				}
			},error:function(result){			 
				alert("密码修改失败");
				 console.log(e);
			} 
		});	
}

function editPersonal(userId){	
	$.ajax({
		type : "post",  
        url : "/Mock/system/user/edit/"+userId+"",  
        dataType: "json", 
        cache: false,
        async: false ,
        success : function(result) {
        	console.log(result);
        	var sex=result.data.sex;
        	var status=result.data.status;
        	$("#personalModal input#hf-per-userId").val(result.data.userId);
        	$("#personalModal input#hf-per-loginName").val(result.data.loginName);
        	$("#personalModal input#hf-per-userName").val(result.data.userName);
        	$("#personalModal input#hf-per-email").val(result.data.email);
        	$("#personalModal input#hf-per-phone").val(result.data.phone);
        	if( sex !=null){        		
        		$("#personalModal input[id='hf-per-sex-radio'][value='"+sex+"']").click(); 
        	}else{
        		$("#personalModal input[id='hf-per-sex-radio'][value='0']").click();
        	}       	
//        	$("#personalModal input#hf-status").val(status);        	
        	$("#personalModal textarea#hf-per-remark").text(result.data.remark);
        },error:function(result){			 
			alert("获取用户数据失败");
			 console.log(e);
		}
	});
	
}
function editPersonalAction(){
	
	var userId=$("#personalModal input#hf-per-userId").val();
	var userName=$("#personalModal input#hf-per-userName").val();
	var email=$("#personalModal input#hf-per-email").val();
	var phone=$("#personalModal input#hf-per-phone").val();
	var sex=$("#personalModal input#hf-per-sex-radio:checked").val();
	var remark=$("#personalModal textarea#hf-per-remark").val();
	
	var data3="{\"updateBy\": "+userId+",\"userId\":"+userId+",\"userName\":\""+userName+"\",\"email\":\""+email+"\",\"phone\":\""+phone+"\",\"sex\":"+sex+",\"remark\":\""+remark+"\"}"; 
	console.log(data3);

	$.ajax({
		type : "post",  
		url : "/Mock/system/user/editAction",  
		dataType: "json", 
		data:data3,		
		contentType : "application/json;charset=UTF-8",
		cache: false,
		async: false ,
		success : function(result) {
			console.log(result);
			var json=eval(result);
			var code=json.code;
			if(code!=0){
				alert(json.errMsg);
			}else{
				alert("编辑个人中心成功");
				window.location.reload();
			}
		},error:function(result){			 
			alert("编辑个人中心失败");
			 console.log(result);
		}
		
	});
}