import java.util.Arrays;
public class DeterministicSelect {
    private static long comparisons = 0;
    private static int currentDepth = 0;
    private static int maxDepth = 0;

    public static int select(int[] arr, int k) {
        return select(arr, 0, arr.length - 1, k);
    }

    private static int select(int[] arr, int left, int right, int k) {
        currentDepth++;
        maxDepth = Math.max(maxDepth, currentDepth);

        if (left == right) {
            currentDepth--;
            return arr[left];
        }

        int pivotIndex = medianOfMedians(arr, left, right);

        pivotIndex = partition(arr, left, right, pivotIndex);

        int rank = pivotIndex - left + 1;

        int result;
        if (k == rank) {
            result = arr[pivotIndex];
        } else if (k < rank) {
            result = select(arr, left, pivotIndex - 1, k);
        } else {
            result = select(arr, pivotIndex + 1, right, k - rank);
        }

        currentDepth--;
        return result;
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            comparisons++;
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right);
        return storeIndex;
    }

    private static int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;
        if (n <= 5) {
            Arrays.sort(arr, left, right + 1);
            return left + n / 2;
        }

        int numMedians = (int) Math.ceil((double) n / 5.0);
        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);

            Arrays.sort(arr, subLeft, subRight + 1);
            int medianIndex = subLeft + (subRight - subLeft) / 2;
            swap(arr, left + i, medianIndex);
        }

        return medianOfMedians(arr, left, left + numMedians - 1);
    }

    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 3, 5, 7, 4, 19, 26, 15, 8, 21};
        int k = 5;

        System.out.println("Original array:");
        System.out.println(Arrays.toString(arr));

        long start = System.nanoTime();
        int kth = select(arr, k);
        long end = System.nanoTime();

        System.out.println(k + "-th smallest element is " + kth);
        System.out.println("Metrics");
        System.out.println("Execution time (ns): " + (end - start));
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Maximum recursion depth: " + maxDepth);
    }
}
