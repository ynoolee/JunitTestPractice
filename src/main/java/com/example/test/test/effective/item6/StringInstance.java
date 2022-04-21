package com.example.test.test.effective.item6;

public class StringInstance {
	private static void appendVarietyTypeToString(){
		String str = 3+"abc"+5+"def"+Integer.valueOf(200);
	}
	private static void appendOnlyString(){
		String str = "3"+"abc"+"5"+"def"+"200";
	}

	public static void main(String[] args) {
		appendOnlyString();
		appendVarietyTypeToString();
	}
}
