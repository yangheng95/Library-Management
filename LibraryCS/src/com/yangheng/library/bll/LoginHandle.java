package com.yangheng.library.bll;

import javax.swing.JOptionPane;

import com.yangheng.library.configuration.UserConfiguration;
import com.yangheng.library.dao.ReaderDAO;
import com.yangheng.library.gui.LoginWindow;
import com.yangheng.library.gui.MainWindow;
import com.yangheng.library.model.Reader;

public class LoginHandle {

	String ID;
	String Pwd;
	LoginWindow loginWindow;

	public LoginHandle(String ID, String Pwd, LoginWindow loginWindow) {
		this.ID = ID;
		this.Pwd = Pwd;
		this.loginWindow = loginWindow;
	}

	public void doHandle() {
		ReaderDAO dao = new ReaderDAO();
		Reader reader;

		try {
			if ((reader = ((Reader) dao.getObjectByID(ID))) == null){
				JOptionPane.showMessageDialog(null, "���˻������ڣ�������ٵ�¼");
				loginWindow.textAccount.setText("");
				loginWindow.textPwd.setText("");
			}
			else if (reader.getRdPwd().equals(Pwd)) {
				UserConfiguration.setUser(reader);
				UserConfiguration.Reader = UserConfiguration.user;
				new MainWindow().setVisible(true);
				loginWindow.LoginWindow.dispose();
			}else {
				JOptionPane.showMessageDialog(null, "�������˻���ƥ�䣡������ٵ�¼");
				loginWindow.textPwd.setText("");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "�������ݿ������ʧ�ܣ��������磡");
			e.printStackTrace();
		}
		
	}
}
