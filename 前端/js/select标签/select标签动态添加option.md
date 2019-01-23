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


