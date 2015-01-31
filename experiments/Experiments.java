package com.dwivedi.experiments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Experiments {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if ("Hello" instanceof String) {
			System.out.println("instanceof works.");
		}

		// testStringReverse();
		// testAnagrams();
		// testPowerOf();
		// testPalindrome();
		// testFibonacci();
		// testScanner();
		// testPascal();
		// testBinaryInPlaceSwap();
		// testArithmaticInPlaceSwap();
		testSqrt();
	}

	private static void testSqrt() {
		System.out.println("Experiments.testSqrt()");
		double d1 = sqrt(100, 0.01);
		System.out.println("Square root of 100 with precision 0.01 is " + d1);
		d1 = sqrt(1, 0.01);
		System.out.println("Square root of 1 with precision 0.01 is " + d1);
		d1 = sqrt(0.5, 0.01);
		System.out.println("Square root of 0.5 with precision 0.01 is " + d1);
		d1 = sqrt(0, 0.01);
		System.out.println("Square root of 0 with precision 0.01 is " + d1);
	}

	private static double sqrt(double d, double p) {
		double g = d / 2;
		while (Math.abs(g * g - d) > p) {
			g = (g + d / g) / 2;
		}
		return g;
	}

	private static void testArithmaticInPlaceSwap() {
		arithmaticInPlaceSwap(10, 20);
	}

	private static void testBinaryInPlaceSwap() {
		binaryInPlaceSwap(10, 20);
	}

	private static void binaryInPlaceSwap(int a, int b) {
		System.out.println("Experiments.binaryInPlaceSwap()");
		System.out.println("Before binary in place swap. a = " + a + ", b = "
				+ b);

		a = a ^ b;
		b = a ^ b;
		a = a ^ b;

		System.out.println("After binary in place swap. a = " + a + ", b = "
				+ b);
		System.out.println("Exiting Experiments.binaryInPlaceSwap()");
	}

	private static void arithmaticInPlaceSwap(int a, int b) {
		System.out.println("Experiments.arithmaticInPlaceSwap()");
		System.out.println("Before arithmatic in place swap. a = " + a
				+ ", b = " + b);

		a = a + b;
		b = a - b;
		a = a - b;

		System.out.println("After arithmatic in place swap. a = " + a
				+ ", b = " + b);
		System.out.println("Exiting Experiments.arithmaticInPlaceSwap()");
	}

	private static void testPascal() {
		genPascalRec(9, 1);
		genPascalIter(9, 1);
	}

	private static void genPascalIter(int depth, int seed) {
		System.out.println("Experiments.genPascalIter()");
		int[] curRow = new int[depth];
		int[] prevRow = new int[depth];

		for (int i = 0; i < depth; i++) {
			if (i == 0) {
				curRow[0] = seed;
			} else {
				curRow[0] = prevRow[0];
				for (int k = 1; k < i; k++) {
					curRow[k] = prevRow[k - 1] + prevRow[k];
				}
				curRow[i] = prevRow[i - 1];

			}

			StringBuilder str = new StringBuilder("Depth = " + i + 1
					+ " Row = ");
			for (int j = 0; j <= i; j++) {
				str.append(curRow[j] + " ");
			}

			int[] temp = prevRow;
			prevRow = curRow;
			curRow = temp;

			System.out.println(str);
		}
	}

	private static int[] genPascalRec(int depth, int seed) {
		System.out.println("Experiments.genPascalRec() depth = " + depth);
		int[] pArr = null;
		if (depth == 1) {
			pArr = new int[] { seed };
		} else {
			int[] prevRow = genPascalRec(depth - 1, seed);
			pArr = new int[depth];
			pArr[0] = prevRow[0];
			for (int i = 1; i < depth - 1; i++) {
				pArr[i] = prevRow[i - 1] + prevRow[i];
			}
			pArr[depth - 1] = prevRow[depth - 2];
		}

		StringBuilder str = new StringBuilder("Depth = " + depth + " Row = ");
		for (int i = 0; i < depth; i++) {
			str.append(pArr[i] + " ");
		}

		System.out.println(str);
		return pArr;
	}

	private static void testScanner() {
		try {
			Scanner in = new Scanner(
					new File(
							"/Users/ddwivedi/Documents/deepesh/coding_interview_questions"));
			Map<String, Integer> countMap = new HashMap<String, Integer>();
			while (in.hasNext()) {
				String word = in.next();
				int count = 0;
				if (countMap.containsKey(word)) {
					count = countMap.get(word);
				}
				count++;
				countMap.put(word, count);
			}
			System.out.println(countMap.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void testFibonacci() {
		fibonacci(1);
		fibonacci(2);
		fibonacci(3);
		fibonacci(9);
		fibonacci(10);

		// recFibonacci(10);
	}

	private static int recFibonacci(int i) {
		int ret = -1;
		if (i == 1)
			ret = 0;
		else if (i == 2)
			ret = 1;
		else
			ret = recFibonacci(i - 1) + recFibonacci(i - 2);

		System.out.println("Position: " + i + " Value: " + ret);
		return ret;
	}

	private static int[] fibonacci(int i) {
		int[] result = new int[i];
		result[0] = 0;
		if (i > 1) {
			result[1] = 1;
			if (i > 2) {
				for (int j = 2; j < i; j++) {
					result[j] = result[j - 1] + result[j - 2];
				}
			}
		}

		System.out.println("Requested length: " + i + " and fibonacci: "
				+ Arrays.toString(result));
		return result;
	}

	private static void testPalindrome() {
		isPalindrome("abcdcba");
		isPalindrome("abccba");
		isPalindrome("abcdeba");
		isPalindrome("abccbe");
	}

	private static boolean isPalindrome(String str) {
		boolean isPalin = true;

		char[] chars = str.toCharArray();
		int l = chars.length;
		for (int i = 0; i < l / 2; i++) {
			if (chars[i] != chars[l - i - 1]) {
				isPalin = false;
				break;
			}
		}

		System.out.println("Passed string is: " + str + ". And analysis is: "
				+ isPalin);

		return isPalin;
	}

	private static void testPowerOf() {
		powerOf(2, 5);
		powerOf(2, 6);

		// Failure cases
		powerOf(0, 3);
		powerOf(-2, 3);
		powerOf(2, -3);
		powerOf(0, -3);
		powerOf(-2, -3);
		powerOf(2, 0);
		powerOf(0, 0);
		powerOf(-2, 0);
	}

	private static int powerOf(int i, int j) {
		int result = 1;
		if (j < 0) {
			result = -1;
		} else if (j == 0) {
			result = 1;
		} else if (i == 0) {
			result = 0;
		} else {
			result = powerIt(i, j);
		}

		System.out.println("Passed i=" + i + ", j=" + j + ". And result is "
				+ result);
		return result;
	}

	private static int powerIt(int i, int j) {
		if (j == 0)
			return 1;
		else {
			int n = powerIt(i, j / 2);
			if ((j % 2) != 0) {
				return n * n * i;
			} else {
				return n * n;
			}
		}
	}

	private static void testAnagrams() {
		isAnagram("tub", "but");
		isAnagram("tub", "bat");
	}

	private static boolean isAnagram(String ana1, String ana2) {
		if (ana1.length() != ana2.length())
			return false;

		boolean isAnagram = true;

		int[] cCount = new int[256];
		char[] char1 = ana1.toCharArray();
		char[] char2 = ana2.toCharArray();
		for (int i = 0; i < char1.length; i++) {
			cCount[char1[i]]++;
			cCount[char2[i]]--;
		}

		for (int j = 0; j < 256; j++) {
			if (cCount[j] != 0) {
				isAnagram = false;
				break;
			}
		}

		System.out.println("Passed string: " + ana1 + " and " + ana2
				+ "And the analysis is: " + isAnagram);

		return isAnagram;
	}

	private static void testStringReverse() {
		reverse("Deepesh");
		reverse("ABCDEF");
	}

	private static String reverse(String str) {
		char[] chars = str.toCharArray();
		int l = chars.length;
		for (int i = 0; i < l / 2; i++) {
			char temp = chars[i];
			chars[i] = chars[l - i - 1];
			chars[l - i - 1] = temp;
		}
		String rString = new String(chars);
		System.out.println(str + " reversed to " + rString);
		return rString;
	}
}
