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

<title>ͼ���ͼ���ѯϵͳ --��¼</title>

<%
	try {
		ReaderDAO readerDAO = new ReaderDAO();
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		Reader reader = (Reader) readerDAO.getObjectByID(account);
		if (reader.getRdPwd().equals(password)) {
			request.setAttribute("reader", reader);
			request.getRequestDispatcher("./main.jsp").forward(request, response);
			out.print("<script>alert('��¼ʧ�ܣ�û�д��û������������');</script>");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
%>

</head>

<body>

	<center>
		<h1>��ӭʹ��ͼ��ݲ�ѯϵͳ</h1>
		<form action="./index.jsp" method="post">
			�˻�<input type="text" name="account"><br>
			<br> ����<input type="password" name="password"><br>
			<br> <input type="submit" value="��¼"> <input
				type="reset" value="���">

		</form>
		<h3>
			ϵͳʱ��:<%=new Date().toLocaleString()%></h3>
	</center>


</body>
</html>
