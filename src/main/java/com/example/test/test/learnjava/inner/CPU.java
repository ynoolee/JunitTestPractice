package com.example.test.test.learnjava.inner;

public class CPU {
	double price;
	// nested class
	class Processor{
		double cores;
		String manufacturer;

		double getCache(){
			return 4.3;
		}
	}
	protected class RAM{
		double memory;
		String manufacturer;

		double getClockSpeed(){
			return 5.5;
		}
	}
}
