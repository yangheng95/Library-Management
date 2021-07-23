package com.yangheng.library.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yangheng.library.model.AbstractModel;
import com.yangheng.library.model.BookLib;

public class BookLibDAO extends AbstractDAO {

	public ArrayList<AbstractModel> getSearchedBooks(String keyWord) {
		String sql;
		ArrayList<AbstractModel> models = new ArrayList<AbstractModel>();

		try {
			Float.parseFloat(keyWord);
			sql = "select * from bookLib where bkid=" + keyWord;
			ResultSet rs = SQLHelper.getResultSet(sql);
			outer: while (rs.next()) {
				BookLib booklib = new BookLib();
				booklib.setBkID(rs.getLong(1));
				booklib.setBkName(rs.getString(2));
				booklib.setBkCatagegroy(rs.getString(3));
				booklib.setBkISBN(rs.getString(4));
				booklib.setCollectDate(rs.getString(5));
				booklib.setBkStatus(rs.getString(6));
				for (int i = 0; i < models.size(); i++) {
					if (booklib.getBkID() == ((BookLib) models.get(i)).getBkID())
						continue outer;

				}
				models.add((AbstractModel) booklib);
			}

		} catch (Exception e) {
		
			
		}
		
		sql = "select * from bookLib where bkname='" + keyWord + "'";
		ResultSet rs = SQLHelper.getResultSet(sql);

		try {
			outer: while (rs.next()) {
				BookLib booklib = new BookLib();
				booklib.setBkID(rs.getLong(1));
				booklib.setBkName(rs.getString(2));
				booklib.setBkCatagegroy(rs.getString(3));
				booklib.setBkISBN(rs.getString(4));
				booklib.setCollectDate(rs.getString(5));
				booklib.setBkStatus(rs.getString(6));
				for (int i = 0; i < models.size(); i++) {
					if (booklib.getBkID() == ((BookLib) models.get(i)).getBkID())
						continue outer;

				}
				models.add((AbstractModel) booklib);
			}

		} catch (SQLException e1) {

		}
		sql = "select * from bookLib where bkCategory like '%" + keyWord + "%'";
		rs = SQLHelper.getResultSet(sql);
		try {
			outer: while (rs.next()) {
				BookLib booklib = new BookLib();
				booklib.setBkID(rs.getLong(1));
				booklib.setBkName(rs.getString(2));
				booklib.setBkCatagegroy(rs.getString(3));
				booklib.setBkISBN(rs.getString(4));
				booklib.setCollectDate(rs.getString(5));
				booklib.setBkStatus(rs.getString(6));
				for (int i = 0; i < models.size(); i++) {
					if (booklib.getBkID() == ((BookLib) models.get(i)).getBkID())
						continue outer;

				}
				models.add((AbstractModel) booklib);
			}
		} catch (SQLException e1) {

		}

		sql = "select * from bookLib where bkISBN like '%" + keyWord + "%'";
		rs = SQLHelper.getResultSet(sql);
		try {
			outer: while (rs.next()) {
				BookLib booklib = new BookLib();
				booklib.setBkID(rs.getLong(1));
				booklib.setBkName(rs.getString(2));
				booklib.setBkCatagegroy(rs.getString(3));
				booklib.setBkISBN(rs.getString(4));
				booklib.setCollectDate(rs.getString(5));
				booklib.setBkStatus(rs.getString(6));
				for (int i = 0; i < models.size(); i++) {
					if (booklib.getBkID() == ((BookLib) models.get(i)).getBkID())
						continue outer;

				}
				models.add((AbstractModel) booklib);
			}

		} catch (SQLException e1) {

		}

		sql = "select * from bookLib where bkname like '%" + keyWord + "%'";
		
		rs = SQLHelper.getResultSet(sql);
		try {
			outer: while (rs.next()) {
				BookLib booklib = new BookLib();
				booklib.setBkID(rs.getLong(1));
				booklib.setBkName(rs.getString(2));
				booklib.setBkCatagegroy(rs.getString(3));
				booklib.setBkISBN(rs.getString(4));
				booklib.setCollectDate(rs.getString(5));
				booklib.setBkStatus(rs.getString(6));
				for (int i = 0; i < models.size(); i++) {
					if (booklib.getBkID() == ((BookLib) models.get(i)).getBkID())
						continue outer;

				}
				//System.out.println(booklib);
				models.add((AbstractModel) booklib);
			}

		} catch (SQLException e1) {

		}


		return models;
	}

