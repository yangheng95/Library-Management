package com.yangheng.library.bll;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.yangheng.library.model.AbstractModel;
import com.yangheng.library.model.Reader;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class OutputToExcelHandle {
	
	public OutputToExcelHandle(ArrayList<AbstractModel> readers) {
		OutputToExcel(readers);
	}
	
	public void OutputToExcel(ArrayList<AbstractModel> readers) {
		WritableWorkbook wb = null;
		File file = new File("D://"+((Reader)readers.get(0)).getRdName()+".xls");
		OutputStream os = null;
		try {
			if (!file.exists())
				file.createNewFile();
			os = new FileOutputStream(file);
			wb = Workbook.createWorkbook(os);
			WritableSheet sheet = wb.createSheet("sheet", 0);
			Label[] label = new Label[10];
			label[0] = new Label(0, 0, "借书证号");
			label[1] = new Label(1, 0, "姓名");
			label[2] = new Label(2, 0, "性别");
			label[3] = new Label(3, 0, "读者类型");
			label[4] = new Label(4, 0, "院系/单位");
			label[5] = new Label(5, 0, "电话");
			label[6] = new Label(6, 0, "电子邮箱");
			label[7] = new Label(7, 0, "借阅证状态");
			label[8] = new Label(8, 0, "已借");
			label[9] = new Label(9, 0, "注册日期");
			sheet.addCell(label[0]);
			sheet.addCell(label[1]);
			sheet.addCell(label[2]);
			sheet.addCell(label[3]);
			sheet.addCell(label[4]);
			sheet.addCell(label[5]);
			sheet.addCell(label[6]);
			sheet.addCell(label[7]);
			sheet.addCell(label[8]);
			sheet.addCell(label[9]);
			for (int i = 0; i < readers.size(); i++) {
				Reader reader = (Reader)(readers.get(0));
				label[0] = new Label(0, i+1, reader.getRdID());
				label[1] = new Label(1, i+1, reader.getRdName());
				label[2] = new Label(2, i+1, reader.getRdSex());
				label[3] = new Label(3, i+1, reader.getRdType());
				label[4] = new Label(4, i+1, reader.getRdDept());
				label[5] = new Label(5, i+1, reader.getRdPhone());
				label[6] = new Label(6, i+1, reader.getRdEmail());
				label[7] = new Label(7, i+1, reader.getRdStatus());
				label[8] = new Label(8, i+1, String.valueOf(reader.getRdBrorrowNum()));
				label[9] = new Label(9, i+1, reader.getRdRegDate());
				sheet.addCell(label[0]);
				sheet.addCell(label[1]);
				sheet.addCell(label[2]);
				sheet.addCell(label[3]);
				sheet.addCell(label[4]);
				sheet.addCell(label[5]);
				sheet.addCell(label[6]);
				sheet.addCell(label[7]);
				sheet.addCell(label[8]);
				sheet.addCell(label[9]);
			}
			wb.write();
			JOptionPane.showMessageDialog(null, "导出成功！");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (RowsExceededException e1) {
			e1.printStackTrace();
		} catch (WriteException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (wb != null)
					wb.close();
				if (os != null)
					os.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (WriteException e1) {
				e1.printStackTrace();
			}
		}

		try {
			Runtime.getRuntime().exec("explorer D:\\"+((Reader)readers.get(0)).getRdName()+".xls");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	

}
