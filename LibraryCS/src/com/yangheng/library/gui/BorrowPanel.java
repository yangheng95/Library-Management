package com.yangheng.library.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.yangheng.library.bll.BorrowPanelHandle;
import com.yangheng.library.configuration.UserConfiguration;
import com.yangheng.library.dao.BookCategoryDAO;
import com.yangheng.library.dao.BookDAO;
import com.yangheng.library.dao.BookLibDAO;
import com.yangheng.library.dao.BorrowRecordDAO;
import com.yangheng.library.dao.ReaderDAO;
import com.yangheng.library.dao.ReaderTypeDAO;
import com.yangheng.library.model.AbstractModel;
import com.yangheng.library.model.Book;
import com.yangheng.library.model.BookCategory;
import com.yangheng.library.model.BookLib;
import com.yangheng.library.model.BorrowRecord;
import com.yangheng.library.model.Reader;
import com.yangheng.library.model.ReaderType;

public class BorrowPanel extends JPanel {
	/**
	 * 控件都是类变量，不是构造方法里的局部变量
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textRdRegDate;
	private JTextField textRdEmail;
	private JTextField textRdPhone;
	private JTextField textRdName;
	private JTextField textRdID;
	private JTextField textRdDept;
	private JTextField textRdSex;
	private JTextField textRdType;
	private JTextField textInput;
	byte[] readerPhoto;
	JLabel lblRdStatus;
	JLabel lblRdHaveBorrowed;
	JLabel lblRdCanLendSum;
	JLabel lblRdLeftBorrowNum;
	JPanel panelBorrowedBooks;
	JButton searchBook;
	JPanel panelSearched = new JPanel();
	JScrollPane scrollPane;
	JCheckBox showReturned;
	private JScrollPane scrollPane_1;
	private JTextField textSerachReader;
	JPanel panel;
	MainWindow mainWindow;

	Reader reader = (Reader) UserConfiguration.Reader;
	byte[] rdPhoto;

	// 数据库操作类
	BorrowRecordDAO borrowRecordDAO = new BorrowRecordDAO();
	BookLibDAO bookLibDAO = new BookLibDAO();
	BookDAO bookDAO = new BookDAO();
	ReaderTypeDAO readerTypeDAO = new ReaderTypeDAO();
	BookCategoryDAO bookCategoryDAO = new BookCategoryDAO();
	ReaderDAO readerDAO = new ReaderDAO();

	// BLL层类对象，用来处理借还书挂失什么操作的
	BorrowPanelHandle borrowPanelHandle;

	ReaderType readerType;
	private JLabel lblRdPhoto;

	/**
	 * Create the panel.
	 */
	public BorrowPanel(MainWindow mainWindow) {

		readerType = (ReaderType) readerTypeDAO.getObjectByID(reader.getRdType());
		this.mainWindow = mainWindow;
		borrowPanelHandle = new BorrowPanelHandle(mainWindow);
		UserConfiguration.borrowPanel = this;
		setLayout(null);
		setFont(new Font("黑体", Font.ITALIC, 36));

		showReturned = new JCheckBox("\u663E\u793A\u5DF2\u8FD8\u56FE\u4E66");
		showReturned.setSelected(UserConfiguration.showReturnedBooks);
		showReturned.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				UserConfiguration.showReturnedBooks = showReturned.isSelected();
				getBorrowedBooks();// 重新搜索已借图书
				borrowPanelHandle.PaintBorrowPanel();// 重新绘制已借图书panel
			}
		});

		showReturned.setFont(new Font("黑体", Font.PLAIN, 12));
		showReturned.setBounds(183, 43, 142, 23);
		add(showReturned);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u8BFB\u8005\u4FE1\u606F",

				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(854, 71, 376, 536);
		add(panel);

		JLabel label = new JLabel("\u501F\u4E66\u8BC1\u53F7");
		label.setFont(new Font("黑体", Font.PLAIN, 26));
		label.setBounds(20, 41, 104, 26);
		panel.add(label);

		JLabel label_1 = new JLabel("\u59D3    \u540D");
		label_1.setFont(new Font("黑体", Font.PLAIN, 26));
		label_1.setBounds(20, 77, 104, 26);
		panel.add(label_1);

		JLabel label_2 = new JLabel("\u6027    \u522B");
		label_2.setFont(new Font("黑体", Font.PLAIN, 26));
		label_2.setBounds(20, 113, 104, 26);
		panel.add(label_2);

		JLabel label_3 = new JLabel("\u8BFB\u8005\u7C7B\u578B");
		label_3.setFont(new Font("黑体", Font.PLAIN, 26));
		label_3.setBounds(20, 149, 104, 26);
		panel.add(label_3);

		JLabel label_5 = new JLabel("\u8BFB\u8005\u5355\u4F4D");
		label_5.setFont(new Font("黑体", Font.PLAIN, 26));
		label_5.setBounds(20, 185, 104, 26);
		panel.add(label_5);

		JLabel label_6 = new JLabel("\u7535\u8BDD\u53F7\u7801");
		label_6.setFont(new Font("黑体", Font.PLAIN, 26));
		label_6.setBounds(20, 221, 104, 26);
		panel.add(label_6);

		JLabel label_7 = new JLabel("\u7535\u5B50\u90AE\u7BB1");
		label_7.setFont(new Font("黑体", Font.PLAIN, 26));
		label_7.setBounds(20, 257, 104, 26);
		panel.add(label_7);

		JLabel label_8 = new JLabel("\u6CE8\u518C\u65E5\u671F");
		label_8.setFont(new Font("黑体", Font.PLAIN, 26));
		label_8.setBounds(20, 293, 104, 26);
		panel.add(label_8);

		textRdRegDate = new JTextField();
		textRdRegDate.setEditable(false);
		textRdRegDate.setText((String) null);
		textRdRegDate.setFont(new Font("黑体", Font.PLAIN, 16));
		textRdRegDate.setColumns(10);
		textRdRegDate.setBounds(146, 293, 194, 26);
		panel.add(textRdRegDate);

		textRdEmail = new JTextField();
		textRdEmail.setEditable(false);
		textRdEmail.setText((String) null);
		textRdEmail.setFont(new Font("黑体", Font.PLAIN, 16));
		textRdEmail.setColumns(10);
		textRdEmail.setBounds(146, 257, 194, 26);
		panel.add(textRdEmail);

		textRdPhone = new JTextField();
		textRdPhone.setEditable(false);
		textRdPhone.setText((String) null);
		textRdPhone.setFont(new Font("黑体", Font.PLAIN, 16));
		textRdPhone.setColumns(10);
		textRdPhone.setBounds(146, 221, 194, 26);
		panel.add(textRdPhone);

		textRdName = new JTextField();
		textRdName.setEditable(false);
		textRdName.setText((String) null);
		textRdName.setFont(new Font("黑体", Font.PLAIN, 16));
		textRdName.setColumns(10);
		textRdName.setBounds(146, 77, 89, 26);
		panel.add(textRdName);

		textRdID = new JTextField();
		textRdID.setEditable(false);
		textRdID.setText((String) null);
		textRdID.setFont(new Font("黑体", Font.PLAIN, 16));
		textRdID.setColumns(10);
		textRdID.setBounds(146, 41, 89, 26);
		panel.add(textRdID);

		JLabel label_9 = new JLabel("\u8BC1\u4EF6\u72B6\u6001");
		label_9.setFont(new Font("黑体", Font.PLAIN, 26));
		label_9.setBounds(20, 329, 104, 26);
		panel.add(label_9);

		JLabel label_10 = new JLabel("\u5DF2\u501F\u6570\u91CF");
		label_10.setFont(new Font("黑体", Font.PLAIN, 26));
		label_10.setBounds(20, 402, 104, 26);
		panel.add(label_10);

		lblRdStatus = new JLabel((String) null);
		lblRdStatus.setFont(new Font("黑体", Font.PLAIN, 26));
		lblRdStatus.setBounds(146, 329, 194, 26);
		panel.add(lblRdStatus);

		lblRdHaveBorrowed = new JLabel("0");
		lblRdHaveBorrowed.setFont(new Font("黑体", Font.PLAIN, 26));
		lblRdHaveBorrowed.setBounds(146, 402, 194, 26);
		panel.add(lblRdHaveBorrowed);

		JLabel label_13 = new JLabel("\u53EF\u501F\u6570\u91CF");
		label_13.setFont(new Font("黑体", Font.PLAIN, 26));
		label_13.setBounds(20, 366, 104, 26);
		panel.add(label_13);

		lblRdCanLendSum = new JLabel("0");
		lblRdCanLendSum.setFont(new Font("黑体", Font.PLAIN, 26));
		lblRdCanLendSum.setBounds(146, 366, 194, 26);
		panel.add(lblRdCanLendSum);

		JLabel label_15 = new JLabel("\u5269\u4F59\u53EF\u501F");
		label_15.setFont(new Font("黑体", Font.PLAIN, 26));
		label_15.setBounds(20, 438, 104, 26);
		panel.add(label_15);

		lblRdLeftBorrowNum = new JLabel("0");
		lblRdLeftBorrowNum.setFont(new Font("黑体", Font.PLAIN, 26));
		lblRdLeftBorrowNum.setBounds(146, 438, 194, 26);
		panel.add(lblRdLeftBorrowNum);

		textRdDept = new JTextField();
		textRdDept.setEditable(false);
		textRdDept.setText((String) null);
		textRdDept.setFont(new Font("黑体", Font.PLAIN, 16));
		textRdDept.setColumns(10);
		textRdDept.setBounds(146, 185, 194, 26);
		panel.add(textRdDept);

		textRdSex = new JTextField();
		textRdSex.setEditable(false);
		textRdSex.setText((String) null);
		textRdSex.setFont(new Font("黑体", Font.PLAIN, 16));
		textRdSex.setColumns(10);
		textRdSex.setBounds(146, 113, 89, 26);
		panel.add(textRdSex);

		textRdType = new JTextField();
		textRdType.setEditable(false);
		textRdType.setText((String) null);
		textRdType.setFont(new Font("黑体", Font.PLAIN, 16));
		textRdType.setColumns(10);
		textRdType.setBounds(146, 149, 89, 26);
		panel.add(textRdType);

		lblRdPhoto = new JLabel("");
		lblRdPhoto.setBounds(245, 41, 95, 134);
		panel.add(lblRdPhoto);

		JLabel label_4 = new JLabel("\u5DF2\u501F\u56FE\u4E66");
		label_4.setFont(new Font("黑体", Font.PLAIN, 32));
		label_4.setBounds(37, 22, 142, 51);
		add(label_4);

		panelBorrowedBooks = new JPanel();
		panelBorrowedBooks.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"\u5DF2\u501F\u56FE\u4E66", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelBorrowedBooks.setBounds(37, 98, 783, 179);
		add(panelBorrowedBooks);

		JLabel label_18 = new JLabel("\u8F93\u5165\u5173\u952E\u5B57\u68C0\u7D22\u56FE\u4E66");
		label_18.setFont(new Font("黑体", Font.PLAIN, 26));
		label_18.setBounds(37, 314, 249, 31);
		add(label_18);

		textInput = new JTextField();
		textInput.setText((String) null);
		textInput.setFont(new Font("黑体", Font.PLAIN, 16));
		textInput.setColumns(10);
		textInput.setBounds(296, 314, 259, 31);
		add(textInput);

		searchBook = new JButton("\u641C\u4E66\u7C4D\u540D\u79F0/\u4E66\u53F7");
		searchBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSearch();
			}
		});
		searchBook.setFont(new Font("黑体", Font.PLAIN, 26));
		searchBook.setBounds(574, 314, 241, 31);
		add(searchBook);

		scrollPane_1 = new JScrollPane();
		panelSearched.add(scrollPane_1);

		panelSearched = new JPanel();
		panelSearched.setBorder(
				new TitledBorder(null, "\u641C\u7D22\u7ED3\u679C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSearched.setBounds(37, 355, 783, 252);
		add(panelSearched);

		JSeparator separator = new JSeparator();
		separator.setBounds(37, 287, 780, 2);
		add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(37, 86, 780, 2);
		add(separator_1);

		JButton btnSerachReader = new JButton("\u641C\u7D22\u5B66\u751F\u501F\u9605\u8BC1\u53F7");
		btnSerachReader.setFont(new Font("黑体", Font.PLAIN, 16));
		btnSerachReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserConfiguration.Reader = (Reader) readerDAO.getObjectByID(textSerachReader.getText());
				borrowPanelHandle.PaintBorrowPanel();// 重新绘制已借图书区域

			}
		});
		btnSerachReader.setBounds(636, 37, 179, 32);
		add(btnSerachReader);

		textSerachReader = new JTextField();
		textSerachReader.setBounds(458, 39, 168, 31);
		add(textSerachReader);
		textSerachReader.setColumns(10);

		getBorrowedBooks();// 获取已借图书，这里面很乱，很多方法重复调用，没办法，脑子都糊了
	}

	public void getBorrowedBooks() {

		textRdID.setText(reader.getRdID());
		textRdName.setText(reader.getRdName());
		textRdSex.setText(reader.getRdSex());
		textRdDept.setText(reader.getRdDept());
		textRdPhone.setText(reader.getRdPhone());
		textRdRegDate.setText(reader.getRdRegDate());
		textRdType.setText(reader.getRdType());
		textRdEmail.setText(reader.getRdEmail());
		lblRdStatus.setText(reader.getRdStatus());
		lblRdLeftBorrowNum.setText(String.valueOf(readerType.getRdCanLendSum() - reader.getRdBrorrowNum()));
		lblRdCanLendSum.setText(String.valueOf(readerType.getRdCanLendSum()));
		lblRdHaveBorrowed.setText(String.valueOf(reader.getRdBrorrowNum()));
		readerPhoto = reader.getRdPhoto();
		rdPhoto = reader.getRdPhoto();
		ImageIcon icon = new ImageIcon(rdPhoto);
		lblRdPhoto.setIcon(icon);

		ArrayList<AbstractModel> Borrowed = borrowRecordDAO
				.getObjectsByRdID(((Reader) UserConfiguration.Reader).getRdID());

		Vector<Vector<String>> tablerows = new Vector<Vector<String>>();
		Vector<String> tablehead = new Vector<String>();
		tablehead.add("借书流水号");
		tablehead.add("图书序号");
		tablehead.add("图书名称");
		// tablehead.add("图书作者");
		tablehead.add("续借次数");
		tablehead.add("借阅日期");
		tablehead.add("应还日期");
		tablehead.add("超期天数");
		tablehead.add("应缴罚金");
		tablehead.add("是否已还");
		for (int i = 0; i < Borrowed.size(); i++) {
			Vector<String> vector = new Vector<String>();
			vector.add(((BorrowRecord) (Borrowed.get(i))).getBorrowID());
			vector.add(String.valueOf(((BorrowRecord) (Borrowed.get(i))).getBkID()));
			// System.out.println(String.valueOf(((BorrowRecord)
			// (Borrowed.get(i))).getBkID()));
			vector.add(String.valueOf(
					((BookLib) bookLibDAO.getObjectByID(((BorrowRecord) (Borrowed.get(i))).getBkID())).getBkName()));
			vector.add(String.valueOf(((BorrowRecord) (Borrowed.get(i))).getContinueTimes()));
			vector.add(String.valueOf(((BorrowRecord) (Borrowed.get(i))).getDateOut()));
			vector.add(String.valueOf(((BorrowRecord) (Borrowed.get(i))).getDateShouldRet()));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date start = null, end = new Date();// end即是当前时间
			try {
				start = sdf.parse((((BorrowRecord) Borrowed.get(i)).getDateShouldRet().replace("-1-", "-01-").replace("-2-", "-02-")
						.replace("-3-", "-03-").replace("-4-", "-04-").replace("-5-", "-05-").replace("-6-", "-06-")
						.replace("-7-", "-07-").replace("-8-", "-08-").replace("-9-", "-09-")));
				int daysbetween = (int) ((end.getTime() - start.getTime()) / (long) 86400000f);
				if (daysbetween > 0) {
					vector.add(String.valueOf(daysbetween));
					vector.add(String.valueOf(readerType.getRdPunishRate() * (daysbetween)));
				}else {
					vector.add(String.valueOf(0));
					vector.add(String.valueOf(0));
				}
			} catch (ParseException e1) {
				vector.add(String.valueOf("计算异常"));
				vector.add(String.valueOf("计算异常"));
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
			if (showReturned.isSelected()) {
				if (((BorrowRecord) (Borrowed.get(i))).getReturned() == 1) {
					vector.add("是");
					tablerows.add(vector);
				}
			} else {
				if (((BorrowRecord) (Borrowed.get(i))).getReturned() == 0) {
					vector.add("否");
					tablerows.add(vector);
				}
			}

			JTable table = new JTable(tablerows, tablehead);
			table.setLayout(new BorderLayout());
			table.setRowHeight(26);
			table.setCellSelectionEnabled(true);
			table.setRowSelectionAllowed(true);
			table.setColumnSelectionAllowed(false);
			scrollPane = new JScrollPane(table);
			panelBorrowedBooks.add(scrollPane);
			scrollPane.setBounds(20, 20, 743, 140);
			panelBorrowedBooks.setLayout(new BorderLayout());

			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {

					table.setSelectionBackground(Color.green);
					if (table.getModel().getValueAt(table.getSelectedRow(), 8).toString().equals("否")) {
						int choose;
						Object options[] = { "还书", "续借", "取消" };
						choose = JOptionPane.showOptionDialog(mainWindow, "请选择续借操作或者还书操作", "选择想要的操作",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
						if (choose == 0) {
							borrowPanelHandle.DoHandle("还书", Long
									.parseLong((table.getModel().getValueAt(table.getSelectedRow(), 0).toString())));
						} else if (choose == 1) {
							borrowPanelHandle.DoHandle("续借", Long
									.parseLong((table.getModel().getValueAt(table.getSelectedRow(), 0).toString())));

						}
						borrowPanelHandle.PaintBorrowPanel();
					}
				}
			});

		}

	}

	public void doSearch() {
		try {// try catch是个好东西，尝试但又不依赖就可以使用它
			panelSearched.remove(scrollPane_1);
		} catch (Exception e) {

		}
		ArrayList<AbstractModel> booklib = (ArrayList<AbstractModel>) bookLibDAO.getSearchedBooks(textInput.getText());
		ArrayList<AbstractModel> booklibInfo = new ArrayList<AbstractModel>();
		for (int i = 0; i < booklib.size(); i++) {
			booklibInfo.add(bookDAO.getObjectByID(((BookLib) booklib.get(i)).getBkISBN()));
		}

		Vector<Vector<String>> tablerows = new Vector<Vector<String>>();
		Vector<String> tablehead = new Vector<String>();
		tablehead.add("序号");
		tablehead.add("分类号");
		tablehead.add("图书名称");
		tablehead.add("作者");
		tablehead.add("语言");
		tablehead.add("出版社");
		tablehead.add("出版日期");
		tablehead.add("ISBN");
		tablehead.add("书籍分类");
		tablehead.add("页数");
		tablehead.add("价格");
		tablehead.add("入馆日期");
		tablehead.add("状态");
		for (int i = 0; i < booklib.size(); i++) {
			Vector<String> vector = new Vector<String>();
			vector.add(String.valueOf(((BookLib) (booklib.get(i))).getBkID()));
			vector.add(String.valueOf(((BookLib) (booklib.get(i))).getBkCatagegroy()));
			vector.add(((Book) (booklibInfo.get(i))).getBkName());
			vector.add(((Book) (booklibInfo.get(i))).getBkAuthor());
			vector.add(((Book) (booklibInfo.get(i))).getBkLanguage());
			vector.add(((Book) (booklibInfo.get(i))).getBkPress());
			vector.add(((Book) (booklibInfo.get(i))).getBkPressDate());

			vector.add(((Book) (booklibInfo.get(i))).getBkISBN());
			vector.add(((BookCategory) bookCategoryDAO.getObjectByID(((Book) (booklibInfo.get(i))).getBkCatageroy()))
					.getCategoryName());
			vector.add(String.valueOf(((Book) (booklibInfo.get(i))).getBkPages()));
			vector.add(String.valueOf(((Book) (booklibInfo.get(i))).getBkPrice()));
			vector.add(((BookLib) (booklib.get(i))).getCollectDate());
			vector.add(((BookLib) (booklib.get(i))).getBkStatus());
			tablerows.add(vector);

		}
		JTable table = new JTable(tablerows, tablehead);

		table.setLayout(new BorderLayout());
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setRowHeight(32);
		scrollPane_1 = new JScrollPane(table);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				table.setSelectionBackground(Color.green);
				String status = ((BookLib) booklib.get(table.getSelectedRow())).getBkStatus();
				if (status.equals("在馆") || status.startsWith(reader.getRdName())) {
					if (JOptionPane.showConfirmDialog(mainWindow, "是否借书？", null, JOptionPane.YES_NO_OPTION) == 0) {
						borrowPanelHandle.DoHandle("借书",
								Long.parseLong((table.getModel().getValueAt(table.getSelectedRow(), 0).toString())));
						borrowPanelHandle.PaintBorrowPanel();
					}
				}
			}
		});

		panelSearched.add(scrollPane_1);
		scrollPane_1.setBounds(20, 20, 743, 200);
		panelSearched.setLayout(new BorderLayout());
		mainWindow.getRootPane().validate();
	}
}
