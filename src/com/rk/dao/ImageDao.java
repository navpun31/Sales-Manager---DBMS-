package com.rk.dao;

import java.util.List;

import com.rk.model.Image;

public interface ImageDao {
	public void save(Image image);
	public List<Image> list();
	public Image get(Integer id);
	public void remove(Integer id);
	public List<Image> list(String productID);
}
