package com.yangheng.library.dao;

import java.util.ArrayList;

import com.yangheng.library.model.AbstractModel;

public abstract class AbstractDAO{
	public abstract ArrayList<AbstractModel> getAllObjects();
	public abstract boolean add(AbstractModel model);
	public abstract boolean delete(AbstractModel model);
	public abstract boolean update(AbstractModel model);
	public abstract AbstractModel getObjectByID(int id);
	public abstract AbstractModel getObjectByID(String id);
	
}
