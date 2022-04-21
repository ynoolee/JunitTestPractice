package com.example.test.test.effective.item10;

import java.util.Objects;

// Broken - violates symmetry!  (Page 39)
public final class CaseInsensitiveString {
	private final String s;

	public CaseInsensitiveString(String s) {
		this.s = Objects.requireNonNull(s);
	}

	// Broken - violates symmetry!
	@Override
	public boolean equals(Object o) {
		if (o instanceof CaseInsensitiveString)
			return s.equalsIgnoreCase(
				((CaseInsensitiveString)o).s);
		if (o instanceof String)  // One-way interoperability!
			return s.equalsIgnoreCase((String)o);
		return false;
	}

}
