package com.dwivedi.experiments.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Stack {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testTowerOfHanoi(18);
	}

	private static void testTowerOfHanoi(int n) {
		Deque<Integer> s1 = new ArrayDeque<Integer>();
		Deque<Integer> s2 = new ArrayDeque<Integer>();
		Deque<Integer> s3 = new ArrayDeque<Integer>();
		for (int i = n; i > 0; i--)
			s1.push(i);

		printStacks(s1, s2, s3, Integer.MIN_VALUE);
		System.out.println("Starting Play !!");

		playTowerOfHanoi(s1, s2, s3, n);

		System.out.println("Played !!");
		printStacks(s1, s2, s3, Integer.MAX_VALUE);
	}

	private static void playTowerOfHanoi(Deque<Integer> s1, Deque<Integer> s2,
			Deque<Integer> s3, int n) {
		if (n == 1) {
			moveOneDisc(s1, s2);
			moveOneDisc(s2, s3);
		} else {
			playTowerOfHanoi(s1, s2, s3, n - 1);

			moveOneDisc(s1, s2);

			playTowerOfHanoi(s3, s2, s1, n - 1);

			moveOneDisc(s2, s3);

			playTowerOfHanoi(s1, s2, s3, n - 1);
		}
	}

	private static void moveOneDisc(Deque<Integer> s1, Deque<Integer> s2) {
		if (s2.isEmpty() || (s2.peek() > s1.peek()))
			s2.push(s1.pop());
		else
			System.out.println("VOILATION HERE !!!!!!!!!");
	}

	private static void printStacks(Deque<Integer> s1, Deque<Integer> s2,
			Deque<Integer> s3, int n) {
		System.out.println("For n=" + n);
		System.out.println("S1 = " + Arrays.toString(s1.toArray()));
		System.out.println("S2 = " + Arrays.toString(s2.toArray()));
		System.out.println("S3 = " + Arrays.toString(s3.toArray()));
	}

}
