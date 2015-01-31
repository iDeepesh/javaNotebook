package com.dwivedi.experiments.sorts;

import java.util.Arrays;

public class Sorts {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		testSelectionSort();
//		testBubbleSort();
//		testInsertionSort();
//		testMergeSort();
//		testQuickSort();
//		testHeapify();
//		testHeapSort();

		testBinarySearchOnArray();
	}

	private static void testBinarySearchOnArray() {
		System.out.println("Experiments.testBinarySearch()");
		int[] arr = new int[] { 8, 7, 3, 1, 9, 0, 2, 6, 4, 5 };
		System.out.println(Arrays.toString(arr));
		boolean found7 = binarySearchOnArray(arr, 7);
		System.out.println("Binary search for 7 in array returned " + found7);
		boolean found57 = binarySearchOnArray(arr, 57);
		System.out.println("Binary search for 57 in array returned " + found57);
	}

	private static boolean binarySearchOnArray(int[] arr, int search) {
		heapSort(arr);
		System.out.println(Arrays.toString(arr));
		return bSearchOnArray(arr, search, 0, arr.length - 1);
	}

	private static boolean bSearchOnArray(int[] arr, int search, int start,
			int end) {
		int mid = start + (end - start) / 2;
		if (search == arr[mid]) {
			return true;
		} else if (start == end) {
			return false;
		} else if (search < arr[mid]) {
			return bSearchOnArray(arr, search, start, mid - 1);
		} else {
			return bSearchOnArray(arr, search, mid + 1, end);
		}
	}

	private static void testHeapSort() {
		System.out.println("Experiments.testHeapSort()");
		int[] arr = new int[] { 8, 7, 3, 1, 9, 0, 2, 6, 4, 5 };
		System.out.println(Arrays.toString(arr));
		heapSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	private static void heapSort(int[] arr) {
		heapify(arr);
		for (int i = arr.length - 1; i > 0; i--) {
			swap(arr, 0, i);
			balanceHeap(arr, 0, i - 1);
		}
	}

	private static void testHeapify() {
		System.out.println("Experiments.testHeapify()");
		int[] arr = new int[] { 8, 7, 3, 1, 9, 0, 2, 6, 4, 5 };
		System.out.println(Arrays.toString(arr));
		heapify(arr);
		System.out.println(Arrays.toString(arr));
	}

	private static void heapify(int[] arr) {
		int end = 0;
		for (int i = 1; i < arr.length; i++) {
			end = i;
			balanceHeap(arr, i, i);
		}
	}

	private static void balanceHeap(int[] arr, int i, int end) {
		int p = i / 2;
		if (arr[p] < arr[i]) {
			swap(arr, p, i);
			balanceHeap(arr, p, end);
		} else if ((2 * i <= end) && (arr[2 * i] > arr[i])) {
			swap(arr, 2 * i, i);
			balanceHeap(arr, 2 * i, end);
			balanceHeap(arr, i, end);
		} else if ((2 * i + 1 <= end) && (arr[2 * i + 1] > arr[i])) {
			swap(arr, 2 * i + 1, i);
			balanceHeap(arr, 2 * i + 1, end);
		}
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	private static void testQuickSort() {
		System.out.println("Experiments.testQuickSort()");
		int[] arr = new int[] { 8, 7, 3, 1, 9, 0, 2, 6, 4, 5 };
		System.out.println(Arrays.toString(arr));
		qSort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));
	}

	private static void qSort(int[] arr, int start, int end) {
		if (start < end) {
			int pivot = partition(arr, start, end);
			qSort(arr, start, pivot - 1);
			qSort(arr, pivot + 1, end);
		}
	}

	private static int partition(int[] arr, int start, int end) {
		// end is pivot
		int pivot = end;
		int tempPivot = start;
		for (int i = start; i < pivot; i++) {
			if (arr[i] < arr[pivot]) {
				int temp = arr[i];
				arr[i] = arr[tempPivot];
				arr[tempPivot] = temp;
				tempPivot++;
			}
		}
		int temp = arr[pivot];
		arr[pivot] = arr[tempPivot];
		arr[tempPivot] = temp;

		return tempPivot;
	}

	private static void testMergeSort() {
		System.out.println("Experiments.testMergeSort()");
		int[] arr = new int[] { 5, 7, 3, 1, 9, 0, 2, 6, 4, 8 };
		System.out.println(Arrays.toString(arr));
		mergeSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	private static void mergeSort(int[] arr) {
		mSort(arr, 0, arr.length - 1);
	}

	private static void mSort(int[] arr, int first, int last) {
		if (first != last) {
			int length = last - first + 1;
			int mid = length / 2;
			mSort(arr, first, mid + first - 1);
			mSort(arr, mid + first, last);
			int fCount = first;
			int sCount = mid + first;
			int[] temp = new int[length];
			for (int i = 0; i < length; i++) {
				if ((fCount < mid + first) && (sCount < last)) {
					if ((arr[fCount] < arr[sCount])) {
						temp[i] = arr[fCount++];
					} else {
						temp[i] = arr[sCount++];
					}
				} else if (fCount < mid + first) {
					temp[i] = arr[fCount++];
				} else {
					temp[i] = arr[sCount++];
				}
			}
			for (int i = 0; i < length; i++) {
				arr[first + i] = temp[i];
			}
		}
	}

	private static void testInsertionSort() {
		System.out.println("Experiments.testInsertionSort()");
		int[] arr = new int[] { 5, 7, 3, 1, 9, 0, 2, 6, 4, 8 };
		System.out.println(Arrays.toString(arr));
		insertionSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	private static void insertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j >= 1; j--) {
				if (arr[j] < arr[j - 1]) {
					int temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
				} else {
					break;
				}
			}
		}
	}

	private static void testBubbleSort() {
		System.out.println("Experiments.testBubbleSort()");
		int[] arr = new int[] { 5, 7, 3, 1, 9, 0, 2, 6, 4, 8 };
		System.out.println(Arrays.toString(arr));
		bubbleSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	private static void bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}

	private static void testSelectionSort() {
		System.out.println("Experiments.testSelectionSort()");
		int[] arr = new int[] { 5, 7, 3, 1, 9, 0, 2, 6, 4, 8 };
		System.out.println(Arrays.toString(arr));
		selectionSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	private static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int n = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[n])
					n = j;
			}
			int temp = arr[i];
			arr[i] = arr[n];
			arr[n] = temp;
		}
	}
}
