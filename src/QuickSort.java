import java.util.Random;
public class QuickSort {
    private static final Random rand = new Random();

    private static long comparisons =0;
    private static long swaps =0;
    private static int currentDepth=0;
    private static int maxDepth=0;
    private static long pivotSelections=0;

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1, 1);
    }
    private static void quickSort(int[] arr, int low, int high, int depth) {
        while (low < high) {
            currentDepth = depth;
            maxDepth=Math.max(maxDepth, depth);
            int pivotIndex = randomizedPartition(arr, low, high);

            int leftSize = pivotIndex - low;
            int rightSize = high - pivotIndex;

            if (leftSize < rightSize) {
                quickSort(arr, low, pivotIndex - 1, depth +1);
                low = pivotIndex + 1;
            } else {
                quickSort(arr, pivotIndex + 1, high, depth +1);
                high = pivotIndex - 1;
            }
        }
    }

    private static int randomizedPartition(int[] arr, int low, int high) {
        pivotSelections++;
        int pivotIndex = low + rand.nextInt(high - low + 1);
        swap(arr, pivotIndex, high);

        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            comparisons++;
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            swaps++;
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5, 3, 12, 6};

        System.out.println("Original array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        long startTime = System.nanoTime();
        quickSort(arr);
        long endTime = System.nanoTime();

        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("Metrics");
        System.out.println("Execution time (ns): " + (endTime - startTime));
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Swaps: " + swaps);
        System.out.println("Maximum recursion depth: " + maxDepth);
        System.out.println("Pivot selections: " + pivotSelections);
    }
}
