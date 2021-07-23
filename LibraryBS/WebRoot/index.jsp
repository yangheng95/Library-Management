<%@page import="com.yangheng.library.model.AbstractModel"%>
<%@page import="com.yangheng.library.model.Reader"%>
<%@page import="com.yangheng.library.dao.ReaderDAO"%>
<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>图书馆图书查询系统 --登录</title>

<%
	try {
		ReaderDAO readerDAO = new ReaderDAO();
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		Reader reader = (Reader) readerDAO.getObjectByID(account);
		if (reader.getRdPwd().equals(password)) {
			request.setAttribute("reader", reader);
			request.getRequestDispatcher("./main.jsp").forward(request, response);
			out.print("<script>alert('登录失败，没有此用户或者密码错误');</script>");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
%>

</head>

<body>

	<center>
		<h1>欢迎使用图书馆查询系统</h1>
		<form action="./index.jsp" method="post">
			账户<input type="text" name="account"><br>
			<br> 密码<input type="password" name="password"><br>
			<br> <input type="submit" value="登录"> <input
				type="reset" value="清空">

		</form>
		<h3>
			系统时间:<%=new Date().toLocaleString()%></h3>
	</center>


</body>
</html>
