<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
  <head>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajax.js"></script>
  	<script type="text/javascript">
  		window.onload=function(){
  			window.setInterval(function(){
	  			//发送AJAX请求检验用户名是否已经存在
				//--创建XMLHttpReqeust对象
				var xhr = ajaxFunction();
				//--设置监听
				var data = null;
				xhr.onreadystatechange=function(){
					var e1_div = document.getElementById("e1_div");
					var e2_div = document.getElementById("e2_div");
					if(xhr.readyState==4){
						if(xhr.status==200||xhr.status==304){
							e1_div.innerText = "";
							e2_div.innerText = "";
							data = xhr.responseText;
							var obj = eval("("+data+")");
							var e1Array = obj.e1s;
							for(var i=0;i<e1Array.length;i++){
								e1_div.innerText += e1Array[i].ip;
							}
							var e2Array = obj.e2s;
							for(var i=0;i<e2Array.length;i++){
								e2_div.innerText += e2Array[i].ip+":"+e2Array[i].port;
							}
						}
					}
				}
				//--打开链接
				xhr.open("GET","${pageContext.request.contextPath}/servlet/ZookeeperState",true);
				//--发送请求
				xhr.send(null);
  			}, 1000);
  		}
  	</script>
  </head>
  <body>
	<div style="border: solid 1px red; " id="e1_div"></div>
	<div style="border: solid 1px red; " id="e2_div"></div>
  </body>
</html>
