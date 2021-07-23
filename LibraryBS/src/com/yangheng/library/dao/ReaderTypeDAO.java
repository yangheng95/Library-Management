package com.yangheng.library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yangheng.library.model.AbstractModel;
import com.yangheng.library.model.ReaderType;

public class ReaderTypeDAO extends AbstractDAO {

	@Override
	public ArrayList<AbstractModel> getAllObjects() {

		return null;
	}

	@Override
	public boolean add(AbstractModel model) {
		return false;
	}

	@Override
	public boolean update(AbstractModel model) {

		return false;
	}

	@Override
	public AbstractModel getObjectByID(int id) {
		String sql = "select * from readertype where rdtype =" + id + "";
		ResultSet rs = SQLHelper.getResultSet(sql);

		try {
			while (rs.next()) {
				ReaderType type = new ReaderType();
				type.setRdType(rs.getString(1));
				type.setRdTypeName(rs.getString(2));
				type.setRdTypeName(rs.getString(2));
				type.setRdCanLendSum(rs.getInt(3));
				type.setRdCanLendDay(rs.getInt(4));
				type.setRdRelendLimit(rs.getInt(5));
				type.setRdPunishRate(rs.getFloat(6));
				type.setRdValidTime(rs.getString(7));
				return type;
			}

		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public boolean delete(AbstractModel model) {

		return false;
	}

	@Override
	public AbstractModel getObjectByID(String id) {
		String sql = "select * from readertype where rdtype ='" + id + "'";
		ResultSet rs = SQLHelper.getResultSet(sql);

		try {
			while (rs.next()) {
				ReaderType type = new ReaderType();
				type.setRdType(rs.getString(1));
				type.setRdTypeName(rs.getString(2));
				type.setRdTypeName(rs.getString(2));
				type.setRdCanLendSum(rs.getInt(3));
				type.setRdCanLendDay(rs.getInt(4));
				type.setRdRelendLimit(rs.getInt(5));
				type.setRdPunishRate(rs.getFloat(6));
				type.setRdValidTime(rs.getString(7));
				// System.out.println(type);
				return type;

			}

		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
		return null;
	}

}
