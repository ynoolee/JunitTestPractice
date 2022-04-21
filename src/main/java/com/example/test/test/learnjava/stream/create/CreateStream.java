package com.example.test.test.learnjava.stream.create;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import org.springframework.util.Assert;


public class CreateStream {

	static class Person {

		private int income;

		private Person(int income) {
			// validation ..
			this.income = income;
		}
		static Person of(int income){
			return new Person(income);
		}

		public int getIncome() {
			return income;
		}

		@Override
		public String toString() {
			return "Person{" +
				"income=" + income +
				'}';
		}
	}
	private void makeArrayStream() {
		String[] arr = {"a", "b", "c"};
		Stream<String> stream = Arrays.stream(arr);
		stream.forEach(System.out::println);
		System.out.println("===============");
		// index 는 0 부터 시작 한다
		// startInclusive ~ " endInclusive 바로앞 " 까지의 데이터 스트림을 생성한다 (
		Stream<String> partialStream = Arrays.stream(arr, 1, 3);
		partialStream.forEach(System.out::println);
	}

	private void makeCollectionStream() {
		List<String> list = Arrays.asList("a", "b", "c");
		Stream<String> stream = list.stream();
		Stream<String> parallelStream = list.parallelStream();
	}

	private Stream<String> streamOf(List<String> list) {
		return list == null || list.isEmpty()
			? Stream.empty()
			: list.stream();
	}

	// 여전히 Stream 의 empty 를 왜 사용하는지 잘 모르겠다
	private void testEmptyStream() {
		List<String> emptyList = null; // 널

//		emptyList.stream().forEach(System.out::println); // NPE
		Stream<String> nullReplaceableStream = streamOf(emptyList);

		emptyList = new ArrayList(); // 비어있는 리스트

		Stream emptyListStream = streamOf(emptyList);
		emptyListStream.forEach(System.out::println);
	}


	private void makeStreamUsingBuilder() {
		Stream<String> builderStream = Stream.<String>builder()
			.add("Eric").add("Elena").add("Java")
			.build();
	}

	private void makeStreamUsingSupplier() {
		Stream<String> generatedStream = Stream.generate(() -> LocalDateTime.now() + "DAY")
			.limit(5);
		generatedStream.forEach(System.out::println);
	}

	private void makeStreamUsingIterate() {
		Stream<Integer> iteratedStream = Stream.iterate(1, n -> n * 2).limit(30);
		iteratedStream.forEach(System.out::println);
	}

	private void makePrimitiveStream() {
		IntStream intStream = IntStream.range(1, 5);                // 1,2,3,4
		LongStream longStream = LongStream.rangeClosed(1, 5);        //1,2,3,4,5 (5 도 포함)
		List<Long> collect = longStream.boxed().collect(Collectors.toList());
	}

	/**===================================================*/
	private Stream<Integer> makeRandomPrimitiveStream() {
		IntStream intStream = new Random().ints(11); // DoubleStream 은 Stream<T> 에 못들어감
		return intStream.boxed();
	}

	// 생성된 난수 스트림에서, 조건을 만족하는 데이터의 개수를 출력하고 싶어서 만들었음
	private <T extends Number> void printAllData(Stream<T> stream, Predicate<T> condition) {
		System.out.println(stream.filter(condition).count());
	}

	private void testRandomStream(){
		// 난수를 생성 하고, 해당 난수에서 조건을 만족하는 데이터의 개수를 출력 해보자
		printAllData(
			makeRandomPrimitiveStream(),
			data -> data > 100
		);
	}

	/**===================================================*/

	private void makeIntStreamUsingString(String str) {
		IntStream valStream = str.chars();
		valStream.forEach(System.out::println);
	}
	/**===================================================*/
	private void testFileStream(){
		makeFileStream("/Users/leeWhyy/javaBack/testText.rtf");
	}

	private void makeFileStream(String path) {
		try {
			Stream<String> lineStream = Files.lines(Paths.get(path), Charset.forName("UTF-8"));
			lineStream.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**===================================================*/

	private void testParallelStream(){
		List<Person> personList = Arrays.asList(Person.of(100),Person.of(1000),Person.of(500));
		// 현재 스트림이 병렬스트림이기 때문에, 다음은 쓰레드를 이용해 병렬 처리 된다.
		makeAndCheckParallel(personList)
			.map(person -> person.getIncome()*100)
			.filter(in -> in > 1000)
			.findAny()
			.ifPresent(System.out::println);
	}

	// 컬렉션으로 병렬스트림 만들기
	private Stream<Person> makeAndCheckParallel(List<Person> personList){
		Stream<Person> personStream = personList.parallelStream();
		Assert.isTrue(personStream.isParallel(),"current stream is ParallelStream");
		return personStream;
	}

	// 배열을 이용해 병렬 스트림 생성
	private Stream<Person> makeAndCheckParallel(Person[] personArray){
		return Arrays.stream(personArray).parallel();
	}
	// 컬렉션과 배열이 아닌 경우
	private IntStream makeParallelIntStream(){
		return IntStream.range(1, 150).parallel();
	}
	// 다시 시퀄셜 모드로 돌리고 싶은 경우 ( 반드시 병렬 스트림이 좋은 것은 아니기에 )
	private IntStream revertToSequential(IntStream stream){
		return stream.sequential();
	}

	/**===================================================*/



	/**===================================================*/

	public static void main(String[] args) {
		CreateStream main = new CreateStream();
		main.testParallelStream();

	}


}
