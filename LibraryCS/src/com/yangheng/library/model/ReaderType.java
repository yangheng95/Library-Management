package com.yangheng.library.model;

public class ReaderType extends AbstractModel {
	
	private String rdType;
	private String rdTypeName;
	private int rdCanLendSum;
	private int rdCanLendDay;
	private int rdRelendLimit;
	private float rdPunishRate;
	private String rdValidTime;

	public ReaderType(String rdType, String rdTypeName, int rdCanLendSum, int rdCanLendDay, int rdRelendLimit,
			float rdPunishRate, String rdValidTime) {
		super();
		this.rdType = rdType;
		this.rdTypeName = rdTypeName;
		this.rdCanLendSum = rdCanLendSum;
		this.rdCanLendDay = rdCanLendDay;
		this.rdRelendLimit = rdRelendLimit;
		this.rdPunishRate = rdPunishRate;
		this.rdValidTime = rdValidTime;
	}


	public ReaderType() {
		// TODO Auto-generated constructor stub
	}


	public String getRdType() {
		return rdType;
	}

	public void setRdType(String rdType) {
		this.rdType = rdType;
	}

	public String getRdTypeName() {
		return rdTypeName;
	}

	public void setRdTypeName(String rdTypeName) {
		this.rdTypeName = rdTypeName;
	}

	public int getRdCanLendSum() {
		return rdCanLendSum;
	}

	public void setRdCanLendSum(int rdCanLendSum) {
		this.rdCanLendSum = rdCanLendSum;
	}

	public int getRdCanLendDay() {
		return rdCanLendDay;
	}

	public void setRdCanLendDay(int rdCanLendDay) {
		this.rdCanLendDay = rdCanLendDay;
	}

	public int getRdRelendLimit() {
		return rdRelendLimit;
	}

	public void setRdRelendLimit(int rdRelendLimit) {
		this.rdRelendLimit = rdRelendLimit;
	}

	public float getRdPunishRate() {
		return rdPunishRate;
	}

	public void setRdPunishRate(float rdPunishRate) {
		this.rdPunishRate = rdPunishRate;
	}

	public String getRdValidTime() {
		return rdValidTime;
	}

	public void setRdValidTime(String rdValidTime) {
		this.rdValidTime = rdValidTime;
	}
	
	
}
