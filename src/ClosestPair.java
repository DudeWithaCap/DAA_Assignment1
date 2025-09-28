import java.util.*;

class Point {
    double x, y;
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

public class ClosestPair {
    private static long comparisons = 0;
    private static int currentDepth = 0;
    private static int maxDepth = 0;
    private static double dist(Point p1, Point p2) {
        comparisons++;
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) +
                (p1.y - p2.y) * (p1.y - p2.y));
    }

    private static double bruteForce(Point[] points, int left, int right) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                min = Math.min(min, dist(points[i], points[j]));
            }
        }
        return min;
    }

    private static double stripClosest(List<Point> strip, double d) {
        double min = d;
        int n = strip.size();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n && (strip.get(j).y - strip.get(i).y) < min; j++) {
                min = Math.min(min, dist(strip.get(i), strip.get(j)));
            }
        }
        return min;
    }

    private static double closestUtil(Point[] pointsX, Point[] pointsY, int left, int right) {
        currentDepth++;
        maxDepth = Math.max(maxDepth, currentDepth);

        if (right - left <= 3) {
            double res = bruteForce(pointsX, left, right);
            currentDepth--;
            return res;
        }

        int mid = (left + right) / 2;
        Point midPoint = pointsX[mid];

        List<Point> leftY = new ArrayList<>();
        List<Point> rightY = new ArrayList<>();
        for (Point p : pointsY) {
            if (p.x <= midPoint.x) {
                leftY.add(p);
            } else {
                rightY.add(p);
            }
        }

        double dl = closestUtil(pointsX, leftY.toArray(new Point[0]), left, mid);
        double dr = closestUtil(pointsX, rightY.toArray(new Point[0]), mid + 1, right);

        double d = Math.min(dl, dr);

        List<Point> strip = new ArrayList<>();
        for (Point p : pointsY) {
            if (Math.abs(p.x - midPoint.x) < d) {
                strip.add(p);
            }
        }

        double result = Math.min(d, stripClosest(strip, d));
        currentDepth--;
        return result;
    }

    public static double closestPair(Point[] points) {
        Point[] pointsX = points.clone();
        Arrays.sort(pointsX, Comparator.comparingDouble(p -> p.x));

        Point[] pointsY = points.clone();
        Arrays.sort(pointsY, Comparator.comparingDouble(p -> p.y));

        return closestUtil(pointsX, pointsY, 0, points.length - 1);
    }

    public static void main(String[] args) {
        Point[] points = {
                new Point(1, 5),
                new Point(5, 10),
                new Point(10, 15),
                new Point(20, 25),
                new Point(50, 100),
                new Point(100, 101)
        };

        System.out.println("Points:");
        for (Point p : points) {
            System.out.println("(" + p.x + ", " + p.y + ")");
        }
        long start = System.nanoTime();
        double minDist = closestPair(points);
        long end = System.nanoTime();
        System.out.println("Closest pair distance: " + minDist);
        System.out.println("Metrics");
        System.out.println("Execution time (ns): " + (end - start));
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Maximum recursion depth: " + maxDepth);
    }
}
