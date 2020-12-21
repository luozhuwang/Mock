//点击空白区域禁止关闭
$("#responseModal").modal({
	   backdrop: "static",
	   show:false
});	


function cleanRespModel(){
	$("#addRespModal").attr("style","background-color:rgb(0,0,0,0.3)");
	$("#addRespModal input#hf-resp-name").val("");
	//请求方式
	$("#addRespModal #hf-resp-method").val("");
	//清空header数据
 	$("#addRespModal #hf-resp-header li:first>a").click();
 	$("#addRespModal #hf-resp-header tr#row>td:contains('删除')").click();
 	//清空Body数据
 	$("#addRespModal #hf-resp-body li:first>a").click();
 	//清空form-data内容
 	$("#addRespModal #hf-resp-body tr#row>td:contains('删除')").click();
 	//清空json数据
 	$("#addRespModal #hf-resp-body textarea#hf-resp-json").val("");
	//清空raw数据
 	$("#addRespModal #hf-resp-body textarea#hf-resp-raw").val("");
 	
 	//是否延迟
 	$("#addRespModal #hf-is-delay").prop("checked",false);
 	$("#addRespModal input#hf-delay").attr("type","hidden");
 	$("#addRespModal input#hf-delay").val("");	
 	
 	$("#addRespModal textarea#hf-resp-content").val("");	
	$("#addRespModal textarea#hf-resp-description").val("");
	
}

function cleanEditRespModel(){
	$("#editRespModal").attr("style","background-color:rgb(0,0,0,0.3)");
	$("#editRespModal input#hf-edit-resp-name").val("");
	//请求方式
	$("#editRespModal #edit-resp-method").val("");
	//清空header数据
 	$("#editRespModal #hf-edit-resp-header li:first>a").click();
 	$("#editRespModal #hf-edit-resp-header tr#row>td:contains('删除')").click();
 	//清空Body数据
 	$("#editRespModal #hf-edit-resp-body li:first>a").click();
 	//清空x-www-form-urlencoded内容
 	$("#editRespModal #hf-edit-resp-body tr#row>td:contains('删除')").click();
 	//清空json数据
 	$("#editRespModal #hf-edit-resp-body textarea#hf-edit-resp-json").val("");
	//清空raw数据
 	$("#editRespModal #hf-edit-resp-body textarea#hf-edit-resp-raw").val("");
 	
 	//是否延迟
 	$("#editRespModal #hf-edit-is-delay").prop("checked",false);
 	$("#editRespModal input#hf-edit-delay").attr("type","hidden");
 	$("#editRespModal input#hf-edit-delay").val("");	
 	
 	$("#editRespModal textarea#hf-edit-resp-content").val("");	
	$("#editRespModal textarea#hf-edit-resp-description").val("");
	
}

