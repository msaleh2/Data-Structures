package chapter2;

import java.util.concurrent.ThreadLocalRandom;

public class Mergesort {
	//typically (n log n)
	
	private static int origin_list [] = new int [10000000];
	
	public static void main (String args[]) {
		//generate 1000 random numbers from 1-1000 and put them into an array
		for(int i = 0; i < 10000000; i++) {
			origin_list[i] = ThreadLocalRandom.current().nextInt(1, 9999999 + 1);
		}
		//call the sort function
		int [] sorted = mergesort(origin_list);
		//check if the array is sorted by 
		/*for(int i = 0; i < sorted.length; i++) {
			System.out.print(sorted[i] + " ");
		}*/
	}
	
	private static int [] mergesort(int [] list) {
		
		//System.out.println("size: " + list.length + "\t");
		
		if(list.length < 1) {
			//error
			return null;
		}
		
		if(list.length == 1) {
			return list;
		}
		int listLength = list.length / 2;
		if(list.length % 2 == 1) {
			listLength = list.length / 2 + 1;
		}
		int [] listA = new int [list.length / 2];
		int [] listB = new int [listLength];
		for(int i = 0, a = 0; i < list.length/2; i++) {
			listA[a++] = list[i];
		}
		for(int i = list.length/2, b = 0; i < list.length; i++) {
			listB[b++] = list[i];
		}
		//repeat above step until all sub arrays have a length of 1
		listA = mergesort(listA);
		listB = mergesort(listB);
		
		//then join them back together in reverse order
		return merge(listA, listB);
	}
	
	private static int [] merge(int [] listA, int [] listB) {
		int size = listA.length + listB.length;
		int merged [] = new int [size];
		for(int i = 0, a = 0, b = 0; i < merged.length; i++) {
			//if one list reaches the end, put other list elements into merged list and exit
			if(listA.length == a) {
				merged[i] = listB[b++];
				continue;
			} else if (listB.length == b) {
				merged[i] = listA[a++];
				continue;
			}
			
			if(listA[a] < listB[b]) {
				merged[i] = listA[a++];
			} else {
				merged[i] = listB[b++];
			}
		}
		return merged;
	}
}