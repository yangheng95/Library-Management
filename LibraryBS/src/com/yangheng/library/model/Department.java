package com.yangheng.library.model;

public class Department extends AbstractModel {

	int DeptID;
	String DeptName;
	public Department(int deptID, String deptName) {
		super();
		DeptID = deptID;
		DeptName = deptName;
	}
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getDeptID() {
		return DeptID;
	}
	public void setDeptID(int deptID) {
		DeptID = deptID;
	}
	public String getDeptName() {
		return DeptName;
	}
	public void setDeptName(String deptName) {
		DeptName = deptName;
	}
	@Override
	public String toString() {
		return "Department [DeptID=" + DeptID + ", DeptName=" + DeptName + "]";
	}
	
	
}
