package com.example.test.test.learnjava.lambda;

import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.IntBinaryOperator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LambdaForPrimitiveTest {
	private final int LEN = 10000;
	private int[] op1 = new int[LEN];
	private int[] op2 = new int[LEN];
	private int[] res = new int[LEN];

	private IntBinaryOperator operator;
	private BiFunction<Integer,Integer,Integer> wrapperOperator;

	@BeforeEach
	public void setUp(){
		Random rand = new Random();

		operator = (a,b) -> b + a;
		wrapperOperator = (a,b) -> b+a;

		for(int i = 0 ; i< LEN;i++){
			op1[i] = rand.nextInt(200000);
			op2[i] = rand.nextInt(200000);
		}
	}

	@Test
	public void primitive_를_위한_람다와_wrapper클래스_를_위한_람다사용_비교(){
		long start =  0L;
		start = System.nanoTime();
		for(int i =0; i < LEN; i++){
			res[i] = operator.applyAsInt(op1[i], op2[i]);
		}
		System.out.println(System.nanoTime() - start);

		start = System.nanoTime();
		for(int i =0; i < LEN; i++){
			res[i] = wrapperOperator.apply(op1[i],op2[i]);
		}
		System.out.println(System.nanoTime() - start);
	}
}
