package com.yangheng.library.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yangheng.library.dao.BookLibDAO;
import com.yangheng.library.dao.ReaderDAO;
import com.yangheng.library.model.BookLib;
import com.yangheng.library.model.Reader;

public class PreBorrow extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
		 * Constructor of the object.
		 */
	public PreBorrow() {
		super();
	}

	/**
		 * Destruction of the servlet. <br>
		 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("GBK");
		BookLibDAO bookLibDAO = new BookLibDAO();
		BookLib bookLib = (BookLib)bookLibDAO.getObjectByID(Long.parseLong(request.getParameter("BkID")));
		
		String RdName = request.getParameter("RdName");
		if(bookLib.getBkStatus().equals("ÔÚ¹Ý")){
			bookLib.setBkStatus(RdName+"Ô¤½è");
			bookLibDAO.update(bookLib);
			Reader reader = (Reader)(new ReaderDAO().getObjectByName(RdName));
			//request.setAttribute("input", bookLib.getBkName());
			request.setAttribute("reader", reader);
			request.getRequestDispatcher("./main.jsp").forward(request, response);
		}
		
	}

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {

	}

}
