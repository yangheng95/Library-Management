package com.yangheng.library.configuration;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.yangheng.library.dao.ReaderTypeDAO;
import com.yangheng.library.gui.BookManagePanel;
import com.yangheng.library.gui.BorrowPanel;
import com.yangheng.library.gui.ReaderPanel;
import com.yangheng.library.model.AbstractModel;
import com.yangheng.library.model.Reader;

public class UserConfiguration {

	public static AbstractModel user;
	public static AbstractModel userType;
	public static AbstractModel Reader;
	public static ReaderPanel readerPanel;
	public static BorrowPanel borrowPanel;
	public static boolean showReturnedBooks = false;
	public static BookManagePanel bookManagePanel;
	public static String BookManage_SearchKeyWord;

	/**
	 * 通过HTTP方式获取文件
	 * 
	 * @param strUrl
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String getRemoteFile(String strUrl) throws IOException {
		URL url = new URL(strUrl);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		DataInputStream input = new DataInputStream(conn.getInputStream());
		// DataOutputStream output = new DataOutputStream(new
		// FileOutputStream(fileName));
		byte[] buffer = new byte[1024 * 8];
		// int count = 0;
		while ((input.read(buffer)) > 0) {
			// output.write(buffer, 0, count);
		}
		// output.close();
		input.close();
		return new String(buffer, "UTF-8");
	}

	/**
	 * 从URL抓取一个文件写到本地 这个方法摘自 <a href=
	 * "http://commons.apache.org/proper/commons-io/apidocs/org/apache/commons/io/FileUtils.html">
	 * org.apache.commons.io.FileUtils.copyURLToFile(URL source, File
	 * destination)</a>
	 * 
	 * @param source
	 * @param destination
	 * @throws IOException
	 */
	public static void copyFileFromURL(URL source) throws IOException {
		File destination = new File("temp.jpg");
		InputStream input = null;
		FileOutputStream output = null;
		byte[] buffer = new byte[1024];

		input = source.openStream();

		if (destination.exists()) {
			if (destination.isDirectory()) {
				throw new IOException("File '" + destination + "' exists but is a directory");
			}
			if (destination.canWrite() == false) {
				throw new IOException("File '" + destination + "' cannot be written to");
			}
		} else {
			File parent = destination.getParentFile();
			if (parent != null) {
				if (!parent.mkdirs() && !parent.isDirectory()) {
					throw new IOException("Directory '" + parent + "' could not be created");
				}
			}
		}

		output = new FileOutputStream(destination, true);

		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
		}
		output.close();
		input.close();
	}

	public static String getContentByTag(String content, String tag) {
		int begin;
		String result = "";
		switch (tag) {
		case "bkName":
			tag = "\"title\"";
			content = content.substring(content.indexOf("isbn"));
			begin = content.indexOf(tag);
			for (int i = begin + tag.length() + 2; i < content.length(); i++) {
				if (content.charAt(i) == '"') {
					return result;
				}
				result += content.charAt(i);
			}
			return "";

		case "bkAuthor":
			content = content.substring(content.indexOf("[") + 1, content.indexOf("]"));

			String tmp[] = content.split("\"");
			result = "";
			for (int i = 0; i < tmp.length; i++) {
				result += tmp[i];
			}
			return result;

		case "bkPressDate":
			tag = "\"pubdate\"";
			begin = content.indexOf(tag);
			result = "";
			for (int i = begin + tag.length() + 2; i < content.length(); i++) {
				if (content.charAt(i) == '"') {
					return result;
				}
				result += content.charAt(i);
			}
			return "";
		case "bkPress":
			tag = "\"publisher\"";
			begin = content.lastIndexOf(tag);
			result = "";
			for (int i = begin + tag.length() + 2; i < content.length(); i++) {
				if (content.charAt(i) == '"') {
					return result;
				}
				result += content.charAt(i);
			}
			return "";
		case "bkISBN":
			tag = "\"isbn13\"";
			begin = content.indexOf(tag);
			result = "";
			for (int i = begin + tag.length() + 2; i < content.length(); i++) {
				if (content.charAt(i) == '"') {
					return result;
				}
				result += content.charAt(i);
			}
			return "";
		case "bkPage":
			tag = "\"pages\"";
			begin = content.indexOf(tag);
			result = "";
			for (int i = begin + tag.length() + 2; i < content.length(); i++) {
				if (content.charAt(i) == '"') {
					return result;
				}
				result += content.charAt(i);
			}
			return "";
		case "bkPrice":
			tag = "\"price\"";
			begin = content.indexOf(tag);
			result = "";
			for (int i = begin + tag.length() + 2; i < content.length(); i++) {
				if (content.charAt(i) == '"') {
					return result;
				}
				result += content.charAt(i);
			}
			return "";
		case "bkBrief":
			tag = "\"summary\"";
			begin = content.lastIndexOf(tag);
			result = "";
			for (int i = begin + tag.length() + 2; i < content.length(); i++) {
				if (content.charAt(i) == '"') {
					if (content.substring(i).startsWith("\"")) {

						// System.out.println(result.replace("\n", "\r\n"));
						return result = result.replace("\\n", "\r\n");
					}
					result += content.charAt(i);

				}
				result += content.charAt(i);
			}
			return "";
		case "bkPhoto":
			tag = "\"large\"";
			begin = content.indexOf(tag);
			result = "";
			for (int i = begin + tag.length() + 2; i < content.length(); i++) {
				if (content.charAt(i) == '"') {
					return result;
				}
				result += content.charAt(i);
			}
			return "";
		default:
			break;
		}
		return "";

	}

	public static void setUser(AbstractModel user) {
		if (user instanceof Reader) {

			UserConfiguration.user = user;
			ReaderTypeDAO dao = new ReaderTypeDAO();
			UserConfiguration.userType = dao.getObjectByID(((Reader) user).getRdType());

		}
	}

}
