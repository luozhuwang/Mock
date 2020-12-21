function edit(userId){	
	$("#editUserModal input#hf-status").val("");
	$.ajax({
		type : "post",  
        url : "edit/"+userId+"",  
        dataType: "json", 
        cache: false,
        async: false ,
        success : function(result) {
        	console.log(result);
        	var sex=result.data.sex;
        	var status=result.data.status;
        	$("#editUserModal input#hf-userId").val(result.data.userId);
        	$("#editUserModal input#hf-loginName").val(result.data.loginName);
        	$("#editUserModal input#hf-userName").val(result.data.userName);
        	$("#editUserModal input#hf-email").val(result.data.email);
        	$("#editUserModal input#hf-phone").val(result.data.phone);
        	if( sex !=null){        		
        		$("#editUserModal input[id='hf-sex-radio'][value='"+sex+"']").click(); 
        	}else{
        		$("#editUserModal input[id='hf-sex-radio'][value='0']").click();
        	}       	
        	$("#editUserModal input#hf-status").val(status);
        	if(status == 0){  
        		$("#editUserModal input#hf-status").prop("checked",true); 
        	}else{
        		$("#editUserModal input#hf-status").prop("checked",false); ;
        	}
        	
        	$("#editUserModal textarea#hf-remark").text(result.data.remark);
        },error:function(result){			 
			alert("获取用户数据失败");
			 console.log(e);
		}
	});
	
}
function editAction(){
	var updateBy=$("#userId").val();
	var userId=$("#editUserModal input#hf-userId").val();
	var userName=$("#editUserModal input#hf-userName").val();
	var email=$("#editUserModal input#hf-email").val();
	var phone=$("#editUserModal input#hf-phone").val();
	var sex=$("#editUserModal input#hf-sex-radio:checked").val();
	var status=$("#editUserModal input#hf-status").val();
	var remark=$("#editUserModal textarea#hf-remark").val();
	
	var data3="{\"updateBy\":"+updateBy+",\"userId\":"+userId+",\"userName\":\""+userName+"\",\"email\":\""+email+"\",\"phone\":\""+phone+"\",\"sex\":"+sex+",\"status\":"+status+",\"remark\":\""+remark+"\"}"; 
	console.log(data3);

	$.ajax({
		type : "post",  
		url : "editAction",  
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
				alert("编辑用户成功");
				window.location.reload();
			}
		},error:function(result){			 
			alert("编辑用户失败");
			 console.log(result);
		}
		
	});
}

function del(userId){
	var updateBy=$("#userId").val();
	var data3="{\"updateBy\":"+updateBy+",\"userId\":"+userId+"}";
	var r=confirm("是否刪除?");
	if(r==true){		
		$.ajax({  
			type : "post",  
			url : "del",  
			dataType: "json", 
			data:data3,		
			contentType : "application/json;charset=UTF-8",
			cache: false,
			async: false ,
			success : function(result) {
				console.log(result);
				window.location.reload();
			},error:function(result){			 
				alert("删除用户失败");
				 console.log(e);
			} 
		});
	}
}
function disable(userId){
	var updateBy=$("#userId").val();
	$.ajax({  
		type : "post",  
		url : "switchOn",			
		dataType: "json", 
		contentType : "application/json;charset=UTF-8",
		data:"{\"userId\": "+userId+",\"status\": 1,\"updateBy\": "+updateBy+"}",
		cache: false,
		async: false ,
		success : function(result) {
				console.log("用户已停用");	
				alert("用户已停用");
				window.location.reload();
		},error:function(result){			 
			alert("更新用户状态失败");
			 console.log(e);
		} 
	});
}
function enable(userId){
	var updateBy=$("#userId").val();
	$.ajax({  
		type : "post",  
		url : "switchOn",			
		dataType: "json", 
		contentType : "application/json;charset=UTF-8",
		data:"{\"userId\": "+userId+",\"status\": 0,\"updateBy\": "+updateBy+"}",
		cache: false,
		async: false ,
		success : function(result) {
			console.log("用户已恢复");					
			alert("用户已恢复");
			window.location.reload();
		},error:function(result){			 
			alert("更新用户状态失败");
			 console.log(e);
		} 
	});
}

