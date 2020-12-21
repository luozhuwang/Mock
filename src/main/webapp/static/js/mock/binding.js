//新增响应 绑定事件
$("input#hf-is-delay").click(function() {
	var ttype=$("input#hf-delay").attr("type");
	if(ttype=="hidden"){
		$("input#hf-delay").attr("type","text");
		$("input#hf-delay").val("");	
	}else if(ttype=="text"){		
		$("input#hf-delay").attr("type","hidden");
	}
});
$("input#hf-edit-is-delay").click(function() {
	var ttype=$("#hf-edit-delay").attr("type");
	if(ttype=="hidden"){
		$("input#hf-edit-delay").attr("type","text");
	 	$("input#hf-edit-delay").val("");	
	}else if(ttype=="text"){		
		$("#hf-edit-delay").attr("type","hidden");
	}
});

$("#addRespModal #hf-resp-body li>a[href='#formData']")[0].onclick = function(){
	var header=$("#addRespModal #hf-resp-header li.active>a").attr("href");
	if(header=="#None"){
		$("#addRespModal #hf-resp-header li:last>a").click();
	}
	var rows=$("#addRespModal #hf-resp-header tr#row");
	var rowll=rows.length;
	if(rowll!=0){
		var paramName=0;
		rows.each(function () {
            var param=$(this).find("td>input#paramName").val();
            if(param=="Content-Type"){
            	$(this).find("td>input#paramValue").val("application/x-www-form-urlencoded");
            	paramName=1;
            }           
        });
		if(paramName==0){
			addtable("hf-resp-header");
			$("#addRespModal #hf-resp-header tr#row:last #paramName").val("Content-Type");
			$("#addRespModal #hf-resp-header tr#row:last #paramValue").val("application/x-www-form-urlencoded");
		}
	}else{
		addtable("hf-resp-header");
		$("#addRespModal #hf-resp-header tr#row:last #paramName").val("Content-Type");
		$("#addRespModal #hf-resp-header tr#row:last #paramValue").val("application/x-www-form-urlencoded");
	}
};

$("#editRespModal #hf-edit-resp-body li>a[href='#edit-formData']")[0].onclick = function(){
	var header=$("#editRespModal #hf-edit-resp-header li.active>a").attr("href");
	if(header=="#edit-None"){
		$("#editRespModal #hf-edit-resp-header li:last>a").click();
	}
	var rows=$("#editRespModal #hf-edit-resp-header tr#row");
	var rowll=rows.length;
	if(rowll!=0){
		var paramName=0;
		rows.each(function () {
            var param=$(this).find("td>input#paramName").val();
            if(param=="Content-Type"){
            	$(this).find("td>input#paramValue").val("application/x-www-form-urlencoded");
            	paramName=1;
            }           
        });
		if(paramName==0){
			addtable("hf-edit-resp-header");
			$("#editRespModal #hf-edit-resp-header tr#row:last #paramName").val("Content-Type");
			$("#editRespModal #hf-edit-resp-header tr#row:last #paramValue").val("application/x-www-form-urlencoded");
		}
	}else{
		addtable("hf-edit-resp-header");
		$("#editRespModal #hf-edit-resp-header tr#row:last #paramName").val("Content-Type");
		$("#editRespModal #hf-edit-resp-header tr#row:last #paramValue").val("application/x-www-form-urlencoded");
	}
};


$("#addRespModal #hf-resp-body li>a[href='#json']")[0].onclick = function(){
	var header=$("#addRespModal #hf-resp-header li.active>a").attr("href");
	if(header=="#None"){
		$("#addRespModal #hf-resp-header li:last>a").click();
	}
	var rows=$("#addRespModal #hf-resp-header tr#row");
	var rowll=rows.length;
	if(rowll!=0){
		var paramName=0;
		rows.each(function () {
            var param=$(this).find("td>input#paramName").val();
            if(param=="Content-Type"){
            	$(this).find("td>input#paramValue").val("application/json");
            	paramName=1;
            }           
        });
		if(paramName==0){
			addtable("hf-resp-header");
			$("#addRespModal #hf-resp-header tr#row:last #paramName").val("Content-Type");
			$("#addRespModal #hf-resp-header tr#row:last #paramValue").val("application/json");
		}
	}else{
		addtable("hf-resp-header");
		$("#addRespModal #hf-resp-header tr#row:last #paramName").val("Content-Type");
		$("#addRespModal #hf-resp-header tr#row:last #paramValue").val("application/json");
	}
};

$("#editRespModal #hf-edit-resp-body li>a[href='#edit-json']")[0].onclick = function(){
	var header=$("#editRespModal #hf-edit-resp-header li.active>a").attr("href");
	console.log(header);
	if(header=="#edit-None"){
		$("#editRespModal #hf-edit-resp-header li:last>a").click();
	}
	var rows=$("#editRespModal #hf-edit-resp-header tr#row");
	var rowll=rows.length;
	if(rowll!=0){
		var paramName=0;
		rows.each(function () {
            var param=$(this).find("td>input#paramName").val();
            if(param=="Content-Type"){
            	$(this).find("td>input#paramValue").val("application/json");
            	paramName=1;
            }           
        });
		if(paramName==0){
			addtable("hf-edit-resp-header");
			$("#editRespModal #hf-edit-resp-header tr#row:last #paramName").val("Content-Type");
			$("#editRespModal #hf-edit-resp-header tr#row:last #paramValue").val("application/json");
		}
	}else{
		addtable("hf-edit-resp-header");
		$("#editRespModal #hf-edit-resp-header tr#row:last #paramName").val("Content-Type");
		$("#editRespModal #hf-edit-resp-header tr#row:last #paramValue").val("application/json");
	}
};