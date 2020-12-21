$(document).ready(function(){
	$("#addModal #hf-name").blur(function(){
        if($(this).val().length == 0) {
        	alert("名称不能为空");    
        	$(this).focus();
        }
    });
	$("#addModal #hf-url").blur(function(){
		var url=$(this).val();
        if(!url.startsWith("/")) {
        	alert("URL路径以/开始，请重新输入");
        	$(this).val("");
        	$(this).focus();
        }
    });
//	$("#addModal #hf-content").blur(function(){
//        if($(this).val().length == 0) {
//        	alert("响应内容不能为空"); 
//        	$(this).focus();
//        }
//    });

	$("#editModal #hf-edit-name").blur(function(){
        if($(this).val().length == 0) {
        	alert("名称不能为空");    
        	$(this).focus();
        }
    });
	$("#editModal #hf-edit-url").blur(function(){
		var url=$(this).val();
        if(!url.startsWith("/")) {
        	alert("URL路径以/开始，请重新输入");
        	$(this).val("");
        	$(this).focus();
        }
    });
//	$("#editModal #hf-edit-content").blur(function(){
//        if($(this).val().length == 0) {
//        	alert("响应内容不能为空"); 
//        	$(this).focus();
//        }
//    });
	
});

function categorylist(){
	var newSelect =$("#addModal select#hf-category");	
	var editSelect=$("#editModal select#hf-edit-category");
	var homeSelect=$("select#hf-search-category");
	
	$.ajax({
		type : "post",  
        url : "/Mock/system/category/list",  
        data:"{\"name\": \"\"}",
        dataType: "json", 
        contentType : "application/json;charset=UTF-8",
        cache: false,
        async: false ,
        success : function(result){
        	var div1="",div="";
            	var json = eval(result.data); //数组   
            	if(json==null){  
    	       		div1="&nbsp;&nbsp;&nbsp;<a class='control-label' href='/Mock/system/category/categoryList'>请添加模块</a>";
    	       			select.after(div);
    	       	}else{
    	       		jsonlenth=json.size; 
    	       		div1="&nbsp;&nbsp;&nbsp;<a class='control-label' href='/Mock/system/category/categoryList'>添加模块</a>";
    	       		for (var i = 0; i <jsonlenth; i++) {
    		   		 	div+="<option value="+result.data.list[i].id+" >"+result.data.list[i].name+"</option>";
    	   		 	}
    	       		newSelect.append(div);
    	       		newSelect.after(div1);
    	       		editSelect.append(div); 
    	       		editSelect.after(div1);
    	       		homeSelect.append(div); 
    	       	}
        },
        error : function(e) {           //请求失败的回调函数
        	alert("获取categoryList数据失败");
            console.log(e);
        }
	});
}

function edit(urlId){
	cleareditModel();
	$.ajax({  
        type : "post",  
        url : "edit/"+urlId+"",  
        dataType: "json", 
        cache: false,
        async: false ,
        success : function(result) {
        	console.log(result);

        	$("#editModal input#hf-edit-id").val(result.data.urlId);
        	$("#editModal input#hf-edit-name").val(result.data.name);
        	$("#editModal select#hf-edit-category").val(result.data.categoryId);
        	$("#editModal input#hf-edit-url").val(result.data.url);
        	$("#editModal textarea#hf-edit-description").val(result.data.description);

        },error:function(result){			 
			alert("编辑mock-url获取数据失败");
//			 console.log(result);
		}
	})
}

function editAction(){
	var requestParam="";
	var paramType="";

	
	var updateBy=$("#userId").val();
	var urlId=$("#editModal input#hf-edit-id").val();
	var name=$("#editModal input#hf-edit-name").val().trim();
	var url=$("#editModal input#hf-edit-url").val().trim();	
	var categoryId=$("#editModal select#hf-edit-category").val();
	var description=$("#editModal textarea#hf-edit-description").val();

	var data3={"categoryId":""+categoryId+"","updateBy":""+updateBy+"","urlId":""+urlId+"","url":""+url+"","name":""+name+"","description":""+description+""}; 
//	var data3={"urlId":""+urlId+"","url":""+url+"","name":""+name+"","content":""+content+"","statusCode":""+statusCode+"","description":""+description+""}; 
	var data4=JSON.stringify(data3);
	$.ajax({
		type : "post",  
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
				alert("编辑mock成功");
				window.location.reload();
			}
		},error:function(result){			 
			alert("编辑mock-url失败");
			 console.log(result);
		}
		
	})
	
}
function save() {

	var createBy=$("#userId").val();
	var name=$("#addModal input#hf-name").val().trim();
	var url=$("#addModal input#hf-url").val().trim();
	var categoryId=$("#addModal select#hf-category").val();

	var description=$("#addModal textarea#hf-description").val();

	var data3={"categoryId":""+categoryId+"","createBy":""+createBy+"","url":""+url+"","name":""+name+"","description":""+description+""}; 
//	var data3={"url":""+url+"","name":""+name+"","content":""+content+"","statusCode":""+statusCode+"","description":""+description+""}; 
	var data4=JSON.stringify(data3);		
		
		
	$.ajax({
		type : "post",  
		url : "addAction",  
		dataType: "json", 
		data:data4,		
		contentType : "application/json;charset=UTF-8",
		cache: false,
		async: false ,
		success : function(result) {
//			console.log(result);
			var json=eval(result);
			var code=json.code;
			if(code!=0){
				alert(json.errMsg);
			}else{
				alert("新增mock成功");
				window.location.reload();
			}
		},error:function(result){			 
			alert("新增mock-url失败");
			 console.log(result);
		} 
	})
}

