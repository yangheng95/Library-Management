package com.yangheng.library.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yangheng.library.model.AbstractModel;
import com.yangheng.library.model.Reader;

public class ReaderDAO extends AbstractDAO{
	
	@Override
	public ArrayList<AbstractModel> getAllObjects() {
		String sql = "select * from reader";
		ResultSet rs = SQLHelper.getResultSet(sql);
		ArrayList<AbstractModel> models = new ArrayList<AbstractModel>();
		try {
			while (rs.next()) {
				Reader reader = new Reader();
				reader.setRdID(rs.getString(1));
				reader.setRdName(rs.getString(2));
				reader.setRdSex(rs.getString(3));
				reader.setRdType(rs.getString(4));
				reader.setRdDept(rs.getString(5));
				reader.setRdPhone(rs.getString(6));
				reader.setRdEmail(rs.getString(7));
				reader.setRdRegDate(rs.getString(8));
				try {
					reader.setRdPhoto(SQLHelper.InputStreamTOByte(rs.getBinaryStream(9)));
				} catch (Exception e) {
					
				}
				reader.setRdStatus(rs.getString(10));
				reader.setRdBrorrowNum(rs.getInt(11));
				reader.setRdPwd(rs.getString(12));
				reader.setRdAdminRole(rs.getInt(13));
				models.add(reader);
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
			Reader reader = (Reader) model;
			// System.out.println(((reader)model).toString());
			String sql = "insert into reader values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement ps = SQLHelper.getConnection().prepareStatement(sql);
			ps.setString(1, reader.getRdID());
			ps.setString(2, reader.getRdName());
			ps.setString(3, reader.getRdSex());
			ps.setString(4, reader.getRdType());
			ps.setString(5, reader.getRdDept());
			ps.setString(6, reader.getRdPhone());
			ps.setString(7, reader.getRdEmail());
			ps.setString(8, reader.getRdRegDate());
			ps.setBinaryStream(9, SQLHelper.byteTOInputStream(reader.getRdPhoto()),(int)reader.getRdPhoto().length);
			ps.setString(10, reader.getRdStatus());
			ps.setInt(11, reader.getRdBrorrowNum());
			ps.setString(12, reader.getRdPwd());
			ps.setInt(13, reader.getRdAdminRole());

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
			Reader reader = (Reader)model;
			String sql = "delete from reader where rdID="+reader.getRdID();
			if(SQLHelper.ExecQuery(sql)>0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(AbstractModel model) {
		try {
			Reader reader = (Reader)model;
			String sql = "update reader set rdName=?,rdSex=?,rdType=?,rdDept=?,rdPhone=?,"
					+ "rdEmail=?,rdRegDate=?,rdPhoto=?,rdStatus=?,rdBrorrowNum=?,rdPwd=?,"
					+ "rdAdminRole=? where rdid ="+reader.getRdID();
			PreparedStatement ps = SQLHelper.getConnection().prepareStatement(sql);
			//ps.setInt(1, reader.getRdID());
			ps.setString(1, reader.getRdName());
			ps.setString(2, reader.getRdSex());
			ps.setString(3, reader.getRdType());
			ps.setString(4, reader.getRdDept());
			ps.setString(5, reader.getRdPhone());
			ps.setString(6, reader.getRdEmail());
			ps.setString(7, reader.getRdRegDate());
			ps.setBinaryStream(8, SQLHelper.byteTOInputStream(reader.getRdPhoto()),(int)reader.getRdPhoto().length);
			ps.setString(9,reader.getRdStatus());
			ps.setInt(10, reader.getRdBrorrowNum());
			ps.setString(11, reader.getRdPwd());
			ps.setInt(12, reader.getRdAdminRole());

			if (ps.executeUpdate() > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public AbstractModel getObjectByID(int id) {
		return null;

	
	}
	
	public AbstractModel getObjectByName(String rdName) {
		String sql = "select * from reader where rdname='" + rdName+"'";
		ResultSet rs = SQLHelper.getResultSet(sql);
		Reader reader;
		try {
			while (rs.next()) {
				reader = new Reader();
				reader.setRdID(rs.getString(1));
				reader.setRdName(rs.getString(2));
				reader.setRdSex(rs.getString(3));
				reader.setRdType(rs.getString(4));
				reader.setRdDept(rs.getString(5));
				reader.setRdPhone(rs.getString(6));
				reader.setRdEmail(rs.getString(7));
				reader.setRdRegDate(rs.getString(8));
				try {
					reader.setRdPhoto(SQLHelper.InputStreamTOByte(rs.getBinaryStream(9)));
				} catch (Exception e) {
					
				}
				reader.setRdStatus(rs.getString(10));
				reader.setRdBrorrowNum(rs.getInt(11));
				reader.setRdPwd(rs.getString(12));
				reader.setRdAdminRole(rs.getInt(13));
				return reader;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		return null;

	
	}

	@Override
	public AbstractModel getObjectByID(String id) {
		String sql = "select * from reader where rdid ='" + id+"'";
		ResultSet rs = SQLHelper.getResultSet(sql);
		Reader reader;
		try {
			while (rs.next()) {
				reader = new Reader();
				reader.setRdID(rs.getString(1));
				reader.setRdName(rs.getString(2));
				reader.setRdSex(rs.getString(3));
				reader.setRdType(rs.getString(4));
				reader.setRdDept(rs.getString(5));
				reader.setRdPhone(rs.getString(6));
				reader.setRdEmail(rs.getString(7));
				reader.setRdRegDate(rs.getString(8));
				try {
					reader.setRdPhoto(SQLHelper.InputStreamTOByte(rs.getBinaryStream(9)));
				} catch (Exception e) {
					
				}
				
				reader.setRdStatus(rs.getString(10));
				reader.setRdBrorrowNum(rs.getInt(11));
				reader.setRdPwd(rs.getString(12));
				reader.setRdAdminRole(rs.getInt(13));
				return reader;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		return null;

	}

	public ArrayList<AbstractModel> getObjectsByName(String rdName) {
		String sql = "select * from reader where rdname='" + rdName+"'";
		ResultSet rs = SQLHelper.getResultSet(sql);
		ArrayList<AbstractModel> readers = new ArrayList<AbstractModel>();
		Reader reader;
		try {
			while (rs.next()) {
				reader = new Reader();
				reader.setRdID(rs.getString(1));
				reader.setRdName(rs.getString(2));
				reader.setRdSex(rs.getString(3));
				reader.setRdType(rs.getString(4));
				reader.setRdDept(rs.getString(5));
				reader.setRdPhone(rs.getString(6));
				reader.setRdEmail(rs.getString(7));
				reader.setRdRegDate(rs.getString(8));
				try {
					reader.setRdPhoto(SQLHelper.InputStreamTOByte(rs.getBinaryStream(9)));
				} catch (Exception e) {
					
				}
				reader.setRdStatus(rs.getString(10));
				reader.setRdBrorrowNum(rs.getInt(11));
				reader.setRdPwd(rs.getString(12));
				reader.setRdAdminRole(rs.getInt(13));
				readers.add(reader);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		return readers;
	}
	


    

}
