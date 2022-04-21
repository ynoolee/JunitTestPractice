package com.example.test.test.effective.item10;

public class Point {
	protected final int x;
	protected final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override public boolean equals(Object o) {
		if (!(o instanceof Point))
			return false;
		Point p = (Point)o;
		return p.x == x && p.y == y;
	}

	// See Item 11
	@Override public int hashCode()  {
		return 31 * x + y;
	}
}
