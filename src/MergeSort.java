public class MergeSort {

    private static final int insertion_sort= 16;
    private static long comparisons = 0;
    private static int currentDepth = 0;
    private static int maxDepth = 0;
    private static int allocations = 0;
    public static void main(String[] args) {
        int[] arr = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("Unsorted array");
        printArray(arr);
        long startTime = System.nanoTime();
        mergeSort(arr);
        long endTime = System.nanoTime();
        System.out.println("Execution time (ns): " + (endTime - startTime));
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Maximum recursion depth: " + maxDepth);
        System.out.println("Array allocations: " + allocations);
        System.out.println("Sorted array");
        printArray(arr);
    }
    public static void mergeSort(int[] arr) {
        allocations++;
        int[] buffer = new int[arr.length];
        mergeSort(arr, buffer, 0, arr.length - 1, 1);
    }

    private static void mergeSort(int[] arr, int[] buffer, int left, int right, int depth) {
        currentDepth = depth;
        maxDepth = Math.max(maxDepth, depth);

        if (right - left <= insertion_sort) {
            insertionSort(arr, left, right);
            return;
        }

        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, buffer, left, mid, depth + 1);
            mergeSort(arr, buffer, mid + 1, right, depth + 1);

            merge(arr, buffer, left, mid, right);
        }
        }

    private static void merge(int[] arr, int[] buffer, int left, int mid, int right) {
        for (int i = left; i <= mid; i++) {
            buffer[i] = arr[i];
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            comparisons++;
            if (buffer[i] <= arr[j]) {
                arr[k++] = buffer[i++];
            } else {
                arr[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            arr[k++] = buffer[i++];
        }
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static void printArray(int[] arr) {
        for (int x : arr) {
            System.out.print(x + " ");
        }
        System.out.println();
    }
}
