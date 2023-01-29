package DataStructures_Algorithms;

import java.util.LinkedList;


public class Radix_Sort {

	public static void main(String[] args) {
		int[] listNumber = {3290, 17, 6392, 8330, 3247 };
		
		System.out.print("After sorting, new list number is: ");
		for (int i = 0; i < listNumber.length; i++) {
			System.out.print(radixSort(listNumber, 5, 4)[i] + " ");
		}

	}
	
	public static int digitAt(int x, int i) {
		while (i-- > 0) {
			x = x / 10;
		}
		return x % 10;
	}
	
	public static int[] radixSort(int[] a, int n, int d) {
		for (int j = 0; j < d; j++) {
			@SuppressWarnings("unchecked")
			LinkedList<Integer>[] queues = new LinkedList[10];
			for (int q = 0; q < 10; q++) {
				queues[q] = new LinkedList<Integer>();
			}
			
			for (int i = 0; i < n; i++) {
				queues[digitAt(a[i], j)].add(a[i]);
			}
			
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
