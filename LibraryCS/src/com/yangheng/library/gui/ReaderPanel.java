package com.yangheng.library.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.yangheng.library.bll.OutputToExcelHandle;
import com.yangheng.library.bll.ReaderPanelHandle;
import com.yangheng.library.bll.SerachReaderHandle;
import com.yangheng.library.configuration.CoreConfiguration;
import com.yangheng.library.configuration.UserConfiguration;
import com.yangheng.library.dao.DepartmentDAO;
import com.yangheng.library.dao.ReaderDAO;
import com.yangheng.library.dao.SQLHelper;
import com.yangheng.library.model.AbstractModel;
import com.yangheng.library.model.Department;
import com.yangheng.library.model.Reader;

public class ReaderPanel extends JPanel {

	private static final long serialVersionUID = -4988742976804449803L;

	/**
	 * �������Щ�ؼ�����WindowBuilder���ɵģ��ǹ��췽���ľֲ��������ɼ���̫С
	 * �����Ұ����Ǽ��е������У�����������ֱ�Ӳ�����Щ�ؼ����𵽷��������
	 */
	JTextField RdName;
	private JTextField textRdRegDate;
	private JTextField textRdEmail;
	private JTextField textRdPhone;
	private JTextField textRdName;
	private JTextField textRdID;
	private JTextField textPwd;
	ReaderPanel readerPanel;
	JComboBox<String> RdDept;
	JComboBox<String> RdType;
	JPanel panel;
	JButton Search;
	JButton ToExcel;
	JPanel panel_1;
	JComboBox<String> comRdSex;
	JComboBox<String> comRdType;
	JComboBox<String> comRdDept;
	JButton btnNewReader;
	JButton uploadPhoto;
	JButton btnChangeReaderInfo;
	JButton btnLockReader;
	JButton btnUnlockReader;
	JButton btnLogout;
	JButton btnChangeInfoConfirm;
	JButton btnNewReaderConfirm;

	// ���������ǿؼ�������û��������һ��
	ReaderDAO readerDAO = new ReaderDAO();
	public Reader reader;
	MainWindow mainWindow;

	byte[] rdPhoto;
	private JLabel lblRdPhoto;
	private JLabel lblRdRole;

