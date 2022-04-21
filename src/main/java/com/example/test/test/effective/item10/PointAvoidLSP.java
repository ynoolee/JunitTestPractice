package com.example.test.test.effective.item10;

import java.awt.*;

public class PointAvoidLSP {
	private final int x;
	private final int y;

	public PointAvoidLSP(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != getClass())
			return false;
		PointAvoidLSP p = (PointAvoidLSP)o;
		return p.x == x && p.y == y;
	}
}
