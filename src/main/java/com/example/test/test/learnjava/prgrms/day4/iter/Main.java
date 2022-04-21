package com.example.test.test.learnjava.prgrms.day4.iter;

import com.example.test.test.learnjava.prgrms.day4.collection.MyCollection;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("A", "CA", "DSD","FF","OO");
		Iterator<String> iter = list.iterator();
		// 데이터를 하나씩 가져온다.
		iter.next();
		System.out.println(iter.next());
		System.out.println(iter.next());
		System.out.println(iter.next());

		iter = list.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}

		// MyCollection 의 iterator 를 만들어보자
		MyIterator<String> iter2 = new MyCollection<>(
			Arrays.asList("A", "CA", "DSD", "FF", "OO"))
			.iterator();
		// 결국 하나씩 꺼낸 것에 대해서는 이런 번거로운 처리를 해야한다.
		while(iter2.hasNext()){
			String s = iter.next();
			int len = s.length();
			if(len %2 ==0)continue;
			System.out.println(s);
		}
		// 데이터 한 건한건에 대한 map, filter 같은 함수형 인터페이스를 사용해처리할 수는 없을까???

	}

}
