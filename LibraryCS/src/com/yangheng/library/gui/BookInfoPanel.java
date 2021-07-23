package com.yangheng.library.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import com.yangheng.library.configuration.UserConfiguration;
import com.yangheng.library.dao.BookCategoryDAO;
import com.yangheng.library.dao.BookDAO;
import com.yangheng.library.dao.BookLibDAO;
import com.yangheng.library.dao.SQLHelper;
import com.yangheng.library.model.AbstractModel;
import com.yangheng.library.model.Book;
import com.yangheng.library.model.BookCategory;
import com.yangheng.library.model.BookLib;


public class BookInfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int width = 327;
	private static final int height = 466;

	private JTextField textBkID;
	private JTextField textBkName;
	private JTextField textBkAuthor;
	private JTextField textBkPress;
	private JTextField textBkPressDate;
	private JTextField textBkISBN;
	private JTextField textBkLanguage;
	private JTextField textBkPages;
	private JTextField textBkPrice;
	private JTextField textCollectDate;
	private JTextField textSameBkSum;

	JComboBox<String> comBkCategory;
	JComboBox<String> comBkCataName;
	JTextArea textBrief;
	JLabel lblBkPhoto;
	JButton btnConfirm;
	byte[] bkPhoto = new byte[10240];
	String ISBN;

	BookLibDAO bookLibDAO = new BookLibDAO();
	BookDAO bookDAO = new BookDAO();
	BookCategoryDAO bookCategoryDAO = new BookCategoryDAO();

	JLabel lblTitle;
	MainWindow mainWindow;
	private JLabel lblUploadCover;
	private JPanel panel;
	private JTextField textInput;


	public BookInfoPanel(MainWindow mainWindow, String title, BookLib bookLib) {
		
		

		this.mainWindow = mainWindow;
		setLayout(null);

		lblTitle = new JLabel("\u65B0\u4E66\u5165\u5E93");
		lblTitle.setFont(new Font("黑体", Font.PLAIN, 26));
		lblTitle.setBounds(33, 14, 210, 40);
		add(lblTitle);

		lblTitle.setText(title);

		JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u7F16\u53F7");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 18));
		lblNewLabel.setBounds(32, 76, 90, 32);
		add(lblNewLabel);

		JLabel label_2 = new JLabel("\u4E66\u7C4D\u540D\u79F0");
		label_2.setFont(new Font("黑体", Font.PLAIN, 18));
		label_2.setBounds(33, 111, 90, 32);
		add(label_2);

		lblBkPhoto = new JLabel("");
		lblBkPhoto.setBounds(838, 76, 356, 512);
		lblBkPhoto.setBorder(BorderFactory.createTitledBorder("图书封面"));
		add(lblBkPhoto);


		JLabel label_3 = new JLabel("\u4E66\u7C4D\u4F5C\u8005");
		label_3.setFont(new Font("黑体", Font.PLAIN, 18));
		label_3.setBounds(33, 152, 92, 32);
		add(label_3);

		JLabel label_4 = new JLabel("\u51FA\u7248\u793E");
		label_4.setFont(new Font("黑体", Font.PLAIN, 18));
		label_4.setBounds(32, 191, 90, 32);
		add(label_4);

		JLabel label_5 = new JLabel("\u51FA\u7248\u65E5\u671F");
		label_5.setFont(new Font("黑体", Font.PLAIN, 18));
		label_5.setBounds(33, 232, 92, 32);
		add(label_5);

		JLabel lblisbn = new JLabel("\u56FE\u4E66ISBN");
		lblisbn.setFont(new Font("黑体", Font.PLAIN, 18));
		lblisbn.setBounds(33, 274, 92, 29);
		add(lblisbn);

		JLabel label_8 = new JLabel("\u5206\u7C7B\u540D");
		label_8.setFont(new Font("黑体", Font.PLAIN, 18));
		label_8.setBounds(33, 315, 92, 32);
		add(label_8);

		JLabel label_9 = new JLabel("\u5206\u7C7B\u53F7");
		label_9.setFont(new Font("黑体", Font.PLAIN, 18));
		label_9.setBounds(33, 355, 92, 32);
		add(label_9);

		JLabel label_11 = new JLabel("\u8BED\u8A00");
		label_11.setFont(new Font("黑体", Font.PLAIN, 18));
		label_11.setBounds(33, 395, 92, 32);
		add(label_11);

		JLabel label_12 = new JLabel("\u9875\u6570");
		label_12.setFont(new Font("黑体", Font.PLAIN, 18));
		label_12.setBounds(33, 435, 92, 32);
		add(label_12);

		JLabel label_6 = new JLabel("\u56FE\u4E66\u4EF7\u683C");
		label_6.setFont(new Font("黑体", Font.PLAIN, 18));
		label_6.setBounds(33, 475, 92, 32);
		add(label_6);

		JLabel label_13 = new JLabel("\u5165\u9986\u65E5\u671F");
		label_13.setFont(new Font("黑体", Font.PLAIN, 18));
		label_13.setBounds(33, 515, 92, 32);
		add(label_13);

		JLabel label_14 = new JLabel("\u5E93\u5B58");
		label_14.setFont(new Font("黑体", Font.PLAIN, 18));
		label_14.setBounds(33, 556, 92, 32);
		add(label_14);

		textBkID = new JTextField();
		textBkID.setText("\u7CFB\u7EDF\u81EA\u52A8\u5206\u914D");
		textBkID.setEnabled(false);
		textBkID.setFont(new Font("黑体", Font.PLAIN, 16));
		textBkID.setBounds(132, 76, 210, 32);
		add(textBkID);
		textBkID.setColumns(10);

		textBrief = new JTextArea();
		textBrief.setForeground(Color.BLACK);
		textBrief.setLineWrap(true);
		textBrief.setBackground(SystemColor.menu);
		textBrief.setBounds(413, 76, 356, 512);
		textBrief.setBackground(new Color(240, 240, 240));
		textBrief.setBorder(BorderFactory.createTitledBorder("图书简介"));
		add(textBrief);
		textBrief.setFont(new Font("黑体", Font.PLAIN, 16));

		textBkName = new JTextField();
		textBkName.setFont(new Font("黑体", Font.PLAIN, 16));
		textBkName.setColumns(10);
		textBkName.setBounds(132, 113, 210, 32);
		add(textBkName);

		textBkAuthor = new JTextField();
		textBkAuthor.setFont(new Font("黑体", Font.PLAIN, 16));
		textBkAuthor.setColumns(10);
		textBkAuthor.setBounds(132, 151, 210, 32);
		add(textBkAuthor);

		textBkPress = new JTextField();
		textBkPress.setFont(new Font("黑体", Font.PLAIN, 16));
		textBkPress.setColumns(10);
		textBkPress.setBounds(132, 191, 210, 32);
		add(textBkPress);

		textBkPressDate = new JTextField();
		textBkPressDate.setFont(new Font("黑体", Font.PLAIN, 16));
		textBkPressDate.setColumns(10);
		textBkPressDate.setBounds(132, 232, 210, 32);
		add(textBkPressDate);

		textBkISBN = new JTextField();
		textBkISBN.setFont(new Font("黑体", Font.PLAIN, 16));
		textBkISBN.setColumns(10);
		textBkISBN.setBounds(132, 274, 210, 32);
		add(textBkISBN);

		textBkLanguage = new JTextField();
		textBkLanguage.setFont(new Font("黑体", Font.PLAIN, 16));
		textBkLanguage.setColumns(10);
		textBkLanguage.setBounds(132, 395, 210, 32);
		add(textBkLanguage);

		textBkPages = new JTextField();
		textBkPages.setFont(new Font("黑体", Font.PLAIN, 16));
		textBkPages.setColumns(10);
		textBkPages.setBounds(132, 435, 210, 32);
		add(textBkPages);

		textBkPrice = new JTextField();
		textBkPrice.setFont(new Font("黑体", Font.PLAIN, 16));
		textBkPrice.setColumns(10);
		textBkPrice.setBounds(132, 475, 210, 32);
		add(textBkPrice);

		textCollectDate = new JTextField();
		textCollectDate.setEnabled(false);
		textCollectDate.setFont(new Font("黑体", Font.PLAIN, 16));
		textCollectDate.setColumns(10);
		textCollectDate.setBounds(132, 515, 210, 32);
		add(textCollectDate);

		textSameBkSum = new JTextField();
		textSameBkSum.setEnabled(false);
		textSameBkSum.setFont(new Font("黑体", Font.PLAIN, 16));
		textSameBkSum.setColumns(10);
		textSameBkSum.setBounds(132, 556, 210, 32);
		add(textSameBkSum);

		JSeparator separator = new JSeparator();
		separator.setBounds(33, 64, 1179, 2);
		add(separator);

		btnConfirm = new JButton("\u4FEE\u6539\u56FE\u4E66\u4FE1\u606F");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (lblTitle.getText().equals("新书入库")) {
					doUpload();
					// textBkID.set((BookLib)bookLibDAO.getObjectByID(textBkISBN.getText())).getBkID();
				} else {
					doUpdate();
				}

			}
		});
		btnConfirm.setFont(new Font("黑体", Font.PLAIN, 16));
		btnConfirm.setBounds(437, 603, 148, 32);
		add(btnConfirm);

		JButton btnCancel = new JButton("\u8FD4\u56DE\u4E0A\u4E00\u5C42");
		btnCancel.setFont(new Font("黑体", Font.PLAIN, 16));
		btnCancel.setBounds(609, 603, 148, 32);
		add(btnCancel);

		comBkCategory = new JComboBox<String>();
		comBkCategory.setFont(new Font("黑体", Font.PLAIN, 16));
		comBkCategory.setBounds(132, 313, 210, 32);
		add(comBkCategory);

		comBkCataName = new JComboBox<String>();
		comBkCataName.setFont(new Font("黑体", Font.PLAIN, 16));
		comBkCataName.setBounds(132, 353, 210, 32);
		add(comBkCataName);
		
		panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(838, 598, 356, 40);
		add(panel);
		panel.setLayout(null);
		
		lblUploadCover = new JLabel("\u9F20\u6807\u79FB\u52A8\u5230\u6B64\u533A\u57DF\u4E0A\u4F20\u5C01\u9762");
		lblUploadCover.setBounds(0, 0, 356, 40);
		panel.add(lblUploadCover);
		lblUploadCover.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				JFileChooser fileChooser = new JFileChooser("上传书籍封面");
				if (fileChooser.showDialog(null, "上传")==JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						FileInputStream fis = new FileInputStream(file);
						bkPhoto = SQLHelper.InputStreamTOByte(fis);
						ImageIcon icon = new ImageIcon(bkPhoto);
						icon.setImage(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
						lblBkPhoto.setIcon(icon);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		});
		lblUploadCover.setFont(new Font("微软雅黑", Font.BOLD, 19));
		lblUploadCover.setHorizontalAlignment(SwingConstants.CENTER);
		
		textInput = new JTextField();
		textInput.setText("9787302444541");
		textInput.setFont(new Font("黑体", Font.PLAIN, 16));
		textInput.setColumns(10);
		textInput.setBounds(812, 14, 210, 32);
		add(textInput);
		
		JButton SearchISBN = new JButton("\u68C0\u7D22\u4E66\u7C4D");
		SearchISBN.setToolTipText("\u6839\u636E\u4E66\u7C4DISBN\u641C\u7D22\u4E66\u7C4D");
		SearchISBN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					initRawPanel(textInput.getText());
				} catch (Exception e) {
					
				}
			}
		});
		SearchISBN.setFont(new Font("黑体", Font.PLAIN, 16));
		SearchISBN.setBounds(1046, 14, 148, 32);
		add(SearchISBN);

		if (null != bookLib) {
			init(bookLib);
		} else {
			//initRawPanel("9787302147510");
			//initRawPanel("9787302444541");
			
		}

	}

	@SuppressWarnings("deprecation")
	protected void doUpload() {
		BookLib bookLib = new BookLib();
		Book book = new Book();
		// bookLib.setBkID(Long.parseLong(textBkID.getText()));
		bookLib.setBkCatagegroy(comBkCategory.getSelectedItem().toString());
		bookLib.setBkName(textBkName.getText());
		bookLib.setBkISBN(textBkISBN.getText());

		bookLib.setCollectDate(new Date().toLocaleString());
		bookLib.setBkStatus("在馆");
		book.setBkAuthor(textBkAuthor.getText());
		book.setBkBrief(textBrief.getText());
		book.setBkISBN(textBkISBN.getText());
		book.setBkCatageroy(comBkCategory.getSelectedItem().toString());
		book.setBkCover(bkPhoto);
		book.setBkLanguage(textBkLanguage.getText());
		book.setBkPages(Integer.parseInt(textBkPages.getText()));
		book.setBkPress(textBkPress.getText());
		book.setBkPressDate(textBkPressDate.getText());
		book.setBkPrice(Float.parseFloat(textBkPrice.getText()));
		book.setBkName(textBkName.getText());
		try {
			//System.out.println(book);
			if (bookDAO.getObjectByID(textBkISBN.getText())==null) {
				bookDAO.add(book);
			}
			if (bookLibDAO.add(bookLib))
				JOptionPane.showMessageDialog(null, "插入成功");
			bookLib = (BookLib) bookLibDAO.getObjectByID(bookLib.getBkISBN());
			mainWindow.setContentPane(new BookInfoPanel(mainWindow, "新书入库", bookLib));
		} catch (Exception e) {

		}

	}

	public void doUpdate() {
		BookLib bookLib = new BookLib();
		Book book = new Book();
		bookLib.setBkID(Long.parseLong(textBkID.getText()));
		bookLib.setBkCatagegroy(comBkCategory.getSelectedItem().toString());
		bookLib.setBkName(textBkName.getText());
		bookLib.setBkISBN(textBkISBN.getText());
		bookLib.setCollectDate(textCollectDate.toString());
		book.setBkAuthor(textBkAuthor.getText());
		book.setBkBrief(textBrief.getText());
		book.setBkISBN(textBkISBN.getText());
		book.setBkCatageroy(comBkCategory.getSelectedItem().toString());
		book.setBkCover(bkPhoto);
		book.setBkLanguage(textBkLanguage.getText());
		book.setBkPages(Integer.parseInt(textBkPages.getText()));
		book.setBkPress(textBkPress.getText());
		book.setBkPressDate(textBkPressDate.getText());
		book.setBkPrice(Float.parseFloat(textBkPrice.getText()));
		book.setBkName(textBkName.getText());
		try {
			if (bookDAO.update(book) && bookLibDAO.update(bookLib))
				JOptionPane.showMessageDialog(null, "修改成功");
			mainWindow.setContentPane(new BookInfoPanel(mainWindow, "图书信息维护", bookLib));
		} catch (Exception e) {

		}

	}

	@SuppressWarnings("deprecation")
	public void initRawPanel(String ISBN) {

		btnConfirm.setText("加入书库");
		textCollectDate.setText(new Date().toLocaleString());
		ArrayList<AbstractModel> bookCategories = bookCategoryDAO.getAllObjects();
		for (int i = 0; i < bookCategories.size(); i++) {
			comBkCataName.addItem(((BookCategory) bookCategories.get(i)).getCategoryName());
			comBkCategory.addItem(((BookCategory) bookCategories.get(i)).getBkCategory());
		}
		if (!ISBN.equals(null)) {

			try {
				String content = UserConfiguration.getRemoteFile("https://api.douban.com/v2/book/isbn/:" + ISBN);
				textBkID.setEnabled(false);
				textCollectDate.setEnabled(false);
				textBkName.setText(UserConfiguration.getContentByTag(content, "bkName"));
				textBkISBN.setText(UserConfiguration.getContentByTag(content, "bkISBN"));
				textBkLanguage.setText(UserConfiguration.getContentByTag(content, "language"));
				textBkLanguage.setText("中文");
				textBkAuthor.setText(UserConfiguration.getContentByTag(content, "bkAuthor"));
				textBkPages.setText(UserConfiguration.getContentByTag(content, "bkPage"));
				textBkPress.setText(UserConfiguration.getContentByTag(content, "bkPress"));
				textBkPressDate.setText(UserConfiguration.getContentByTag(content, "bkPressDate"));
				textBkPrice.setText(UserConfiguration.getContentByTag(content, "bkPrice"));
				textBrief.setText(UserConfiguration.getContentByTag(content, "bkBrief"));
				
				String remoteImg = UserConfiguration.getContentByTag(content, "bkPhoto");
		
				remoteImg = remoteImg.replace("https", "http").replace("\\", "/");
				
				Image image = ImageIO.read(new URL(remoteImg));
				ImageIcon icon = new ImageIcon(bkPhoto);
				icon.setImage(image.getScaledInstance(width, height, Image.SCALE_DEFAULT));
				lblBkPhoto.setIcon(icon);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,"找不到该书目信息，尝试更换ISBN检索\n"
						+ "异常："+e.fillInStackTrace());
			}
			String sql = "select count(bkisbn) as bksum from bookLib where bkisbn ='" + textBkISBN.getText() + "'";
			ResultSet resultSet = SQLHelper.getResultSet(sql);
			int sum = 0;
			try {
				if (resultSet.next()) {
					sum = resultSet.getInt(1);
					textSameBkSum.setText(String.valueOf(sum));
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
				textSameBkSum.setText("查询失败");
			}
		}
	}

	public void init(BookLib bookLib) {

		ArrayList<AbstractModel> bookCategories = bookCategoryDAO.getAllObjects();
		for (int i = 0; i < bookCategories.size(); i++) {
			comBkCataName.addItem(((BookCategory) bookCategories.get(i)).getCategoryName());
			comBkCategory.addItem(((BookCategory) bookCategories.get(i)).getBkCategory());
		}
		BookCategory bookCategory = (BookCategory) bookCategoryDAO.getObjectByID(bookLib.getBkCatagegroy());
		Book book = (Book) bookDAO.getObjectByID(bookLib.getBkISBN());
		textBkID.setEnabled(false);
		textCollectDate.setEnabled(false);
		textBkID.setText(String.valueOf(bookLib.getBkID()));
		textBkName.setText(bookLib.getBkName());
		textBkISBN.setText(bookLib.getBkISBN());
		textBkLanguage.setText(book.getBkLanguage());
		textBkAuthor.setText(book.getBkAuthor());
		textBkPages.setText(String.valueOf(book.getBkPages()));
		textBkPress.setText(book.getBkPress());
		textBkPressDate.setText(book.getBkPressDate());
		textBkPrice.setText(String.valueOf(book.getBkPrice()));
		textBrief.setText(book.getBkBrief());
		comBkCategory.setSelectedItem(bookLib.getBkCatagegroy());
		comBkCataName.setSelectedItem(bookCategory.getCategoryName());
		textCollectDate.setText(bookLib.getCollectDate());
		String sql = "select count(bkname) as bksum from bookLib where bkname ='" + bookLib.getBkName() + "'";
		ResultSet resultSet = SQLHelper.getResultSet(sql);
		int sum = 0;
		try {
			if (resultSet.next()) {
				sum = resultSet.getInt(1);
				textSameBkSum.setText(String.valueOf(sum));
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
			textSameBkSum.setText("查询失败");
		}

		try {
			bkPhoto = book.getBkCover();
			ImageIcon icon = new ImageIcon(bkPhoto);
			icon.setImage(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
			lblBkPhoto.setIcon(icon);
		} catch (Exception e) {

		}

	}
}
