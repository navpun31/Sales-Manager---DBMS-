package com.rk.service;

import java.util.List;

import com.rk.model.Color;

public interface ColorService {
	public void addColor(Color color);
	public List<Color> listColors();
	public Color getColor(String colorCode);
	public void deleteColor(Color color);
}
