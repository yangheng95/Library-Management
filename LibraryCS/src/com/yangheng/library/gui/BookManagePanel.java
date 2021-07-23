package com.yangheng.library.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.yangheng.library.configuration.UserConfiguration;
import com.yangheng.library.dao.BookCategoryDAO;
import com.yangheng.library.dao.BookDAO;
import com.yangheng.library.dao.BookLibDAO;
import com.yangheng.library.model.AbstractModel;
import com.yangheng.library.model.Book;
import com.yangheng.library.model.BookCategory;
import com.yangheng.library.model.BookLib;

public class BookManagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textInput;
	JPanel panelSearched;
	JTable table;
	JScrollPane scrollPane;
	BookLibDAO bookLibDAO = new BookLibDAO();
	BookDAO bookDAO = new BookDAO();
	BookCategoryDAO bookCategoryDAO = new BookCategoryDAO();
	MainWindow mainWindow;

	long row = -1;

	public BookManagePanel(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		UserConfiguration.bookManagePanel = this;
		setLayout(null);

		JLabel lblNewLabel = new JLabel("\u6A21\u7CCA\u68C0\u7D22\u4E66\u7C4D");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 26));
		lblNewLabel.setBounds(37, 22, 171, 31);
		add(lblNewLabel);

		textInput = new JTextField();
		textInput.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (textInput.getText().equals("书名/书号/ISBN/分类号")) {
					textInput.setText("");
					textInput.setForeground(Color.BLACK);
					textInput.setFont(new Font("黑体", Font.PLAIN, 16));
				}

			}
			@Override
			public void focusLost(FocusEvent e) {
				if(textInput.getText().equals(""))
				textInput.setText("书名/书号/ISBN/分类号");
				textInput.setForeground(Color.BLACK);
				textInput.setFont(new Font("黑体", Font.ITALIC, 16));
				
			}
		});

		textInput.setForeground(Color.LIGHT_GRAY);
		textInput.setHorizontalAlignment(SwingConstants.CENTER);
		textInput.setFont(new Font("黑体", Font.ITALIC, 16));
		textInput.setText("\u4E66\u540D/\u4E66\u53F7/ISBN/\u5206\u7C7B\u53F7");
		textInput.setBounds(221, 22, 235, 31);
		add(textInput);
		textInput.setColumns(10);

		JButton btnSearch = new JButton("\u641C\u7D22");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				UserConfiguration.BookManage_SearchKeyWord = textInput.getText();
				doSearch(UserConfiguration.BookManage_SearchKeyWord);
			
			}
		});
		btnSearch.setFont(new Font("黑体", Font.PLAIN, 16));
		btnSearch.setBounds(485, 22, 179, 31);
		add(btnSearch);

		panelSearched = new JPanel();
		panelSearched.setBorder(
				new TitledBorder(null, "\u641C\u7D22\u7ED3\u679C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSearched.setBounds(37, 74, 1184, 523);
		add(panelSearched);

		scrollPane = new JScrollPane();
		panelSearched.add(scrollPane);

		JButton btnSold = new JButton("\u53D8\u5356");
		btnSold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println(table.getSelectedRow());
				try {
					BookLib bookLib = (BookLib) bookLibDAO.getObjectByID(
							Long.parseLong(table.getModel().getValueAt(table.getSelectedRow(), 0).toString()));
					bookLib.setBkStatus("变卖");
					bookLibDAO.update(bookLib);
					JOptionPane.showMessageDialog(null, "操作成功！");
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "操作异常！");
				}
			}
		});
		btnSold.setFont(new Font("黑体", Font.PLAIN, 16));
		btnSold.setBounds(366, 607, 115, 31);
		add(btnSold);

		JButton btnDestroied = new JButton("\u9500\u6BC1");
		btnDestroied.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BookLib bookLib = (BookLib) bookLibDAO.getObjectByID(
							Long.parseLong(table.getModel().getValueAt(table.getSelectedRow(), 0).toString()));
					bookLib.setBkStatus("销毁");
					bookLibDAO.update(bookLib);
					JOptionPane.showMessageDialog(null, "操作成功！");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "操作异常！");
				}
			}
		});
		btnDestroied.setFont(new Font("黑体", Font.PLAIN, 16));
		btnDestroied.setBounds(512, 607, 115, 31);
		add(btnDestroied);

		JButton brnLost = new JButton("\u9057\u5931");
		brnLost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BookLib bookLib = (BookLib) bookLibDAO.getObjectByID(
							Long.parseLong(table.getModel().getValueAt(table.getSelectedRow(), 0).toString()));
					bookLib.setBkStatus("遗失");
					bookLibDAO.update(bookLib);
					JOptionPane.showMessageDialog(null, "操作成功！");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "操作异常！");
				}
			}
		});
		brnLost.setFont(new Font("黑体", Font.PLAIN, 16));
		brnLost.setBounds(651, 607, 115, 31);
		add(brnLost);

		JButton btnRefresh = new JButton("\u5237\u65B0");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainWindow.setContentPane(new BookManagePanel(mainWindow));
				mainWindow.getRootPane().validate();
			}
		});
		btnRefresh.setFont(new Font("黑体", Font.PLAIN, 16));
		btnRefresh.setBounds(691, 22, 179, 31);
		add(btnRefresh);

		JButton btnBackToLib = new JButton("\u91CD\u65B0\u5165\u5E93");
		btnBackToLib.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					BookLib bookLib = (BookLib) bookLibDAO.getObjectByID(
							Long.parseLong(table.getModel().getValueAt(table.getSelectedRow(), 0).toString()));
					bookLib.setBkStatus("在馆");
					bookLibDAO.update(bookLib);
					JOptionPane.showMessageDialog(null, "操作成功！");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "操作异常！");
				}
			}
		});
		btnBackToLib.setFont(new Font("黑体", Font.PLAIN, 16));
		btnBackToLib.setBounds(793, 607, 115, 31);
		add(btnBackToLib);

		if (null!=UserConfiguration.BookManage_SearchKeyWord) {
			doSearch(UserConfiguration.BookManage_SearchKeyWord);
			
		}
		
	}

	public void doSearch(String keyWord) {
		if (null!=keyWord) {
			try {
				panelSearched.remove(scrollPane);
			} catch (Exception e) {

			}
			ArrayList<AbstractModel> booklib = (ArrayList<AbstractModel>) bookLibDAO.getSearchedBooks(keyWord);
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
			table = new JTable(tablerows, tablehead);
			table.setLayout(new BorderLayout());
			table.setCellSelectionEnabled(true);
			table.setColumnSelectionAllowed(false);
			table.setRowSelectionAllowed(true);
			table.setRowHeight(26);
			scrollPane = new JScrollPane(table);

			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {

					row = Long.parseLong((table.getModel().getValueAt(table.getSelectedRow(), 0).toString()));
					// System.out.println(row);
					if (JOptionPane.showConfirmDialog(null, "是否变更图书信息？", null, JOptionPane.YES_NO_OPTION) == 0) {
						BookLib bookLib = (BookLib) bookLibDAO.getObjectByID(
								Long.parseLong((table.getModel().getValueAt(table.getSelectedRow(), 0).toString())));
						mainWindow.setContentPane(new BookInfoPanel(mainWindow, "图书信息维护", bookLib));
					}

				}
			});

			panelSearched.add(scrollPane);
			scrollPane.setBounds(20, 20, 1146, 490);
			panelSearched.setLayout(new BorderLayout());
			mainWindow.getRootPane().validate();
		}

	}
}
