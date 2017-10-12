<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


</head>
<body>


    Click on the link to download:<br>
	
	<div id="lstNames">
	
	</div>

<script type="text/javascript">
	var filesName=<%=request.getAttribute("files")%>;
	console.log(filesName);
	
	var div=document.getElementById("lstNames");
	var url="<a href=\"GetFile?name=:name:\">:name:</a><br>";
	
	for(var x=0;x<filesName.length;x++){
		div.innerHTML +=(url.replace(/\:name:/g,filesName[x]));
	}

</script>


</body>
</html>