function responselist(name,urlId){
	var element = $("#responseModal #pages");
	var pageSize=10;
	var total=0
   	var jsonlenth=0;
	var currentPage=0;
	var totalPages=0;
	var numberOfPages=0;	
	var jsonlenth=0;
	$.ajax({  
        type : "post",  
        url : "/Mock/system/mockresp/list", 
        data:"{\"urlId\": \""+urlId+"\",\"pageNum\":1,\"pageSize\":"+pageSize+"}",
        dataType: "json", 
        contentType : "application/json;charset=UTF-8",
        cache: false,
        async: false ,
        success : function(result) {
        	var div=""; 
        	$("#responseModal input#urlId").val(urlId);
        	$("#responseModal input#mockName").val(name);
        	$("#responseModal h4#myLargeModalLabel").html("【"+name+"】响应列表");
        	console.log(result);
        	var json = eval(result.data); //数组   
        	console.log(json);
	       	if(json==null){  
	       		div+="<tr><td colspan='7' style='text-align:center'>无</td></tr>"        		
	       	}else{
	       		jsonlenth=json.size;
	       		total=json.total;
	       		currentPage=json.pageNum;
	   		 	totalPages=json.pages;
	   		 	numberOfPages=json.pageSize	   		 	
	       		for (var i = 0; i <jsonlenth; i++) {
	       			var delay=json.list[i].delay;
	       			div+="<tr id='row"+json.list[i].id+"'><td id='id' style='display:none'>"+json.list[i].id+"</td>"+
	       			"<td title='"+json.list[i].name+"' id='name'>"+result.data.list[i].name+"</td>"+
	       			"<td title='"+json.list[i].requestMethod+"' id='method'>"+json.list[i].requestMethod+"</td>"+
	       			"<td title='"+(delay==0 ? '无':delay)+"' id='delay'>"+(delay==0 ? '无':delay)+"</td>"+
	       			"<td title='"+result.data.list[i].content+"' id='content'>"+json.list[i].content+"</td>"+
	       			"<td title='"+result.data.list[i].description+"' id='description'>"+result.data.list[i].description+"</td>"+
	       			"<td title='"+result.data.list[i].createTime+"' id='createTime'>"+result.data.list[i].createTime+"</td>"+
	       			"<td><a class='btn btn-info btn-xs' data-toggle='modal' data-target='#editRespModal' data-whatever='@mdo' onclick='editResp("+result.data.list[i].id+")' title='编辑'  data-toggle='tooltip'><i class='mdi mdi-pencil'></i>编辑</a>"+
	       			"<a class='btn btn-danger btn-xs' onclick='delResp("+result.data.list[i].id+")' title='删除'  data-toggle='tooltip'><i class='mdi mdi-window-close'></i>删除</a></td></tr>";
	       		}
	       	}
	       	$("#responseModal #group_one").html(div);
	       	$("#responseModal #count").html("共计"+total+"条数据，每页"+pageSize+"条，当前第1页"); 
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
                	        url : "/Mock/system/mockresp/list", 
                	        data:"{\"urlId\": \""+urlId+"\",\"pageNum\":"+page+",\"pageSize\":"+pageSize+"}",
                	        dataType: "json", 
                	        contentType : "application/json;charset=UTF-8",
                	        cache: false,
                	        async: false ,
                	        success : function(result) {
                	        	var div=""; 
                	        	$("#responseModal input#urlId").val(urlId);
                	        	$("#responseModal input#mockName").val(name);
                	        	$("#responseModal h4#myLargeModalLabel").html("【"+name+"】响应列表");
                	        	console.log(result);
                	        	var json = eval(result.data); //数组   
                	        	console.log(json);
                		       	if(json==null){  
                		       		div+="<tr><td colspan='7' style='text-align:center'>无</td></tr>"        		
                		       	}else{
                		       		jsonlenth=json.size;
                		       		total=json.total;
                		       		currentPage=json.pageNum;
                		   		 	totalPages=json.pages;
                		   		 	numberOfPages=json.pageSize	   		 	
                		       		for (var i = 0; i <jsonlenth; i++) {
                		       			var delay=json.list[i].delay;
                		       			div+="<tr id='row"+json.list[i].id+"'><td id='id' style='display:none'>"+json.list[i].id+"</td>"+
                		       			"<td title='"+json.list[i].name+"' id='name'>"+result.data.list[i].name+"</td>"+
                		       			"<td title='"+json.list[i].requestMethod+"' id='method'>"+json.list[i].requestMethod+"</td>"+
                		       			"<td title='"+(delay==0 ? '无':delay)+"' id='delay'>"+(delay==0 ? '无':delay)+"</td>"+
                		       			"<td title='"+result.data.list[i].content+"' id='content'>"+json.list[i].content+"</td>"+
                		       			"<td title='"+result.data.list[i].description+"' id='description'>"+result.data.list[i].description+"</td>"+
                		       			"<td title='"+result.data.list[i].createTime+"' id='createTime'>"+result.data.list[i].createTime+"</td>"+
                		       			"<td><a class='btn btn-info btn-xs' data-toggle='modal' data-target='#editRespModal' data-whatever='@mdo' onclick='editResp("+result.data.list[i].id+")' title='编辑'  data-toggle='tooltip'><i class='mdi mdi-pencil'></i>编辑</a>"+
                		       			"<a class='btn btn-danger btn-xs' onclick='delResp("+result.data.list[i].id+")' title='删除'  data-toggle='tooltip'><i class='mdi mdi-window-close'></i>删除</a></td></tr>";
                		       		}
                		       	}
                		       	$("#responseModal #group_one").html(div);
                		       	$("#responseModal #count").html("共计"+total+"条数据，每页"+pageSize+"条，当前第"+page+"页");                	        	
                	        }
                	 })
                 }
   		 }
   		 //初始化分页框
   		 element.bootstrapPaginator(options);
        },
        error : function(e) {           //请求失败的回调函数
        	alert("请求responseList列表错误");
            console.log(e);
        }
	});
}


