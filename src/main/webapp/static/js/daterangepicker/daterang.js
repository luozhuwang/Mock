//参考文献：
//https://www.jq22.com/yanshi10689
//https://www.cnblogs.com/dreamkeeper/archive/2004/01/13/7758316.html

$(document).ready(function () {
        $('#date-range').daterangepicker({
            "showDropdowns": true,
            "showISOWeekNumbers": true,
//            "ranges": {
//                '今天': [moment(), moment()],
//                '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
//                '近一周': [moment().subtract(6, 'days'), moment().subtract(6, 'days')],
//                '近一个月': [moment().subtract(29, 'days'), moment()],
//                '本月': [moment().startOf('month'), moment().endOf('month')],
//                '上个月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
//            },
            "locale": {
                "format": "YYYY-MM-DD",
                "separator": " 至 ",
                "applyLabel": "确定",
                "cancelLabel": "取消",
                "fromLabel": "从",
                "toLabel": "到",
                "customRangeLabel": "自定义",
                "weekLabel": "周",
                "daysOfWeek": [
                    "日",
                    "一",
                    "二",
                    "三",
                    "四",
                    "五",
                    "六"
                ],
                "monthNames": [
                    "1月",
                    "2月",
                    "3月",
                    "4月",
                    "5月",
                    "6月",
                    "7月",
                    "8月",
                    "9月",
                    "10月",
                    "11月",
                    "12月"
                ],
                "firstDay": 1
            },
            "alwaysShowCalendars": true,
            "startDate": moment().subtract(6, 'days'),
            "endDate": moment(),
//            "minDate": "2017年01月01日",
            "linkedCalendars": false,
//            "maxDate": myDate.toLocaleDateString()
        }, function (start, end, label) {
        	var beginDate=start.format('YYYY-MM-DD');
        	var endDate=end.format('YYYY-MM-DD');
        	console.log("接口请求统计时间范围:" + beginDate + ' to ' + endDate + " (predefined range: " + label + ")"); 
        	countByName(beginDate,endDate);
        });
    });
$(document).ready(function () {
    $('#date-range1').daterangepicker({
        "showDropdowns": true,
        "showISOWeekNumbers": true,
//        "ranges": {
//            '今天': [moment(), moment()],
//            '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
//            '近一周': [moment().subtract(6, 'days'), moment().subtract(6, 'days')],
//            '近一个月': [moment().subtract(29, 'days'), moment()],
//            '本月': [moment().startOf('month'), moment().endOf('month')],
//            '上个月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
//        },
        "locale": {
            "format": "YYYY-MM-DD",
            "separator": " 至 ",
            "applyLabel": "确定",
            "cancelLabel": "取消",
            "fromLabel": "从",
            "toLabel": "到",
            "customRangeLabel": "自定义",
            "weekLabel": "周",
            "daysOfWeek": [
                "日",
                "一",
                "二",
                "三",
                "四",
                "五",
                "六"
            ],
            "monthNames": [
                "1月",
                "2月",
                "3月",
                "4月",
                "5月",
                "6月",
                "7月",
                "8月",
                "9月",
                "10月",
                "11月",
                "12月"
            ],
            "firstDay": 1
        },
        "alwaysShowCalendars": true,
        "startDate": moment().subtract(6, 'days'),
        "endDate": moment(),
//        "minDate": "2017年01月01日",
        "linkedCalendars": false,
//        "maxDate": myDate.toLocaleDateString()
    }, function (start, end, label) {
    	var beginDate=start.format('YYYY-MM-DD');
    	var endDate=end.format('YYYY-MM-DD');
    	console.log("接口请求统计时间范围:" + beginDate + ' to ' + endDate + " (predefined range: " + label + ")");
    	countByDate(beginDate, endDate);   
    });
});