public class SelectionSort extends SortAlgorithm {

	public SelectionSort(int input_array[]) {
		super(input_array);
	}

    @Override
    public void sort() {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                comparison_counter++; // Increment comparison counter
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            //if (minIndex != i) {
                // Swap arr[i] and arr[minIndex]
                swap(i, minIndex); // if block prevents swapping number with itself, so i comment line'd them

            //}
        }
    }

    @Override
    public void print() {
    	System.out.print("Selection Sort\t=>\t");
    	super.print();
    }
}
