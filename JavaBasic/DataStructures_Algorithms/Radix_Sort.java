package DataStructures_Algorithms;

import java.util.LinkedList;


public class Radix_Sort {

	public static void main(String[] args) {
		int[] listNumber = {4525, 98, 345, 1237, 978 };
		System.out.print("Before sorting array elements are: ");
		printArray(listNumber);
		
		// d = number of digits in the largest element(max)
		int d = Integer.toString(getMax(listNumber)).length();
		
		System.out.print("\nAfter applying Radix sort, the array elements are: ");
		printArray(radixSort(listNumber, listNumber.length, d));

	}
	
	public static void printArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		
	}
	
	// Get digit x at position i
	public static int digitAt(int x, int i) {
		while (i-- > 0) {
			x = x / 10;
		}
		return x % 10;
	}
	
	// max = largest element in the given array a
	public static int getMax(int[] a) {
		int max = a[0];
		for (int i = 0; i < a.length; i++) {
			if (a[i] > max) {
				a[i] = max;
			}
		}
		return max;
	}
	
	
	
	public static int[] radixSort(int[] a, int n, int d) {
		for (int j = 0; j < d; j++) {
			
			// Declare 10 queues(Linked list)
			@SuppressWarnings("unchecked")
			LinkedList<Integer>[] queues = new LinkedList[10];
			
			// Create 10 Linked list
			for (int q = 0; q < 10; q++) {
				queues[q] = new LinkedList<Integer>();
			}
			
			// Add every a[i] to queues
			for (int i = 0; i < n; i++) {
				queues[digitAt(a[i], j)].add(a[i]);
			}
			
			// Create a temporary array to contain elements which are polled from queues
			int[] temp = new int[n];
			int count = 0;
			for (int q = 0; q < 10; q++) {
				while (!queues[q].isEmpty()) {
					temp[count++] = (int) queues[q].poll();
				}
			}
			a = temp;
		}
		return a;
	}
	

}
