package com.example.test.test.effective.item10;

import java.util.Objects;

public class CaseInsensitiveStringRefactored {
	private final String s;

	public CaseInsensitiveStringRefactored(String s) {
		this.s = Objects.requireNonNull(s);
	}

	// Broken - violates symmetry!
	@Override
	public boolean equals(Object o) {
		return o instanceof CaseInsensitiveStringRefactored &&
			((CaseInsensitiveStringRefactored)o).s.equalsIgnoreCase(s);

	}

}
