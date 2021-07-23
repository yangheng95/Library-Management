package com.yangheng.library.model;

public class BookLib extends AbstractModel {
	
	private long bkID;
	String bkName;
	private String bkISBN;
	private String CollectDate;
	private String bkStatus;
	private String bkCatagegroy;
	public BookLib() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookLib(long bkID, String bkName, String bkISBN, String collectDate, String bkStatus, String bkCatagegroy) {
		super();
		this.bkID = bkID;
		this.bkName = bkName;
		this.bkISBN = bkISBN;
		CollectDate = collectDate;
		this.bkStatus = bkStatus;
		this.bkCatagegroy = bkCatagegroy;
	}
	public long getBkID() {
		return bkID;
	}
	public void setBkID(long bkID) {
		this.bkID = bkID;
	}
	public String getBkName() {
		return bkName;
	}
	public void setBkName(String bkName) {
		this.bkName = bkName;
	}
	public String getBkISBN() {
		return bkISBN;
	}
	public void setBkISBN(String bkISBN) {
		this.bkISBN = bkISBN;
	}
	public String getCollectDate() {
		return CollectDate;
	}
	public void setCollectDate(String collectDate) {
		CollectDate = collectDate;
	}
	public String getBkStatus() {
		return bkStatus;
	}
	public void setBkStatus(String bkStatus) {
		this.bkStatus = bkStatus;
	}
	public String getBkCatagegroy() {
		return bkCatagegroy;
	}
	public void setBkCatagegroy(String bkCatagegroy) {
		this.bkCatagegroy = bkCatagegroy;
	}
	@Override
	public String toString() {
		return "BookLib [bkID=" + bkID + ", bkName=" + bkName + ", bkISBN=" + bkISBN + ", CollectDate=" + CollectDate
				+ ", bkStatus=" + bkStatus + ", bkCatagegroy=" + bkCatagegroy + "]";
	}

	
	
	
}
