package com.dwivedi.experiments;

public class FeatureValidations {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testReferences();
		// testInPlaceObjectSwapping();
		// testBoxedByReference();
	}

	private static void testReferences() {
		String s1 = "Deepesh";
		String s2 = "Neelima";

		System.out.println("Sent Orignal: " + s1 + " " + s2);
		swapString(s1, s2);
		System.out.println("Received New: " + s1 + " " + s2);
	}

	private static void swapString(String s1, String s2) {
		System.out.println("Received Orignal: " + s1 + " " + s2);
		s1 = s2;
		System.out.println("Sent New: " + s1 + " " + s2);
	}

	private static void testBoxedByReference() {
		Integer i = 10;
		System.out.println("Before updateInteger call. Integer i = " + 1);
		updateInteger(i);
		System.out.println("After updateInteger call. Integer i = " + 1);

		String str = "Deepesh";
		System.out.println("Before updateString call. str = " + str);
		updateString(str);
		System.out.println("After updateString call. str = " + str);

	}

	private static void updateString(String str) {
		str.concat(" Dwivedi");
	}

	private static void updateInteger(Integer i) {
		i = 20;
	}

	private static void testInPlaceObjectSwapping() {
		System.out.println("FeatureValidations.testInPlaceObjectSwapping()");
		Integer oA = 11, oB = 21;
		System.out.println("Before binary in place object swap. oA = " + oA
				+ ", oB = " + oB);
		binaryInPlaceObjectSwap(oA, oB);
		System.out.println("After binary in place object swap. oA = " + oA
				+ ", oB = " + oB);
	}

	private static void binaryInPlaceObjectSwap(Integer oA, Integer oB) {
		oA = oA ^ oB;
		oB = oA ^ oB;
		oA = oA ^ oB;
		System.out.println("After binary in place object swap. oA = " + oA
				+ ", oB = " + oB);
	}

}
