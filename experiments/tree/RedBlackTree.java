package com.dwivedi.experiments.tree;

public class RedBlackTree {

	class Node {
		public int data;
		public Node left;
		public Node right;
		// public int height;
		public boolean red;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RedBlackTree tree = new RedBlackTree();
		tree.testCreateRBTree();
	}

	private void testCreateRBTree() {
		int[] arr = new int[] { 8, 7, 3, 1, 9, 0, 2, 6, 4, 5 };
		Node root = this.createRBTree(arr);
	}

	private Node createRBTree(int[] arr) {
		Node root = null;
		for (int i = 0; i < arr.length; i++) {
			root = this.insertInRBTree(root, arr[i]);
			if(root.red){
				root.red =false;
			}
		}
		return root;
	}

	private Node insertInRBTree(Node root, int value) {
		if (root == null) {
			root = this.new Node();
			root.data = value;
			root.red = true;
			return root;
		} else if (value < root.data) {
			root.left = insertInRBTree(root.left, value);
			root = leftBalanceRBTree(root);
		} else if (value > root.data) {
			root.right = insertInRBTree(root.right, value);
			root = rightBalanceRBTree(root);
		}

		return root;
	}

	private Node rightBalanceRBTree(Node root) {
		// Root is grand parent
		Node parent = root.right;
		Node uncle = root.left;
		if (parent == null || !parent.red) {
			// No violation
			return root;
		}

		Node newRoot = null;
		if (parent.right != null && parent.right.red) {
			// zigzig violation

			if (uncle.red) {
				newRoot = recolor(root);
			} else {
				newRoot = leftRotate(root, true);
			}

		} else if (parent.left != null && parent.left.red) {
			// zigzag violation

			if (uncle.red) {
				newRoot = recolor(root);
			} else {
				newRoot = rightRotate(root.left, false);
				newRoot = leftRotate(newRoot, true);
			}
		}

		return newRoot;
	}

	private Node leftBalanceRBTree(Node root) {
		// Root is grand parent
		Node parent = root.left;
		Node uncle = root.right;
		if (parent == null || !parent.red) {
			// No violation
			return root;
		}

		Node newRoot = null;
		if (parent.left != null && parent.left.red) {
			// zigzig violation

			if (uncle.red) {
				newRoot = recolor(root);
			} else {
				newRoot = rightRotate(root, true);
			}

		} else if (parent.right != null && parent.right.red) {
			// zigzag violation

			if (uncle.red) {
				newRoot = recolor(root);
			} else {
				newRoot = leftRotate(root.left, false);
				newRoot = rightRotate(newRoot, true);
			}
		}

		return newRoot;
	}

	private Node leftRotate(Node root, boolean recolor) {
		Node newRoot = root.right;
		root.right = newRoot.left;
		newRoot.left = root;

		if (recolor) {
			root.red = true;
			newRoot.red = false;
		}

		return newRoot;
	}

	private Node rightRotate(Node root, boolean recolor) {
		Node newRoot = root.left;
		root.left = newRoot.right;
		newRoot.right = root;

		if (recolor) {
			root.red = true;
			newRoot.red = false;
		}

		return newRoot;
	}

	private Node recolor(Node root) {
		root.left.red = false;
		root.right.red = false;
		root.red = true;

		return root;
	}

}
