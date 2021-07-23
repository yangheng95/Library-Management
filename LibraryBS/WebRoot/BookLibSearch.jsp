<%@page import="com.yangheng.library.dao.ReaderDAO"%>
<%@page import="com.yangheng.library.dao.BookCategoryDAO"%>
<%@page import="com.yangheng.library.model.BookCategory"%>
<%@page import="com.yangheng.library.model.Book"%>
<%@page import="com.yangheng.library.model.BookLib"%>
<%@page import="com.yangheng.library.model.AbstractModel"%>
<%@page import="com.yangheng.library.dao.BookDAO"%>
<%@page import="com.yangheng.library.dao.BookLibDAO"%>
<%@page import="com.yangheng.library.dao.ReaderTypeDAO"%>
<%@page import="com.yangheng.library.dao.BorrowRecordDAO"%>
<%@page import="com.yangheng.library.model.Reader"%>
<%@page import="com.yangheng.library.model.ReaderType"%>
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

<title>图书馆图书查询系统 -- <%=request.getParameter("input")%>搜索结果
</title>
<%
	//Reader reader = (Reader) request.getAttribute("reader");
	request.setCharacterEncoding("GBK");
	ReaderDAO readerDAO = new ReaderDAO();
	String s = request.getParameter("RdID");
	Reader reader = (Reader) readerDAO.getObjectByID(s);
	BorrowRecordDAO borrowRecordDAO = new BorrowRecordDAO();
	ReaderTypeDAO readerTypeDAO = new ReaderTypeDAO();
	BookLibDAO bookLibDAO = new BookLibDAO();
	//System.out.print(reader.getRdType());
	//out.print("<script>alert("+reader.getRdType()+");</script>");
	ReaderType readerType = (ReaderType) readerTypeDAO.getObjectByID(reader.getRdType());
	BookDAO bookDAO = new BookDAO();
	ArrayList<AbstractModel> booklib = bookLibDAO.getSearchedBooks(request.getParameter("input"));
	ArrayList<AbstractModel> booklibInfo = new ArrayList<AbstractModel>();
	for (int i = 0; i < booklib.size(); i++) {
		booklibInfo.add(bookDAO.getObjectByID(((BookLib) booklib.get(i)).getBkISBN()));
	}
	BookCategoryDAO bookCategoryDAO = new BookCategoryDAO();
%>


</head>

<body>
	<h2>
		当前用户:<%=reader.getRdName()%></h2>
	已借书数量：<%=reader.getRdBrorrowNum()%>&nbsp;&nbsp;剩余可借：<%=readerType.getRdCanLendSum() - reader.getRdBrorrowNum()%>
	<h2>搜索结果</h2>
	<table border="1px" width="100%">
		<tr>
			<td>图书ID</td>
			<td>分类号</td>
			<td>图书名称</td>
			<td>图书作者</td>
			<td>语言</td>
			<td>出版社</td>
			<td>出版日期</td>
			<td>ISBN</td>
			<td>书籍分类</td>
			<td>页数</td>
			<td>价格</td>
			<td>入馆日期</td>
			<td>状态</td>
			<td>操作</td>
		</tr>
		<%
			for (int i = 0; i < booklib.size(); i++) {
		%><tr>

			<td><%=((BookLib) (booklib.get(i))).getBkID()%></td>
			<td><%=((BookLib) (booklib.get(i))).getBkCatagegroy()%></td>
			<td><%=((Book) (booklibInfo.get(i))).getBkName()%></td>
			<td><%=((Book) (booklibInfo.get(i))).getBkAuthor()%></td>
			<td><%=((Book) (booklibInfo.get(i))).getBkLanguage()%></td>
			<td><%=((Book) (booklibInfo.get(i))).getBkPress()%></td>
			<td><%=((Book) (booklibInfo.get(i))).getBkPressDate()%></td>
			<td><%=((Book) (booklibInfo.get(i))).getBkISBN()%></td>
			<td><%=((BookCategory) bookCategoryDAO.getObjectByID(((Book) (booklibInfo.get(i))).getBkCatageroy()))
						.getCategoryName()%></td>
			<td><%=((Book) (booklibInfo.get(i))).getBkPages()%></td>
			<td><%=((Book) (booklibInfo.get(i))).getBkPrice()%></td>
			<td><%=((BookLib) (booklib.get(i))).getCollectDate()%></td>
			<td><%=((BookLib) (booklib.get(i))).getBkStatus()%></td>
			<td>
				<%
					if (((BookLib) (booklib.get(i))).getBkStatus().equals("在馆")) {
				%>
				<form action="PreBorrow" method="post">
					<input type="submit" value="预借"> <input type="hidden"
						name="BkID" value="<%=((BookLib) (booklib.get(i))).getBkID()%>">
					<input type="hidden" name="RdName" value="<%=reader.getRdName()%>">
				</form> <%
 	} else if (((BookLib) booklib.get(i)).getBkStatus().startsWith(reader.getRdName())) {
 		%>

				<form action="PreBorrow" method="post">
					<input type="submit" value="预借"> <input type="hidden"
						name="BkID" value="<%=((BookLib) (booklib.get(i))).getBkID()%>">
					<input type="hidden" name="RdName" value="<%=reader.getRdName()%>">
				</form>
 			<%} %>
			</td>
		</tr>
		<%
			}
		%>

	</table>


</body>
</html>
