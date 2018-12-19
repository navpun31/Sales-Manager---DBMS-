package com.rk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rk.dao.ImageDao;
import com.rk.model.Image;

@Service("imageService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ImageServiceImpl implements ImageService {
	@Autowired
	private ImageDao imageDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void save(Image image) {
		imageDao.save(image);
	}

	public List<Image> list() {
		return imageDao.list();
	}

	public Image get(Integer id) {
		return imageDao.get(id);
	}
	
	public void remove(Integer id) {
		imageDao.remove(id);
	}
	
	public List<Image> list(String productID){
		return imageDao.list(productID);
	}
}
