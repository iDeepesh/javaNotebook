package com.dwivedi.experiments.linkedlist;

import java.util.Deque;

public class LinkedList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testPalindrome();
	}

	private static void testPalindrome() {
		int[] arr1 = new int[] { 2, 3, 7, 1, 1, 7, 3, 2 };
		Node l1 = createLinkedList(arr1);
		boolean b1 = isPalindrome(l1);
		System.out.println("l1 isPalindrome = " + b1);

		int[] arr2 = new int[] { 2, 5, 7, 1, 10, 1, 7, 5, 2 };
		Node l2 = createLinkedList(arr2);
		boolean b2 = isPalindrome(l2);
		System.out.println("l2 isPalindrome = " + b2);
		
		int[] arr3 = new int[] { 2, 3, 7, 1, 1, 7, 2, 2 };
		Node l3 = createLinkedList(arr3);
		boolean b3 = isPalindrome(l3);
		System.out.println("l3 isPalindrome = " + b3);

		int[] arr4 = new int[] { 2, 5, 7, 1, 10, 1, 7, 5, 0 };
		Node l4 = createLinkedList(arr4);
		boolean b4 = isPalindrome(l4);
		System.out.println("l4 isPalindrome = " + b4);
	}

	private static boolean isPalindrome(Node head) {
		boolean isPalindrome = true;

		if (head == null)
			return false;

		Deque<Node> stack = new java.util.LinkedList<Node>();
		Node runner = head, fastRunner = head;
		while (runner != null) {
			if ((fastRunner != null) && (fastRunner.next != null)) {
				stack.push(runner);
				runner = runner.next;
				fastRunner = fastRunner.next.next;
			} else
				break;
		}
		
		if(fastRunner != null)
			runner = runner.next;

		while (runner != null) {
			if(runner.data != stack.pop().data){
				isPalindrome = false;
				break;
			}
			runner = runner.next;
		}

		return isPalindrome;
	}

	private static Node createLinkedList(int[] arr) {
		Node head = null;
		for (int i = 0; i < arr.length; i++) {
			Node node = new Node();
			node.data = arr[i];
			node.next = head;
			head = node;
		}

		return head;
	}

}
