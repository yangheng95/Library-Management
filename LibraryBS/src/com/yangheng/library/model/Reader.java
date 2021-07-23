package com.yangheng.library.model;

public class Reader extends AbstractModel {


	private String rdID;
	private String rdName;
	private String rdSex;
	private String rdType;
	private String rdDept;
	private String rdPhone;
	private String rdEmail;
	private String rdRegDate;
	private String rdPhoto;
	private String rdStatus;
	private int rdBrorrowNum;
	private String rdPwd;
	private int rdAdminRole;
	
	/**
	 * @param rdID
	 * @param rdName
	 * @param rdSex
	 * @param rdType
	 * @param rdDept
	 * @param rdPhone
	 * @param rdEmail
	 * @param rdRegDate
	 * @param rdPhoto
	 * @param rdStatus
	 * @param rdBrorrowNum
	 * @param rdPwd
	 * @param rdAdminRole
	 */
	public Reader(String rdID, String rdName, String rdSex, String rdType, String rdDept, String rdPhone, String rdEmail,
			String rdRegDate, String rdPhoto, String rdStatus, int rdBrorrowNum, String rdPwd, int rdAdminRole) {
		super();
		this.rdID = rdID;
		this.rdName = rdName;
		this.rdSex = rdSex;
		this.rdType = rdType;
		this.rdDept = rdDept;
		this.rdPhone = rdPhone;
		this.rdEmail = rdEmail;
		this.rdRegDate = rdRegDate;
		this.rdPhoto = rdPhoto;
		this.rdStatus = rdStatus;
		this.rdBrorrowNum = rdBrorrowNum;
		this.rdPwd = rdPwd;
		this.rdAdminRole = rdAdminRole;
	}

	public Reader() {
		// TODO Auto-generated constructor stub
	}

	public String getRdID() {
		return rdID;
	}

	public void setRdID(String rdID) {
		this.rdID = rdID;
	}

	public String getRdName() {
		return rdName;
	}

	public void setRdName(String rdName) {
		this.rdName = rdName;
	}

	public String getRdSex() {
		return rdSex;
	}

	public void setRdSex(String rdSex) {
		this.rdSex = rdSex;
	}

	public String getRdType() {
		return rdType;
	}

	public void setRdType(String rdType) {
		this.rdType = rdType;
	}

	public String getRdDept() {
		return rdDept;
	}

	public void setRdDept(String rdDept) {
		this.rdDept = rdDept;
	}

	public String getRdPhone() {
		return rdPhone;
	}

	public void setRdPhone(String rdPhone) {
		this.rdPhone = rdPhone;
	}

	public String getRdEmail() {
		return rdEmail;
	}

	public void setRdEmail(String rdEmail) {
		this.rdEmail = rdEmail;
	}

	public String getRdRegDate() {
		return rdRegDate;
	}

	public void setRdRegDate(String rdRegDate) {
		this.rdRegDate = rdRegDate;
	}

	public String getRdPhoto() {
		return rdPhoto;
	}

	public void setRdPhoto(String rdPhoto) {
		this.rdPhoto = rdPhoto;
	}

	public String getRdStatus() {
		return rdStatus;
	}

	public void setRdStatus(String rdStatus) {
		this.rdStatus = rdStatus;
	}

	public int getRdBrorrowNum() {
		return rdBrorrowNum;
	}

	public void setRdBrorrowNum(int rdBrorrowNum) {
		this.rdBrorrowNum = rdBrorrowNum;
	}

	public String getRdPwd() {
		return rdPwd;
	}

	public void setRdPwd(String rdPwd) {
		this.rdPwd = rdPwd;
	}

	public int getRdAdminRole() {
		return rdAdminRole;
	}

	public void setRdAdminRole(int rdAdminRole) {
		this.rdAdminRole = rdAdminRole;
	}

	@Override
	public String toString() {
		return "Reader [rdID=" + rdID + ", rdName=" + rdName + ", rdSex=" + rdSex + ", rdType=" + rdType + ", rdDept="
				+ rdDept + ", rdPhone=" + rdPhone + ", rdEmail=" + rdEmail + ", rdRegDate=" + rdRegDate + ", rdPhoto="
				+ rdPhoto + ", rdStatus=" + rdStatus + ", rdBrorrowNum=" + rdBrorrowNum + ", rdPwd=" + rdPwd
				+ ", rdAdminRole=" + rdAdminRole + "]";
	}

	

}
