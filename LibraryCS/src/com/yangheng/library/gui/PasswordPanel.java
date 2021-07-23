package com.yangheng.library.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.yangheng.library.configuration.UserConfiguration;
import com.yangheng.library.dao.DepartmentDAO;
import com.yangheng.library.dao.ReaderDAO;
import com.yangheng.library.model.AbstractModel;
import com.yangheng.library.model.Department;
import com.yangheng.library.model.Reader;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PasswordPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField RdName;
	private JTextField textRdRegDate;
	private JTextField textRdEmail;
	private JTextField textRdPhone;
	private JTextField textRdName;
	private JTextField textRdID;
	private JTextField textRdPwd;
	JLabel lblRdBorrowedNum;
	JLabel lblRdStatus;
	JLabel lblRdPhoto;
	JComboBox<String> RdDept;
	JComboBox<String> RdType;
	JComboBox<String> comRdSex;
	JComboBox<String> comRdType;
	JComboBox<String> comRdDept;
	JScrollPane scrollPane;

	ReaderDAO readerDAO = new ReaderDAO();
	MainWindow mainWindow;
	Reader reader;
	byte[] rdPhoto;

	/**
	 * Create the panel.
	 */
	public PasswordPanel(MainWindow mainWindow) {
		setLayout(null);

		JLabel label = new JLabel("\u8BFB\u8005\u7C7B\u522B");
		label.setFont(new Font("黑体", Font.PLAIN, 16));
		label.setBounds(33, 29, 74, 29);
		add(label);

		RdType = new JComboBox<String>();
		RdType.addItem("本科生");
		RdType.addItem("专科生");
		RdType.addItem("硕士研究生");
		RdType.addItem("博士研究生");
		RdType.addItem("教师");
		RdType.setBounds(111, 30, 104, 28);
		add(RdType);

		JLabel label_1 = new JLabel("\u9662\u7CFB/\u5355\u4F4D");
		label_1.setFont(new Font("黑体", Font.PLAIN, 16));
		label_1.setBounds(241, 33, 74, 25);
		add(label_1);

		RdDept = new JComboBox<String>();

		JLabel label_2 = new JLabel("\u59D3\u540D");
		label_2.setFont(new Font("黑体", Font.PLAIN, 16));
		label_2.setBounds(498, 33, 46, 25);
		add(label_2);

		RdName = new JTextField();
		RdName.setColumns(10);
		RdName.setBounds(540, 30, 116, 28);
		add(RdName);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "\u67E5\u8BE2\u7ED3\u679C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(43, 68, 790, 510);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JButton btnSearch = new JButton("\u67E5\u627E");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserConfiguration.Reader = (Reader) readerDAO.getObjectByName(RdName.getText());
				ArrayList<AbstractModel> readers = readerDAO.getAllObjects();
				Vector<Vector<String>> vectortable = new Vector<Vector<String>>();
				Vector<String> vector = new Vector<String>();
				vector.add("借书证号");
				vector.add("姓名");
				vector.add("性别");
				vector.add("读者类型");
				vector.add("单位");
				vector.add("电话");
				vector.add("电子邮箱");
				vector.add("借书证状态");
				vector.add("已借");
				vector.add("注册日期");
				for (int j = 0; j < readers.size(); j++) {

					if (((Reader) readers.get(j)).getRdType().equals(RdType.getSelectedItem().toString())
							&& ((Reader) readers.get(j)).getRdDept().equals(RdDept.getSelectedItem().toString())
							&& ((Reader) readers.get(j)).getRdName().equals(RdName.getText())) {
						Vector<String> v = new Vector<String>();
						v.add(((Reader) readers.get(j)).getRdID());
						v.add(((Reader) readers.get(j)).getRdName());
						v.add(((Reader) readers.get(j)).getRdSex());
						v.add(((Reader) readers.get(j)).getRdType());
						v.add(((Reader) readers.get(j)).getRdDept());
						v.add(((Reader) readers.get(j)).getRdPhone());
						v.add(((Reader) readers.get(j)).getRdEmail());
						v.add(((Reader) readers.get(j)).getRdStatus());
						v.add(String.valueOf(((Reader) readers.get(j)).getRdBrorrowNum()));
						v.add(((Reader) readers.get(j)).getRdRegDate());
						vectortable.add(v);
					}
				}

				JTable resultTable = new JTable(vectortable, vector);
				resultTable.setLayout(new BorderLayout());
				resultTable.setCellSelectionEnabled(true);
				resultTable.setRowSelectionAllowed(true);
				resultTable.setColumnSelectionAllowed(false);
				resultTable.setRowHeight(32);
				scrollPane = new JScrollPane(resultTable);
				scrollPane.setBounds(20, 20, 750, 470);
				panel.add(scrollPane);
				panel.setLayout(new BorderLayout(0, 0));

				resultTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						String string = resultTable.getModel().getValueAt(resultTable.getSelectedRow(), 0).toString();
						reader = (Reader) readerDAO.getObjectByID(string);
						textRdID.setText(reader.getRdID());
						textRdName.setText(reader.getRdName());
						comRdSex.setSelectedItem(reader.getRdSex());
						comRdDept.setSelectedItem(reader.getRdDept());
						comRdType.setSelectedItem(reader.getRdType());
						textRdEmail.setText(reader.getRdEmail());
						textRdPhone.setText(reader.getRdPhone());
						lblRdBorrowedNum.setText(String.valueOf(reader.getRdBrorrowNum()));
						lblRdStatus.setText(reader.getRdStatus());
						textRdRegDate.setText(reader.getRdRegDate());
						rdPhoto = reader.getRdPhoto();
						ImageIcon icon = new ImageIcon(rdPhoto);
						lblRdStatus.setIcon(icon);
						resultTable.setSelectionBackground(Color.green);
						UserConfiguration.Reader = (AbstractModel) reader;// 操作的Reader改了之后要改UserConfiguration里面的Reader
					}
				});

				try {
					textRdID.setText(reader.getRdID());
					textRdName.setText(reader.getRdName());
					comRdSex.setSelectedItem(reader.getRdSex());
					comRdDept.setSelectedItem(reader.getRdDept());
					// comRdRole.setSelectedItem(reader.getRdAdminRole());
					comRdType.setSelectedItem(reader.getRdType());
					textRdEmail.setText(reader.getRdEmail());
					textRdPhone.setText(reader.getRdPhone());
					lblRdBorrowedNum.setText(String.valueOf(reader.getRdBrorrowNum()));
					lblRdStatus.setText(reader.getRdStatus());
					textRdRegDate.setText(reader.getRdRegDate());
					// textPwd.setText("********");

				} catch (Exception e2) {
				}

			}
		});
		btnSearch.setFont(new Font("黑体", Font.PLAIN, 16));
		btnSearch.setBounds(693, 28, 130, 30);
		add(btnSearch);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u8BFB\u8005\u4FE1\u606F",

				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(854, 71, 376, 507);
		add(panel_1);

		JLabel label_3 = new JLabel("\u501F\u4E66\u8BC1\u53F7");
		label_3.setFont(new Font("黑体", Font.PLAIN, 26));
		label_3.setBounds(20, 41, 104, 26);
		panel_1.add(label_3);

		JLabel label_4 = new JLabel("\u59D3    \u540D");
		label_4.setFont(new Font("黑体", Font.PLAIN, 26));
		label_4.setBounds(20, 77, 104, 26);
		panel_1.add(label_4);

		JLabel label_5 = new JLabel("\u6027    \u522B");
		label_5.setFont(new Font("黑体", Font.PLAIN, 26));
		label_5.setBounds(20, 113, 104, 26);
		panel_1.add(label_5);

		JLabel label_6 = new JLabel("\u8BFB\u8005\u7C7B\u578B");
		label_6.setFont(new Font("黑体", Font.PLAIN, 26));
		label_6.setBounds(20, 149, 104, 26);
		panel_1.add(label_6);

		JLabel label_8 = new JLabel("\u8BFB\u8005\u5355\u4F4D");
		label_8.setFont(new Font("黑体", Font.PLAIN, 26));
		label_8.setBounds(20, 185, 104, 26);
		panel_1.add(label_8);

		JLabel label_9 = new JLabel("\u7535\u8BDD\u53F7\u7801");
		label_9.setFont(new Font("黑体", Font.PLAIN, 26));
		label_9.setBounds(20, 221, 104, 26);
		panel_1.add(label_9);

		JLabel label_10 = new JLabel("\u7535\u5B50\u90AE\u7BB1");
		label_10.setFont(new Font("黑体", Font.PLAIN, 26));
		label_10.setBounds(20, 257, 104, 26);
		panel_1.add(label_10);

		JLabel label_11 = new JLabel("\u6CE8\u518C\u65E5\u671F");
		label_11.setFont(new Font("黑体", Font.PLAIN, 26));
		label_11.setBounds(20, 293, 104, 26);
		panel_1.add(label_11);

		textRdRegDate = new JTextField();
		textRdRegDate.setText((String) null);
		textRdRegDate.setFont(new Font("黑体", Font.PLAIN, 16));
		textRdRegDate.setEnabled(false);
		textRdRegDate.setEditable(false);
		textRdRegDate.setColumns(10);
		textRdRegDate.setBounds(146, 293, 194, 26);
		panel_1.add(textRdRegDate);

		textRdEmail = new JTextField();
		textRdEmail.setText((String) null);
		textRdEmail.setFont(new Font("黑体", Font.PLAIN, 16));
		textRdEmail.setEnabled(false);
		textRdEmail.setColumns(10);
		textRdEmail.setBounds(146, 257, 194, 26);
		panel_1.add(textRdEmail);

		textRdPhone = new JTextField();
		textRdPhone.setText((String) null);
		textRdPhone.setFont(new Font("黑体", Font.PLAIN, 16));
		textRdPhone.setEnabled(false);
		textRdPhone.setColumns(10);
		textRdPhone.setBounds(146, 221, 194, 26);
		panel_1.add(textRdPhone);

		textRdName = new JTextField();
		textRdName.setText((String) null);
		textRdName.setFont(new Font("黑体", Font.PLAIN, 16));
		textRdName.setEnabled(false);
		textRdName.setColumns(10);
		textRdName.setBounds(146, 77, 89, 26);
		panel_1.add(textRdName);

		comRdSex = new JComboBox<String>();
		comRdSex.addItem("男");
		comRdSex.addItem("女");
		comRdSex.setFont(new Font("黑体", Font.PLAIN, 16));
		comRdSex.setEnabled(false);
		comRdSex.setBounds(146, 113, 89, 26);
		panel_1.add(comRdSex);

		comRdType = new JComboBox<String>();
		comRdType.addItem("本科生");
		comRdType.addItem("专科生");
		comRdType.addItem("硕士研究生");
		comRdType.addItem("博士研究生");
		comRdType.addItem("教师");
		comRdType.setFont(new Font("黑体", Font.PLAIN, 16));
		comRdType.setEnabled(false);
		comRdType.setBounds(146, 149, 89, 26);
		panel_1.add(comRdType);

		comRdDept = new JComboBox<String>();
		comRdDept.setFont(new Font("黑体", Font.PLAIN, 16));
		comRdDept.setEnabled(false);
		comRdDept.setBounds(146, 185, 194, 26);
		panel_1.add(comRdDept);

		textRdID = new JTextField();
		textRdID.setText((String) null);
		textRdID.setFont(new Font("黑体", Font.PLAIN, 16));
		textRdID.setEnabled(false);
		textRdID.setColumns(10);
		textRdID.setBounds(146, 41, 89, 26);
		panel_1.add(textRdID);

		JLabel label_12 = new JLabel("\u8BC1\u4EF6\u72B6\u6001");
		label_12.setFont(new Font("黑体", Font.PLAIN, 26));
		label_12.setBounds(20, 366, 104, 26);
		panel_1.add(label_12);

		JLabel label_13 = new JLabel("\u5DF2\u501F\u6570\u91CF");
		label_13.setFont(new Font("黑体", Font.PLAIN, 26));
		label_13.setBounds(20, 402, 104, 26);
		panel_1.add(label_13);

		lblRdStatus = new JLabel((String) null);
		lblRdStatus.setFont(new Font("黑体", Font.PLAIN, 26));
		lblRdStatus.setBounds(146, 366, 194, 26);
		panel_1.add(lblRdStatus);

		lblRdBorrowedNum = new JLabel("0");
		lblRdBorrowedNum.setFont(new Font("黑体", Font.PLAIN, 26));
		lblRdBorrowedNum.setBounds(146, 402, 194, 26);
		panel_1.add(lblRdBorrowedNum);

		JLabel label_16 = new JLabel("\u5BC6    \u7801");
		label_16.setFont(new Font("黑体", Font.PLAIN, 26));
		label_16.setBounds(20, 329, 104, 27);
		panel_1.add(label_16);

		textRdPwd = new JTextField();
		textRdPwd.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textRdPwd.setText("");
			}
		});
		textRdPwd.setText("\u5BC6\u7801\u660E\u6587\u663E\u793A\uFF0C\u4FEE\u6539\u540E\u7262\u8BB0\u65B0\u5BC6\u7801");
		textRdPwd.setFont(new Font("黑体", Font.PLAIN, 12));
		textRdPwd.setColumns(10);
		textRdPwd.setBounds(146, 329, 194, 27);
		panel_1.add(textRdPwd);

		lblRdPhoto = new JLabel("");
		lblRdPhoto.setBounds(245, 41, 95, 134);
		panel_1.add(lblRdPhoto);

		JButton btnCancel = new JButton("\u53D6\u6D88");
		btnCancel.setFont(new Font("黑体", Font.PLAIN, 16));
		btnCancel.setBounds(1062, 599, 130, 29);
		add(btnCancel);

		JButton btnConfirm = new JButton("\u786E\u8BA4\u4FEE\u6539\u5BC6\u7801");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updatePassword();
			}
		});
		btnConfirm.setFont(new Font("黑体", Font.PLAIN, 16));
		btnConfirm.setBounds(890, 599, 130, 29);
		add(btnConfirm);

		DepartmentDAO departmentDAO = new DepartmentDAO();
		ArrayList<AbstractModel> departments = departmentDAO.getAllObjects();
		for (int i = 0; i < departments.size(); i++) {
			comRdDept.addItem(((Department) departments.get(i)).getDeptName());
			RdDept.addItem(((Department) departments.get(i)).getDeptName());
		}
		RdDept.setBounds(325, 30, 151, 28);
		add(RdDept);

		drawReaderInfoPanel((Reader) UserConfiguration.Reader);

	}

	public void drawReaderInfoPanel(Reader reader) {

		textRdID.setText(reader.getRdID());
		textRdName.setText(reader.getRdName());
		textRdPhone.setText(reader.getRdPhone());
		textRdRegDate.setText(reader.getRdRegDate());
		textRdEmail.setText(reader.getRdEmail());
		comRdDept.setSelectedItem(reader.getRdDept());
		comRdSex.setSelectedItem(reader.getRdSex());
		comRdType.setSelectedItem(reader.getRdType());
		lblRdBorrowedNum.setText(String.valueOf(reader.getRdBrorrowNum()));
		lblRdStatus.setText(reader.getRdStatus());
		rdPhoto = reader.getRdPhoto();
		ImageIcon icon = new ImageIcon(rdPhoto);
		lblRdPhoto.setIcon(icon);
	}

	public void updatePassword() {

		Reader reader = (Reader) UserConfiguration.Reader;
		if (reader.getRdPwd().equals(JOptionPane.showConfirmDialog(null, "请输入旧密码："))) {
			reader.setRdPwd(textRdPwd.getText());
			try {
				readerDAO.update(reader);
				JOptionPane.showMessageDialog(null, "密码修改成功！您的新密码为：" + textRdPwd.getText() + "请务必牢记！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "密码不正确");
		}
	}

}
