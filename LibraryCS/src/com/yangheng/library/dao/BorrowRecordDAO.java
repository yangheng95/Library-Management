package com.yangheng.library.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yangheng.library.model.AbstractModel;
import com.yangheng.library.model.BorrowRecord;

public class BorrowRecordDAO extends AbstractDAO {

	public ArrayList<AbstractModel> getObjectsByRdID(String rdID) {
		String sql = "select * from borrowrecord where rdId ='" + rdID + "'";
		ResultSet rs = SQLHelper.getResultSet(sql);
		ArrayList<AbstractModel> borrowRecord = new ArrayList<AbstractModel>();
		try {
			while (rs.next()) {
				BorrowRecord record = new BorrowRecord();
				record.setBorrowID(rs.getString(1));
				record.setRdID(rs.getString(2));
				record.setBkID(rs.getInt(3));
				record.setContinueTimes(rs.getInt(4));
				record.setDateOut(rs.getString(5));
				record.setDateShouldRet(rs.getString(6));
				record.setDateActRet(rs.getString(7));
				record.setOverDay(rs.getInt(8));
				record.setPunishMoney(rs.getFloat(9));
				record.setReturned(rs.getInt(10));
				record.setOperatorLend(rs.getString(11));
				record.setOperatorRet(rs.getString(12));
				borrowRecord.add(record);
				// System.out.println(record.toString());
			}
			return borrowRecord;
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}

	}

