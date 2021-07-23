package com.yangheng.library.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.yangheng.library.bll.LoginHandle;
import com.yangheng.library.configuration.CoreConfiguration;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.Color;

public class LoginWindow {

	public JFrame LoginWindow;
	public JTextField textAccount;
	public JPasswordField textPwd;
	static LoginWindow window;
	JRadioButton rdSQLServer;
	JRadioButton rdMYSQL;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new LoginWindow();
//					UIManager.setLookAndFeel(javax.swing.plaf.nimbus.NimbusLookAndFeel.class.getName());
//					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//					window.LoginWindow.setLocation((dim.width - window.LoginWindow.getWidth()) / 2,
//							(dim.height - window.LoginWindow.getHeight()) / 2);
					window.LoginWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		LoginWindow = new JFrame();
		LoginWindow.setTitle("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF\u767B\u5F55");
		LoginWindow.setResizable(false);
		LoginWindow.setBounds(100, 100, 450, 320);
		LoginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LoginWindow.getContentPane().setLayout(null);

		JLabel ShowMe = new JLabel("\u957F\u6C5F\u5927\u5B66\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF");
		ShowMe.setHorizontalAlignment(SwingConstants.CENTER);
		ShowMe.setFont(new Font("华文仿宋", Font.BOLD, 18));
		ShowMe.setBounds(109, 243, 209, 38);
		LoginWindow.getContentPane().add(ShowMe);

		JLabel label = new JLabel("\u767B\u5F55\u8D26\u53F7");
		label.setToolTipText("\u7BA1\u7406\u5458\u8D26\u53F7\u6216\u8005\u8BFB\u8005\u8D26\u53F7");
		label.setFont(new Font("黑体", Font.PLAIN, 16));
		label.setBounds(76, 61, 77, 21);
		LoginWindow.getContentPane().add(label);

		JLabel label_1 = new JLabel("\u767B\u9646\u5BC6\u7801");
		label_1.setToolTipText("\u6559\u52A1\u7CFB\u7EDF\u5BC6\u7801");
		label_1.setFont(new Font("黑体", Font.PLAIN, 16));
		label_1.setBounds(76, 107, 77, 21);
		LoginWindow.getContentPane().add(label_1);

		textAccount = new JTextField();
		textAccount.setFont(new Font("黑体", Font.PLAIN, 16));
		textAccount.setText("201403644");
		textAccount.setBounds(163, 62, 211, 21);
		LoginWindow.getContentPane().add(textAccount);
		textAccount.setColumns(10);

		textPwd = new JPasswordField();
		textPwd.setFont(new Font("Consolas", Font.PLAIN, 12));
		textPwd.setBounds(163, 107, 211, 21);
		LoginWindow.getContentPane().add(textPwd);

		JButton btnConfirm = new JButton("\u767B\u5F55");
		btnConfirm.setFont(new Font("黑体", Font.PLAIN, 16));
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdSQLServer.isSelected()) {
					CoreConfiguration.DBtype = CoreConfiguration.SQLSERVER;
				} else {
					CoreConfiguration.DBtype = CoreConfiguration.MYSQL;
				}
				LoginHandle loginHandle = new LoginHandle(textAccount.getText(), String.valueOf(textPwd.getPassword()),
						window);
				loginHandle.doHandle();
			}
		});
		btnConfirm.setBounds(109, 210, 93, 23);
		LoginWindow.getContentPane().add(btnConfirm);

		JButton btnCancel = new JButton("\u9000\u51FA");
		btnCancel.setFont(new Font("黑体", Font.PLAIN, 16));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginWindow.dispose();
			}
		});
		btnCancel.setBounds(244, 210, 93, 23);
		LoginWindow.getContentPane().add(btnCancel);

		rdSQLServer = new JRadioButton("\u4F7F\u7528SQLSERVER\u8FDC\u7A0B\u6570\u636E\u5E93\u670D\u52A1\u5668\uFF08\u8054\u7F51\uFF09");
		rdSQLServer.setHorizontalAlignment(SwingConstants.CENTER);
		rdSQLServer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				rdSQLServer.setToolTipText("使用托管在互联网上的Microsoft SQLSERVER数据库服务器，程序速度受网络影响，（两个数据库服务器数据不互通！）");
			}
		});
		rdSQLServer.setSelected(true);
		rdSQLServer.setFont(new Font("黑体", Font.PLAIN, 14));
		rdSQLServer.setBounds(76, 143, 298, 29);
		LoginWindow.getContentPane().add(rdSQLServer);

		rdMYSQL = new JRadioButton("\u4F7F\u7528MySQL\u672C\u5730\u6570\u636E\u5E93\u670D\u52A1\u5668\uFF08\u65E0\u9700\u8054\u7F51\uFF09");
		rdMYSQL.setHorizontalAlignment(SwingConstants.CENTER);
		rdMYSQL.setToolTipText("使用Localhost的MySQL数据库服务器（两个数据库服务器数据不互通！）");
		rdMYSQL.setFont(new Font("黑体", Font.PLAIN, 14));
		rdMYSQL.setBounds(76, 175, 298, 29);
		LoginWindow.getContentPane().add(rdMYSQL);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdSQLServer);
		buttonGroup.add(rdMYSQL);
		
		JLabel label_2 = new JLabel("\u8FDB\u5165\u7F51\u9875\u7AEF");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try {
					Runtime.getRuntime().exec(
							"rundll32 url.dll,FileProtocolHandler http://winky.top");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		label_2.setToolTipText("www.winky.top");
		label_2.setForeground(Color.BLUE);
		label_2.setFont(new Font("黑体", Font.ITALIC, 14));
		label_2.setBounds(351, 256, 83, 15);
		LoginWindow.getContentPane().add(label_2);
	}
}
