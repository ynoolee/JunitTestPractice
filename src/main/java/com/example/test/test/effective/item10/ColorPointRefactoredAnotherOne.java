package com.example.test.test.effective.item10;

import java.awt.*;

// 대치성 해결, 추이성 위반
public class ColorPointRefactoredAnotherOne extends Point{
	private final Color color;

	public ColorPointRefactoredAnotherOne(int x, int y, Color color) {
		super(x, y);
		this.color = color;
	}

	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Point) )return false;
		// ColorPointRefactoredOne 타입이 아니면 색상을 무시하고 비교
		// 이렇게 생각한 것 : Point 타입이면서 ColorPointRefactoredOne 타입이 아닌 것 -> Point 타입
		if(!(o instanceof ColorPointRefactoredAnotherOne)) return o.equals(this);
		return super.equals((ColorPointRefactoredAnotherOne)o) && ((ColorPointRefactoredAnotherOne)o).color.equals(color); // == 으로 비교중
	}
}
