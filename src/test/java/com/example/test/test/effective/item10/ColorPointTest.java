package com.example.test.test.effective.item10;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ColorPointTest {
	@Test
	public void given_ColorPointAndPoint_when_EqualityEvaluated_then_SymmetryFails() {
		ColorPoint cp = new ColorPoint(1, 2, new Color(100));
		Point p = new Point(1, 2);

		assertThat(cp.equals(p) == p.equals(cp), is(Boolean.FALSE));
	}

	@Test
	public void given_ColorPointAndPoint_when_EqualityEvaluatedByUsingEqualsOfColorPoint_then_Fails() {
		ColorPoint cp = new ColorPoint(1, 2, new Color(100));
		Point p = new Point(1, 2);

		assertThat(cp.equals(p), is(Boolean.FALSE));
	}

	@Test
	public void given_ColorPointRefactoredAndPoint_when_EqualityEvaluated_then_SymmetrySuccess(){
		Point p = new Point(1,2);
		ColorPointRefactoredOne cp = new ColorPointRefactoredOne(1,2,new Color(100));

		assertThat(cp.equals(p) == p.equals(cp), is(Boolean.TRUE));
	}

	@Test
	public void given_ThreePointType_when_EqualityEvaluated_thenFailTransitivity(){
		Point p = new Point(1,2);
		ColorPointRefactoredOne cp1 = new ColorPointRefactoredOne(1,2,new Color(100));
		ColorPointRefactoredOne cp2 = new ColorPointRefactoredOne(1,2,new Color(120));

		assertThat(p.equals(cp1), is(Boolean.TRUE));
		assertThat(p.equals(cp2), is(Boolean.TRUE));
		assertThat(cp1.equals(cp2), is(Boolean.FALSE));
	}

	@Test
	@DisplayName("자기 타입의 인스턴스가 아닌 경우에는 해당 인스턴스 클래스의 equals 를 호출하는 방식의 구현을 한 경우,두 서브타입 클래스 객체 사이에 equals 를 호출할 경우 무한 재귀에 빠진다.")
	public void given_TwoPointSubclassTypedInstance_when_EqualityEvaluated_thenStackOverFlowBecauseOfRecursion(){
		ColorPointRefactoredOne cp1 = new ColorPointRefactoredOne(1,2,new Color(100));
		ColorPointRefactoredAnotherOne cp2 = new ColorPointRefactoredAnotherOne(1,2,new Color(100));

		assertThrows(StackOverflowError.class, ()-> cp1.equals(cp2));
	}
	// 채우는거 가능?
	private <T> boolean isPossibleToFill(Set<T> set,T obj){
		if(set.contains(obj))return false;
		set.add(obj);
		return true;
	}
	@Test
	@DisplayName("이미 칠해져 있는 칸에 칠하려 하는 경우에도 칠해버린다")
	public void given_CoordinateFilledWithPointsAndColorPoints_when_TryingAddingPointToPositionAlreadyFilled_thenSuccess(){
		Set<PointAvoidLSP> coord = new HashSet<>();
		coord.add(new PointAvoidLSP(1,2));
		coord.add(new ColorPointAvoidLSP(2,4,new Color(100)));
		coord.add(new PointAvoidLSP(1,3));

		assertThat(isPossibleToFill(coord,new PointAvoidLSP(2,4)), is(Boolean.TRUE));
		assertThat(isPossibleToFill(coord,new ColorPointAvoidLSP(1,2, new Color(100))), is(Boolean.TRUE));
	}

}