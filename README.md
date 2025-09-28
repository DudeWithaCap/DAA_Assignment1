Kabiyev Adilet, SE-2433

Assignment 1.

Architecture notes:
Each algorithm had comparisons counter, time measurement, and recursion depth measurement built-in in algorithms files.

Algorithm Notes:
MergeSort - breaks the array into halves, sorts them, then merges. Linear merge, reusable buffer and insertion sort were used. Small parts are sorted with insertion sort instead of recursion (faster for small inputs).

QuickSort - picks a random pivot, splits into smaller and bigger parts, following basics of its algorithm. Counts swaps done between variables in array for further analysis. Swaps number always vary even with the same array due to the random pivot selection, so does time.

Deterministic Select - finds the k-th smallest element. Groups elements in 5s, finds medians, then picks the median of those. Slowest algorithm, max time taken for the algorithm to be done was 3.000.000+- ns. when other algorithms run under 50.000 ns maximum. Deterministic select had a fixed pivot of fifth element in array because of grouping into 5s.

Closest Pair of Points - sorts points by x, splits into halves, and checks a middle “strip”. 7 neighbors checking. Counts swaps too like Quicksort. Isnt as time consuming algorithm as Deterministic Select.

Summary:
Randomized pivot can greatly affect the algorithms metrics, every time providing new metrics. This may cause algorithm to run either very fast or very slow time-wise, especially for QuickSort, taking into fact that QuickSort has worst bad-case-scenario time complexity.
MergeSort showed good metrics due to linear merge and reusable buffer.
Deterministic Selects' grouping into 5s method solves k-th smallest element job great, despite drawbacks in metrics.
Closest Pair of Points showed good comparison rate.
