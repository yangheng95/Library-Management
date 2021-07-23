
package com.yangheng.library.dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.yangheng.library.configuration.CoreConfiguration;

public class SQLHelper {

	/**
	 * ������ʵ��������
	 */
	private SQLHelper() {
	}

	/**
	 * ��ȡһ�����ݿ����� ͨ��������� driver / url / user / password ���ĸ���̬������ �������ݿ���������
	 * 
	 * @return ���ݿ�����
	 */
	public static Connection getConnection() {
		try {
			if (CoreConfiguration.DBtype == CoreConfiguration.SQLSERVER) {
				Class.forName(CoreConfiguration.SQLSERVER_DRIVER);
			} else {
				Class.forName(CoreConfiguration.MYSQL_DRIVER);
			}

		} catch (ClassNotFoundException ex) {
			// Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE,
			// null,
			// ex);
		}
		try {
			if (CoreConfiguration.DBtype == CoreConfiguration.SQLSERVER) {
				return DriverManager.getConnection(CoreConfiguration.SQLSERVER_URL, CoreConfiguration.SQLSERVER_USER,
						CoreConfiguration.SQLSERVER_PASSWORD);

			} else {
				return DriverManager.getConnection(CoreConfiguration.MYSQL_URL, CoreConfiguration.MYSQL_USER,
						CoreConfiguration.MYSQL_PASSWORD);
			}

		} catch (SQLException ex) {
			// Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE,
			// null,
			// ex);
			return null;
		}
	}

	/**
	 * ��ȡһ�� Statement �� Statement �Ѿ��������ݼ� ���Թ���,���Ը���
	 * 
	 * @return �����ȡʧ�ܽ����� null,����ʱ�ǵü�鷵��ֵ
	 */
	public static Statement getStatement() {
		Connection conn = getConnection();
		if (conn == null) {
			return null;
		}
		try {
			return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			// �������ݼ����Թ���,���Ը���
		} catch (SQLException ex) {
			Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
			close(conn);
		}
		return null;
	}

