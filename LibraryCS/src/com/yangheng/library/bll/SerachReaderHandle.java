package com.yangheng.library.bll;

import com.yangheng.library.gui.MainWindow;
import com.yangheng.library.gui.ReaderPanel;

public class SerachReaderHandle {
	
	public SerachReaderHandle(MainWindow mainWindow,String rdType,String rdDept,String rdName) {
		mainWindow.setContentPane(new ReaderPanel(mainWindow,rdType,rdDept,rdName));
	}

}
