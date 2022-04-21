package com.example.test.test.learnjava.inner;

import com.example.test.test.learnjava.inner.CPU.Processor;
import com.example.test.test.learnjava.inner.CPU.RAM;

public class CpuMain {

	public static void main(String[] args) {
		CPU cpu = new CPU();

		// outer class 를 사용해 inner class 객체 생성
		Processor processor = cpu.new Processor();
		// outer class 를 사용해 inner class 객체 생성
		RAM ram = cpu.new RAM();
		System.out.println("processor.getCache() = " + processor.getCache());
		System.out.println("ram.getClockSpeed() = " + ram.getClockSpeed());
	}
}
