package com.example.test.test.effective.item10;

import java.awt.*;
// 대치성 위반
public class ColorPoint extends Point{
	private final Color color;

	public ColorPoint(int x, int y, Color color) {
		super(x, y);
		this.color = color;
	}

	@Override
	public boolean equals(Object o) {
		if(!(o instanceof ColorPoint) )return false;
		return super.equals((ColorPoint)o) && ((ColorPoint)o).color.equals(color);
	}
}
