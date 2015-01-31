package com.dwivedi.experiments.tree;


public class AVLTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// testCreateAVL();
		testDeleteAVL();

	}

	private static void testDeleteAVL() {
		System.out.println("AVLTree.testCreateAVL()");
		int[] arr = new int[] { 8, 7, 3, 1, 9, 0, 2, 6, 4, 5 };
		TreeNode root = createAVL(arr);
		root = deleteAVL(root, 3);
	}

	private static TreeNode deleteAVL(TreeNode root, int value) {
		if (root == null) {
			return null;
		}

		if (root.data == value) {
			if ((root.left == null) && (root.right == null)) {
				return null;
			} else if (root.left == null) {
				root = root.right;
			} else if (root.right == null) {
				root = root.left;
			} else { // both child is non empty
				root.data = findMaxValue(root.left);
				root.left = deleteAVL(root.left, root.data);
			}
		} else if (value < root.data) {
			root.left = deleteAVL(root.left, value);
		} else { // value > root.data
			root.right = deleteAVL(root.right, value);
		}

		root.height = reCalculateHeight(root);
		balanceTree(root);
		return root;
	}

	private static int findMaxValue(TreeNode root) {
		if (root.right == null) {
			return root.data;
		} else {
			return findMaxValue(root.right);
		}
	}

	private static void testCreateAVL() {

		System.out.println("AVLTree.testCreateAVL()");
		int[] arr = new int[] { 8, 7, 3, 1, 9, 0, 2, 6, 4, 5 };
		TreeNode root = createAVL(arr);

	}

	private static TreeNode createAVL(int[] arr) {
		TreeNode root = null;

		for (int i = 0; i < arr.length; i++) {
			root = insertInAVL(root, arr[i]);
		}

		return root;
	}

	private static TreeNode insertInAVL(TreeNode root, int value) {

		if (root == null) {
			TreeNode node = new TreeNode();
			node.data = value;
			node.height = 1;
			return node;
		}

		if (value <= root.data) {
			root.left = insertInAVL(root.left, value);
		} else {
			root.right = insertInAVL(root.right, value);
		}

		root.height = reCalculateHeight(root);
		root = balanceTree(root);

		return root;
	}

	private static TreeNode balanceTree(TreeNode root) {

		int balanceFactor = calculateBalanceFactor(root);

		if (balanceFactor == 2) { // Left tree insertion
			if ((root.left.right == null)
					|| ((root.left.left != null) && (root.left.left.height > root.left.right.height))) {
				// Right rotation
				root = rightRotate(root);
			} else {
				// Left Right rotation
				root.left = leftRotate(root.left);
				root = rightRotate(root);
			}
		} else if (balanceFactor == -2) { // Right tree insertion
			if ((root.right.left == null)
					|| ((root.right.right != null) && (root.right.left.height < root.right.right.height))) {
				// Left rotation
				root = leftRotate(root);
			} else {
				// Right Left rotation
				root.right = rightRotate(root.right);
				root = leftRotate(root);
			}

		} // else means no imbalance

		return root;
	}

	private static int calculateBalanceFactor(TreeNode root) {
		int lHeight, rHeight;
		if (root.left == null)
			lHeight = 0;
		else
			lHeight = root.left.height;

		if (root.right == null)
			rHeight = 0;
		else
			rHeight = root.right.height;

		return lHeight - rHeight;
	}

	private static TreeNode leftRotate(TreeNode root) {
		TreeNode newRoot = root.right;
		root.right = newRoot.left;
		root.height = reCalculateHeight(root);
		newRoot.left = root;
		newRoot.height = reCalculateHeight(newRoot);
		return newRoot;
	}

	private static TreeNode rightRotate(TreeNode root) {
		TreeNode newRoot = root.left;
		root.left = newRoot.right;
		root.height = reCalculateHeight(root);
		newRoot.right = root;
		newRoot.height = reCalculateHeight(newRoot);
		return newRoot;
	}

	private static int reCalculateHeight(TreeNode root) {
		int lHeight, rHeight;
		if (root.left == null)
			lHeight = 0;
		else
			lHeight = root.left.height;

		if (root.right == null)
			rHeight = 0;
		else
			rHeight = root.right.height;

		return 1 + (lHeight >= rHeight ? lHeight : rHeight);
	}

}
