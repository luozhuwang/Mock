function htmlEscape(text){ 
  return text.replace(/[<>"&]/g, function(match, pos, originalText){
    switch(match){
    case "\"":return "\\\""; 
  } 
  }); 
}
function addtable(modal){
	var modalBox=$("#"+modal);
	    var table = modalBox.find("#para_table");
			var tr= $("<tr id='row'>" +
			"<td><input type='text' id='paramName' name='paramName' value='' class='form-control' placeholder='名称' />"+"</td>"+
	        "<td><input type='text' id='paramValue' name='paramValue' value='' class='form-control' placeholder='实际值' />"+"</td>" +
	        "<td  align='center' onclick='deletetr(this)'><button type='button'  class='btn btn-danger' >"+"删除"+"</button></td></tr>");
	    table.append(tr);
	}
function deletetr(tdobject){
	    var td=$(tdobject);
	    td.parents("tr").remove();
	}
	//从一个tbody中获取数据转化成json
function changeTbodyToDetail(tbody) {
			var result = "{";
	        tbody.find("tr#row").each(function () {
	            var b = changeTrtoDetail($(this));
	            result = result + b + ",";
	        });
	        result = result.substring(0, result.length - 1)+"}";
	   		 console.log(result);
			 return result
	}

function changeTrtoDetail(tr){
   	 var result="";
   	 var paramName = tr.find("td>input#paramName");
   	 var paramValue = tr.find("td>input#paramValue");
   	 if (paramName.length > 0) {
   		 result="\"" +paramName.val()+"\"" +":"+"\"" +htmlEscape(paramValue.val())+"\"" ;
            return result;
        }
    }