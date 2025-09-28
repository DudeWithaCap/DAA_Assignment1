Kabiyev Adilet, SE-2433

Assignment 1.

Architecture notes:
Each algorithm had comparisons counter, time measurement, and recursion depth measurement built-in in algorithms files.

Algorithm Notes:
MergeSort - breaks the array into halves, sorts them, then merges. Linear merge, reusable buffer and insertion sort were used. Small parts are sorted with insertion sort instead of recursion (faster for small inputs).

QuickSort - picks a random pivot, splits into smaller and bigger parts, following basics of its algorithm. Counts swaps done between variables in array for further analysis. Swaps number always vary even with the same array due to the random pivot selection, so does time.

Deterministic Select - finds the k-th smallest element. Groups elements in 5s, finds medians, then picks the median of those. Deterministic select had a fixed pivot of fifth element in array because of grouping into 5s.

Closest Pair of Points - sorts points by x, splits into halves, and checks a middle “strip”. 7 neighbors checking. Counts swaps too like Quicksort. Proven to be slowest algorithm taking minimum 6.000.000 ns

Summary:
Randomized pivot can greatly affect the algorithms metrics, every time providing new metrics. This may cause algorithm to run either very fast or very slow time-wise, especially for QuickSort, taking into fact that QuickSort has worst bad-case-scenario time complexity. Comparison rate doubles as the array gets bigger.
MergeSort showed good metrics due to linear merge and reusable buffer. Comparison doubles as the array gets bigger.
Deterministic Selects' grouping into 5s method solves k-th smallest element job great
Closest Pair of Points showed slowest time.

<img width="819" height="534" alt="image" src="https://github.com/user-attachments/assets/eecfefeb-f5f7-40d9-9b7c-c18a0a45c5d0" />

Table with metrics
