package com.yangheng.library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yangheng.library.model.AbstractModel;
import com.yangheng.library.model.Department;

public class DepartmentDAO extends AbstractDAO{

	@Override
	public ArrayList<AbstractModel> getAllObjects() {
		String sql = "select * from Department";
		ResultSet rs = SQLHelper.getResultSet(sql);
		ArrayList<AbstractModel> models = new ArrayList<AbstractModel>();
		try {
			while (rs.next()) {
				Department department = new Department();
				department.setDeptID(rs.getInt(1));
				department.setDeptName(rs.getString(2));
				models.add(department);
			}
			return models;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean add(AbstractModel model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(AbstractModel model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(AbstractModel model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AbstractModel getObjectByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractModel getObjectByID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