	@Override
	public ArrayList<AbstractModel> getAllObjects() {
		String sql = "select * from bookLib";
		ResultSet rs = SQLHelper.getResultSet(sql);
		ArrayList<AbstractModel> models = new ArrayList<AbstractModel>();
		try {
			while (rs.next()) {
				BookLib booklib = new BookLib();
				booklib.setBkID(rs.getLong(1));
				booklib.setBkName(rs.getString(2));
				booklib.setBkCatagegroy(rs.getString(3));
				booklib.setBkISBN(rs.getString(4));
				booklib.setCollectDate(rs.getString(5));
				booklib.setBkStatus(rs.getString(6));
				models.add((AbstractModel) booklib);
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
			BookLib booklib = (BookLib) model;

			String sql = "insert into bookLib(bkname,bkCategory,bkisbn,CollectDate,bkstatus) values(?,?,?,?,?)";
			System.out.println(booklib);
			PreparedStatement ps = SQLHelper.getConnection().prepareStatement(sql);
			ps.setString(1, booklib.getBkName());
			ps.setString(2, booklib.getBkCatagegroy());
			ps.setString(3, booklib.getBkISBN());
			ps.setString(4, booklib.getCollectDate());
			ps.setString(5, booklib.getBkStatus());

			if (ps.executeUpdate() > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public boolean delete(AbstractModel model) {

		try {
			BookLib booklib = (BookLib) model;
			String sql = "delete from booklib where bkid = " + booklib.getBkID();
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
			BookLib booklib = (BookLib) model;

			String sql = "update booklib set BkCategory=?,bkName=?,bkISBN=?,CollectDate=?,bkStatus=? where bkid = "
					+ ((BookLib) model).getBkID();

			PreparedStatement ps = SQLHelper.getConnection().prepareStatement(sql);
			ps.setString(1, booklib.getBkCatagegroy());
			ps.setString(2, booklib.getBkName());
			ps.setString(3, booklib.getBkISBN());
			ps.setString(4, booklib.getCollectDate());
			ps.setString(5, booklib.getBkStatus());

			if (ps.executeUpdate() > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public AbstractModel getObjectByID(int id) {
		String sql = "select * from bookLib where bkid=" + id;
		ResultSet rs = SQLHelper.getResultSet(sql);
		try {
			while (rs.next()) {
				BookLib booklib = new BookLib();
				booklib.setBkID(rs.getLong(1));
				booklib.setBkName(rs.getString(2));
				booklib.setBkCatagegroy(rs.getString(3));
				booklib.setBkISBN(rs.getString(4));
				booklib.setCollectDate(rs.getString(5));
				booklib.setBkStatus(rs.getString(6));
				return (BookLib) booklib;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public AbstractModel getObjectByID(long id) {
		String sql = "select * from bookLib where bkid=" + id;
		ResultSet rs = SQLHelper.getResultSet(sql);
		try {
			while (rs.next()) {
				BookLib booklib = new BookLib();
				booklib.setBkID(rs.getLong(1));
				booklib.setBkName(rs.getString(2));
				booklib.setBkCatagegroy(rs.getString(3));
				booklib.setBkISBN(rs.getString(4));
				booklib.setCollectDate(rs.getString(5));
				booklib.setBkStatus(rs.getString(6));
				return (BookLib) booklib;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public AbstractModel getObjectByID(String id) {
		return null;

	}

}