function saveResp() {
	var urlId=$("#responseModal input#urlId").val();
	var urlName= $("#responseModal input#mockName").val();
	var requestParam="";
	var paramType=0;
	var delay=0;
	var header="";
	var method=$("#addRespModal #hf-resp-method").val();
	
	var headerActive=$("#addRespModal #hf-resp-header li.active>a").attr("href");
	var bodyActive=$("#addRespModal #hf-resp-body li.active>a").attr("href");
	//拼接Header
	if(headerActive == "#header"){
		var tbody=$("#addRespModal #hf-resp-header #para_table");
		header=changeTbodyToDetail(tbody);
	}
	
		//拼接Body
		if(bodyActive == "#NoneBody"){
			paramType=-1;
		}else if(bodyActive == "#formData"){
			var tbody2=$("#addRespModal #hf-resp-body #para_table");
			requestParam=changeTbodyToDetail(tbody2);
			paramType=1;
		}else if(bodyActive == "#json"){
			requestParam=jQuery("#addRespModal #hf-resp-body textarea#hf-resp-json").val();
			paramType=2;
		}else if(bodyActive == "#raw"){
			requestParam=jQuery("#addRespModal #hf-resp-body textarea#hf-resp-raw").val();
			paramType=3;
		}
	
	var createBy=$("#userId").val();
	var name=$("#addRespModal input#hf-resp-name").val();
	
	var isDlay=$("#addRespModal #hf-is-delay").prop("checked");
	if(isDlay){
		delay=$("#addRespModal input#hf-delay").val();	
	}
	
	
	var content=$("#addRespModal textarea#hf-resp-content").val();	
	var description=$("#addRespModal textarea#hf-resp-description").val();

	var data3={"urlId":""+urlId+"","name":""+name+"","requestMethod":""+method+"","requestHeader":""+header+"","paramType":""+paramType+"","requestParam":""+requestParam+"","delay":""+delay+"","content":""+content+"","createBy":""+createBy+"","description":""+description+""}; 
//	var data3={"url":""+url+"","name":""+name+"","content":""+content+"","statusCode":""+statusCode+"","description":""+description+""}; 
	var data4=JSON.stringify(data3);		
		
		
	$.ajax({
		type : "post",  
		url : "/Mock/system/mockresp/addAction",  
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
				alert("新增mock【响应】 成功");
				$("#addRespModal .close").click();
				responselist(urlName,urlId);				
			}
		},error:function(result){			 
			alert("新增mock【响应】失败");
			 console.log(result);
		} 
	});
}

function editResp(respId){
	cleanEditRespModel();
	var data3={"id":respId};
	var data4=JSON.stringify(data3);	
	
	$.ajax({  
        type : "post",  
        url : "/Mock/system/mockresp/edit",  
        dataType: "json",
		data:data4,		
		contentType : "application/json;charset=UTF-8",
        cache: false,
        async: false ,
        success : function(result) {
        	console.log(result);
        	var name=result.data.name;
        	var requestParam=result.data.requestParam;
        	var paramType=result.data.paramType;
        	var header=result.data.requestHeader;
        	var delay=result.data.delay;
        	var method=result.data.requestMethod;
        	$("#editRespModal #hf-edit-resp-name").val(name);
        	$("#editRespModal #hf-edit-resp-id").val(result.data.id);
        	//处理header
        	if(header !=""){
        		$("#editRespModal #hf-edit-resp-header li>a[href='#edit-header']").click();
        		console.log(header);
        		var json=JSON.parse(header); 
        		var table = $("#editRespModal #hf-edit-resp-header #para_table");
        		for (var key in json) {
        			var tr= $("<tr id='row'>" +
        					"<td><input type='text' id='paramName' name='paramName' value='"+key+"' class='form-control' placeholder='名称' />"+"</td>"+
        			        "<td><input type='text' id='paramValue' name='paramValue' value='"+json[key]+"' class='form-control' placeholder='实际值' />"+"</td>" +
        			        "<td  align='center' onclick='deletetr(this)'><button type='button'  class='btn btn-danger' >"+"删除"+"</button></td></tr>");
        			    table.append(tr);
        		}
        	}
        	//处理body参数
	        if(paramType == -1){
	    		$("#editRespModal #hf-edit-resp-body li>a[href='#edit-NoneBody']").click();
	    	}else if(paramType == 1){
	    		$("#editRespModal #hf-edit-resp-body li>a[href='#edit-formData']").click();
	    		console.log(requestParam);
	    		var json=JSON.parse(requestParam); 
	    		var table = $("#editRespModal #hf-edit-resp-body #para_table");
	    		for (var key in json) {
	    			var tr= $("<tr id='row'>" +
	    					"<td><input type='text' id='paramName' name='paramName' value='"+key+"' class='form-control' placeholder='名称' />"+"</td>"+
	    			        "<td><input type='text' id='paramValue' name='paramValue' value='"+json[key]+"' class='form-control' placeholder='实际值' />"+"</td>" +
	    			        "<td  align='center' onclick='deletetr(this)'><button type='button'  class='btn btn-danger' >"+"删除"+"</button></td></tr>");
	    			    table.append(tr);
	    		}
	    	}else if(paramType == 2){
	    		$("#editRespModal #hf-edit-resp-body  li>a[href='#edit-json']").click();
	    		$("#editRespModal #hf-edit-resp-body textarea#hf-edit-resp-json").val(requestParam);
	    	}else if(paramType == 3){
	    		$("#editRespModal #hf-edit-resp-body  li>a[href='#edit-raw']").click();
	    		$("#editRespModal #hf-edit-resp-body textarea#hf-edit-resp-raw").val(requestParam);
	    	}
	        if(delay!=0){
	        	$("#editRespModal  #hf-edit-is-delay").click();
	        	$("#editRespModal  #hf-edit-delay").val(delay);
	        }
	        $("#editRespModal #edit-resp-method").val(method);
	        $("#editRespModal  textarea#hf-edit-resp-content").val(result.data.content);
        	$("#editRespModal  textarea#hf-edit-resp-description").val(result.data.description);
        }
	});
}

