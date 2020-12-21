function cleanLogin(){
	$("#signup-loginName").val("");
	$("#signup-userName").val("");
	$("#signup-password").val("");
	$("#signin-loginName").val("");
	$("#signin-password").val("");
};

function Loginout(){
	var r=confirm("是否退出系统！");
	if(r==true){		
		$.ajax({
			type : "post",  
			url : "/Mock/system/outLogin",  
			cache: false,
			async: false ,
			success : function(result) {
				window.location.replace("/Mock/system/login.html");
			}
		});
	}
};
function registSys(){
	var loginName=$("#signup-loginName").val();
	var userName=$("#signup-userName").val();
	var password=$("#signup-password").val();
	var data3="{\"loginName\":\""+loginName+"\",\"userName\":\""+userName+"\",\"password\":\""+password+"\"}"; 
	console.log(data3);
	
	$.ajax({
		type : "post",  
        url : "registAction",  
        dataType: "json", 
        data:data3,		
        contentType : "application/json;charset=UTF-8",
        cache: false,
        async: false ,
        success : function(result) {
        	var json=eval(result);
			var code=json.code;
			if(code!=0){
				alert(json.errMsg);
				console.log(result);
			}else{
				alert(json.msg);
				cleanLogin();
				$("#toLogin").click();
			}
        },error:function(result){			 
			alert("注册异常");
			 console.log(result);
		}
	});
}
function loginSys(){	
	var loginName=$("#signin-loginName").val();
	var password=$("#signin-password").val();
	var data3="{\"loginName\":\""+loginName+"\",\"password\":\""+password+"\"}"; 
	
	$.ajax({
		type : "post",  
        url : "loginAction",  
        dataType: "json", 
        data:data3,		
        contentType : "application/json;charset=UTF-8",
        cache: false,
        async: false ,
        success : function(result) {
        	var json=eval(result);
			var code=json.code;
			if(code!=0){
				alert(json.errMsg);
				console.log(result);
			}else{
				window.location.replace("index.html");
			}
        },error:function(result){			 
			alert("登陆异常");
			 console.log(result);
		}
	});
}