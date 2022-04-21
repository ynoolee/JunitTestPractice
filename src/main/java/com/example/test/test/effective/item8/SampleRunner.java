package com.example.test.test.effective.item8;

public class SampleRunner {
	public static void main0(){
		NotClosableSampleResource sampleResource = new NotClosableSampleResource();
		sampleResource.hello();
	}

	public static void main2(){
		SampleResource sampleResource = null;
		try{
			sampleResource = new SampleResource();
			sampleResource.hello();
		}finally{
			sampleResource.close();
		}
	}
	// Java 7 부터는 main2() 처럼 하지 않아도 됨. 이게 가장 이상적
	public static void main3() throws Exception {
		try (SampleResource sampleResource = new SampleResource()) {
			sampleResource.hello();
		}
	}
	// Ideally , the best way is for client to call close()
	// 안정망 : finalize() 에서 close() 호출 해 주는 것
	public static void main4() {
		SampleResourceSafetyCleaner sampleResourceSafetyCleaner = new SampleResourceSafetyCleaner();
		sampleResourceSafetyCleaner.hello();
		// hello 만 출력 - gc 가 안되고 그냥 끝난거니 finalize() 호출이 안되고 끝났으니.
	}
	public static void main4_5() {
		SampleResourceSafetyCleaner sampleResourceSafetyCleaner = new SampleResourceSafetyCleaner();
		sampleResourceSafetyCleaner.hello();
		sampleResourceSafetyCleaner.close();
	}
	// try - with - resources
	public static void main5() throws Exception {
		try(SampleResourceSafetyCleaner sampleResourceSafetyCleaner = new SampleResourceSafetyCleaner()){
			sampleResourceSafetyCleaner.hello();
		}
	}

	public static void main(String[] args) throws Exception {
		SampleRunner sampleRunner = new SampleRunner();
		sampleRunner.main4_5(); // main0 메소드 호출 종료로, SampleResource 가 더이상 유효하지 않은 객체가 되었음에도  finalize 는 호출되지 않음
	}

}
