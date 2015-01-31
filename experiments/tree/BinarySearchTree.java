package com.dwivedi.experiments.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class BinarySearchTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// testCreateBST();
		// testInOrderTraversalOfBST();
		// testLevelOrderTraversalOfBST();
		// testBinarySearchOnBST();
		// testFindMinInBST();
		// testDeletionInBST();
		// testHeightOfBST();
		testVerifyBST();
	}

	private static void testVerifyBST() {
		System.out.println("BinarySearchTree.testBSTCheck()");
		int[] arr = new int[] { 8, 7, 3, 1, 9, 0, 2, 6, 4, 5 };
		TreeNode root = createBST(arr);
		boolean bst = verifyBST(root);
		System.out.println("The BST check on tree returned " + bst);
		root.data = 100;
		bst = verifyBST(root);
		System.out.println("Set root as 100. The BST check on tree returned " + bst);
		root.data = -1;
		bst = verifyBST(root);
		System.out.println("Set root as -1. The BST check on tree returned " + bst);
	}

	private static boolean verifyBST(TreeNode root) {
		return vBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private static boolean vBST(TreeNode root, int minValue, int maxValue) {
		if (root == null)
			return true;
		if ((root.data > maxValue) || (root.data <= minValue)) {
			return false;
		}
		if (vBST(root.left, minValue, root.data)
				&& (vBST(root.right, root.data, maxValue)))
			return true;

		return false;
	}

	private static void testHeightOfBST() {
		System.out.println("BinarySearchTree.testHeightOfBST()");
		int[] arr = new int[] { 8, 7, 3, 1, 9, 0, 2, 6, 4, 5 };
		TreeNode root = createBST(arr);
		int height = heightOfBST(root);
		System.out.println("Height of the Tree is " + height);
	}

	private static int heightOfBST(TreeNode root) {
		if (root == null)
			return 0;
		int left = heightOfBST(root.left);
		int right = heightOfBST(root.right);
		return left >= right ? left + 1 : right + 1;
	}

	private static void testLevelOrderTraversalOfBST() {
		System.out.println("BinarySearchTree.testLevelOrderTraversalOfBST()");
		int[] arr = new int[] { 8, 7, 3, 1, 9, 0, 2, 6, 4, 5 };
		TreeNode root = createBST(arr);
		levelOrderTraversal(root);
	}

	private static void levelOrderTraversal(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		lOrderTraversal(root, queue);
	}

	private static void lOrderTraversal(TreeNode root, Queue<TreeNode> queue) {
		if (root == null)
			return;
		else
			queue.add(root);
		while (queue.peek() != null) {
			levelTraverseNode(queue.remove(), queue);
		}
	}

	private static void levelTraverseNode(TreeNode root, Queue<TreeNode> queue) {
		System.out.println("Value = " + root.data);
		if (root.left != null)
			queue.add(root.left);
		if (root.right != null)
			queue.add(root.right);
	}

	private static void testInOrderTraversalOfBST() {
		System.out.println("BinarySearchTree.testPreOrderTraversal()");
		int[] arr = new int[] { 8, 7, 3, 1, 9, 0, 2, 6, 4, 5 };
		TreeNode root = createBST(arr);
		inOrderTraversalOfBST(root);
	}

	private static void inOrderTraversalOfBST(TreeNode root) {
		if (root == null)
			return;
		inOrderTraversalOfBST(root.left);
		System.out.println("Value = " + root.data);
		inOrderTraversalOfBST(root.right);
	}

	private static void testDeletionInBST() {
		System.out.println("BinarySearchTree.testDeletionInBST()");
		int[] arr = new int[] { 8, 7, 3, 1, 9, 0, 2, 6, 4, 5 };
		TreeNode root = createBST(arr);
		inOrderTraversalOfBST(root);
		root = deleteInBST(root, 5);
		System.out.println("After Deletion");
		inOrderTraversalOfBST(root);
	}

	private static TreeNode deleteInBST(TreeNode root, int value) {
		if (root == null) {
			return null;
		} else if (root.data == value) {
			if ((root.left == null) && (root.right == null)) {
				return null;
			} else if (root.left == null) {
				return root.right;
			} else if (root.right == null) {
				return root.left;
			} else {
				TreeNode min = findMinInBST(root.right);
				root.data = min.data;
				deleteInBST(root.right, root.data);
			}

		} else if (value < root.data) {
			root.left = deleteInBST(root.left, value);
		} else {
			root.right = deleteInBST(root.right, value);
		}

		return root;
	}

	private static void testFindMinInBST() {
		System.out.println("BinarySearchTree.testFindMinInBST()");
		int[] arr = new int[] { 8, 7, 3, 1, 9, 0, 2, 6, 4, 5 };
		TreeNode root = createBST(arr);
		TreeNode min = findMinInBST(root);
		System.out.println("The min value is " + min.data);
	}

	private static TreeNode findMinInBST(TreeNode root) {
		if (root == null) {
			return root;
		}

		if (root.left == null) {
			return root;
		}

		return findMinInBST(root.left);
	}

	private static void testBinarySearchOnBST() {
		System.out.println("BinarySearchTree.testCreateBST()");
		int[] arr = new int[] { 8, 7, 3, 1, 9, 0, 2, 6, 4, 5 };
		TreeNode root = createBST(arr);
		boolean found7 = binarySearchOnBST(root, 7);
		System.out.println("Binary search for 7 in array returned " + found7);
		boolean found57 = binarySearchOnBST(root, 57);
		System.out.println("Binary search for 57 in array returned " + found57);
	}

	private static boolean binarySearchOnBST(TreeNode root, int value) {
		if (root == null) {
			return false;
		} else if (root.data == value) {
			return true;
		} else if ((root.left == null) && (root.right == null)) {
			return false;
		} else if (value < root.data) {
			return binarySearchOnBST(root.left, value);
		} else {
			return binarySearchOnBST(root.right, value);
		}
	}

	private static void testCreateBST() {
		System.out.println("BinarySearchTree.testCreateBST()");
		int[] arr = new int[] { 8, 7, 3, 1, 9, 0, 2, 6, 4, 5 };
		TreeNode root = createBST(arr);
	}

	private static TreeNode createBST(int[] arr) {
		System.out.println(Arrays.toString(arr));
		TreeNode root = null;
		for (int i = 0; i < arr.length; i++) {
			root = insertBST(root, arr[i]);
		}

		return root;
	}

	private static TreeNode insertBST(TreeNode root, int value) {
		if (root == null) {
			root = new TreeNode();
			root.data = value;
			return root;
		}

		if (value <= root.data) {
			root.left = insertBST(root.left, value);
		} else {
			root.right = insertBST(root.right, value);
		}

		return root;
	}

}
