<%--
  Created by IntelliJ IDEA.
  User: huangwenbo
  Date: 2018/5/25
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    <title>Jpa EasyUI.This is a Jsp page.</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}" />
        
</head>
<body>
    <h3>数字格式化:</h3>
    <c:set var="balance" value="120000.2309" />
    <p>格式化数字 (1): <fmt:formatNumber value="${balance}"
                                type="currency"/></p>
    <c:out value="输出的值" escapeXml="false" default="如果value为空,则输出该值"></c:out><br/>
    <c:out value="&lt要显示的数据对象（未使用转义字符）&gt" escapeXml="true" default="默认值"></c:out><br/>
    <c:out value="&lt要显示的数据对象（使用转义字符）&gt" escapeXml="false" default="默认值"></c:out><br/>
    <c:out value="${null}" escapeXml="false">使用的表达式结果为null，则输出该默认值</c:out><br/>
    
</body>
</html>
