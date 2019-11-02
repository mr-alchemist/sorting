

public class Program {
	
	public static void main(String[] args) {
		
		
		Program program = new Program();
		//program.run();
		//System.out.println("");
		
		program.runTest();
	}
	
	void run() {
		int[] arr = new int[100000];
		fillArrayRandom(arr);
		System.out.println("isSorted: "+ isSorted(arr));
		System.out.println("sum: "+ getSum(arr));
		System.out.println("sorting...");
		//bubbleSortArrayOpti();
		//shakerSortArray();
		//selectionSortArray();
		//insertionSortArray();
		shellSortArray2(arr);
		System.out.println("isSorted: "+ isSorted(arr));
		System.out.println("sum: "+ getSum(arr));
	}
	
	void runTest() {
		System.out.println("size;insertion(full);insertion(10%);insertion(5);shell(full);shell(10%);shell(5);shell_hibbard(full);shell_hibbard(10%);shell_hibbard(5);");
		int size = 20;
		while(size <= 2000_000) {
			int[] testArray = new int[size];
			System.out.print(size + ";");
			TestSort1(testArray, true);
			TestSort2(testArray, false);
			TestSort3(testArray, false);
			System.out.println("");
			
			size += size;
		}
	}
	
	void TestSort1(int[] array, boolean fillArray){
		if(fillArray) {
			fillArrayOrdered(array);
		}
		
		shuffleArray(array);
		
		Stopwatch sw = new Stopwatch();
		
		sw.start();
		insertionSortArray(array);
		System.out.print(sw.getDuration() + ";");
		
		shuffleArray(array, array.length/10);
		sw.start();
		insertionSortArray(array);
		System.out.print(sw.getDuration() + ";");
		
		shuffleArray(array, 5);
		sw.start();
		insertionSortArray(array);
		System.out.print(sw.getDuration() + ";");
	}
	
	void TestSort2(int[] array, boolean fillArray){
		if(fillArray) {
			fillArrayOrdered(array);
		}
		
		shuffleArray(array);
		
		Stopwatch sw = new Stopwatch();
		
		sw.start();
		shellSortArray(array);
		System.out.print(sw.getDuration() + ";");
		
		shuffleArray(array, array.length/10);
		sw.start();
		shellSortArray(array);
		System.out.print(sw.getDuration() + ";");
		
		shuffleArray(array, 5);
		sw.start();
		shellSortArray(array);
		System.out.print(sw.getDuration() + ";");
	}
	
	void TestSort3(int[] array, boolean fillArray){
		if(fillArray) {
			fillArrayOrdered(array);
		}
		
		shuffleArray(array);
		
		Stopwatch sw = new Stopwatch();
		
		sw.start();
		shellSortArray2(array);
		System.out.print(sw.getDuration() + ";");
		
		shuffleArray(array, array.length/10);
		sw.start();
		shellSortArray2(array);
		System.out.print(sw.getDuration() + ";");
		
		shuffleArray(array, 5);
		sw.start();
		shellSortArray2(array);
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
	
	void bubbleSortArray(int[] array) {
		
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
	
	void bubbleSortArray_(int[] array) {
		
		for(int j = array.length - 1; j >= 1 ; j--) { 
			for(int i = 1; i <= j; i++) {
				if( array[i] < array[i-1]) {
					int c = array[i-1];
					array[i-1] = array[i];
					array[i] = c;
				}
			}
		}
	}
	
	void bubbleSortArrayOpti(int[] array) {
		
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
	
	void shakerSortArray(int[] array) {
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
	
	void selectionSortArray(int[] array) {
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
	
	void insertionSortArray(int[] array) {
		if (array.length <= 1) return;
		
		for(int i = 1; i < array.length; i++) {
			int key = array[i];
			int j = i - 1;
			while(j >= 0 && array[j] > key ) {
				array[j+1] = array[j];
				j--;
			}
			array[j+1] = key;
		}
	}
	
	//Сортировка Шелла. Взята последовательность шагов, предложенная Шеллом
	void shellSortArray(int[] array) {
		int n = array.length;
		if (n <= 1) return;
		
		int gap = n / 2;//последовательность, предложенная Шеллом
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
			gap = gap / 2;
		}
		
	}
	
	//Сортировка Шелла и использованием последовательности шагов, предолженной Хиббардом
	void shellSortArray2(int[] array) {
		int n = array.length;
		if (n <= 1) return;
		
		int exp2 = 2;
		while(exp2 <= n) {
			exp2 += exp2;
		}
		exp2 = exp2/2;
		
		//System.out.println("exp2: " + exp2);
		
		int gap = exp2 - 1;
		
		while(gap >= 1) {
			//System.out.println("gap: " + gap);
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
			exp2 = exp2 / 2;
			gap = exp2 - 1;
		}
		
	}
	
	
	
}
