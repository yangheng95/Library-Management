package com.yangheng.library.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yangheng.library.model.AbstractModel;
import com.yangheng.library.model.BookCategory;

public class BookCategoryDAO extends AbstractDAO {
	

	@Override
	public ArrayList<AbstractModel> getAllObjects() {
		String sql = "select * from BookCategory";
		ResultSet rs = SQLHelper.getResultSet(sql);
		ArrayList<AbstractModel> models = new ArrayList<AbstractModel>();
		try {
			while (rs.next()) {
				BookCategory cata = new BookCategory();
				cata.setBkCategory(rs.getString(1));
				cata.setCategoryName(rs.getString(2));
				cata.setCategoryID(rs.getInt(3));
				models.add(cata);
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
			BookCategory cata = (BookCategory) model;

			String sql = "insert into BookCategory values(?,?)";

			PreparedStatement ps = SQLHelper.getConnection().prepareStatement(sql);
			ps.setString(1, cata.getBkCategory());
			ps.setString(2, cata.getCategoryName());
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
			BookCategory book = (BookCategory) model;
			String sql = "delete from BookCategory where id = "+book.getCategoryID();
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
			BookCategory cata = (BookCategory) model;
			String sql = "update BookCategory set bkCategory=?,CataName=? where "
					+ "id = "+cata.getCategoryID();

			PreparedStatement ps = SQLHelper.getConnection().prepareStatement(sql);
			ps.setString(1, cata.getBkCategory());
			ps.setString(2, cata.getCategoryName());
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
		return null;
	}

	@Override
	public AbstractModel getObjectByID(String id) {
		String sql = "select * from BookCategory where bkcategory ='"+id+"'";
		ResultSet rs = SQLHelper.getResultSet(sql);
		try {
			while (rs.next()) {
				BookCategory cata = new BookCategory();
				cata.setBkCategory(rs.getString(1));
				cata.setCategoryName(rs.getString(2));
				cata.setCategoryID(rs.getInt(3));
				return cata;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

}
