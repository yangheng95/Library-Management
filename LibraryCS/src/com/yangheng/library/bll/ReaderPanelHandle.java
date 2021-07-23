package com.yangheng.library.bll;

import javax.swing.JOptionPane;

import com.yangheng.library.dao.ReaderDAO;
import com.yangheng.library.gui.ReaderPanel;
import com.yangheng.library.model.Reader;

public class ReaderPanelHandle {

	ReaderDAO readerDAO = new ReaderDAO();

	public ReaderPanelHandle(ReaderPanel readerPanel, Reader reader, String op) {

		switch (op) {
		case "��ʧ":
			try {
				readerDAO.update(reader);
				readerPanel.inNormal();
				JOptionPane.showMessageDialog(null, "��ʧ�ɹ���");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "δ֪ԭ���²���ʧ�ܣ�");
			}
			break;
		case "���":
			try {
				readerDAO.update(reader);
				readerPanel.inNormal();
				JOptionPane.showMessageDialog(null, "�����ʧ�ɹ���");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "δ֪ԭ���²���ʧ�ܣ�");
				e.printStackTrace();
			}
			break;
		case "ע��":
			try {
				readerDAO.update(reader);
				readerPanel.inNormal();
				JOptionPane.showMessageDialog(null, "ע���û��ɹ���");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "����ʧ�ܣ����û�����"+reader.getRdBrorrowNum()+"��ͼ��û�й黹��");
				e.printStackTrace();
			}
			break;
		case "����¶���":
			try {
				readerDAO.add(reader);
				readerPanel.inNormal();
				JOptionPane.showMessageDialog(null, "����¶��߳ɹ���");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "δ֪ԭ���²���ʧ�ܣ�");
				e.printStackTrace();
			}
			break;
		case "���¶���":
			try {
				readerDAO.update(reader);
				readerPanel.inNormal();
				JOptionPane.showMessageDialog(null, "���¶��߳ɹ���");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "δ֪ԭ���²���ʧ�ܣ�");
				e.printStackTrace();
			}
			break;
		default:
			break;
		}

	}

}
