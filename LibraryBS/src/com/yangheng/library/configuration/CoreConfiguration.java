package com.yangheng.library.configuration;

public class CoreConfiguration {
	
	
	public final static int SQLSERVER  = 0;
	public final static int MYSQL  = 1;

	public static int DBtype= SQLSERVER;
	
	public final static String SQLSERVER_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public final static String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	public final static String SQLSERVER_URL = "jdbc:sqlserver://120.77.36.34:1433;databaseName=Library2;integratedSecurity=false";
	public final static String MYSQL_URL = "jdbc:mysql://localhost:3306/Library2?useSSL=false";
	public final static String SQLSERVER_USER = "LibAdmin";
	public final static String MYSQL_USER = "root";
	public final static String SQLSERVER_PASSWORD = "1079580303aaa";
	public final static String MYSQL_PASSWORD = "1079580303aaa";

}
