package com.example.test.test.effective.item6;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnnecessaryTest {

	private Map<Integer, String> map;

	@BeforeEach
	public void setup(){
		this.map = new HashMap<>();
		map.put(1,"HELLO");
		map.put(2,"STRANGE");
	}

	@Test
	public void check_IsSameSetInstance() {
		Set<Integer> set1 = map.keySet();
		Set<Integer> set2 = map.keySet();
		assertThat(set1 == set2, is(Boolean.TRUE));
	}
	@Test
	public void check_IsStillSameSetInstance_afterAddNewElementToMap(){
		Set<Integer> set1 = map.keySet();
		map.put(3,"WHAT");
		Set<Integer> set2 = map.keySet();
		assertThat(set1 == set2, is(Boolean.TRUE));
	}


}
