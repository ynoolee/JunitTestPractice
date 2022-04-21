package com.example.test.test.learnjava.interfaceJava8.lazy;

import java.util.function.Supplier;

public class LazyEvaluation {
	public Person person = new Person();

	// 배부를 줄도 모르고 친구 어머니께 치킨, 돼지고기스테이크 둘 다 만들어 달라 했음
	// 너무 배불러서 돼지고기를 못 먹음 ㅠㅠ 친구어머니께 죄지은 기분
	public void todayLateNightSnackEager(Food chicken, Food pork) {
		// 치킨 먹고 배불러지면 돼지고기는 안 먹는 거임
		person.eat(chicken);
		if (person.areYouHungry())
			person.eat(pork); // 이 때만 pork 가 사용된다
		System.out.println("끝");
	}

	public static void eager(String[] args) {
		LazyEvaluation lazyEval = new LazyEvaluation();
		lazyEval.todayLateNightSnackEager(new Chicken(40), new Pork(60));
	}

	// 치킨 먹고 배불러진 경우 -> 돼지고기는 만들지 않음 -> 돼지고기도 만들어 달라했으면 다시는 못 놀러올 뻔함.
	public void todayLateNightSnackLazy(Supplier<Food> chicken, Supplier<Food> pork) {
		// 치킨 먹고 배불러지면 돼지고기는 안 먹는 거임
		person.eat(chicken.get());
		if (person.areYouHungry())
			person.eat(pork.get());
		System.out.println("끝");
	}

	public static void main(String[] args) {
		LazyEvaluation lazyEvaluation = new LazyEvaluation();
		lazyEvaluation.todayLateNightSnackLazy(() -> new Chicken(45), () -> new Pork(66));
	}

}
