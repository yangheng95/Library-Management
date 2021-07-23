package com.yangheng.library.model;

public class Book extends AbstractModel {
	
	private String bkName;
	private String bkAuthor;
	private String bkPress;
	private String bkPressDate;
	private String bkISBN;
	private String bkCatageroy;
	private String bkLanguage;
	private int bkPages;
	private float bkPrice;
	private String bkBrief;
	private byte[] bkCover;
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(String bkName, String bkAuthor, String bkPress, String bkPressDate, String bkISBN, String bkCatageroy,
			String bkLanguage, int bkPages, float bkPrice, String bkBrief, byte[] bkCover) {
		super();
		this.bkName = bkName;
		this.bkAuthor = bkAuthor;
		this.bkPress = bkPress;
		this.bkPressDate = bkPressDate;
		this.bkISBN = bkISBN;
		this.bkCatageroy = bkCatageroy;
		this.bkLanguage = bkLanguage;
		this.bkPages = bkPages;
		this.bkPrice = bkPrice;
		this.bkBrief = bkBrief;
		this.bkCover = bkCover;
	}
	
	
	public String getBkName() {
		return bkName;
	}
	public void setBkName(String bkName) {
		this.bkName = bkName;
	}
	public String getBkAuthor() {
		return bkAuthor;
	}
	public void setBkAuthor(String bkAuthor) {
		this.bkAuthor = bkAuthor;
	}
	public String getBkPress() {
		return bkPress;
	}
	public void setBkPress(String bkPress) {
		this.bkPress = bkPress;
	}
	public String getBkPressDate() {
		return bkPressDate;
	}
	public void setBkPressDate(String bkPressDate) {
		this.bkPressDate = bkPressDate;
	}
	public String getBkISBN() {
		return bkISBN;
	}
	public void setBkISBN(String bkISBN) {
		this.bkISBN = bkISBN;
	}
	public String getBkCatageroy() {
		return bkCatageroy;
	}
	public void setBkCatageroy(String bkCatageroy) {
		this.bkCatageroy = bkCatageroy;
	}
	public String getBkLanguage() {
		return bkLanguage;
	}
	public void setBkLanguage(String bkLanguage) {
		this.bkLanguage = bkLanguage;
	}
	public int getBkPages() {
		return bkPages;
	}
	public void setBkPages(int bkPages) {
		this.bkPages = bkPages;
	}
	public float getBkPrice() {
		return bkPrice;
	}
	public void setBkPrice(float bkPrice) {
		this.bkPrice = bkPrice;
	}
	public String getBkBrief() {
		return bkBrief;
	}
	public void setBkBrief(String bkBrief) {
		this.bkBrief = bkBrief;
	}
	public byte[] getBkCover() {
		return bkCover;
	}
	public void setBkCover(byte[] bkCover) {
		this.bkCover = bkCover;
	}
	@Override
	public String toString() {
		return "Book [bkName=" + bkName + ", bkAuthor=" + bkAuthor + ", bkPress=" + bkPress + ", bkPressDate="
				+ bkPressDate + ", bkISBN=" + bkISBN + ", bkCatageroy=" + bkCatageroy + ", bkLanguage=" + bkLanguage
				+ ", bkPages=" + bkPages + ", bkPrice=" + bkPrice + ", bkBrief=" + bkBrief + ", bkCover=" + bkCover
				+ "]";
	}


	
}
