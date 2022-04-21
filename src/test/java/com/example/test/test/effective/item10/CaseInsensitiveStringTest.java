package com.example.test.test.effective.item10;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CaseInsensitiveStringTest {

	@Test
	public void givenCaseInsensitiveStringAndString_WhenEvaluatingEquality_ThenFailSymmetry() {
		CaseInsensitiveString insensitiveString = new CaseInsensitiveString("AbcD");
		String string = "abcd";

		assertThat(string.equals(insensitiveString) == insensitiveString.equals(string),
			is(Boolean.FALSE));
	}

	@Test
	public void given_CaseInsensitiveStringRefactoredAndString_WhenEqualityEvaluatedBetweenThen_ThenSuccessSymmetry(){
		CaseInsensitiveStringRefactored insensitiveString = new CaseInsensitiveStringRefactored("AbcD");
		String string = "abcd";

		assertThat(string.equals(insensitiveString) == insensitiveString.equals(string),
			is(Boolean.TRUE));
	}

	@Test
	public void givenCaseInsensitiveStringAndString_WhenInsensitiveStringAddedToListThenCheckedByString_ThenFalse() {
		CaseInsensitiveString insensitiveString = new CaseInsensitiveString("AbcD");
		String string = "abcd";
		List<CaseInsensitiveString> list = new ArrayList<>();
		list.add(insensitiveString);

		assertThat(list.contains(string), is(Boolean.FALSE));
	}

}