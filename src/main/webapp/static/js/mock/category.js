function categorylist() {	
	initDataCategory("");
}
function clearModal() {
	$("#hf-name").val("");
	$("#hf-remark").val("");	
}
function cleareditModal() {
	$("#hf-edit-name").val("");
	$("#hf-edit-remark").val("");	
}

function addAction(){
	var createBy=$("#userId").val();
	var name=$("#addCategoryModal input#hf-name").val();
	var remark=$("#addCategoryModal textarea#hf-remark").val();
	
	var data3={"name":""+name+"","remark":""+remark+"","createBy":""+createBy+""}; 
	var data4=JSON.stringify(data3);
	$.ajax({
		type : "post",  
		url : "/Mock/system/category/addAction",  
		dataType: "json", 
		data:data4,		
		contentType : "application/json;charset=UTF-8",
		cache: false,
		async: false ,
		success : function(result) {
			var json=eval(result);
			var code=json.code;
			if(code!=0){
				alert(json.errMsg);
			}else{
				alert("新增【模块】 成功");
				$("#addCategoryModal .close").click();
				initDataCategory("");				
			}
		},error:function(result){			 
			alert("新增【模块】失败");
			 console.log(result);
		} 
	});
}

function edit(id){
	cleareditModal();
	$.ajax({  
        type : "post",  
        url : "/Mock/system/category/edit/"+id+"",  
        dataType: "json", 
        cache: false,
        async: false ,
        success : function(result) {
        	console.log(result);
        	$("#editCategoryModal input#hf-edit-id").val(result.data.id);
        	$("#editCategoryModal input#hf-edit-name").val(result.data.name);
        	$("#editCategoryModal textarea#hf-edit-remark").val(result.data.remark);
        },error:function(result){			 
			alert("获取【模块】数据失败");
		}
	});
}
function editAction(){
	var updateBy=$("#userId").val();
	var id=$("#hf-edit-id").val();
	var name=$("#editCategoryModal input#hf-edit-name").val();
	var remark=$("#editCategoryModal textarea#hf-edit-remark").val();
	
	var data3={"id":""+id+"","name":""+name+"","remark":""+remark+"","updateBy":""+updateBy+""}; 
	var data4=JSON.stringify(data3);
	
	$.ajax({  
        type : "post",  
        url : "/Mock/system/category/editAction",  
		url : "editAction",  
		dataType: "json", 
		data:data4,		
		contentType : "application/json;charset=UTF-8",
		cache: false,
		async: false ,
        success : function(result) {
        	var json=eval(result);
			var code=json.code;
			if(code!=0){
				alert(json.errMsg);
			}else{
				alert("编辑【模块】成功");
				window.location.reload();
			}
        },error:function(result){			 
			alert("编辑【模块】失败");
		}
	});
}

function initDataCategory(name){
	var element = $("#pages");
	var pageSize=10;
	var total=0
   	var jsonlenth=0;
	var currentPage=0;
	var totalPages=0;
	var numberOfPages=0;
	
	$.ajax({
		type : "post",  
        url : "list",  
        data:"{\"name\": \""+name+"\",\"pageNum\":1,\"pageSize\":"+pageSize+"}",
        dataType: "json", 
        contentType : "application/json;charset=UTF-8",
        cache: false,
        async: false ,
        success : function(result){
        	var div=""
        	var json = eval(result.data); //数组   
        	if(json==null){  
	       		div+="<tr><td colspan='4' style='text-align:center'>无</td></tr>"        		
	       	}else{
	       		jsonlenth=json.size; 
	       		 total=json.total;
	       		currentPage=result.data.pageNum;
	   		 	totalPages=result.data.pages;
	   		 	numberOfPages=result.data.pageSize
	   		 	for (var i = 0; i <jsonlenth; i++) {
		   		 	div+="<tr id='row"+result.data.list[i].id+"'><td id='id' style='display:none'>"+result.data.list[i].id+"</td>"+
	       			"<td title='"+result.data.list[i].name+"' id='name'>"+result.data.list[i].name+"</td>"+
	       			"<td title='"+result.data.list[i].remark+"' id='remark'>"+result.data.list[i].remark+"</td>"+
	       			"<td title='"+result.data.list[i].createTime+"' id='createTime'>"+result.data.list[i].createTime+"</td>"+
	       			"<td><a class='btn btn-info btn-xs' data-toggle='modal' data-target='#editCategoryModal' data-whatever='@mdo' onclick='edit("+result.data.list[i].id+")' title='编辑'  data-toggle='tooltip'><i class='mdi mdi-pencil'></i>编辑</a>"+
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
                	        data:"{\"name\": \""+name+"\",\"pageNum\":"+page+",\"pageSize\":"+pageSize+"}",
                	        dataType: "json", 
                	        contentType : "application/json;charset=UTF-8",
                	        cache: false,
                	        async: false ,
                	        success : function(result) {
                	        	var json = eval(result.data); //数组   
                	       	 	var div="";
                	       	 if(json==null){  
                		       		div+="<tr><td colspan='4' style='text-align:center'>无</td></tr>"        		
                		       	}else{
                		       		jsonlenth=json.size; 
                		       		 total=json.total;
                		       		currentPage=result.data.pageNum;
                		   		 	totalPages=result.data.pages;
                		   		 	numberOfPages=result.data.pageSize
                		   		 	for (var i = 0; i <jsonlenth; i++) {
                			   		 	div+="<tr id='row"+result.data.list[i].id+"'><td id='id' style='display:none'>"+result.data.list[i].id+"</td>"+
                		       			"<td title='"+result.data.list[i].name+"' id='name'>"+result.data.list[i].name+"</td>"+
                		       			"<td title='"+result.data.list[i].remark+"' id='remark'>"+result.data.list[i].remark+"</td>"+
                		       			"<td title='"+result.data.list[i].createTime+"' id='createTime'>"+result.data.list[i].createTime+"</td>"+
                		       			"<td><a class='btn btn-info btn-xs' data-toggle='modal' data-target='#editCategoryModal' data-whatever='@mdo' onclick='edit("+result.data.list[i].id+")' title='编辑'  data-toggle='tooltip'><i class='mdi mdi-pencil'></i>编辑</a>"+
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
        	alert("请求categoryList列表错误");
            console.log(e);
        }
	});
}