function resetPWD(userId){
	var updateBy=$("#userId").val();
	var r=confirm("重置密码");
	if(r==true){		
		$.ajax({  
			type : "post",  
			url : "resetPWD",  
			dataType: "json", 
			contentType : "application/json;charset=UTF-8",
			data:"{\"userId\": "+userId+",\"updateBy\": "+updateBy+"}",
			cache: false,
			async: false ,
			success : function(result) {
				console.log(result);
				alert("新密码：123456");
				window.location.reload();
			},error:function(result){			 
				alert("重置用户密码失败");
				 console.log(e);
			} 
		});
	}
}
function initData(loginName,phone,status,beginTime,endTime){
	var data4;
	var element = $("#pages");
	var pageSize=10;
	var total=0
   	var jsonlenth=0;
	var currentPage=0;
	var totalPages=0;
	var numberOfPages=0;
	var userType=$("#userType").val();
	$.ajax({
		type : "post",  
        url : "list",  
        data:"{\"loginName\": \""+loginName+"\",\"status\": \""+status+"\",\"phone\": \""+phone+"\",\"beginTime\": \""+beginTime+"\",\"endTime\": \""+endTime+"\",\"pageNum\":1,\"pageSize\":"+pageSize+"}",
        dataType: "json", 
        contentType : "application/json;charset=UTF-8",
        cache: false,
        async: false ,
        success : function(result) {
        	var json = eval(result.data); //数组   
       	 	var div="", switchinfo="",Authority=""; 
	       	if(json==null){  
	       		div+="<tr><td colspan='7' style='text-align:center'>无</td></tr>"        		
	       	}else{
	       		jsonlenth=json.size; 
	       		 total=json.total;
	       		currentPage=result.data.pageNum;
	   		 	totalPages=result.data.pages;
	   		 	numberOfPages=result.data.pageSize
	       		for (var i = 0; i <jsonlenth; i++) {
	       			var Number=result.data.list[i].phone;
	       			var User=result.data.list[i].userName;
	       			var loginDate=result.data.list[i].loginDate;
	       			if(Number==null || Number==''){
	       				Number='无';
	       			}
	       			if(User==null){
	       				User='无';
	       			}
	       			if(loginDate==null || loginDate==''){
	       				loginDate='无';
	       			}
	       			//普通用户只有列表权限
	       			if(userType == 0){
	       				Authority="无";
	       				switchinfo="<td  id='status' value='"+result.data.list[i].status+"'>"+(result.data.list[i].status==0?'正常':'停用')+"</td>";
	       			}else{
	       				//管理员可操作用户
	       				var Id=result.data.list[i].userId;
	       				var statuss=result.data.list[i].status;
	       				if(statuss==0){
	       					switchinfo="<td value='0' id='status' ><label class='lyear-switch switch-solid switch-primary'><input type='checkbox' checked='' onclick='disable("+Id+")'><span></span></label></td>"
	       				}else{
	       					switchinfo="<td value='1' id='status'  ><label  class='lyear-switch switch-solid switch-primary' ><input type='checkbox' onclick='enable("+Id+")'><span></span</label></td>"
	       				}
	       				var Type=result.data.list[i].userType;
	       				if(Type==1){
		       				switchinfo="<td  id='status' value='"+result.data.list[i].status+"'>"+(result.data.list[i].status==0?'正常':'停用')+"</td>";
	       					Authority="";
	       				}else{		       					
	       					Authority="<a class='btn btn-info btn-xs' data-toggle='modal' data-target='#editUserModal' data-whatever='@mdo' onclick='edit("+result.data.list[i].userId+")' title='编辑'  data-toggle='tooltip'><i class='mdi mdi-pencil'></i>编辑</a>"+
	       					"<a class='btn btn-danger btn-xs'  onclick='del("+result.data.list[i].userId+")' title='删除'  data-toggle='tooltip'><i class='mdi mdi-window-close'></i>删除</a>"+
	       					"<a class='btn btn-success btn-xs' onclick='resetPWD("+result.data.list[i].userId+")' title='重置密码'  data-toggle='tooltip'><i class='mdi mdi-key-variant'></i>重置密码</a>";
	       				}
	       			}
	       			div+="<tr id='row"+result.data.list[i].userId+"'><td id='userId' style='display:none'>"+result.data.list[i].userId+"</td>"+
	       			"<td title='"+result.data.list[i].loginName+"' id='loginName'>"+result.data.list[i].loginName+"</td>"+
	       			"<td title='"+(result.data.list[i].userType==0 ? '否':'是')+"' id='userType'>"+(result.data.list[i].userType==0?'否':'是')+"</td>"+
	       			"<td title='"+result.data.list[i].userName+"' id='userName'>"+User+"</td>"+
	       			"<td title='"+Number+"' id='phone'>"+Number+"</td>"+
	       				switchinfo+
	       			"<td title='"+loginDate+"' id='loginDate'>"+loginDate+"</td>"+
	       			"<td>"+Authority+
	       			"</td></tr>";
	       		}
	       		
	       	}
	        $("#group_one").html(div);
	        $("#count").html("共计"+total+"条数据，每页"+pageSize+"条，当前第1页");
   		 	if(totalPages<=0){
   		 		totalPages=1;
   		 	}
   		 var options = {
   				 bootstrapMajorVersion:3, //bootstrap的版本要求
                 currentPage:currentPage,//当前页数
                 totalPages:totalPages,//总页数
                 numberOfPages:numberOfPages,//每页记录数               
                 
                 itemTexts : function(type, page, current) {//设置分页按钮显示字体样式
                     switch (type) {
                     case "first":
                         return "首页";
                     case "prev":
                         return "上一页";
                     case "next":
                         return "下一页";
                     case "last":
                         return "末页";
                     case "page":
                         return page;
                     }
                 },
                 
                 onPageClicked:function(event,originalEvent,type,page){//分页按钮点击事件
                	 $.ajax({  
                	        type : "post",  
                	        url : "list",  
                	        data:"{\"loginName\": \""+loginName+"\",\"status\": \""+status+"\",\"phone\": \""+phone+"\",\"beginTime\": \""+beginTime+"\",\"endTime\": \""+endTime+"\",\"pageNum\":"+page+",\"pageSize\":"+pageSize+"}",  
                	        dataType: "json", 
                	        contentType : "application/json;charset=UTF-8",
                	        cache: false,
                	        async: false ,
                	        success : function(result) {
                	        	var json = eval(result.data); //数组   
                	       	 	var div="";
                		       	if(json==null){  
                		       		div+="<tr><td colspan='7' style='text-align:center'>无</td></tr>"        		
                		       	}else{
                		       		var jsonlenth=json.size;
                		       		var total=json.total;
                		       		for (var i = 0; i <jsonlenth; i++) {
                		       			var loginDate=result.data.list[i].loginDate;
                		       			var Number=result.data.list[i].phone;
                		       			var User=result.data.list[i].userName;
                		       			if(Number==null || Number==''){
                		       				Number='无';
                		       			}
                		       			if(User==null){
                		       				User='无';
                		       			}
                		       			if(loginDate==null || loginDate==''){
                		       				loginDate='无';
                		       			}
                		       			if(userType == 0){
                		       				Authority="无";
                		       				switchinfo="<td  id='status' value='"+result.data.list[i].status+"'>"+(result.data.list[i].status==0?'正常':'停用')+"</td>";
                		       			}else{                		       				
                		       				var Id=result.data.list[i].userId;
                		       				var statuss=result.data.list[i].status;
                		       				if(statuss==0){
                		       					switchinfo="<td value='0' id='status' ><label class='lyear-switch switch-solid switch-primary'><input type='checkbox' checked='' onclick='disable("+Id+")'><span></span></label></td>"
                		       				}else{
                		       					switchinfo="<td value='1' id='status'  ><label  class='lyear-switch switch-solid switch-primary' ><input type='checkbox' onclick='enable("+Id+")'><span></span</label></td>"
                		       				}
                		       				
                		       				var Type=result.data.list[i].userType;
                		       				if(Type==1){
                		       					switchinfo="<td 'id='status' >正常</td>";
                		       					Authority="";
                		       				}else{	       				
                		       					Authority="<a class='btn btn-info btn-xs' data-toggle='modal' data-target='#editUserModal' data-whatever='@mdo' onclick='edit("+result.data.list[i].userId+")' title='编辑'  data-toggle='tooltip'><i class='mdi mdi-pencil'></i>编辑</a>"+
                		       					"<a class='btn btn-danger btn-xs'  onclick='del("+result.data.list[i].userId+")' title='删除'  data-toggle='tooltip'><i class='mdi mdi-window-close'></i>删除</a>"+
                		       					"<a class='btn btn-success btn-xs' onclick='resetPWD("+result.data.list[i].userId+")' title='重置密码'  data-toggle='tooltip'><i class='mdi mdi-key-variant'></i>重置密码</a>";
                		       				}
                		       			}
                		       			div+="<tr id='row"+result.data.list[i].userId+"'><td id='userId' style='display:none'>"+result.data.list[i].userId+"</td>"+
                		       			"<td title='"+result.data.list[i].loginName+"' id='loginName'>"+result.data.list[i].loginName+"</td>"+
                		       			"<td title='"+(result.data.list[i].userType==0 ? '否':'是')+"' id='userType'>"+(result.data.list[i].userType==0?'否':'是')+"</td>"+
                		       			"<td title='"+result.data.list[i].userName+"' id='userName'>"+User+"</td>"+
                		       			"<td title='"+Number+"' id='phone'>"+Number+"</td>"+
                		       				switchinfo+
                		       			"<td title='"+loginDate+"' id='loginDate'>"+loginDate+"</td>"+
                		       			"<td>"+Authority+
                		       			"</td></tr>";
                		       		
                		       		}
                		       	}
                		        $("#group_one").html(div);
                		        $("#count").html("共计"+total+"条数据，每页"+pageSize+"条，当前第"+page+"页");
                	        }
                	 })
                 }
   		 }
   		 //初始化分页框
   		 element.bootstrapPaginator(options);
        },
        error : function(e) {           //请求失败的回调函数
        	alert("请求user列表错误");
            console.log(e);
        }
		
	});
	
}