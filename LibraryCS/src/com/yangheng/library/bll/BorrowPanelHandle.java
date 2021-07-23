package com.yangheng.library.bll;

import java.awt.HeadlessException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import com.yangheng.library.configuration.UserConfiguration;
import com.yangheng.library.dao.BookLibDAO;
import com.yangheng.library.dao.BorrowRecordDAO;
import com.yangheng.library.dao.ReaderDAO;
import com.yangheng.library.dao.ReaderTypeDAO;
import com.yangheng.library.gui.BorrowPanel;
import com.yangheng.library.gui.MainWindow;
import com.yangheng.library.model.AbstractModel;
import com.yangheng.library.model.BookLib;
import com.yangheng.library.model.BorrowRecord;
import com.yangheng.library.model.Reader;
import com.yangheng.library.model.ReaderType;

public class BorrowPanelHandle {

	MainWindow mainWindow;
	BookLibDAO bookLibDAO = new BookLibDAO();
	BorrowRecordDAO borrowRecordDAO = new BorrowRecordDAO();
	ReaderTypeDAO readerTypeDAO = new ReaderTypeDAO();
	ReaderDAO readerDAO = new ReaderDAO();

	public BorrowPanelHandle(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	public void PaintBorrowPanel() {

		mainWindow.setContentPane(new BorrowPanel(mainWindow));
		mainWindow.getRootPane().updateUI();
		mainWindow.getRootPane().validate();
		// UserConfiguration.borrowPanel.validate();

	}

	@SuppressWarnings("deprecation")
	public void DoHandle(String handel, long bkID) {

		BorrowRecord borrowRecord = null;
		BookLib bookLib = null;
		ReaderType readerType = (ReaderType) readerTypeDAO
				.getObjectByID(((Reader) (UserConfiguration.Reader)).getRdType());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date start = null, end = new Date();// end即是当前时间
		switch (handel) {
		case "还书":
			borrowRecord = (BorrowRecord) borrowRecordDAO.getObjectByID(String.valueOf(bkID));
			bookLib = (BookLib) bookLibDAO.getObjectByID(borrowRecord.getBkID());
			// System.out.println(borrowRecord );

			try {

				start = sdf.parse(borrowRecord.getDateShouldRet().replace("-1-", "-01-").replace("-2-", "-02-")
						.replace("-3-", "-03-").replace("-4-", "-04-").replace("-5-", "-05-").replace("-6-", "-06-")
						.replace("-7-", "-07-").replace("-8-", "-08-").replace("-9-", "-09-"));
				bookLib.setBkStatus("在馆");
		
				borrowRecord.setReturned(1);
				borrowRecord.setDateActRet(new Date().toLocaleString());
				borrowRecord.setOperatorRet(((Reader) UserConfiguration.user).getRdName());
				int daysbetween = (int) ((end.getTime() - start.getTime()) / (long) 86400000f);
				if (daysbetween > 0) {
					borrowRecord.setOverDay(daysbetween);
					borrowRecord.setPunishMoney(readerType.getRdPunishRate() * (daysbetween));

				}
				((Reader) UserConfiguration.Reader)
						.setRdBrorrowNum(((Reader) UserConfiguration.Reader).getRdBrorrowNum() - 1);
				JOptionPane.showMessageDialog(null, "还书成功！");

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "还书失败！");
				e.printStackTrace();
			}
			bookLibDAO.update(bookLib);
			break;

		case "借书":

			bookLib = (BookLib) bookLibDAO.getObjectByID(bkID);
			borrowRecord = (BorrowRecord) borrowRecordDAO.getObjectByID(String.valueOf(bkID));

			try {
				if (((Reader) UserConfiguration.Reader).getRdBrorrowNum() + 1 <= readerType.getRdCanLendSum()
						&& ((Reader) (UserConfiguration.Reader)).getRdStatus().equals("正常")) {
					ArrayList<AbstractModel> pubishedBorrowRecord = borrowRecordDAO
							.getPunishMoneyByRdName(((Reader) UserConfiguration.Reader).getRdID());
					int punishMoney = 0;
					for (int i = 0; i < pubishedBorrowRecord.size(); i++) {
						punishMoney += ((BorrowRecord) pubishedBorrowRecord.get(i)).getPunishMoney();
					}
					if (punishMoney > 0) {

						if (!(JOptionPane.showConfirmDialog(null, "尚有"+punishMoney+"罚款未缴！请缴清后点击确定借书！") == JOptionPane.YES_OPTION))
							return;

					}
					for (int i = 0; i < pubishedBorrowRecord.size(); i++) {
						((BorrowRecord) pubishedBorrowRecord.get(i)).setPunishMoney(0);
						borrowRecordDAO.update(pubishedBorrowRecord.get(i));
					}
					String borrowid = end.getYear()+1900 + "" + (end.getMonth() + 1) + "" + end.getDay() + ""
							+ end.getHours() + "" + end.getMinutes() + "" + end.getSeconds() + ""
							+ (int) (10 * Math.random());
					BorrowRecord newborrowRecord = new BorrowRecord();
					newborrowRecord.setBorrowID(borrowid);
					newborrowRecord.setBkID(bookLib.getBkID());
					newborrowRecord.setRdID(((Reader) UserConfiguration.Reader).getRdID());
					newborrowRecord.setContinueTimes(0);
					newborrowRecord.setDateOut(end.toLocaleString().split(" ")[0]);// end是当前时间
					end.setTime((long) ((long) end.getTime() + 2678400000f));
					newborrowRecord.setDateShouldRet(end.toLocaleString().split(" ")[0]);
					newborrowRecord.setOperatorLend(((Reader) UserConfiguration.user).getRdName());
					newborrowRecord.setOverDay(0);
					newborrowRecord.setPunishMoney(0);
					newborrowRecord.setReturned(0);
					borrowRecordDAO.add(newborrowRecord);
					((Reader) UserConfiguration.Reader)
							.setRdBrorrowNum(((Reader) UserConfiguration.Reader).getRdBrorrowNum() + 1);
					readerDAO.update(((Reader) UserConfiguration.Reader));
					bookLib.setBkStatus("借出");

					if (borrowRecordDAO.getObjectByID(borrowid) != null && bookLibDAO.update(bookLib)) {
						JOptionPane.showMessageDialog(null, "借书成功！");
						bookLibDAO.update(bookLib);
					} else {
						JOptionPane.showMessageDialog(null, "借书异常！");
					}

				} else {
					JOptionPane.showMessageDialog(null, "借书失败，超过最大可借数量，或者借阅证已过期！");
					return;
				}
			} catch (HeadlessException e) {
				JOptionPane.showMessageDialog(null, "借书异常！");
				e.printStackTrace();
				return;
			}

			break;
		case "续借":
			borrowRecord = (BorrowRecord) borrowRecordDAO.getObjectByID(String.valueOf(bkID));
			bookLib = (BookLib) bookLibDAO.getObjectByID(borrowRecord.getBkID());
			Date newEnd = null;
			try {
				newEnd = sdf.parse(borrowRecord.getDateShouldRet());

				newEnd.setTime((long) ((long) (sdf.parse(borrowRecord.getDateShouldRet()).getTime()) + 2678400000f));

				if (borrowRecord.getContinueTimes() < readerType.getRdRelendLimit()) {
					borrowRecord.setContinueTimes(borrowRecord.getContinueTimes() + 1);
					borrowRecord.setDateShouldRet(newEnd.toLocaleString().split(" ")[0]);
					JOptionPane.showMessageDialog(null, "续借成功！");
				} else
					JOptionPane.showMessageDialog(null, "已达到最大可续借次数，不可再续借！");
			} catch (ParseException e2) {
				JOptionPane.showMessageDialog(null, "续借异常！");
				e2.printStackTrace();
			}
			break;
		default:

			break;
		}
		// bookLibDAO.update(bookLib);
		readerDAO.update(UserConfiguration.Reader);
		borrowRecordDAO.update(borrowRecord);

	}

}