	/**
	 * ��ȡһ�� Statement �� Statement �Ѿ��������ݼ� ���Թ���,���Ը���
	 * 
	 * @param conn
	 *            ���ݿ�����
	 * @return �����ȡʧ�ܽ����� null,����ʱ�ǵü�鷵��ֵ
	 */
	public static Statement getStatement(Connection conn) {
		if (conn == null) {
			return null;
		}
		try {
			return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			// �������ݼ����Թ���,���Ը���
		} catch (SQLException ex) {
			Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	/**
	 * ��ȡһ���������� PreparedStatement �� PreparedStatement �Ѿ��������ݼ� ���Թ���,���Ը���
	 * 
	 * @param cmdText
	 *            ��Ҫ ? ������ SQL ���
	 * @param cmdParams
	 *            SQL ���Ĳ�����
	 * @return �����ȡʧ�ܽ����� null,����ʱ�ǵü�鷵��ֵ
	 */
	public static PreparedStatement getPreparedStatement(String cmdText, Object... cmdParams) {
		Connection conn = getConnection();
		if (conn == null) {
			return null;
		}
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(cmdText, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			int i = 1;
			for (Object item : cmdParams) {
				pstmt.setObject(i, item);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			close(conn);
		}
		return pstmt;
	}

	/**
	 * ��ȡһ���������� PreparedStatement �� PreparedStatement �Ѿ��������ݼ� ���Թ���,���Ը���
	 * 
	 * @param conn
	 *            ���ݿ�����
	 * @param cmdText
	 *            ��Ҫ ? ������ SQL ���
	 * @param cmdParams
	 *            SQL ���Ĳ�����
	 * @return �����ȡʧ�ܽ����� null,����ʱ�ǵü�鷵��ֵ
	 */
	public static PreparedStatement getPreparedStatement(Connection conn, String cmdText, Object... cmdParams) {
		if (conn == null) {
			return null;
		}
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(cmdText, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			int i = 1;
			for (Object item : cmdParams) {
				pstmt.setObject(i, item);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			close(pstmt);
		}
		return pstmt;
	}

	/**
	 * ִ�� SQL ���,���ؽ��Ϊ���� ��Ҫ����ִ�зǲ�ѯ���
	 * 
	 * @param cmdText
	 *            SQL ���
	 * @return �Ǹ���:����ִ��; -1:ִ�д���; -2:���Ӵ���
	 */
	public static int ExecQuery(String cmdText) {
		Statement stmt = getStatement();
		if (stmt == null) {
			return -2;
		}
		int i;
		try {
			i = stmt.executeUpdate(cmdText);
		} catch (SQLException ex) {
			Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
			i = -1;
		}
		closeConnection(stmt);
		return i;
	}

	/**
	 * ִ�� SQL ���,���ؽ��Ϊ���� ��Ҫ����ִ�зǲ�ѯ���
	 * 
	 * @param cmdText
	 *            SQL ���
	 * @return �Ǹ���:����ִ��; -1:ִ�д���; -2:���Ӵ���
	 */
	public static int ExecQuery(Connection conn, String cmdText) {
		Statement stmt = getStatement(conn);
		if (stmt == null) {
			return -2;
		}
		int i;
		try {
			i = stmt.executeUpdate(cmdText);
		} catch (SQLException ex) {
			Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
			i = -1;
		}
		close(stmt);
		return i;
	}

	/**
	 * ִ�� SQL ���,���ؽ��Ϊ���� ��Ҫ����ִ�зǲ�ѯ���
	 * 
	 * @param cmdText
	 *            ��Ҫ ? ������ SQL ���
	 * @param cmdParams
	 *            SQL ���Ĳ�����
	 * @return �Ǹ���:����ִ��; -1:ִ�д���; -2:���Ӵ���
	 */
	public static int ExecQuery(String cmdText, Object... cmdParams) {
		PreparedStatement pstmt = getPreparedStatement(cmdText, cmdParams);
		if (pstmt == null) {
			return -2;
		}
		int i;
		try {
			i = pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
			i = -1;
		}
		closeConnection(pstmt);
		return i;
	}

	/**
	 * ִ�� SQL ���,���ؽ��Ϊ���� ��Ҫ����ִ�зǲ�ѯ���
	 * 
	 * @param conn
	 *            ���ݿ�����
	 * @param cmdText
	 *            ��Ҫ ? ������ SQL ���
	 * @param cmdParams
	 *            SQL ���Ĳ�����
	 * @return �Ǹ���:����ִ��; -1:ִ�д���; -2:���Ӵ���
	 */
	public static int ExecSql(Connection conn, String cmdText, Object... cmdParams) {
		PreparedStatement pstmt = getPreparedStatement(conn, cmdText, cmdParams);
		if (pstmt == null) {
			return -2;
		}
		int i;
		try {
			i = pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
			i = -1;
		}
		close(pstmt);
		return i;
	}

	/**
	 * ���ؽ�����ĵ�һ�е�һ�е�ֵ,��������
	 * 
	 * @param cmdText
	 *            SQL ���
	 * @return
	 */
	public static Object ExecScalar(String cmdText) {
		ResultSet rs = getResultSet(cmdText);
		Object obj = buildScalar(rs);
		closeConnection(rs);
		return obj;
	}

	/**
	 * ���ؽ�����ĵ�һ�е�һ�е�ֵ,��������
	 * 
	 * @param conn
	 *            ���ݿ�����
	 * @param cmdText
	 *            SQL ���
	 * @return
	 */
	public static Object ExecScalar(Connection conn, String cmdText) {
		ResultSet rs = getResultSet(conn, cmdText);
		Object obj = buildScalar(rs);
		closeEx(rs);
		return obj;
	}

	/**
	 * ����һ�� ResultSet
	 * 
	 * @param cmdText
	 *            SQL ���
	 * @return
	 */
	public static ResultSet getResultSet(String cmdText) {
		Statement stmt = getStatement();
		if (stmt == null) {
			return null;
		}
		try {
			return stmt.executeQuery(cmdText);
		} catch (SQLException ex) {
			Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
			closeConnection(stmt);
		}
		return null;
	}

	/**
	 * ����һ�� ResultSet
	 * 
	 * @param conn
	 * @param cmdText
	 *            SQL ���
	 * @return
	 */
	public static ResultSet getResultSet(Connection conn, String cmdText) {
		Statement stmt = getStatement(conn);
		if (stmt == null) {
			return null;
		}
		try {
			return stmt.executeQuery(cmdText);
		} catch (SQLException ex) {
			Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
			close(stmt);
		}
		return null;
	}

	/**
	 * ����һ�� ResultSet
	 * 
	 * @param cmdText
	 *            ��Ҫ ? ������ SQL ���
	 * @param cmdParams
	 *            SQL ���Ĳ�����
	 * @return
	 */
	public static ResultSet getResultSet(String cmdText, Object... cmdParams) {
		PreparedStatement pstmt = getPreparedStatement(cmdText, cmdParams);
		if (pstmt == null) {
			return null;
		}
		try {
			return pstmt.executeQuery();
		} catch (SQLException ex) {
			Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
			closeConnection(pstmt);
		}
		return null;
	}

	/**
	 * ����һ�� ResultSet
	 * 
	 * @param conn
	 *            ���ݿ�����
	 * @param cmdText
	 *            ��Ҫ ? ������ SQL ���
	 * @param cmdParams
	 *            SQL ���Ĳ�����
	 * @return
	 */
	public static ResultSet getResultSet(Connection conn, String cmdText, Object... cmdParams) {
		PreparedStatement pstmt = getPreparedStatement(conn, cmdText, cmdParams);
		if (pstmt == null) {
			return null;
		}
		try {
			return pstmt.executeQuery();
		} catch (SQLException ex) {
			Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
			close(pstmt);
		}
		return null;
	}

	public static Object buildScalar(ResultSet rs) {
		if (rs == null) {
			return null;
		}
		Object obj = null;
		try {
			if (rs.next()) {
				obj = rs.getObject(1);
			}
		} catch (SQLException ex) {
			Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
		}
		return obj;
	}

	/**
	 * ��ȡһ�����и��¹��ܵ�����ģ�� ���ֻҪ��ȡ���ݣ��Ͳ�Ҫ������
	 * 
	 * @param cmdText
	 *            �ܷ���һ�����ݼ��Ĳ�ѯSQL ���
	 * @return �������ģ��
	 * 
	 * 
	 *         DataSet û���ҵ����ĸ�����,��Ϊ��ʱ�ò�������ʡ�Դ˷���
	 * 
	 *         public static DataSet getDataSet(String cmdText) { Statement stmt
	 *         = getStatement(); DataSet dbc = new DataSet(); if (stmt == null)
	 *         { dbc.code = -2; return dbc; } try { // ��ѯ��� dbc.rs =
	 *         stmt.executeQuery(cmdText); dbc.model = buildTableModel(dbc.rs);
	 *         dbc.code = dbc.model.getRowCount(); } catch (SQLException ex) {
	 *         Logger.getLogger(SQLHelper1.class.getName()).log(Level.SEVERE,
	 *         null, ex); dbc.code = -1; } return dbc; }
	 */

	/**
	 * ��ȡһ�����и��¹��ܵ�����ģ�� ���ֻҪ��ȡ���ݣ��Ͳ�Ҫ������
	 * 
	 * @param conn
	 *            ���ݿ�����
	 * @param cmdText
	 *            �ܷ���һ�����ݼ��Ĳ�ѯSQL ���
	 * @return �������ģ��
	 * 
	 *         ͬ��һ������
	 * 
	 *         public static DataSet getDataSet(Connection conn, String cmdText)
	 *         { Statement stmt = getStatement(conn); DataSet dbc = new
	 *         DataSet(); if (stmt == null) { dbc.code = -2; return dbc; } try {
	 *         // ��ѯ��� dbc.rs = stmt.executeQuery(cmdText); dbc.model =
	 *         buildTableModel(dbc.rs); dbc.code = dbc.model.getRowCount(); }
	 *         catch (SQLException ex) {
	 *         Logger.getLogger(SQLHelper1.class.getName()).log(Level.SEVERE,
	 *         null, ex); dbc.code = -1; } return dbc; }
	 */

	/**
	 * ��ȡһ�����и��¹��ܵ�����ģ�� ���ֻҪ��ȡ���ݣ��Ͳ�Ҫ������
	 * 
	 * @param cmdText
	 *            ��Ҫ ? ������ SQL ���
	 * @param cmdParams
	 *            SQL ���Ĳ�����
	 * @return �������ģ��
	 * 
	 * 
	 *         ͬ��һ������ *
	 * 
	 * 
	 *         public static DataSet getDataSet(String cmdText, Object...
	 *         cmdParams) { PreparedStatement pstmt =
	 *         getPreparedStatement(cmdText, cmdParams); DataSet dbc = new
	 *         DataSet(); if (pstmt == null) { dbc.code = -2; return dbc; } try
	 *         { // ��ѯ��� dbc.rs = pstmt.executeQuery(); dbc.model =
	 *         buildTableModel(dbc.rs); dbc.code = dbc.model.getRowCount(); }
	 *         catch (SQLException ex) {
	 *         Logger.getLogger(SQLHelper1.class.getName()).log(Level.SEVERE,
	 *         null, ex); dbc.code = -1; } return dbc; }
	 */
	/**
	 * ��ȡһ�����и��¹��ܵ�����ģ�� ���ֻҪ��ȡ���ݣ��Ͳ�Ҫ������
	 * 
	 * @param conn
	 *            ���ݿ�����
	 * @param cmdText
	 *            ��Ҫ ? ������ SQL ���
	 * @param cmdParams
	 *            SQL ���Ĳ�����
	 * @return �������ģ��
	 * 
	 * 
	 *         ͬ��
	 * 
	 * 
	 *         public static DataSet getDataSet(Connection conn, String cmdText,
	 *         Object... cmdParams) { PreparedStatement pstmt =
	 *         getPreparedStatement(conn, cmdText, cmdParams); DataSet dbc = new
	 *         DataSet(); if (pstmt == null) { dbc.code = -2; return dbc; } try
	 *         { // ��ѯ��� dbc.rs = pstmt.executeQuery(); dbc.model =
	 *         buildTableModel(dbc.rs); dbc.code = dbc.model.getRowCount(); }
	 *         catch (SQLException ex) {
	 *         Logger.getLogger(SQLHelper1.class.getName()).log(Level.SEVERE,
	 *         null, ex); dbc.code = -1; } return dbc; }
	 */
	private static void close(Object obj) {
		if (obj == null) {
			return;
		}
		try {
			if (obj instanceof Statement) {
				((Statement) obj).close();
			} else if (obj instanceof PreparedStatement) {
				((PreparedStatement) obj).close();
			} else if (obj instanceof ResultSet) {
				((ResultSet) obj).close();
			} else if (obj instanceof Connection) {
				((Connection) obj).close();
			}
		} catch (SQLException ex) {
			Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private static void closeEx(Object obj) {
		if (obj == null) {
			return;
		}
		try {
			if (obj instanceof Statement) {
				((Statement) obj).close();
			} else if (obj instanceof PreparedStatement) {
				((PreparedStatement) obj).close();
			} else if (obj instanceof ResultSet) {
				((ResultSet) obj).getStatement().close();
			} else if (obj instanceof Connection) {
				((Connection) obj).close();
			}
		} catch (SQLException ex) {
			Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private static void closeConnection(Object obj) {
		if (obj == null) {
			return;
		}
		try {
			if (obj instanceof Statement) {
				((Statement) obj).getConnection().close();
			} else if (obj instanceof PreparedStatement) {
				((PreparedStatement) obj).getConnection().close();
			} else if (obj instanceof ResultSet) {
				((ResultSet) obj).getStatement().getConnection().close();
			} else if (obj instanceof Connection) {
				((Connection) obj).close();
			}
		} catch (SQLException ex) {
			Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	final static int BUFFER_SIZE = 4096;

	// ��InputStreamת����byte����
	public static byte[] InputStreamTOByte(InputStream in) throws IOException {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		byte[] data = new byte[BUFFER_SIZE];

		int count = -1;

		while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)

			outStream.write(data, 0, count);

		data = null;

		return outStream.toByteArray();

	}

	// ��byte����ת����InputStream
	public static InputStream byteTOInputStream(byte[] in) throws Exception {

		ByteArrayInputStream is = new ByteArrayInputStream(in);

		return is;

	}

}