	@Override
	public ArrayList<AbstractModel> getAllObjects() {
		String sql = "select * from borrowrecord";
		ResultSet rs = SQLHelper.getResultSet(sql);
		ArrayList<AbstractModel> models = new ArrayList<AbstractModel>();
		try {
			while (rs.next()) {
				BorrowRecord record = new BorrowRecord();
				record.setBorrowID(rs.getString(1));
				record.setRdID(rs.getString(2));
				record.setBkID(rs.getLong(3));
				record.setContinueTimes(rs.getInt(4));
				record.setDateOut(rs.getString(5));
				record.setDateShouldRet(rs.getString(6));
				record.setDateActRet(rs.getString(7));
				record.setOverDay(rs.getInt(8));
				record.setPunishMoney(rs.getFloat(9));
				record.setReturned(rs.getInt(10));
				record.setOperatorLend(rs.getString(11));
				record.setOperatorRet(rs.getString(12));
				models.add(record);
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
			BorrowRecord record = (BorrowRecord) model;
			// System.out.println(((record)model).toString());
			String sql = "insert into borrowrecord values(?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement ps = SQLHelper.getConnection().prepareStatement(sql);
			ps.setString(1, record.getBorrowID());
			ps.setString(2, record.getRdID());
			ps.setLong(3, record.getBkID());
			ps.setInt(4, record.getContinueTimes());
			ps.setString(5, record.getDateOut());
			ps.setString(6, record.getDateShouldRet());
			ps.setString(7, record.getDateActRet());
			ps.setInt(8, record.getOverDay());
			ps.setFloat(9, record.getPunishMoney());
			ps.setInt(10, record.getReturned());
			ps.setString(11, record.getOperatorLend());
			ps.setString(12, record.getOperatorRet());
			;

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
			BorrowRecord record = (BorrowRecord) model;
			String sql = "delete from BorrowRecord where borrowid='" + record.getBorrowID() + "'";
			if (SQLHelper.ExecQuery(sql) > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * (non-Javadoc) 借阅记录只允许修改续借次数，实际归还时间，超出时间，罚款，是否归还，还书操作员
	 * 
	 * @see
	 * com.yangheng.library.dao.AbstractDAO#update(com.yangheng.library.model.
	 * AbstractModel)
	 */
	@Override
	public boolean update(AbstractModel model) {
		try {
			BorrowRecord record = (BorrowRecord) model;
			String sql = "update BorrowRecord set ContinueTimes=?,DateShouldRet=?,DateActRet=?,OverDay=?,PunishMoney=?,Returned=?,"
					+ "OperatorRet=? where borrowid = '" + record.getBorrowID() + "'";
			PreparedStatement ps = SQLHelper.getConnection().prepareStatement(sql);

			ps.setInt(1, record.getContinueTimes());
			ps.setString(2, record.getDateShouldRet());
			ps.setString(3, record.getDateActRet());
			ps.setInt(4, record.getOverDay());
			ps.setFloat(5, record.getPunishMoney());
			ps.setInt(6, record.getReturned());
			ps.setString(7, record.getOperatorRet());
			if (ps.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public AbstractModel getObjectByID(int id) {
		String sql = "select * from borrowrecord where borrowid =" + id;
		ResultSet rs = SQLHelper.getResultSet(sql);

		try {
			while (rs.next()) {
				BorrowRecord record = new BorrowRecord();
				record.setBorrowID(rs.getString(1));
				record.setRdID(rs.getString(2));
				record.setBkID(rs.getInt(3));
				record.setContinueTimes(rs.getInt(4));
				record.setDateOut(rs.getString(5));
				record.setDateShouldRet(rs.getString(6));
				record.setDateActRet(rs.getString(7));
				record.setOverDay(rs.getInt(8));
				record.setPunishMoney(rs.getFloat(9));
				record.setReturned(rs.getInt(10));
				record.setOperatorLend(rs.getString(11));
				record.setOperatorRet(rs.getString(12));
				return record;

			}

		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
		return null;
	}

	public AbstractModel getObjectByID(long id) {
		String sql = "select * from borrowrecord where bkid =" + id;
		ResultSet rs = SQLHelper.getResultSet(sql);

		try {
			while (rs.next()) {
				BorrowRecord record = new BorrowRecord();
				if (record.getReturned() == 0) {
					record.setBorrowID(rs.getString(1));
					record.setRdID(rs.getString(2));
					record.setBkID(rs.getInt(3));
					record.setContinueTimes(rs.getInt(4));
					record.setDateOut(rs.getString(5));
					record.setDateShouldRet(rs.getString(6));
					record.setDateActRet(rs.getString(7));
					record.setOverDay(rs.getInt(8));
					record.setPunishMoney(rs.getFloat(9));
					record.setReturned(rs.getInt(10));
					record.setOperatorLend(rs.getString(11));
					record.setOperatorRet(rs.getString(12));
					return record;
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
		return null;
	}

	public ArrayList<AbstractModel> getPunishMoneyByRdName(String id) {
		String sql = "select * from borrowrecord where rdid ='" + id + "' and PunishMoney > 0";
		ResultSet rs = SQLHelper.getResultSet(sql);
	
		ArrayList<AbstractModel> arrayList = new ArrayList<AbstractModel>();
		try {
			while (rs.next()) {
				BorrowRecord record = new BorrowRecord();
				if (record.getReturned() == 0) {
					record.setBorrowID(rs.getString(1));
					record.setRdID(rs.getString(2));
					record.setBkID(rs.getInt(3));
					record.setContinueTimes(rs.getInt(4));
					record.setDateOut(rs.getString(5));
					record.setDateShouldRet(rs.getString(6));
					record.setDateActRet(rs.getString(7));
					record.setOverDay(rs.getInt(8));
					record.setPunishMoney(rs.getFloat(9));
					record.setReturned(rs.getInt(10));
					record.setOperatorLend(rs.getString(11));
					record.setOperatorRet(rs.getString(12));
					arrayList.add(record);
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
		return arrayList;
	}

	@Override
	public AbstractModel getObjectByID(String id) {
		String sql = "select * from borrowrecord where borrowid ='" + id + "'";
		ResultSet rs = SQLHelper.getResultSet(sql);

		try {
			while (rs.next()) {
				BorrowRecord record = new BorrowRecord();
				if (record.getReturned() == 0) {
					record.setBorrowID(rs.getString(1));
					record.setRdID(rs.getString(2));
					record.setBkID(rs.getInt(3));
					record.setContinueTimes(rs.getInt(4));
					record.setDateOut(rs.getString(5));
					record.setDateShouldRet(rs.getString(6));
					record.setDateActRet(rs.getString(7));
					record.setOverDay(rs.getInt(8));
					record.setPunishMoney(rs.getFloat(9));
					record.setReturned(rs.getInt(10));
					record.setOperatorLend(rs.getString(11));
					record.setOperatorRet(rs.getString(12));
					return record;
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
		return null;
	}

}
