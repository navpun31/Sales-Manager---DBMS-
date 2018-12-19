package com.rk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rk.dao.ColorDao;
import com.rk.model.Color;

@Service("colorService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ColorServiceImpl implements ColorService {
	@Autowired
	private ColorDao colorDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addColor(Color color) {
		colorDao.addColor(color);
	}

	public List<Color> listColors() {
		return colorDao.listColors();
	}

	public Color getColor(String colorCode) {
		return colorDao.getColor(colorCode);
	}

	public void deleteColor(Color color) {
		colorDao.deleteColor(color);
	}
}
