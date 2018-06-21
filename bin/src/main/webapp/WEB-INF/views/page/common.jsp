<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- c标签：定义项目的路径 -->
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- easyui默认主题样式 -->
<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
<!-- easyui图标样式-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/icon.css">
<!-- easyui颜色样式 -->
<link rel="stylesheet" type="text/css" href="${ctx}/easyui/themes/color.css">
<!-- 先引入jQuery核心的js -->
<script type="text/javascript" src=""></script>
<!-- 在引入easyui的核心的js-->
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<!-- 国际化的js-->
<script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>