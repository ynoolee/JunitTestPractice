package com.example.test.test.effective.item10;

import java.awt.*;

public class ColorPointAvoidLSP extends PointAvoidLSP {
	private final Color color;

	public ColorPointAvoidLSP(int x, int y, Color color) {
		super(x, y);
		this.color = color;
	}
}