function del(urlId){
	var r=confirm("是否刪除?");
	if(r==true){		
		$.ajax({  
			type : "post",  
			url : "del/"+urlId+"",  
			dataType: "json", 
			cache: false,
			async: false ,
			success : function(result) {
//				console.log(result);
				window.location.reload();
			},error:function(result){			 
				alert("删除mock-url失败");
				 console.log(e);
			} 
		})
	}
}
function initData(urlName,categoryId) {	
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
        data:"{\"name\": \""+urlName+"\",\"categoryId\": \""+categoryId+"\",\"pageNum\":1,\"pageSize\":"+pageSize+"}",  
        dataType: "json", 
        contentType : "application/json;charset=UTF-8",
        cache: false,
        async: false ,
        success : function(result) {
        	var json = eval(result.data); //数组   
       	 	var div=""; 
	       	if(json==null){  
	       		div+="<tr><td colspan='5' style='text-align:center'>无</td></tr>"        		
	       	}else{
	       		 jsonlenth=json.size; 
	       		 total=json.total;
	       		currentPage=result.data.pageNum;
	   		 	totalPages=result.data.pages;
	   		 	numberOfPages=result.data.pageSize
	       		for (var i = 0; i <jsonlenth; i++) {
	       			var hitUrl=encodeURIComponent(result.data.list[i].url);
	       			div+="<tr id='row"+result.data.list[i].urlId+"'><td id='urlId' style='display:none'>"+result.data.list[i].urlId+"</td>"+
	       			"<td title='"+result.data.list[i].name+"' id='mockName'>"+result.data.list[i].name+"</td>"+
	       			"<td title='"+result.data.list[i].url+"' id='mockUrl'>"+result.data.list[i].url+"</td>"+
	       			"<td title='"+result.data.list[i].description+"' id='description'>"+result.data.list[i].description+"</td>"+
	       			"<td title='"+result.data.list[i].createTime+"' id='createTime'>"+result.data.list[i].createTime+"</td>"+
	       			"<td><a class='btn btn-info btn-xs' data-toggle='modal' data-target='#editModal' data-whatever='@mdo' onclick='edit("+result.data.list[i].urlId+")' title='编辑'  data-toggle='tooltip'><i class='mdi mdi-pencil'></i>编辑</a>"+
	       			"<a class='btn btn-warning btn-xs' onclick='responselist(\""+result.data.list[i].name+"\","+result.data.list[i].urlId+")' data-toggle='modal' data-target='#responseModal' data-whatever='@mdo' title='响应列表'  data-toggle='tooltip'><i class='mdi mdi-view-list' ></i>响应列表</a>"+	       			
	       			"<a class='btn btn-cyan btn-xs' href='/Mock/system/url/mockLog?hitUrl="+hitUrl+"'><i class='mdi mdi-view-headline'></i>日志列表</a>"+
	       			"<a class='btn btn-danger btn-xs' onclick='del("+result.data.list[i].urlId+")' title='删除'  data-toggle='tooltip'><i class='mdi mdi-window-close'></i>删除</a></td></tr>";
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
                	        data:"{\"name\": \""+urlName+"\",\"categoryId\": \""+categoryId+"\",\"pageNum\":"+page+",\"pageSize\":"+pageSize+"}",  
                	        dataType: "json", 
                	        contentType : "application/json;charset=UTF-8",
                	        cache: false,
                	        async: false ,
                	        success : function(result) {
                	        	var json = eval(result.data); //数组   
                	       	 	var div="";
                	       	 if(json==null){  
                		       		div+="<tr><td colspan='6' style='text-align:center'>无</td></tr>"        		
                		       	}else{
                		       		 jsonlenth=json.size; 
                		       		 total=json.total;
                		       		currentPage=result.data.pageNum;
                		   		 	totalPages=result.data.pages;
                		   		 	numberOfPages=result.data.pageSize
                		       		for (var i = 0; i <jsonlenth; i++) {
                		       			var hitUrl=encodeURIComponent(result.data.list[i].url);
                		       			div+="<tr id='row"+result.data.list[i].urlId+"'><td id='urlId' style='display:none'>"+result.data.list[i].urlId+"</td>"+
                		       			"<td title='"+result.data.list[i].name+"' id='mockName'>"+result.data.list[i].name+"</td>"+
                		       			"<td title='"+result.data.list[i].url+"' id='mockUrl'>"+result.data.list[i].url+"</td>"+
                		       			"<td title='"+result.data.list[i].description+"' id='description'>"+result.data.list[i].description+"</td>"+
                		       			"<td title='"+result.data.list[i].createTime+"' id='createTime'>"+result.data.list[i].createTime+"</td>"+
                		       			"<td><a class='btn btn-info btn-xs' data-toggle='modal' data-target='#editModal' data-whatever='@mdo' onclick='edit("+result.data.list[i].urlId+")' title='编辑'  data-toggle='tooltip'><i class='mdi mdi-pencil'></i>编辑</a>"+
                		       			"<a class='btn btn-warning btn-xs' onclick='responselist(\""+result.data.list[i].name+"\","+result.data.list[i].urlId+")' data-toggle='modal' data-target='#responseModal' data-whatever='@mdo' title='响应列表'  data-toggle='tooltip'><i class='mdi mdi-view-list' ></i>响应列表</a>"+	       			
                		       			"<a class='btn btn-cyan btn-xs' href='/Mock/system/url/mockLog?hitUrl="+hitUrl+"'><i class='mdi mdi-view-headline'></i>日志列表</a>"+
                		       			"<a class='btn btn-danger btn-xs' onclick='del("+result.data.list[i].urlId+")' title='删除'  data-toggle='tooltip'><i class='mdi mdi-window-close'></i>删除</a></td></tr>";
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
        	alert("请求mockUrl列表错误");
            console.log(e);
        }

	});
}
