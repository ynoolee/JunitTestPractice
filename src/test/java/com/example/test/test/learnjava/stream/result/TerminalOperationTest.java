package com.example.test.test.learnjava.stream.result;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

import java.util.Arrays;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TerminalOperationTest {

	private IntStream emptyStream;
	private List<Product> productList;

	@BeforeEach
	public void setUp() {
		emptyStream = IntStream.empty();
		productList = Arrays.asList(new Product(23, "potatoes"),
			new Product(14, "orange"),
			new Product(13, "lemon"),
			new Product(23, "bread"),
			new Product(13, "sugar")); //
	}

	@Test
	public void min_max_average_method_of_EmptyStream_returns_Optional() {
		// 리턴되어온 옵셔널을 사용
		assertThat(emptyStream.max().orElse(1), is(1));
	}

	@Test
	public void count_sum_method_of_EmtpyStream_returns_zero() {
		assertThat(emptyStream.count(), is(0L));
	}

	@Test
	public void 하나의인자만을_받는_reduce_get_parameter_of_associative_accumulation_function() {
		// 1+2 -> 3+3
		// Optional 을 리턴
		IntStream.range(1, 4).reduce(Integer::sum)
			.ifPresent(result -> assertThat(result, is(6)));
	}

	@Test
	public void make_pow_of_two_using_reduce() {
		IntStream.generate(() -> 2).limit(5)
			.reduce((a, b) -> a * b)
			.ifPresent(result -> assertThat(result, is((int)Math.pow(2, 5))));
	}

	@Test
	public void 요소가한개인경우_reduce() {
		IntStream.range(1, 2).reduce(Integer::sum)
			.ifPresent(result -> assertThat(result, is(1)));
	}

	@Test
	public void 요소가한개_데이터도한개_인경우_reduce() {
		IntStream.of(1).reduce(Integer::sum)
			.ifPresent(result -> assertThat(result, is(1)));
	}

	@Test
	public void 두개의인자를_받는_reduce_에_초기값을_주고_요소들을처리하는로직_을_넘긴경우() {
		// 초기값이 주어진 경우에는 Optional 을 리턴하지 않는다
		int result = IntStream.range(1, 4).reduce(10, (a, b) -> Integer.sum(a, b));
		assertThat(result, is(16));
	}

	// 병렬 스트림의 경우,
	@Test
	public void reduce_의_combiner_는_병렬스트림에서_동작한다() {
		// Combiner 는 identity dhk accumulator 를 갖고 여러 스레드에서 나눠 계산한 결과를 합치는 역할
		// 12 + 13 = 25 , 25 + 11 = 36 -> 이렇게 두 번 호출된다고 한다.
		Integer result = Stream.of(1, 2, 3).parallel()
			.reduce(10
				, Integer::sum
				, (a, b) -> {
					System.out.println("Combiner was called");
					return a + b;
				});
		assertThat(result, is(36));
	}

	@Test
	public void 세개의인자를_받는_reduce_의_combiner_는_일반스트림에선_동작하지않는다() {
		Integer result = Stream.of(1, 2, 3).reduce(10,
			Integer::sum, (a, b) -> {
				System.out.println("Combiner was called");
				return a + b;
			});
		assertThat(result, not(36));
	}

	// 일반 스트림 ( 시퀀셜 스트림 ) 의 경우 , 하나의 스레드에서, 앞선 accumulator 의 연산 결과와 스트림 내 다음 데이터에 대하여
	// 다음 accumulator 연산을 하는 것
	@Test
	public void 요소들의_이름을가져와_리스트로리턴() {
		productList.stream()
			.map(product -> product.getName())
			.collect(Collectors.toList());
	}

	@Test
	public void 스트림작업결과를_하나의_스트링으로이어붙이기() {
		String result = productList.stream()
			.map(Product::getName)
			.collect(Collectors.joining());
		assertThat(result, is("potatoesorangelemonbreadsugar"));
	}

	@Test
	public void makeString_usingDelimiterPrefixSuffix_in_joining() {
		String result = productList.stream()
			.map(Product::getName)
			.collect(Collectors.joining(", ", "<", ">"));
		assertThat(result, is("<potatoes, orange, lemon, bread, sugar>"));
	}

	@Test
	public void calculate_average_of_allProductsAmount() {
		Double avgAmount = productList.stream()
			.collect(Collectors.averagingInt(product -> product.getAmount()));
		assertThat(avgAmount, is(17.2));

	}

	@Test
	public void calculate_sum_of_allProductsAmount() {
		Integer sum = productList.stream()
			.collect(Collectors.summingInt(product -> product.getAmount()));
		assertThat(sum, is(86));
	}

	@Test
	public void calculate_sum_of_allProductsAmount_byConverting_Collection_toIntStream() {
		int sum = productList.stream()
			.mapToInt(Product::getAmount)
			.sum();
		assertThat(sum, is(86));
	}

	@Test
	public void get_IntSummaryStatistics() {
		IntSummaryStatistics summary = productList.stream()
			.collect(Collectors.summarizingInt(Product::getAmount));
		assertThat(summary.toString(), is("IntSummaryStatistics{count=5, sum=86, min=13, average=17.200000, max=23}"));
	}

	@Test
	public void groupingByAmount_then_check_sizeOfGroup_which_consistOfProduct_whichHasAmountOfThirteen() {
		Map<Integer, List<Product>> groupedMap = productList.stream()
			.collect(Collectors.groupingBy(Product::getAmount));
		assertThat(groupedMap
				.get(13)
				.size()
			, is(2));
	}

	@Test
	public void partitionByAmount_then_check_sizeOfGroup_which_hasPassedCondition() {
		Map<Boolean, List<Product>> partitionedMap = productList.stream()
			.collect(Collectors.partitioningBy(e -> e.getAmount() > 15));
		assertThat(partitionedMap
				.get(Boolean.TRUE)
				.size()
			, is(2));

	}

	@Test
	public void collectToSet_then_convertToImmutableSet() {
		Set<Product> unmodifiableSet = productList.stream()
			.collect(
				Collectors.collectingAndThen(
					Collectors.toSet(),
					Collections::unmodifiableSet
				)
			);
		Assertions.assertThrows(UnsupportedOperationException.class, () ->
			unmodifiableSet.add(new Product(12, "test"))
		);
	}

	@Test
	public void makeCustomCollector_then_useFor_collect() {
		// Supplier 로 LinkedList zero parameter 인 생성자를 넘겨줌
		// accumulator 에는 리스트에 추가하는 add 메소드를 넘겨줌
		// 이 컬렉터는 스트림의 각 요소에 대해 LinkedList 를 만들고 요소를 추가하게 된다.
		// 마지막으로 combiner 를 이용해 결과를 조합하는데, 생성된 리스트들을 하나의 리스트로 합치고 있다.
		Collector<Object, LinkedList<Object>, LinkedList<Object>> toLinkedList = Collector.of(
			LinkedList::new,
			LinkedList::add,
			(first, second) -> {
				first.addAll(second);
				return first;
			});
		// collect 메소드에 위에서 직접 만든 커스텀 컬렉터를 넘겨 줄 수 있다.
		// 결과가 담긴 LinkedList 가 반환된다.
		LinkedList<Object> linkedListOfProducts = productList.stream()
			.collect(toLinkedList);
	}

	@Test
	public void check_Products_if_there_are_product_whoseNameIsOrange() {
		assertThat(
			Boolean.TRUE
			, is(productList.stream()
				.anyMatch(p ->
					p.getName().equals("orange")))
		);
	}


}