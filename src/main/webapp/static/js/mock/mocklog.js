$(window).load( function(){
	var isParam=window.location.search;
	var hitUrl="";
	if(isParam!=""){		
		hitUrl=getUrlParam("hitUrl");
	}
	initData("",hitUrl,"","","","");
	methodList();
} );

function mockLog() {
	var requestUrl=$("#requestUrl").val().trim();
	var hitUrl="";
	var requestIp=$("#requestIp").val().trim();
	var requestMethod=$('#requestMethod option:selected').val();
	var beginTime=$("#beginTime").val();
	var endTime=$("#endTime").val();
	initData(requestUrl,hitUrl,requestIp,requestMethod,beginTime,endTime);
}

function clearLog() {
	var requestUrl=$("#requestUrl").val("");
	var requestIp=$("#requestIp").val("");
	var requestMethod=$("#requestMethod").val("");
	var beginTime=$("#beginTime").val("");
	var endTime=$("#endTime").val("");
}

function cleanData(){
	$("#viewModal input#hf-requestUrl").val("");
	$("#viewModal input#hf-hitUrl").val("");
	$("#viewModal input#hf-requestIp").val("");
	$("#viewModal input#hf-requestMethod").val("");
	$("#viewModal div#hf-requestHeader").html("");
	$("#viewModal div#hf-requestDetail").html("");
	$("#viewModal div#hf-requestDetail").removeAttr("title");
	$("#viewModal textarea#hf-responseDetail").html("");
	$("#viewModal textarea#hf-responseDetail").attr("title","");
	$("#viewModal input#hf-createTime").val("");
}

function methodList(){
	var div="";
	$.ajax({  
        type : "post",  
        url : "/Mock/system/basic/method", 
        data:"{\"associateId\": \"1\"}",
        dataType: "json", 
        contentType : "application/json;charset=UTF-8",
        cache: false,
        async: false ,
        success : function(result) {        	
        	var Method=$("#requestMethod");        	
        	console.log(result);        	
        	var jsonlenth=result.data.length;
        	for (var i = 0; i <jsonlenth; i++){
        		div+="<option value="+result.data[i].value+" >"+result.data[i].value+"</option>";
        	}
        	Method.append(div);
        }
	});
}

function strToJson(str) {
    return JSON.parse(str);
}
//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

function viewData(logId){
	cleanData();
	$.ajax({  
        type : "post",  
        url : "mockLog/"+logId+"",  
        dataType: "json", 
        cache: false,
        async: false ,
        success : function(result) {
        	console.log(result);
        	var detail=result.data.requestDetail;
        	var method=result.data.requestMethod;
        	var header=result.data.requestHeader;
        	var requestHeader=strToJson(header);
        	
        	$("#viewModal input#hf-requestUrl").val(result.data.requestUrl);
        	$("#viewModal input#hf-hitUrl").val(result.data.hitUrl);
        	$("#viewModal input#hf-requestIp").val(result.data.requestIp);
        	$("#viewModal input#hf-requestMethod").val(result.data.requestMethod);
        	if(requestHeader==null){
        		$("#viewModal div#hf-requestHeader").html("无");
        	}else{ 
        		var headerHtml="";
        		
        		for (var val in requestHeader) {
//        			headerHtml+="<p class='form-control-static'>"+val+":</p><pre>"+requestHeader[val]+"</pre>" 
        			headerHtml+="<p style='text-overflow: ellipsis;overflow: hidden;white-space: nowrap;' title='"+val+"："+requestHeader[val]+"'>"+val+"："+requestHeader[val]+"</p>"  
        		}
        		$("#viewModal div#hf-requestHeader").html(headerHtml);
        	}
        	if(detail==null){
        		$("#viewModal div#hf-requestDetail").html("无");
        	}else{
        		var detailHtml="";
        		//如果是GET方法，没有head中没有content-type，需要格式化
        		if(header.indexOf("x-www-form-urlencoded") !=-1 ||header.indexOf("form-data") !=-1 || method=="GET" &&  detail!=null){
        			var requestDetail=strToJson(detail);
        			for (var val in requestDetail) {
//        			detailHtml+="<p class='form-control-static'>"+val+":</p><pre>"+requestDetail[val]+"</pre>"
        				detailHtml+="<p style='text-overflow: ellipsis;overflow: hidden;white-space: nowrap;' title='"+val+"："+requestDetail[val]+"'>"+val+"："+requestDetail[val]+"</p>"        		  
        			}
        		}else{        			
        			detailHtml=detail;
        		}
        		$("#viewModal div#hf-requestDetail").html(detailHtml);
        		$("#viewModal div#hf-requestDetail").attr("title",detailHtml);
        	}
        	$("#viewModal textarea#hf-responseDetail").html(result.data.responseDetail);
        	$("#viewModal textarea#hf-responseDetail").attr("title",result.data.responseDetail);
        	$("#viewModal input#hf-createTime").val(result.data.createTime);
        },error:function(result){			 
			alert("查看mock--日志获取数据失败");
			 console.log(e);
		}
	})
}

