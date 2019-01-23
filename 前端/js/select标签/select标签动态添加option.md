# select标签动态添加option

```jsp
function numfun() {
	//选择框保留第一个选项，其他全删
    document.getElementById("sel").options.length=1;
    <% for(int i=0;i<b.length;i++){ %>
    $("#sel").append("<option value=''><%= b[i] %></option>");
    <%}%>
}
```


## jsp源码如下：

```jsp
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String [] a = new String[] {"aa","bb","cc"};
    String [] b = new String[] {"1","2","3"};
%>
<html>
<head>
    <title>My first Spring boot web demo</title>
</head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
    function letfun() {
        document.getElementById("sel").options.length=1;
        <% for(int i=0;i<a.length;i++){ %>
        $("#sel").append("<option value=''><%= a[i] %></option>");
        <%}%>
    }
    function numfun() {
        document.getElementById("sel").options.length=1;
        <% for(int i=0;i<b.length;i++){ %>
        $("#sel").append("<option value=''><%= b[i] %></option>");
        <%}%>
    }
</script>
<body>
<h2>Hello</h2>

<button type="button" onclick="letfun()" id="let">显示字母</button>
<button type="button" onclick="numfun()" id="num">显示数字</button>

<select id="sel">
    <option>请选择</option>
    <option>1</option>
    <option>2</option>
</select>

</body>
</html>

```
