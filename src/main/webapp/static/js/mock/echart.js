function countByName(beginDate, endDate){
	var data3={"beginTime":""+beginDate+"","endTime":""+endDate+""}
	var data4=JSON.stringify(data3);
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('chart1'));
	var data_x = [];
	var data_y = [];
	
	$.ajax({  
        type : "post",  
        url : "/Mock/system/url/mockLog/byName",  
        dataType: "json", 
		data:data4,		
		contentType : "application/json;charset=UTF-8",
		cache: false,
		async: false ,
        success : function(result) {
        	json=result.data;
        	//循环数据，并放入数组
//        	console.log(json); 
        	if(json == null){
        		data_x.push(0);
        		data_y.push({value: 0, name: '暂无数据'});
        	}else{        		
        		for (var cc in json) {
        			data_x.push(json[cc].name);  
        			var vValue={"value":""+json[cc].count+"","name":""+json[cc].name+""}
        			data_y.push(vValue);
        		}
        	}
//        	console.log(data_y);	
        	myChart.setOption({
        		title: {
        			text: '接口统计',
        			left: 'center'
        		},
        		tooltip: {
        			trigger: 'item',
        			formatter: '{a} <br/>{b} : {c} ({d}%)'
        		},
        		toolbox: {
        			left: 'right',
        			feature: {	            	
        				dataView: {
        					readOnly: true
        				},	                
        				restore: {},
        				saveAsImage: {}
        			}
        		},
        		legend: {
        			orient: 'vertical',
        			left: 'left',
        			data: data_x
        		},
        		series: [
        			{
        				name: '接口',
        				type: 'pie',
        				radius: '55%',
        				center: ['50%', '60%'],
        				data:data_y,
        				emphasis: {
        					itemStyle: {
        						shadowBlur: 10,
        						shadowOffsetX: 0,
        						shadowColor: 'rgba(0, 0, 0, 0.5)'
        					}
        				}
        			}
        			]
        		
        	});
        	
        	
        },
        error:function(result){
			alert("接口请求统计异常");
	}
	});
}

function countByDate(beginDate, endDate){
	var data3={"beginTime":""+beginDate+"","endTime":""+endDate+""}
	var data4=JSON.stringify(data3);
	
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('chart2'));
	var data_x = [];
	var data_y = [];
	$.ajax({  
        type : "post",  
        url : "/Mock/system/url/mockLog/byDate",  
        dataType: "json", 
		data:data4,		
		contentType : "application/json;charset=UTF-8",
		cache: false,
		async: false ,
        success : function(result) {
        	json=result.data;
        	//循环数据，并放入数组
//        	console.log(json);
        	for (var cc in json) {
        		data_x.push(json[cc].key);
        		data_y.push(json[cc].value);
        	}
        	myChart.setOption({
        		xAxis: {
    		    	name: '日期',
    		        type: 'category',
    		        data: data_x
        		},        		
    		    toolbox: {
    	            left: 'right',
    	            feature: {	
    	            	dataView: {
        					readOnly: true
        				},
    	                magicType: {type: ['line', 'bar']},
    	                restore: {},
    	                saveAsImage: {}
    	            }
    	        },
        		tooltip:{		    	
    		    	trigger: 'axis'
    		    },    		    
    		    yAxis: {
    		    	name: '点击次数',
    		        type: 'value'
    		    },
    		    series: [{
    		    	name: '请求数',    		        
    		        type: 'line',
    		        data: data_y
    		    }]
        		
        	});        	
        },
        error:function(result){
				alert("每日请求统计异常");
		}
	});
}