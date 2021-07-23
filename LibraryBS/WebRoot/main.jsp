<%@page import="com.yangheng.library.dao.BookLibDAO"%>
<%@page import="com.yangheng.library.model.BookLib"%>
<%@page import="com.yangheng.library.dao.BookDAO"%>
<%@page import="com.yangheng.library.model.ReaderType"%>
<%@page import="com.yangheng.library.dao.ReaderTypeDAO"%>
<%@page import="com.yangheng.library.model.AbstractModel"%>
<%@page import="com.yangheng.library.model.BorrowRecord"%>
<%@page import="com.yangheng.library.dao.BorrowRecordDAO"%>
<%@page import="com.yangheng.library.model.Reader"%>
<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%
	request.setCharacterEncoding("GBK");
	Reader reader = (Reader) request.getAttribute("reader");
	BorrowRecordDAO borrowRecordDAO = new BorrowRecordDAO();
	ReaderTypeDAO readerTypeDAO = new ReaderTypeDAO();
	BookLibDAO bookLibDAO = new BookLibDAO();
	ReaderType readerType = (ReaderType) readerTypeDAO.getObjectByID(reader.getRdType());
	BookDAO bookDAO = new BookDAO();

	ArrayList<AbstractModel> borrowrecord = borrowRecordDAO.getObjectsByRdID(reader.getRdID());
%>

<base href="<%=basePath%>">

<title>ͼ���ͼ���ѯϵͳ --<%=reader.getRdName()%></title>

</head>

<body>
	<h2>
		��ǰ�û�:<%=reader.getRdName()%></h2>
	<br> �ѽ���������<%=reader.getRdBrorrowNum()%>&nbsp;&nbsp;ʣ��ɽ裺<%=readerType.getRdCanLendSum() - reader.getRdBrorrowNum()%>
	<form action="./BookLibSearch.jsp" method="post">
		<input type="text" name = "input" value ="�����ݲ�ͼ��ID/����">
		<input type="hidden" name = "RdID" value ="<%=reader.getRdID() %>">
		<input type="submit" value="����">
	</form>
	<h2>δ��ͼ���б�</h2>
	<table border="1px" width="100%
">

		<tr>
			<td>������ˮ��</td>
			<td>ͼ��ID</td>
			<td>ͼ������</td>
			<td>�������</td>
			<td>��������</td>
			<td>Ӧ������</td>
			<td>��������</td>
			<td>Ӧ������</td>
			<td>�Ƿ��ѻ�</td>
		</tr>
		<%
			for (int i = 0; i < borrowrecord.size(); i++) {
				if (((BorrowRecord) borrowrecord.get(i)).getReturned() == 0) {
					String bkname = ((BookLib) bookLibDAO.getObjectByID(((BorrowRecord) borrowrecord.get(i)).getBkID()))
							.getBkName();
		%>

		<tr>

			<td><%=((BorrowRecord) borrowrecord.get(i)).getBorrowID()%></td>
			<td><%=((BorrowRecord) borrowrecord.get(i)).getBkID()%></td>
			<td><%=bkname%></td>
			<td><%=((BorrowRecord) borrowrecord.get(i)).getContinueTimes()%></td>
			<td><%=((BorrowRecord) borrowrecord.get(i)).getDateOut()%></td>
			<td><%=((BorrowRecord) borrowrecord.get(i)).getDateShouldRet()%></td>
			<td><%=((BorrowRecord) borrowrecord.get(i)).getOverDay()%></td>
			<td><%=((BorrowRecord) borrowrecord.get(i)).getPunishMoney()%></td>
			<td><%=((BorrowRecord) borrowrecord.get(i)).getReturned() == 1 ? "��" : "��"%></td>
		</tr>
		<%
			}
			}
		%>
	</table>

</body>
</html>
