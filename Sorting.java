import java.util.Arrays;

public class Sorting {
	public static void mergeSort(int[] array) {
		int[] copy = new int[array.length];
		System.arraycopy(array, 0, copy, 0, array.length);
		splitMerge(copy, 0, array.length, array);
	}
	
	private static void splitMerge(int[] copy, int begin, int end, int[] array) {
		if (end - begin < 2)
			return;
		int middle = begin + (end - begin) / 2;
		splitMerge(array, begin, middle, copy);
		splitMerge(array, middle, end, copy);
		merge(copy, begin, middle, end, array);
	}
	
	private static void merge(int[] src, int begin, int middle, int end, int[] dest) {
		int fst = begin;
		int snd = middle;
		for(int k = begin; k < end; k++) {
			if( fst < middle && (snd >= end || src[fst] <= src[snd]) ) {
				dest[k] = src[fst];
				fst++;
			}
			else {
				dest[k] = src[snd];
				snd++;
			}
			
		}
	}
	
	public static void mergeSortOpti(int[] array, int minrun) {
		int[] copy = new int[array.length];
		System.arraycopy(array, 0, copy, 0, array.length);
		splitMergeOpti(copy, 0, array.length, array, minrun);
	}
	
	private static void splitMergeOpti(int[] copy, int begin, int end, int[] array, int minrun) {
		if (end - begin < 2)
			return;
		
		if(end - begin <= minrun) {
			insertionSort(array, begin, (end-begin));
			return;
		}
		
		int middle = begin + (end - begin) / 2;
		splitMergeOpti(array, begin, middle, copy, minrun);
		splitMergeOpti(array, middle, end, copy, minrun);
		merge(copy, begin, middle, end, array);
	}
	
	public static void insertionSort(int[] array) {
		if (array.length <= 1) return;
		insertionSort(array, 0, array.length);
	}
	
	public static void insertionSort(int[] array, int fromIndex, int count) {
		
		for(int i = fromIndex + 1; i < (fromIndex + count); i++) {
			int key = array[i];
			int j = i - 1;
			while(j >= fromIndex && array[j] > key ) {
				array[j+1] = array[j];
				j--;
			}
			array[j+1] = key;
		}
	}
	
	//Сортировка Шелла. Взята последовательность шагов, предложенная Шеллом
	public static void shellSort(int[] array) {
		shellSort(array, new ShellGapSequence(array.length));
	}
	
	//Сортировка Шелла с использованием последовательности шагов, предложенной Хиббардом
	public static void shellSort2(int[] array) {
		shellSort(array, new HibbardGapSequence(array.length));
	}
	
	public static void shellSort(int[] array, GapSequenceIterator gapIterator) {
		int n = array.length;
		if (n <= 1) return;
		
		int gap = gapIterator.next();
		while(gap >= 1) {
			for(int k = 0;k < gap;k++) {
				for(int i = k + gap; i < n; i+= gap) {//sort subsequence
					int key = array[i];
					int j = i - gap;
					while(j >= 0 && array[j] > key ) {
						array[j + gap] = array[j];
						j -= gap;
					}
					array[j + gap] = key;
				}
			}
			gap = gapIterator.next();
		}
		
	}
	
	
	public static void bubbleSort(int[] array) {
		
		for(int j = array.length - 1; j > 0 ; j--) { 
			for(int i = 0; i < j; i++) {
				if(array[i] > array[i+1]) {
					int c = array[i];
					array[i] = array[i+1];
					array[i+1] = c;
				}
			} 
		}
	}
	
	public static void bubbleSortOpti(int[] array) {
		
		for(int j = array.length - 1; j > 0 ; j--) { 
			boolean wasSwap = false;
			for(int i = 0; i < j; i++) {
				
				if(array[i] > array[i+1]) {
					int c = array[i];
					array[i] = array[i+1];
					array[i+1] = c;
					wasSwap = true;
				}
			} 
			if(!wasSwap)
				return;
		}
	}
	
	public static void shakerSort(int[] array) {
		int l = 0;
		int r = array.length - 1;
		
		while(l < r) {
			boolean wasSwap = false;
			for(int i = l; i < r; i++) {//bubble to right
				if(array[i] > array[i+1]) {
					int c = array[i];
					array[i] = array[i+1];
					array[i+1] = c;
					wasSwap = true;
				}
			}
			r--;
			if(!wasSwap)break;
			if(l >= r)break;
			wasSwap = false;
			for(int i = r; i > l; i--) {//bubble to left
				if(array[i] < array[i-1]) {
					int c = array[i];
					array[i] = array[i-1];
					array[i-1] = c;
					wasSwap = true;
				}
			}
			l++;
			if(!wasSwap)break;
		}
		
	}
	
	public static void selectionSort(int[] array) {
		for(int l = 0; l < array.length-1;l++) {
			int min = array[l];
			int minIndex = l;
			for(int i = l+1; i < array.length; i++) {
				if(array[i] < min) {
					min = array[i];
					minIndex = i;
				}
			}
			if(minIndex != l) {
				int c = array[l];
				array[l] = array[minIndex];
				array[minIndex] = c;
			}
		}
		
	}
	
	public static void countingSort(int[] array, int k) { //array[i] in [0..k)
		int[] C = new int[k];
		Arrays.fill(C, 0);
		for(int i = 0; i < array.length; i++) 
			C[array[i]]++;
		
		int j = 0;
		for(int x = 0; x < k; x++)
			for(int i = 0; i < C[x]; i++) 
				array[j++] = x;
		
		
	}
}