function editRespAction(){
	var urlId=$("#responseModal input#urlId").val();
	var urlName= $("#responseModal input#mockName").val();
	var respId=$("#editRespModal #hf-edit-resp-id").val();
	var requestParam="";
	var paramType=0;
	var delay=0;
	var header="";
	
	var headerActive=jQuery("#editRespModal #hf-edit-resp-header li.active>a").attr("href");
	var bodyActive=jQuery("#editRespModal #hf-edit-resp-body li.active>a").attr("href");
	//拼接Header
	if(headerActive == "#edit-header"){
		var tbody=$("#editRespModal #hf-edit-resp-header #para_table");
		header=changeTbodyToDetail(tbody);
	}
	
	//拼接Body
	if(bodyActive == "#edit-NoneBody"){
		paramType=-1;
	}else if(bodyActive == "#edit-formData"){
		var tbody2=$("#editRespModal #hf-edit-resp-body #para_table");
		requestParam=changeTbodyToDetail(tbody2);
		paramType=1;
	}else if(bodyActive == "#edit-json"){
		requestParam=jQuery("#editRespModal #hf-edit-resp-body textarea#hf-edit-resp-json").val();
		paramType=2;
	}else if(bodyActive == "#edit-raw"){
		requestParam=jQuery("#editRespModal #hf-edit-resp-body textarea#hf-edit-resp-raw").val();
		paramType=3;
	}
	var updateBy=$("#userId").val();
	var name=$("#editRespModal input#hf-edit-resp-name").val();
	
	var isDlay=$("#editRespModal #hf-edit-is-delay").prop("checked");
	if(isDlay){
		delay=$("#editRespModal input#hf-edit-delay").val();	
	}
	
	var method=$("#editRespModal #edit-resp-method").val();
	var content=$("#editRespModal textarea#hf-edit-resp-content").val();	
	var description=$("#editRespModal textarea#hf-edit-resp-description").val();

	var data3={"Id":""+respId+"","name":""+name+"","requestMethod":""+method+"","requestHeader":""+header+"","paramType":""+paramType+"","requestParam":""+requestParam+"","delay":""+delay+"","content":""+content+"","updateBy":""+updateBy+"","description":""+description+""}; 
//	var data3={"url":""+url+"","name":""+name+"","content":""+content+"","statusCode":""+statusCode+"","description":""+description+""}; 
	var data4=JSON.stringify(data3);		
		
		
	$.ajax({
		type : "post",  
		url : "/Mock/system/mockresp/editAction",  
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
				alert("编辑mock【响应】 成功");
				$("#editRespModal .close").click();
				responselist(urlName,urlId);				
			}
		},error:function(result){			 
			alert("编辑mock【响应】失败");
			 console.log(result);
		} 
	});
}

function delResp(respId){
	var urlId=$("#responseModal input#urlId").val();
	var urlName= $("#responseModal input#mockName").val();
	var r=confirm("是否刪除?");
	if(r==true){		
		$.ajax({  
			type : "post",  
			url : "/Mock/system/mockresp/del/"+respId+"",  
			dataType: "json", 
			cache: false,
			async: false ,
			success : function(result) {
				$("#addRespModal .close").click();
				responselist(urlName,urlId);	
			},error:function(result){			 
				alert("删除mock【响应】失败");
				 console.log(e);
			} 
		})
	}
}
