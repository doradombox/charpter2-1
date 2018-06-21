<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div id="main" style="height: 400px"></div>
	<!-- jquery -->
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<!-- ECharts单文件引入 -->
	<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
	
	<script type="text/javascript">
        // 路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main')); 
                var option = {
                    tooltip: {
                        show: true
                    },
                    legend: {
                        data:['销量']
                    },
                    xAxis : [
                        {
                            type : 'category',
                            data :(function(){
                                var arr=[];
                                $.ajax({
                                     type : "get",
                                     async : false, //同步执行
                                     url : "http://localhost:8081/chapter2-1/test/test/getCategory",
                                     dataType : "json", //返回数据形式为json
                                     success : function(result) {
                                    	 console.log(result);
                                       if (result) {
                                            for(var i=0;i<result.length;i++){
                                            console.log(result[i].name);
                                            arr.push(result[i].name);
                                         }    
                                       }
                                                            
                                    },
                                        error : function(errorMsg) {
                                            alert("不好意思，图表请求数据失败啦!");
                                             myChart.hideLoading();
                                           }
                                   })
                                     return arr;
                                  })()
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            "name":"销量",
                            "type":"bar",
                            "data":(function(){
                                var arr=[];
                                $.ajax({
                                     type : "get",
                                     async : false, //同步执行
                                     url : "http://localhost:8081/chapter2-1/test/test/getCategory",
                                     dataType : "json", //返回数据形式为json
                                     success : function(result) {
                                         console.log(result);
                                       if (result) {
                                            for(var i=0;i<result.length;i++){
                                            console.log(result[i].sales);
                                            arr.push(result[i].sales);
                                         }    
                                       }
                                                            
                                    },
                                        error : function(errorMsg) {
                                            alert("不好意思，图表请求数据失败啦!");
                                             myChart.hideLoading();
                                           }
                                   })
                                     return arr;
                                  })()
                        }
                    ]
                };
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
    </script>
</body>
</html>