	public ReaderPanel(MainWindow mainWindow, String rdType, String rdDept, String rdName) {

		UserConfiguration.readerPanel = readerPanel = this;
		// �����Panel�ŵ��������У��´���ʾ���panel����Ҫ�����ɣ�����ֱ��������������򲻻Ῠ

		this.mainWindow = mainWindow;
		reader = (Reader) readerDAO.getObjectByName(rdName);// ȡ��ȫ�ֵĵ�ǰҪ���黹��֮������Ķ���

		setLayout(null);
		panel = new JPanel();
		DepartmentDAO departmentDAO = new DepartmentDAO();
		JLabel lblNewLabel = new JLabel("\u8BFB\u8005\u7C7B\u522B");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 16));
		lblNewLabel.setBounds(33, 29, 74, 29);
		add(lblNewLabel);

		RdType = new JComboBox<String>();
		RdType.setBounds(111, 30, 104, 28);
		RdType.addItem("������");
		RdType.addItem("ר����");
		RdType.addItem("˶ʿ�о���");
		RdType.addItem("��ʿ�о���");
		RdType.addItem("��ʦ");
		add(RdType);

		JLabel lblNewLabel_1 = new JLabel("\u9662\u7CFB/\u5355\u4F4D");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(241, 33, 74, 25);
		add(lblNewLabel_1);

		RdDept = new JComboBox<String>();
		RdDept.setBounds(325, 30, 151, 28);
		add(RdDept);

		JLabel lblNewLabel_2 = new JLabel("\u59D3\u540D");
		lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(498, 33, 46, 25);
		add(lblNewLabel_2);

		RdName = new JTextField();
		RdName.setText(rdName);
		RdName.setBounds(540, 30, 116, 28);
		add(RdName);
		RdName.setColumns(10);

		Search = new JButton("\u67E5\u627E");
		Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new SerachReaderHandle(mainWindow, RdType.getSelectedItem().toString(),
						RdDept.getSelectedItem().toString(), RdName.getText());
			}
		});
		Search.setFont(new Font("����", Font.PLAIN, 16));
		Search.setBounds(693, 28, 130, 30);
		add(Search);

		ToExcel = new JButton("\u5BFC\u51FA\u5230Excel");
		ToExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<AbstractModel> readers = readerDAO.getObjectsByName(RdName.getText());
				new OutputToExcelHandle(readers);
			}

		});
		ToExcel.setFont(new Font("����", Font.PLAIN, 16));
		ToExcel.setBounds(855, 28, 130, 30);
		add(ToExcel);

		panel.setBorder(
				new TitledBorder(null, "\u67E5\u8BE2\u7ED3\u679C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(43, 68, 790, 510);
		add(panel);

		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u8BFB\u8005\u4FE1\u606F",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(854, 71, 376, 507);
		add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("\u501F\u4E66\u8BC1\u53F7");
		lblNewLabel_3.setFont(new Font("����", Font.PLAIN, 26));
		lblNewLabel_3.setBounds(20, 41, 104, 26);
		panel_1.add(lblNewLabel_3);

		JLabel label = new JLabel("\u59D3    \u540D");
		label.setFont(new Font("����", Font.PLAIN, 26));
		label.setBounds(20, 77, 104, 26);
		panel_1.add(label);

		JLabel label_2 = new JLabel("\u6027    \u522B");
		label_2.setFont(new Font("����", Font.PLAIN, 26));
		label_2.setBounds(20, 113, 104, 26);
		panel_1.add(label_2);

		JLabel label_3 = new JLabel("\u8BFB\u8005\u7C7B\u578B");
		label_3.setFont(new Font("����", Font.PLAIN, 26));
		label_3.setBounds(20, 149, 104, 26);
		panel_1.add(label_3);

		JLabel label_4 = new JLabel("\u8BFB\u8005\u89D2\u8272");
		label_4.setFont(new Font("����", Font.PLAIN, 26));
		label_4.setBounds(20, 185, 104, 26);
		panel_1.add(label_4);

		JLabel label_5 = new JLabel("\u8BFB\u8005\u5355\u4F4D");
		label_5.setFont(new Font("����", Font.PLAIN, 26));
		label_5.setBounds(20, 221, 104, 26);
		panel_1.add(label_5);

		JLabel label_6 = new JLabel("\u7535\u8BDD\u53F7\u7801");
		label_6.setFont(new Font("����", Font.PLAIN, 26));
		label_6.setBounds(20, 257, 104, 26);
		panel_1.add(label_6);

		JLabel label_7 = new JLabel("\u7535\u5B50\u90AE\u7BB1");
		label_7.setFont(new Font("����", Font.PLAIN, 26));
		label_7.setBounds(20, 293, 104, 26);
		panel_1.add(label_7);

		JLabel label_8 = new JLabel("\u6CE8\u518C\u65E5\u671F");
		label_8.setFont(new Font("����", Font.PLAIN, 26));
		label_8.setBounds(20, 329, 104, 26);
		panel_1.add(label_8);

		textRdRegDate = new JTextField();
		textRdRegDate.setFont(new Font("����", Font.PLAIN, 16));
		textRdRegDate.setEditable(false);
		textRdRegDate.setBounds(146, 329, 194, 26);
		panel_1.add(textRdRegDate);
		textRdRegDate.setColumns(10);

		textRdEmail = new JTextField();
		textRdEmail.setFont(new Font("����", Font.PLAIN, 16));
		textRdEmail.setColumns(10);
		textRdEmail.setBounds(146, 293, 194, 26);
		panel_1.add(textRdEmail);

		textRdPhone = new JTextField();
		textRdPhone.setFont(new Font("����", Font.PLAIN, 16));
		textRdPhone.setColumns(10);
		textRdPhone.setBounds(146, 257, 194, 26);
		panel_1.add(textRdPhone);

		textRdName = new JTextField();
		textRdName.setFont(new Font("����", Font.PLAIN, 16));
		textRdName.setColumns(10);
		textRdName.setBounds(146, 77, 89, 26);
		panel_1.add(textRdName);

		comRdSex = new JComboBox<String>();
		comRdSex.setFont(new Font("����", Font.PLAIN, 16));
		comRdSex.setBounds(146, 113, 89, 26);
		comRdSex.addItem("��");
		comRdSex.addItem("Ů");
		panel_1.add(comRdSex);

		comRdType = new JComboBox<String>();
		comRdType.setFont(new Font("����", Font.PLAIN, 16));
		comRdType.setBounds(146, 149, 89, 26);
		comRdType.addItem("������");
		comRdType.addItem("ר����");
		comRdType.addItem("˶ʿ�о���");
		comRdType.addItem("��ʿ�о���");
		comRdType.addItem("��ʦ");
		panel_1.add(comRdType);

		comRdDept = new JComboBox<String>();
		comRdDept.setFont(new Font("����", Font.PLAIN, 16));
		comRdDept.setBounds(146, 221, 194, 26);

		ArrayList<AbstractModel> departments = departmentDAO.getAllObjects();
		for (int i = 0; i < departments.size(); i++) {
			comRdDept.addItem(((Department) departments.get(i)).getDeptName());
			RdDept.addItem(((Department) departments.get(i)).getDeptName());
		}

		panel_1.add(comRdDept);

		uploadPhoto = new JButton("\u4E0A\u4F20\u7167\u7247");
		uploadPhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fs = new JFileChooser("ѡ���ϴ���ͼƬ");
				if (fs.showOpenDialog(null) == 0) {
					String photoPath = fs.getSelectedFile().getAbsolutePath();
					File file = new File(photoPath);
					try {
						rdPhoto = SQLHelper.InputStreamTOByte(new FileInputStream(file));
						reader.setRdPhoto(rdPhoto);
						ImageIcon icon = new ImageIcon(rdPhoto);
						lblRdPhoto.setIcon(icon);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		});
		uploadPhoto.setBounds(245, 185, 96, 26);
		panel_1.add(uploadPhoto);

		textRdID = new JTextField();
		textRdID.setEnabled(false);
		textRdID.setFont(new Font("����", Font.PLAIN, 16));
		textRdID.setColumns(10);
		textRdID.setBounds(146, 41, 89, 26);
		panel_1.add(textRdID);

		JLabel label_9 = new JLabel("\u8BC1\u4EF6\u72B6\u6001");
		label_9.setFont(new Font("����", Font.PLAIN, 26));
		label_9.setBounds(20, 402, 104, 26);
		panel_1.add(label_9);

		JLabel label_10 = new JLabel("\u5DF2\u501F\u6570\u91CF");
		label_10.setFont(new Font("����", Font.PLAIN, 26));
		label_10.setBounds(20, 438, 104, 26);
		panel_1.add(label_10);

		JLabel lblRdStatus = new JLabel("");
		lblRdStatus.setFont(new Font("����", Font.PLAIN, 26));
		lblRdStatus.setBounds(146, 402, 194, 26);
		panel_1.add(lblRdStatus);

		JLabel lblRdBorrowed = new JLabel("");
		lblRdBorrowed.setFont(new Font("����", Font.PLAIN, 26));
		lblRdBorrowed.setBounds(146, 438, 194, 26);
		panel_1.add(lblRdBorrowed);

		JLabel label_1 = new JLabel("\u5BC6    \u7801");
		label_1.setFont(new Font("����", Font.PLAIN, 26));
		label_1.setBounds(20, 365, 104, 27);
		panel_1.add(label_1);

		textPwd = new JTextField();
		textPwd.setEnabled(false);
		textPwd.setFont(new Font("Consolas", Font.PLAIN, 16));
		textPwd.setColumns(10);
		textPwd.setBounds(146, 365, 194, 27);
		panel_1.add(textPwd);

		btnNewReader = new JButton("\u529E\u7406\u501F\u4E66\u8BC1");
		btnNewReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textRdID.setText("");
				textRdName.setText("");
				comRdSex.setSelectedItem("");
				comRdDept.setSelectedItem("");
				comRdType.setSelectedItem("");
				textRdEmail.setText("");
				textRdPhone.setText("");
				lblRdBorrowed.setText("");
				lblRdStatus.setText("");
				textRdRegDate.setText("");
				textPwd.setText("");
				lblRdPhoto.setIcon(null);
				inAdd();// �л�����״̬�ķ�������ؼ�setEnabled()����

			}
		});
		btnNewReader.setFont(new Font("����", Font.PLAIN, 16));
		btnNewReader.setBounds(122, 604, 116, 31);
		add(btnNewReader);

		btnChangeReaderInfo = new JButton("\u53D8\u66F4\u4FE1\u606F");
		btnChangeReaderInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					textRdID.setText(reader.getRdID());
					textRdName.setText(reader.getRdName());
					comRdSex.setSelectedItem(reader.getRdSex());
					comRdDept.setSelectedItem(reader.getRdDept());
					comRdType.setSelectedItem(reader.getRdType());
					textRdEmail.setText(reader.getRdEmail());
					textRdPhone.setText(reader.getRdPhone());
					lblRdBorrowed.setText(String.valueOf(reader.getRdBrorrowNum()));
					lblRdStatus.setText(reader.getRdStatus());
					textRdRegDate.setText(reader.getRdRegDate());
					textPwd.setText("********");
					inUpdate();// �л�����״̬�ķ�������ؼ�setEnabled()����

				} catch (Exception e2) {
				}

			}
		});
		btnChangeReaderInfo.setFont(new Font("����", Font.PLAIN, 16));
		btnChangeReaderInfo.setBounds(248, 604, 116, 31);
		add(btnChangeReaderInfo);

		btnLockReader = new JButton("\u501F\u4E66\u8BC1\u6302\u5931");
		btnLockReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LockReader();

			}
		});
		btnLockReader.setFont(new Font("����", Font.PLAIN, 16));
		btnLockReader.setBounds(374, 604, 116, 31);
		add(btnLockReader);

		btnUnlockReader = new JButton("\u501F\u4E66\u8BC1\u89E3\u6302");
		btnUnlockReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UnlockReader();
			}
		});
		btnUnlockReader.setFont(new Font("����", Font.PLAIN, 16));
		btnUnlockReader.setBounds(500, 604, 116, 31);
		add(btnUnlockReader);

		btnLogout = new JButton("\u6CE8\u9500\u501F\u4E66\u8BC1");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FreezeReader();
			}
		});
		btnLogout.setFont(new Font("����", Font.PLAIN, 16));
		btnLogout.setBounds(626, 604, 116, 31);
		add(btnLogout);

		JScrollPane scrollPane = null;
		try {
			panel.remove(scrollPane);
		} catch (Exception e) {
		}

		ArrayList<AbstractModel> readers = readerDAO.getObjectsByName(RdName.getText());
		Vector<Vector<String>> vectortable = new Vector<Vector<String>>();
		Vector<String> vector = new Vector<String>();
		vector.add("����֤��");
		vector.add("����");
		vector.add("�Ա�");
		vector.add("��������");
		vector.add("��λ");
		vector.add("�绰");
		vector.add("��������");
		vector.add("����֤״̬");
		vector.add("�ѽ�");
		vector.add("ע������");
		for (int j = 0; j < readers.size(); j++) {

			if (((Reader) readers.get(j)).getRdType().equals(rdType)
					&& ((Reader) readers.get(j)).getRdDept().equals(rdDept)
					&& ((Reader) readers.get(j)).getRdName().equals(rdName)) {
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
		resultTable.setRowHeight(32);
		scrollPane = new JScrollPane(resultTable);
		scrollPane.setBounds(20, 20, 750, 470);
		panel.add(scrollPane);
		panel.setLayout(new BorderLayout(0, 0));

		btnChangeInfoConfirm = new JButton("\u786E\u8BA4\u53D8\u66F4\u4FE1\u606F");
		btnChangeInfoConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reader.setRdPhoto(rdPhoto);
				reader.setRdName(textRdName.getText());
				reader.setRdSex(comRdSex.getSelectedItem().toString());
				reader.setRdType(comRdType.getSelectedItem().toString());
				reader.setRdDept(comRdDept.getSelectedItem().toString());
				reader.setRdPhone(textRdPhone.getText());
				reader.setRdEmail(textRdEmail.getText());
				new ReaderPanelHandle(readerPanel, reader, "���¶���");// BLL���ֱ࣬��ͨ�����췽����ɸ��¶�����Ϣ�Ĺ��ܣ�Ҳ����ֻ�����������ʹ���˹��췽��
			}
		});
		btnChangeInfoConfirm.setFont(new Font("����", Font.PLAIN, 16));
		btnChangeInfoConfirm.setBounds(1009, 604, 148, 31);
		add(btnChangeInfoConfirm);

		btnNewReaderConfirm = new JButton("\u786E\u8BA4\u529E\u7406\u501F\u4E66\u8BC1");
		btnNewReaderConfirm.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Reader newreader = new Reader();
				newreader.setRdID(textRdID.getText());
				newreader.setRdName(textRdName.getText());
				newreader.setRdSex(comRdSex.getSelectedItem().toString());
				newreader.setRdType(comRdType.getSelectedItem().toString());
				newreader.setRdAdminRole(0);
				newreader.setRdDept(comRdDept.getSelectedItem().toString());
				newreader.setRdPhone(textRdPhone.getText());
				newreader.setRdEmail(textRdEmail.getText());
				newreader.setRdRegDate(new Date().toLocaleString());
				newreader.setRdBrorrowNum(0);
				newreader.setRdStatus("����");
				newreader.setRdPhoto(rdPhoto);
				new ReaderPanelHandle(readerPanel, newreader, "����¶���");

			}
		});
		btnNewReaderConfirm.setFont(new Font("����", Font.PLAIN, 16));
		btnNewReaderConfirm.setBounds(855, 604, 149, 31);
		add(btnNewReaderConfirm);
		panel.validate();

		resultTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String string = resultTable.getModel().getValueAt(resultTable.getSelectedRow(), 0).toString();
				reader = (Reader) readerDAO.getObjectByID(string);
				textRdID.setText(reader.getRdID());
				textRdName.setText(reader.getRdName());
				comRdSex.setSelectedItem(reader.getRdSex());
				String role = "���� ";
				if ((((Reader) UserConfiguration.Reader).getRdAdminRole() & 1) != 0) {
					role += "����/����֤����Ա ";
				}

				if ((((Reader) UserConfiguration.Reader).getRdAdminRole() & 2) != 0) {
					role += "ͼ�����Ա ";
				}

				if ((((Reader) UserConfiguration.Reader).getRdAdminRole() & 4) != 0) {
					role += "����/���Ĺ���Ա ";
				}
				if ((((Reader) UserConfiguration.Reader).getRdAdminRole() & 8) != 0) {
					role += "ϵͳ����Ա ";
				}
				lblRdRole.setToolTipText(role);
				comRdDept.setSelectedItem(reader.getRdDept());
				comRdType.setSelectedItem(reader.getRdType());
				textRdEmail.setText(reader.getRdEmail());
				textRdPhone.setText(reader.getRdPhone());
				rdPhoto = reader.getRdPhoto();
				lblRdBorrowed.setText(String.valueOf(reader.getRdBrorrowNum()));
				lblRdStatus.setText(reader.getRdStatus());
				textRdRegDate.setText(reader.getRdRegDate());
				textPwd.setText("********");
				resultTable.setColumnSelectionAllowed(false);
				resultTable.setRowSelectionAllowed(true);
				resultTable.setSelectionBackground(Color.green);
				resultTable.setToolTipText("����" + (CoreConfiguration.DBtype == 0 ? "Զ��MS SQLSERVER������" : "����MySQL������"));
				UserConfiguration.Reader = (AbstractModel) reader;// ������Reader����֮��Ҫ��UserConfiguration�����Reader
				rdPhoto = reader.getRdPhoto();
				try {
					ImageIcon icon = new ImageIcon(rdPhoto);
					lblRdPhoto.setIcon(icon);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "�ö��߻�û����Ƭ���뼰ʱΪ�ö����ύ��Ƭ��");
					lblRdPhoto.setIcon(null);
				}
			}
		});

		try {
			textRdID.setText(reader.getRdID());
			textRdName.setText(reader.getRdName());
			comRdSex.setSelectedItem(reader.getRdSex());
			comRdDept.setSelectedItem(reader.getRdDept());
			comRdType.setSelectedItem(reader.getRdType());
			textRdEmail.setText(reader.getRdEmail());
			textRdPhone.setText(reader.getRdPhone());
			lblRdBorrowed.setText(String.valueOf(reader.getRdBrorrowNum()));
			lblRdStatus.setText(reader.getRdStatus());
			textRdRegDate.setText(reader.getRdRegDate());
			textPwd.setText("********");

			lblRdPhoto = new JLabel("");
			lblRdPhoto.setBounds(245, 41, 95, 134);
			panel_1.add(lblRdPhoto);

		} catch (Exception e2) {
		}
		RdDept.setSelectedItem(rdDept);
		RdName.setText(rdName);

		JButton btnCancel = new JButton("\u53D6\u6D88");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.setContentPane(new ReaderPanel(mainWindow, rdType, rdDept, rdName));
			}
		});
		btnCancel.setFont(new Font("����", Font.PLAIN, 16));
		btnCancel.setBounds(1163, 604, 68, 31);
		add(btnCancel);
		
		lblRdRole = new JLabel("\u60AC\u505C\u9F20\u6807\u67E5\u770B");
		lblRdRole.setHorizontalAlignment(SwingConstants.CENTER);
		lblRdRole.setFont(new Font("����", Font.ITALIC, 13));
		lblRdRole.setBounds(146, 185, 89, 26);
		panel_1.add(lblRdRole);
		try {
			inNormal();// �л�����״̬
			rdPhoto = reader.getRdPhoto();
			ImageIcon icon = new ImageIcon(rdPhoto);
			lblRdPhoto.setIcon(icon);

			
			String role = "���� ";
			if ((((Reader) UserConfiguration.Reader).getRdAdminRole() & 1) != 0) {
				role += "����/����֤����Ա ";
			}

			if ((((Reader) UserConfiguration.Reader).getRdAdminRole() & 2) != 0) {
				role += "ͼ�����Ա ";
			}

			if ((((Reader) UserConfiguration.Reader).getRdAdminRole() & 4) != 0) {
				role += "����/���Ĺ���Ա ";
			}
			if ((((Reader) UserConfiguration.Reader).getRdAdminRole() & 8) != 0) {
				role += "ϵͳ����Ա ";
			}
			lblRdRole.setToolTipText(role);
		} catch (Exception e) {
		}

	}

	public boolean LockReader() {// ��ʧ����,��Ҫ������Reader�����Ͳ������ݹ�ȥ
		reader = (Reader) readerDAO.getObjectByName(RdName.getText());
		reader.setRdStatus("��ʧ");

		try {
			new ReaderPanelHandle(readerPanel, reader, "��ʧ");
			new SerachReaderHandle(mainWindow, RdType.getSelectedItem().toString(), RdDept.getSelectedItem().toString(),
					RdName.getText());// ���������Ķ���panel��table
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean UnlockReader() {
		reader = (Reader) readerDAO.getObjectByName(RdName.getText());
		reader.setRdStatus("����");
		try {
			new ReaderPanelHandle(readerPanel, reader, "���");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		new SerachReaderHandle(mainWindow, RdType.getSelectedItem().toString(), RdDept.getSelectedItem().toString(),
				RdName.getText());
		return true;

	}

	public boolean FreezeReader() {

		if (JOptionPane.showConfirmDialog(null, "���Ƿ������ע�����û���ע������������ת!") == 0) {
			reader.setRdStatus("ע��");
			try {
				new ReaderPanelHandle(readerPanel, reader, "ע��");
				new SerachReaderHandle(mainWindow, RdType.getSelectedItem().toString(),
						RdDept.getSelectedItem().toString(), RdName.getText());
				return true;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;

	}

	public void inAdd() {// �޸�Ȩ�޵Ľ������ʹ�õ�һ�������ұߵ�����panel��ֻҪ��һ��ϸ��
		RdName.setEnabled(true);
		textRdRegDate.setEnabled(true);
		textRdEmail.setEnabled(true);
		textRdPhone.setEnabled(true);
		textRdName.setEnabled(true);
		textRdID.setEnabled(true);
		textPwd.setEnabled(true);
		btnNewReader.setEnabled(false);
		uploadPhoto.setEnabled(true);
		btnChangeReaderInfo.setEnabled(false);
		comRdSex.setEnabled(true);
		comRdType.setEnabled(true);
		// comRdRole.setEnabled(true);//�ڳ�������ԱȨ�������������ſ��Ա���
		comRdDept.setEnabled(true);

		btnLockReader.setEnabled(false);
		btnUnlockReader.setEnabled(false);
		btnLogout.setEnabled(false);

		btnChangeInfoConfirm.setEnabled(false);
		btnNewReaderConfirm.setEnabled(true);
	}

	public void inUpdate() {
		RdName.setEnabled(true);
		textRdRegDate.setEnabled(false);
		textRdEmail.setEnabled(true);
		textRdPhone.setEnabled(true);
		textRdName.setEnabled(true);
		textRdID.setEnabled(false);
		textPwd.setEnabled(false);
		btnNewReader.setEnabled(false);
		uploadPhoto.setEnabled(true);
		btnChangeReaderInfo.setEnabled(false);
		comRdSex.setEnabled(true);
		comRdType.setEnabled(true);
		// comRdRole.setEnabled(true);
		comRdDept.setEnabled(true);

		btnLockReader.setEnabled(false);
		btnUnlockReader.setEnabled(false);
		btnLogout.setEnabled(false);

		btnChangeInfoConfirm.setEnabled(true);
		btnNewReaderConfirm.setEnabled(false);

	}

	public void inNormal() {
		textRdRegDate.setEnabled(false);
		textRdEmail.setEnabled(false);
		textRdPhone.setEnabled(false);
		textRdName.setEnabled(false);
		textRdID.setEnabled(false);
		textPwd.setEnabled(false);
		btnNewReader.setEnabled(true);
		uploadPhoto.setEnabled(false);
		btnChangeReaderInfo.setEnabled(true);
		comRdSex.setEnabled(false);
		uploadPhoto.setEnabled(false);
		comRdType.setEnabled(false);
		comRdDept.setEnabled(false);

		btnLockReader.setEnabled(reader.getRdStatus().equals("����"));
		btnUnlockReader.setEnabled(reader.getRdStatus().equals("��ʧ"));
		btnLogout.setEnabled(!reader.getRdStatus().equals("ע��"));

		btnChangeInfoConfirm.setEnabled(false);
		btnNewReaderConfirm.setEnabled(false);

	}
}
