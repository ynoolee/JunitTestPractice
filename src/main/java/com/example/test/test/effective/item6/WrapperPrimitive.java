package com.example.test.test.effective.item6;

public class WrapperPrimitive {

	private static long sum(){
		Long sum = 0L;
		for (long i =0;i<=Integer.MAX_VALUE;i++){
			sum += i;
		}
		return sum;
	}

	public static void main(String[] args) {
		sum();
	}
}
