
class SortingMethods {
    static int[] selectionSort(int[] arr) { // selecting an element and compare to other elements
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
        return arr;
    }

    static int[] bubbleSort(int arr[]) { // bubbling out largest number
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    static int[] insertionSort(int arr[]) // inserting elements in sorted way prefrable if array is almost sorted ie.for
                                          // best case
    {
        for (int i = 1; i < arr.length; i++) { // shifting larger elements to next position and inserting key element to
                                               // pcorrect location
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        return arr;
    }

    static void mergeSort(int arr[], int low, int high) { // prefered worst case=nlogn
        if (low < high) {
            int mid = low + (high - low) / 2; // this is prefered over (low+high)/2 because low+high may cause overflow
                                              // ie.if it is greater than maxinteger
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }

    static void merge(int[] arr, int low, int mid, int high) { // part of mergesort
        int n1 = mid - low + 1;
        int n2 = high - mid;
        int L[] = new int[n1];
        int R[] = new int[n2];
        for (int i = 0; i < n1; i++) {
            L[i] = arr[low + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[mid + 1 + j];
        }
        int i = 0, j = 0;
        int k = low;
        // sorting and merging
        while (i < n1 && j < n2) { // compare and insert
            if (L[i] < R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) { // insert if remaining
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) { // insert if remaining
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    static void radixSort(int[] arr) { // complexity O(d*n)
        // input range natural numbers only
        int bucket[][] = new int[10][arr.length];
        int ln = 0;
        for (int i : arr) {
            int temp = (int) (Math.log10(i) + 1);
            System.out.print(temp + " "); // only for checking no of digits
            if (temp > ln) {
                ln = temp;
            }
        }
        for (int i = 0; i < ln; i++) {
            System.out.println();
            for (int j = 0; j < arr.length; j++) {
                int d = ((int) (arr[j] / Math.pow(10, i))) % 10;
                System.out.print(d + " "); // this is only for printing digit at given place
                bucket[d][j] = arr[j];
            }
            int k = 0;
            for (int l = 0; l < 10; l++) {
                for (int m = 0; m < bucket[l].length; m++) {
                    if (bucket[l][m] != 0) {
                        arr[k] = bucket[l][m];// error index out of bonds
                        k++;
                    }
                }
            }
        }
    }

    static void print(int[] arr) {
        System.out.println();
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int arr[] = { 6, 2, 9, 46, 7, 4, -1, -2, 0, -4, 5 };
        mergeSort(arr, 0, arr.length - 1);
        print(arr);
        int arr1[] = { 3, 5, 1, 2, 9, 6, 5, 4, -1, -2 };
        insertionSort(arr1);
        print(arr1);
        int arr2[] = { 1, 7, 8, 5, 4, 3, 7, 2, 00005 }; // find problem for 2 digit
        radixSort(arr2);
        print(arr2);
    }
}
