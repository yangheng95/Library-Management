package com.yangheng.library.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yangheng.library.model.AbstractModel;
import com.yangheng.library.model.Book;

public class BookDAO extends AbstractDAO {
	@Override
	public ArrayList<AbstractModel> getAllObjects() {
		String sql = "select * from book";
		ResultSet rs = SQLHelper.getResultSet(sql);
		ArrayList<AbstractModel> models = new ArrayList<AbstractModel>();
		try {
			while (rs.next()) {
				Book book = new Book();
				book.setBkName(rs.getString(1));
				book.setBkAuthor(rs.getString(2));
				book.setBkPress(rs.getString(3));
				book.setBkPressDate(rs.getString(4));
				book.setBkISBN(rs.getString(5));
				book.setBkCatageroy(rs.getString(6));
				book.setBkLanguage(rs.getString(7));
				book.setBkPages(rs.getInt(8));
				book.setBkPrice(rs.getFloat(9));
				book.setBkBrief(rs.getString(10));
				try {
					book.setBkCover(SQLHelper.InputStreamTOByte(rs.getBinaryStream(11)));
				} catch (IOException e) {
					e.printStackTrace();
				}
				models.add((AbstractModel) book);
			}
			return models;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean add(AbstractModel model) {

		try {
			Book book = (Book) model;
			// System.out.println(((Book)model).toString());
			String sql = "insert into book values(?,?,?,?,?,?,?,?,?,?,?)";
			String sql1 = "insert into book values(?,?,?,?,?,?,?,?,?,?)";
			if (book.getBkCover().equals(null)) {
				PreparedStatement ps = SQLHelper.getConnection().prepareStatement(sql1);
				ps.setString(1, book.getBkName());
				ps.setString(2, book.getBkAuthor());
				ps.setString(3, book.getBkPress());
				ps.setString(4, book.getBkPressDate());
				ps.setString(5, book.getBkISBN());
				ps.setString(6, book.getBkCatageroy());
				ps.setString(7, book.getBkLanguage());
				ps.setString(8, String.valueOf(book.getBkPages()));
				ps.setString(9, String.valueOf(book.getBkPrice()));
				ps.setString(10, book.getBkBrief());
				if (ps.executeUpdate() > 0)
					return true;
			}else {
				PreparedStatement ps = SQLHelper.getConnection().prepareStatement(sql);
				ps.setString(1, book.getBkName());
				ps.setString(2, book.getBkAuthor());
				ps.setString(3, book.getBkPress());
				ps.setString(4, book.getBkPressDate());
				ps.setString(5, book.getBkISBN());
				ps.setString(6, book.getBkCatageroy());
				ps.setString(7, book.getBkLanguage());
				ps.setString(8, String.valueOf(book.getBkPages()));
				ps.setString(9, String.valueOf(book.getBkPrice()));
				ps.setString(10, book.getBkBrief());
				ps.setBinaryStream(11, SQLHelper.byteTOInputStream(book.getBkCover()), (int) book.getBkCover().length);
				if (ps.executeUpdate() > 0)
					return true;
			}
			

			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;

	}

	@Override
	public boolean delete(AbstractModel model) {
		try {
			Book book = (Book) model;
			String sql = "delete from book where bkISBN = '" + book.getBkISBN() + "'";
			if (SQLHelper.ExecQuery(sql) > 0)
				return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public boolean update(AbstractModel model) {
		try {
			Book book = (Book) model;
			// System.out.println(((Book)model).toString());
			String sql = "update book set bkName=?,bkAuthor=?,bkPress=?,"
					+ "bkPressDate=?,bkISBN=?,bkCategory=?,bkLanguage=?,bkPages=?,bkPrice=?,"
					+ "bkBrief=?,bkImage=? where bkISBN = '" + book.getBkISBN() + "'";

			PreparedStatement ps = SQLHelper.getConnection().prepareStatement(sql);
			ps.setString(1, book.getBkName());
			ps.setString(2, book.getBkAuthor());
			ps.setString(3, book.getBkPress());
			ps.setString(4, book.getBkPressDate());
			ps.setString(5, book.getBkISBN());
			ps.setString(6, book.getBkCatageroy());
			ps.setString(7, book.getBkLanguage());
			ps.setInt(8, book.getBkPages());
			ps.setFloat(9, book.getBkPrice());
			ps.setString(10, book.getBkBrief());
			try {
				ps.setBinaryStream(11, SQLHelper.byteTOInputStream(book.getBkCover()), (int) book.getBkCover().length);
			} catch (Exception e) {
				ps.setNull(11, 0);
			}

			if (ps.executeUpdate() > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yangheng.library.dao.AbstractDAO#getObjectByID(int) get book by
	 * ISBN
	 */

	public AbstractModel getObjectByID(long l) {

		String sql = "select * from book where bkid = " + l;
		ResultSet rs = SQLHelper.getResultSet(sql);
		Book book;
		try {
			while (rs.next()) {
				book = new Book();
				book.setBkName(rs.getString(1));
				book.setBkAuthor(rs.getString(2));
				book.setBkPress(rs.getString(3));
				book.setBkPressDate(rs.getString(4));
				book.setBkISBN(rs.getString(5));
				book.setBkCatageroy(rs.getString(6));
				book.setBkLanguage(rs.getString(7));
				book.setBkPages(rs.getInt(8));
				book.setBkPrice(rs.getFloat(9));
				book.setBkBrief(rs.getString(10));
				try {
					book.setBkCover(SQLHelper.InputStreamTOByte(rs.getBinaryStream(11)));
				} catch (IOException e) {
					e.printStackTrace();
				}
				return book;
			}

		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public AbstractModel getObjectByID(String id) {

		//System.out.println(id);
		String sql = "select * from book where bkISBN = '" + id + "'";
		ResultSet rs = SQLHelper.getResultSet(sql);
		Book book;
		try {
			while (rs.next()) {
				book = new Book();
				book.setBkName(rs.getString(1));
				book.setBkAuthor(rs.getString(2));
				book.setBkPress(rs.getString(3));
				book.setBkPressDate(rs.getString(4));
				book.setBkISBN(rs.getString(5));
				book.setBkCatageroy(rs.getString(6));
				book.setBkLanguage(rs.getString(7));
				book.setBkPages(rs.getInt(8));
				book.setBkPrice(rs.getFloat(9));
				book.setBkBrief(rs.getString(10));
				try {
					book.setBkCover(SQLHelper.InputStreamTOByte(rs.getBinaryStream(11)));
				} catch (Exception e) {
					System.out.println("图书封面不存在");
				}
				return book;
			}

		} catch (SQLException e) {

			e.printStackTrace();

		}
		return null;
	}

	@Override
	public AbstractModel getObjectByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
