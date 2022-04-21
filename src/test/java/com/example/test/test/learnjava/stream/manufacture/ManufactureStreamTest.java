package com.example.test.test.learnjava.stream.manufacture;

import static java.util.stream.Collectors.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ManufactureStreamTest {

	private List<String> names;
	private List<String> upperNames;
	private List<List<String>> nestedStringList;
	private List<Score> scores;
	private String[][] multiDimensionedArray;

	@BeforeEach
	private void setUp() {
		names = Arrays.asList("Eric", "Elena", "Java");
		upperNames = Arrays.asList("ERIC", "ELENA", "JAVA");
		nestedStringList = Arrays.asList(Arrays.asList("a"), Arrays.asList("b"));
		scores = Arrays.asList(new Score(20, 35, 33), new Score(20, 30, 50), new Score(80, 30, 50));
		multiDimensionedArray = new String[][] {{"A", "B"}, {"C", "D"}};
	}

	private long castToPrimitiveLong(int n) {
		return (long)n;
	}

	@Test
	public void 특정글자를포함한_데이터의개수를_필터링() {
		assertThat(
			names.stream()
				.filter(name -> name.contains("a"))
				.count()
			, is(2L)
		);
	}

	@Test
	public void 매핑을통해_리스트의_항목들을_대문자로변환() {

		long count = names.stream()
			.map(String::toUpperCase)
			.filter(e -> upperNames.contains(e))
			.count();
		assertThat(count, is(castToPrimitiveLong(upperNames.size())));

	}

	@Test
	public void map_streamOfArray_to_streamOfString() {
		// Stream<String[]> --> Stream<String>
		List<String> words = List.of("abc", "cddd"); // ImmutableCollection 생성

		words.stream()
			.map(w -> w.split(""))
			.flatMap(Arrays::stream)// 각 배열{"a","b","c"},{"c","d","d","d"}을 -> 스트림으로 만들어
			.distinct()
			.collect(toList());

	}

	@Test
	public void givenListOfNumbers_WhenMakingListOfEachNumbersSquareWithOrder_thenCorrect() {
		List<Integer> result = List.of(1, 2, 3, 4, 5).stream()
			.map(num -> num * num)
			.collect(toList());
		assertThat(result, contains(1, 4, 9, 16, 25));
	}

	@Test
	public void givenTwoListOfNumbers_whenMakingEveryPairOfNumbersWithoutOrderUsingFlatMap_thenCorrect() {
		List<Integer> list1 = List.of(1, 3);
		List<Integer> list2 = List.of(3, 4);

		// 하나의 i 에 대해 모든 j 로 만들 수 있는 Pair 의 스트림들을 flaMap 을 통해 하나의 스트림으로 리턴할 수 있디
		List<int[]> pairs = list1.stream()
			.flatMap(i -> list2.stream()
				.map(j -> new int[] {i, j})
			)
			.collect(toList());
		assertThat(pairs, containsInAnyOrder(new int[] {1, 3}, new int[] {1, 4}, new int[] {3, 3}, new int[] {3, 4}));
	}

	@Test
	public void givenTwoListOfNumbers_whenMakingEveryPairOfNumbersWithoutOrderFirstlyUsingMapThenFlattening_thenCorrectFormatIsReturned() {
		List<Integer> list1 = List.of(1, 3);
		List<Integer> list2 = List.of(3, 4);

		Stream<Stream<int[]>> pairs = list1.stream()
			.map(i -> list2.stream()
				.map(j -> new int[] {i, j}));

		// int[] 의 List 가 아닌
		// Stream<int[]> 의 List 가 나옴 -> 이를 다시 플래트닝 시켜야 원하는 형태가 나온다
		List<int[]> refined = pairs.flatMap(stream -> stream)
			.collect(toList());

		assertThat(refined, containsInAnyOrder(new int[] {1, 3}, new int[] {1, 4}, new int[] {3, 3}, new int[] {3, 4}));
	}

	@Test
	public void givenTwoListOfNumbers_whenAfterMakingEveryPairOfNumbersFilterPairDivisibleByThreeWithoutOrder_thenCorrect() {
		List<Integer> list1 = List.of(1, 3);
		List<Integer> list2 = List.of(3, 4);

		List<int[]> pairs = list1.stream()
			.flatMap(i -> list2.stream()
				.filter(j -> (i + j) % 3 == 0)
				.map(j -> new int[] {i, j})
			).collect(toList());
		assertThat(pairs, containsInAnyOrder(new int[] {3, 3}));
	}

	@Test
	public void givenTwoDimensionalStringArray_whenFilteringStringIsUsedToCountNumberOfElement_thenCorrect() {
		String[][] namesArray = new String[][] {
			{"kim", "taeng"}, {"kim", "play"}};

		final String condition = "kim";

		long count = Arrays.stream(namesArray)
			.flatMap(inner -> Arrays.stream(inner))
			.filter(name -> name.equals(condition))
			.count();
		assertThat(count, is(2L));

		Arrays.stream(namesArray)
			.map(inner -> Arrays.stream(inner)
				.filter(str -> str.equals(condition))
				.count()
			).reduce(Long::sum)
			.ifPresent(result ->
				assertThat(result, is(2L)));
	}

	@Test
	public void 중첩리스트를_플래트닝시키기() {
		List<String> flattened = nestedStringList.stream()
			.flatMap(Collection::stream)
			.collect(toList());
	}

	@Test
	public void flatten_multidimensionalArray_to_1DArray() {

	}

	// map 메소드 자체만으로는 한 번에 할 수 없는 기능이라고 함
	@Test
	public void 어떤학생의_점수정보를갖는_스코어객체의스트림_에서_학생의_국영수정수를뽑아_새로운스트림생성해_평균을구하기() {
		scores.stream()
			.flatMapToInt(score ->
				IntStream.of(
					score.getKor(),
					score.getEng(),
					score.getMath()))
			.average()
			.ifPresent(avg -> System.out.println(Math.round(avg)));

	}

	@Test
	public void 스트림역시_기본정렬은_오름차순이다() {
		int[] sortedArr = IntStream.of(14, 11, 20, 39, 23)
			.sorted()
			.toArray();
		int[] expected = new int[] {11, 14, 20, 23, 39};

		assertThat(Arrays.equals(sortedArr, expected), is(Boolean.TRUE));
	}

	@Test
	public void 작업처리_중간에_결과를_확인해볼때_사용가능() {
		int sum = IntStream.of(1, 3, 5, 7, 9)
			.peek(System.out::println)
			.sum();
	}

}