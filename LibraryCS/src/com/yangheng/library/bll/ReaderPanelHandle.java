package com.yangheng.library.bll;

import javax.swing.JOptionPane;

import com.yangheng.library.dao.ReaderDAO;
import com.yangheng.library.gui.ReaderPanel;
import com.yangheng.library.model.Reader;

public class ReaderPanelHandle {

	ReaderDAO readerDAO = new ReaderDAO();

	public ReaderPanelHandle(ReaderPanel readerPanel, Reader reader, String op) {

		switch (op) {
		case "挂失":
			try {
				readerDAO.update(reader);
				readerPanel.inNormal();
				JOptionPane.showMessageDialog(null, "挂失成功！");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "未知原因导致操作失败！");
			}
			break;
		case "解挂":
			try {
				readerDAO.update(reader);
				readerPanel.inNormal();
				JOptionPane.showMessageDialog(null, "解除挂失成功！");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "未知原因导致操作失败！");
				e.printStackTrace();
			}
			break;
		case "注销":
			try {
				readerDAO.update(reader);
				readerPanel.inNormal();
				JOptionPane.showMessageDialog(null, "注销用户成功！");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "操作失败！该用户还有"+reader.getRdBrorrowNum()+"本图书没有归还！");
				e.printStackTrace();
			}
			break;
		case "添加新读者":
			try {
				readerDAO.add(reader);
				readerPanel.inNormal();
				JOptionPane.showMessageDialog(null, "添加新读者成功！");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "未知原因导致操作失败！");
				e.printStackTrace();
			}
			break;
		case "更新读者":
			try {
				readerDAO.update(reader);
				readerPanel.inNormal();
				JOptionPane.showMessageDialog(null, "更新读者成功！");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "未知原因导致操作失败！");
				e.printStackTrace();
			}
			break;
		default:
			break;
		}

	}

}
