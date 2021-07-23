package com.yangheng.library.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import com.yangheng.library.bll.BorrowPanelHandle;
import com.yangheng.library.configuration.UserConfiguration;
import com.yangheng.library.dao.SQLHelper;
import com.yangheng.library.model.Reader;
import java.awt.Font;

/**
 * @author chuan 
 *			MainWindow 通过引用其他Panel绘制软件界面，更好的实现方法是使用CardLayout，我后面搜到的
 *         默认多个布局之中只可以显示一个，可以避免多次生成panel或者重绘，不然程序会卡
 *         而且如果不使用cardlayout最好所有的panel继承同一个父类（public mainPanel extends
 *         JPanel），方便定义统一的panel尺寸和属性 我后面想到的，所以我的没有继承自一个panel父类
 *
 */
public class MainWindow extends JFrame {

	final String backupdatabase = "BACKUP DATABASE library2 TO DISK="
			+ "'C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 8.0\\webapps\\ROOT\\softwareupdate\\Library2.bak'"
			+ " WITH INIT";

	private static final long serialVersionUID = 2244408643214132257L;

	public ReaderPanel readerPanel;
	MainWindow mainWindow;

	public MainWindow() {
		mainWindow = this;
		setTitle("\u56FE\u4E66\u9986\u7BA1\u7406\u7CFB\u7EDF");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu ReaderManage = new JMenu("\u8BFB\u8005\u7BA1\u7406");
		ReaderManage.setFont(new Font("黑体", Font.PLAIN, 18));
		menuBar.add(ReaderManage);

		JMenuItem NewReader = new JMenuItem("\u529E\u7406\u501F\u4E66\u8BC1");
		NewReader.setFont(new Font("黑体", Font.PLAIN, 16));
		NewReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserConfiguration.readerPanel.inAdd();
				setContentPane(UserConfiguration.readerPanel);

			}
		});
		ReaderManage.add(NewReader);

		JMenuItem ReaderInfoUpdate = new JMenuItem("\u501F\u4E66\u8BC1\u4FE1\u606F\u53D8\u66F4");
		ReaderInfoUpdate.setFont(new Font("黑体", Font.PLAIN, 16));
		ReaderInfoUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserConfiguration.readerPanel.inUpdate();
				setContentPane(UserConfiguration.readerPanel);
			}
		});
		ReaderManage.add(ReaderInfoUpdate);

		readerPanel = new ReaderPanel(this, "", "", "");
		readerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(readerPanel);

		JMenuItem LockReader = new JMenuItem("\u501F\u4E66\u8BC1\u6302\u5931");
		LockReader.setFont(new Font("黑体", Font.PLAIN, 16));
		LockReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (readerPanel.RdName.getText() != null) {
					readerPanel.LockReader();
				}

			}
		});
		ReaderManage.add(LockReader);

		JMenuItem UnlockReader = new JMenuItem("\u501F\u4E66\u8BC1\u89E3\u6302");
		UnlockReader.setFont(new Font("黑体", Font.PLAIN, 16));
		UnlockReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (readerPanel.RdName.getText() != null) {
					readerPanel.UnlockReader();
				}
			}
		});
		ReaderManage.add(UnlockReader);

		JMenuItem DisableReader = new JMenuItem("\u6CE8\u9500\u501F\u4E66\u8BC1");
		DisableReader.setFont(new Font("黑体", Font.PLAIN, 16));
		DisableReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (readerPanel.RdName.getText() != null) {
					readerPanel.FreezeReader();
				}
			}
		});
		ReaderManage.add(DisableReader);

		JMenuItem ChangeReaderType = new JMenuItem("\u8BFB\u8005\u7C7B\u578B\u7BA1\u7406");
		ChangeReaderType.setFont(new Font("黑体", Font.PLAIN, 16));
		ReaderManage.add(ChangeReaderType);

		JMenu BookManage = new JMenu("\u56FE\u4E66\u7BA1\u7406");
		BookManage.setFont(new Font("黑体", Font.PLAIN, 18));
		menuBar.add(BookManage);

		JMenuItem NewBook = new JMenuItem("\u65B0\u4E66\u5165\u5E93");
		NewBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setContentPane(new BookInfoPanel(mainWindow, "新书入库", null));
			}
		});
		NewBook.setFont(new Font("黑体", Font.PLAIN, 16));
		BookManage.add(NewBook);

		JMenuItem ChangeBookInfo = new JMenuItem("\u56FE\u4E66\u4FE1\u606F\u7EF4\u62A4");
		ChangeBookInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainWindow.setContentPane(new BookManagePanel(mainWindow));
				mainWindow.getRootPane().invalidate();
				mainWindow.getRootPane().updateUI();
			}
		});
		ChangeBookInfo.setFont(new Font("黑体", Font.PLAIN, 16));
		BookManage.add(ChangeBookInfo);

		JMenu BorrowManage = new JMenu("\u501F\u9605\u7BA1\u7406");
		BorrowManage.setFont(new Font("黑体", Font.PLAIN, 18));
		menuBar.add(BorrowManage);

		JMenuItem Borrow = new JMenuItem("\u501F\u9605\u7BA1\u7406");
		Borrow.setFont(new Font("黑体", Font.PLAIN, 16));
		Borrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (UserConfiguration.borrowPanel == null) {
					new BorrowPanelHandle(mainWindow).PaintBorrowPanel();
				} else {
					mainWindow.setContentPane(UserConfiguration.borrowPanel);
				}
				mainWindow.getRootPane().validate();
			}
		});
		BorrowManage.add(Borrow);

		JMenuItem ReBorrow = new JMenuItem("\u7EED\u501F");
		ReBorrow.setFont(new Font("黑体", Font.PLAIN, 16));
		ReBorrow.setVisible(false);
		BorrowManage.add(ReBorrow);

		JMenuItem Return = new JMenuItem("\u8FD8\u4E66");
		Return.setFont(new Font("黑体", Font.PLAIN, 16));
		Return.setVisible(false);
		BorrowManage.add(Return);

		JMenu SystemManage = new JMenu("\u7CFB\u7EDF\u7BA1\u7406");
		SystemManage.setFont(new Font("黑体", Font.PLAIN, 18));
		menuBar.add(SystemManage);

		JMenuItem PermissionManage = new JMenuItem("\u6743\u9650\u7BA1\u7406");
		PermissionManage.setFont(new Font("黑体", Font.PLAIN, 16));
		PermissionManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.setContentPane(new PermissionPanel(mainWindow));
				mainWindow.getRootPane().validate();
			}
		});
		SystemManage.add(PermissionManage);

		JMenuItem SystemBackup = new JMenuItem("\u6570\u636E\u5907\u4EFD");
		SystemBackup.setFont(new Font("黑体", Font.PLAIN, 16));
		SystemBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (JOptionPane.showConfirmDialog(null, "是否备份数据库?")==0) {
					try {
						SQLHelper.ExecQuery(backupdatabase);
						JOptionPane.showMessageDialog(null, "备份成功");
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "备份失败");
					}
					
				}
				

			}
		});
		SystemManage.add(SystemBackup);
		
		JMenuItem Back = new JMenuItem("\u4E0B\u8F7D\u6570\u636E\u5E93\u5907\u4EFD");
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "是否下载数据库备份") == 0) {
					try {
						Runtime.getRuntime().exec(
								"rundll32 url.dll,FileProtocolHandler http://winky.top/softwareupdate/Library2.bak");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		Back.setFont(new Font("黑体", Font.PLAIN, 16));
		SystemManage.add(Back);

		JMenu PwdManage = new JMenu("\u5BC6\u7801\u7BA1\u7406");
		PwdManage.setFont(new Font("黑体", Font.PLAIN, 18));
		menuBar.add(PwdManage);

		JMenuItem PwdUpdate = new JMenuItem("\u5BC6\u7801\u4FEE\u6539");
		PwdUpdate.setFont(new Font("黑体", Font.PLAIN, 16));
		PwdUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainWindow.setContentPane(new PasswordPanel(mainWindow));
				mainWindow.getRootPane().validate();
			}
		});
		PwdManage.add(PwdUpdate);

		JMenu Help = new JMenu("\u5E2E\u52A9");
		Help.setFont(new Font("黑体", Font.PLAIN, 18));
		menuBar.add(Help);

		JMenuItem About = new JMenuItem("\u5173\u4E8E...");
		About.setFont(new Font("黑体", Font.PLAIN, 16));
		About.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "图书馆管理系统（长江大学2017数据库实训）\n" + "Author yangheng\n\n"+
			"广告位招租");
			}
		});
		Help.add(About);

		JMenuItem Update = new JMenuItem("\u66F4\u65B0\u7A0B\u5E8F\u5230\u6700\u65B0\u7248");
		Update.setFont(new Font("黑体", Font.PLAIN, 16));
		Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "是否下载最新版程序") == 0) {
					try {
						Runtime.getRuntime().exec(
								"rundll32 url.dll,FileProtocolHandler http://winky.top/Library/update.jar");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

			}
		});
		Help.add(Update);

		JMenuItem DownloadProject = new JMenuItem("\u4E0B\u8F7D\u9879\u76EE\u4EE3\u7801");
		DownloadProject.setFont(new Font("黑体", Font.PLAIN, 16));
		DownloadProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "是否下载项目文件") == 0) {
					try {
						Runtime.getRuntime().exec(
								"rundll32 url.dll,FileProtocolHandler http://winky.top/Library/LibraryCS.zip");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		Help.add(DownloadProject);

		if ((((Reader) UserConfiguration.user).getRdAdminRole() & 1) != 0)
			ReaderManage.setEnabled(true);
		else {
			ReaderManage.setEnabled(false);
		}
		if ((((Reader) UserConfiguration.user).getRdAdminRole() & 2) != 0)
			BookManage.setEnabled(true);
		else {
			BookManage.setEnabled(false);
		}
		if ((((Reader) UserConfiguration.user).getRdAdminRole() & 4) != 0)
			BorrowManage.setEnabled(true);
		else {
			BorrowManage.setEnabled(false);
		}
		if ((((Reader) UserConfiguration.user).getRdAdminRole() & 8) != 0)
			SystemManage.setEnabled(true);
		else {
			SystemManage.setEnabled(false);
		}

	}
}
