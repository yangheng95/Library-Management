package com.yangheng.library.model;

public class BorrowRecord extends AbstractModel {

	private String BorrowID;
	private String rdID;
	private long bkID;
	private int ContinueTimes;
	private String DateOut;
	private String DateShouldRet;
	private String DateActRet;
	private int OverDay;
	private float PunishMoney;
	private int Returned = 0;
	private String OperatorLend;
	private String OperatorRet;
	
	/**
	 * @param borrowID
	 * @param rdID
	 * @param bkID
	 * @param continueTimesl
	 * @param dateOut
	 * @param dateShouldRet
	 * @param dateActRet
	 * @param overDay
	 * @param punishMoney
	 * @param returned
	 * @param operatorLend
	 * @param operatorRet
	 */
	public BorrowRecord(String borrowID, String rdID, long bkID, int continueTimes, String dateOut, String dateShouldRet,
			String dateActRet, int overDay, float punishMoney, int returned, String operatorLend, String operatorRet) {
		super();
		BorrowID = borrowID;
		this.rdID = rdID;
		this.bkID = bkID;
		ContinueTimes = continueTimes;
		DateOut = dateOut;
		DateShouldRet = dateShouldRet;
		DateActRet = dateActRet;
		OverDay = overDay;
		PunishMoney = punishMoney;
		Returned = returned;
		OperatorLend = operatorLend;
		OperatorRet = operatorRet;
	}

	

	public BorrowRecord() {
		// TODO Auto-generated constructor stub
	}



	public String getBorrowID() {
		return BorrowID;
	}

	public void setBorrowID(String borrowID) {
		BorrowID = borrowID;
	}

	public String getRdID() {
		return rdID;
	}

	public void setRdID(String rdID) {
		this.rdID = rdID;
	}

	public long getBkID() {
		return bkID;
	}

	public void setBkID(long bkID) {
		this.bkID = bkID;
	}

	public int getContinueTimes() {
		return ContinueTimes;
	}

	public void setContinueTimes(int continueTimes) {
		ContinueTimes = continueTimes;
	}

	public String getDateOut() {
		return DateOut;
	}

	public void setDateOut(String dateOut) {
		DateOut = dateOut;
	}

	public String getDateShouldRet() {
		return DateShouldRet;
	}

	public void setDateShouldRet(String dateShouldRet) {
		DateShouldRet = dateShouldRet;
	}

	public String getDateActRet() {
		return DateActRet;
	}

	public void setDateActRet(String dateActRet) {
		DateActRet = dateActRet;
	}

	public int getOverDay() {
		return OverDay;
	}

	public void setOverDay(int overDay) {
		OverDay = overDay;
	}

	public float getPunishMoney() {
		return PunishMoney;
	}

	public void setPunishMoney(float punishMoney) {
		PunishMoney = punishMoney;
	}

	public int getReturned() {
		return Returned;
	}

	public void setReturned(int returned) {
		Returned = returned;
	}

	public String getOperatorLend() {
		return OperatorLend;
	}

	public void setOperatorLend(String operatorLend) {
		OperatorLend = operatorLend;
	}

	public String getOperatorRet() {
		return OperatorRet;
	}

	public void setOperatorRet(String operatorRet) {
		OperatorRet = operatorRet;
	}



	@Override
	public String toString() {
		return "BorrowRecord [BorrowID=" + BorrowID + ", rdID=" + rdID + ", bkID=" + bkID + ", ContinueTimes="
				+ ContinueTimes + ", DateOut=" + DateOut + ", DateShouldRet=" + DateShouldRet + ", DateActRet="
				+ DateActRet + ", OverDay=" + OverDay + ", PunishMoney=" + PunishMoney + ", Returned=" + Returned
				+ ", OperatorLend=" + OperatorLend + ", OperatorRet=" + OperatorRet + "]";
	}
	
	

}
