package com.example.test.test.effective.item8;

// 이런 식으로 "안정망" 으로 쓰는 건 괜춘 ( 보장한다 라고 생각하지 않고 )
public class SampleResourceSafety implements AutoCloseable{
	private boolean closed;

	@Override
	public void close() {
		if(this.closed) {
			throw new IllegalArgumentException();
		}
		closed = true;
		System.out.println("close");
	}
	public void hello(){
		System.out.println("hello");
	}

	@Override
	protected void finalize() throws Throwable {
		if(!this.closed) close();
	}
}
