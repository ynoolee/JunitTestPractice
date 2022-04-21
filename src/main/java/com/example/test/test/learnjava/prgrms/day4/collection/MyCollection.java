package com.example.test.test.learnjava.prgrms.day4.collection;

import com.example.test.test.learnjava.prgrms.day4.iter.MyIterator;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

// 함수형 인터페이스를 사용하여, 사용자가 "기능"을 정의할 수 있는 범용적인 컬렉션을 만들어냄
// 컬렉션은 데이터의 덩어리
public class MyCollection<T> {
	private List<T> list;

	public MyCollection(List<T> list) {
		this.list = list;
	}

	// 이 메소드에서만 사용하는 제네릭 타입 U
	public <U> MyCollection<U> map(Function<T,U> function){
		List<U> newList = new ArrayList<>();
		forEach(d -> newList.add(function.apply(d)));
		return new MyCollection<>(newList);
	}

	public MyCollection<T> filter(Predicate<T> predicate){
		List<T> newList = new ArrayList<>();
		forEach(d -> {
			if(predicate.test(d)) newList.add(d);
		});
		return new MyCollection<>(newList);
	}
	public int size(){
		return list.size();
	}

	public void forEach(Consumer<T> consumer) {
		for (int i =0; i < list.size(); i++) {
			T data = list.get(i);
			consumer.accept(data);
		}
	}

	public MyIterator<T> iterator(){
		return new MyIterator<T>() {
			private int index = 0;
			@Override
			public boolean hasNext() {
				return index < list.size();
			}

			@Override
			public T next() {
				return list.get(index++);
			}
		};
	}
}
