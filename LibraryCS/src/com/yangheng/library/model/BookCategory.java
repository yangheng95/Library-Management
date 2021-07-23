package com.yangheng.library.model;

public class BookCategory extends AbstractModel {

	private String bkCategory;
	private String CategoryName;
	private int CategoryID;
	public BookCategory() {
		super();
	}
	public BookCategory(String bkCategory, String CategoryName, int CategoryID) {
		super();
		this.bkCategory = bkCategory;
		this.CategoryName = CategoryName;
		this.CategoryID = CategoryID;
	}
	public String getBkCategory() {
		return bkCategory;
	}
	public void setBkCategory(String bkCategory) {
		this.bkCategory = bkCategory;
	}
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String CategoryName) {
		this.CategoryName = CategoryName;
	}
	public int getCategoryID() {
		return CategoryID;
	}
	public void setCategoryID(int CategoryID) {
		this.CategoryID = CategoryID;
	}
	@Override
	public String toString() {
		return "BookCategory [bkCategory=" + bkCategory + ", CategoryName=" + CategoryName + ", CategoryID="
				+ CategoryID + "]";
	}
	

	
	
}
