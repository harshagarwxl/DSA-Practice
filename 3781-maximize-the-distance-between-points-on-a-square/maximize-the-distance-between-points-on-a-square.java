import java.util.*;

class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int m = points.length;
        long[] positions = new long[m];
        
        // Step 1: Transform 2D coordinates to 1D perimeter positions
        for (int i = 0; i < m; i++) {
            int x = points[i][0], y = points[i][1];
            if (y == 0) positions[i] = x;                // Bottom edge
            else if (x == side) positions[i] = side + y; // Right edge
            else if (y == side) positions[i] = 2L * side + (side - x); // Top edge
            else positions[i] = 3L * side + (side - y);  // Left edge
        }
        Arrays.sort(positions);

        long low = 1, high = side;
        int ans = 0;

        // Step 2: Binary Search on the distance
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (canPlace(positions, k, mid, 4L * side)) {
                ans = (int) mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    private boolean canPlace(long[] pos, int k, long dist, long totalPerimeter) {
        int n = pos.length;
        // Since it's a loop, the first point doesn't have to be pos[0].
        // However, checking from each possible starting point in the first 'gap' is sufficient.
        for (int i = 0; i < n; i++) {
            if (pos[i] >= pos[0] + dist) break; // Optimization: only need to check starts within the first gap
            
            int count = 1;
            long lastPos = pos[i];
            long firstPos = pos[i];
            
            for (int j = 1; j < n; j++) {
                int idx = (i + j) % n;
                long currentPos = pos[idx];
                long actualPos = (idx < i) ? currentPos + totalPerimeter : currentPos;
                
                if (actualPos - lastPos >= dist) {
                    count++;
                    lastPos = actualPos;
                }
                if (count == k) {
                    // Check wrap-around distance to the first point
                    if (firstPos + totalPerimeter - lastPos >= dist) return true;
                    break; 
                }
            }
        }
        return false;
    }
}