function initData(requestUrl,hitUrl,requestIp,requestMethod,beginTime,endTime) {
	var element = $("#pages");
	var pageSize=10;
	 	var total=0
   	 	var jsonlenth=0;
	 	var currentPage=0;
		var totalPages=0;
		var numberOfPages=0;
	$.ajax({
		 type : "post",  
	        url : "mockLog/list",  
	        data:"{\"requestUrl\": \""+requestUrl+"\",\"hitUrl\": \""+hitUrl+"\",\"requestIp\": \""+requestIp+"\",\"requestMethod\": \""+requestMethod+"\",\"beginTime\": \""+beginTime+"\",\"endTime\": \""+endTime+"\",\"pageNum\":1,\"pageSize\":"+pageSize+"}",  
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
		       			div+="<tr id='row"+result.data.list[i].logId+"'><td id='logId' style='display:none'>"+result.data.list[i].logId+"</td>"+
		       			"<td title='"+result.data.list[i].requestUrl+"' id='requestUrl'>"+result.data.list[i].requestUrl+"</td>"+
		       			"<td title='"+result.data.list[i].hitUrl+"' id='hitUrl'>"+result.data.list[i].hitUrl+"</td>"+
		       			"<td title='"+result.data.list[i].requestIp+"' id='requestIp'>"+result.data.list[i].requestIp+"</td>"+
		       			"<td title='"+result.data.list[i].requestMethod+"' id='requestMethod'>"+result.data.list[i].requestMethod+"</td>"+
		       			"<td title='"+result.data.list[i].createTime+"' id='createTime'>"+result.data.list[i].createTime+"</td>"+
		       			"<td><a class='btn btn-info btn-xs' data-toggle='modal' data-target='#viewModal' data-whatever='@mdo' onclick='viewData("+result.data.list[i].logId+")' title='查看详情'  data-toggle='tooltip'><i class='mdi mdi-pencil'></i>查看详情</a>"+
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
	                	        url : "mockLog/list",  
	                	        data:"{\"requestUrl\": \""+requestUrl+"\",\"hitUrl\": \""+hitUrl+"\",\"requestIp\": \""+requestIp+"\",\"requestMethod\": \""+requestMethod+"\",\"beginTime\": \""+beginTime+"\",\"endTime\": \""+endTime+"\",\"pageNum\":"+page+",\"pageSize\":"+pageSize+"}",  
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
	                		       		var jsonlenth=json.size;
	                		       		var total=json.total;
	                		       		for (var i = 0; i <jsonlenth; i++) {
	                		       			div+="<tr id='row"+result.data.list[i].logId+"'><td id='logId' style='display:none'>"+result.data.list[i].logId+"</td>"+
	                		       			"<td title='"+result.data.list[i].requestUrl+"' id='requestUrl'>"+result.data.list[i].requestUrl+"</td>"+
	                		       			"<td title='"+result.data.list[i].hitUrl+"' id='hitUrl'>"+result.data.list[i].hitUrl+"</td>"+
	                		       			"<td title='"+result.data.list[i].requestIp+"' id='requestIp'>"+result.data.list[i].requestIp+"</td>"+
	                		       			"<td title='"+result.data.list[i].requestMethod+"' id='requestMethod'>"+result.data.list[i].requestMethod+"</td>"+
	                		       			"<td title='"+result.data.list[i].createTime+"' id='createTime'>"+result.data.list[i].createTime+"</td>"+
	                		       			"<td><a class='btn btn-info btn-xs' data-toggle='modal' data-target='#viewModal' data-whatever='@mdo' onclick='viewData("+result.data.list[i].logId+")' title='查看详情'  data-toggle='tooltip'><i class='mdi mdi-pencil'></i>查看详情</a>"+
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
	        	alert("请求mock日志列表错误");
	            console.log(e);
	        }
	
	});

}