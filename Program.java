

public class Program {
	
	public static void main(String[] args) {
		
		
		Program program = new Program();
		//program.run();
		//System.out.println("");
		
		program.runPerfomanceTest();
	}
	
	void run() {
		int[] arr = new int[10_000_000];
		fillArrayRandom(arr);
		System.out.println("isSorted: "+ isSorted(arr));
		System.out.println("sum: "+ getSum(arr));
		System.out.println("sorting...");
		Sorting.mergeSort(arr);
		System.out.println("isSorted: "+ isSorted(arr));
		System.out.println("sum: "+ getSum(arr));
	}
	
	void runPerfomanceTest() {
		System.out.println("size;insertion(full);insertion(10%);insertion(5);shell(full);shell(10%);shell(5);shell_hibbard(full);shell_hibbard(10%);shell_hibbard(5);");
		int size = 1000;
		while(size <= 2500_000) {
			
			int[] orderedArray = new int[size];
			fillArrayOrdered(orderedArray);
			
			int[] fullyShuffledArray = new int[size];
			System.arraycopy(orderedArray, 0, fullyShuffledArray, 0, orderedArray.length);
			shuffleArray(fullyShuffledArray);
			
			int[] shuffledArray10perc = new int[size];
			System.arraycopy(orderedArray, 0, shuffledArray10perc, 0, orderedArray.length);
			shuffleArray(shuffledArray10perc,shuffledArray10perc.length/10);
			
			int[] shuffledArray5items = new int[size];
			System.arraycopy(orderedArray, 0, shuffledArray5items, 0, orderedArray.length);
			shuffleArray(shuffledArray5items, 5);
			
			
			System.out.print(size + ";");
			
			int[] testArray = new int[size];
			
			for(int i = 0; i < 7; i++) {
				System.arraycopy(fullyShuffledArray, 0, testArray, 0, fullyShuffledArray.length);
				testSort(testArray, i);
				System.arraycopy(shuffledArray10perc, 0, testArray, 0, shuffledArray10perc.length);
				testSort(testArray, i);
				System.arraycopy(shuffledArray5items, 0, testArray, 0, shuffledArray5items.length);
				testSort(testArray, i);
			}
			
			System.out.println("");
			
			size += size;
		}
	}
	
	
	void testSort(int[] array, int sortMethod) {
		Stopwatch sw = new Stopwatch();
		sw.start();
		
		switch(sortMethod) {
			case 0:
				Sorting.insertionSort(array);
				break;
			case 1:
				Sorting.shellSort(array);
				break;
			case 2:
				Sorting.shellSort2(array);
				break;
			case 3:
				Sorting.mergeSort(array);
				break;
			case 4:
				Sorting.mergeSortOpti(array, 32);
				break;
			case 5:
				Sorting.mergeSortOpti(array, 64);
				break;
			case 6:
				Sorting.mergeSortOpti(array, 128);
				break;
		}
		
		System.out.print(sw.getDuration() + ";");
	}
	
	void fillArrayOrdered(int[] array) {
		for(int i = 0; i < array.length; i++) {
			array[i] = i + 1;
		}
	}
	
	void shuffleArray(int[] array) {
		shuffleArray(array, array.length);
	}
	
	void shuffleArray(int[] array, int n) {
		for(int i = 0; i < n; i++) {
			int randomIndexFrom = (int)( Math.round(Math.random()*(array.length-1)) );
			int randomIndexTo = (int)( Math.round(Math.random()*(array.length-1)) );
			
			if (randomIndexFrom > randomIndexTo) {
				int buf = array[randomIndexFrom];
				for(int j = randomIndexFrom;j > randomIndexTo ;j--) 
					array[j] = array[j - 1];
				array[randomIndexTo] = buf;
			}
			else {
				int buf = array[randomIndexFrom];
				for(int j = randomIndexFrom; j < randomIndexTo; j++)
					array[j] = array[j + 1];
				array[randomIndexTo] = buf;
			}
		}
	}
	
	void fillArrayRandom(int[] array) {
		for(int i = 0; i < array.length; i++) {
			int value = (int)(Math.random()*1000);
			array[i] = value;
		}
		
	}
	
	int getSum(int[] array) {
		int res = 0;
		for(int i = 0; i < array.length; i++) 
			res += array[i];
		return res;
	}
	
	boolean isSorted(int[] array) {
		if(array.length <= 1)
			return true;
		
		int c = array[0];
		for(int i = 1; i < array.length; i++) {
			int d = array[i];
			if(d < c)return false;
			c = d;
		} 
		return true;
	}
	
	
}
