package com.example.test.test.effective.item8;

import java.lang.ref.Cleaner;

// Cleaner 를 써서 안정망으로 사용하는 방법
public class SampleResourceSafetyCleaner implements AutoCloseable{

	private boolean closed;

	private static final Cleaner CLEANER = Cleaner.create();

	// 근데 실제 청소하려면 Cleaner 가 아닌 Cleanable 이 필요함
	private final Cleaner.Cleanable cleanable;
	// Cleaner.Cleanable 로 clean 할 때는 별도의 스레드가 필요함.
	// finalize() 는 gc 를 처리하는 스레드가 호출했을 텐 여기서는 clean 을 위한 별도의 스레들르 만들어줘야 한다.

	public SampleResourceSafetyCleaner() {
		ResourceCleaner resourceCleaner = new ResourceCleaner(); // 클리너를 위한 스레드 생성
		this.cleanable = CLEANER.register(this, resourceCleaner); // 이 인스턴스를 정리 할 때 이 클리너를 사용하라고 등록해 놓는 것.
		// 실제 클린은 close() 에서
	}

	private static class ResourceCleaner implements Runnable {
		// 정리 할 리소스가 여기 있어야 한다.
		// 그런데 여기에 SampleResource 참조타입을 여기에 넣으면 안됨 -> 순환참조 일어나게 된다.
		@Override
		public void run() {
			System.out.println("Clean");
		}
	}

	@Override
	public void close() {
		if(this.closed) {
			throw new IllegalArgumentException();
		}
		closed = true;
		cleanable.clean();
	}
	public void hello(){
		System.out.println("hello");
	}

	@Override
	protected void finalize() throws Throwable {
		if(!this.closed) close();
	}
}
