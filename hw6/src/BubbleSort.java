public class BubbleSort extends SortAlgorithm {

	public BubbleSort(int input_array[]) {
		super(input_array);
	}
	
    @Override
    public void sort() {
        int n = arr.length;
        boolean sorted = false; // Flag to check if the array is sorted

        for (int i = 0; i < n-1; i++) {
            sorted = true; // Assume array is sorted at the beginning of each pass

            for (int j = 0; j < n - i - 1; j++) {
                comparison_counter++;

                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    swap(j, j+1);
                    sorted = false; // Array is not sorted yet
                }
            }

            // If the array is sorted, break out of the loop
            if (sorted) {
                break;
            }
        }
    }

    @Override
    public void print() {
    	System.out.print("Bubble Sort\t=>\t");
    	super.print();
    }
}
