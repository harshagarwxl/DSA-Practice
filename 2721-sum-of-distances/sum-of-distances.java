import java.util.*;

public class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] result = new long[n];
        Map<Integer, List<Integer>> map = new HashMap<>();

        // Group indices of the same values
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        // Process each group of indices
        for (List<Integer> indices : map.values()) {
            int m = indices.size();
            if (m <= 1) continue;

            // Calculate total sum of indices for prefix sum logic
            long totalSum = 0;
            for (int index : indices) {
                totalSum += index;
            }

            long prefixSum = 0;
            for (int i = 0; i < m; i++) {
                int currentIndex = indices.get(i);
                
                // Mathematical formula for sum of distances:
                // Left side: (count of elements to left * current index) - (sum of left indices)
                // Right side: (sum of right indices) - (count of elements to right * current index)
                long leftDist = (long) i * currentIndex - prefixSum;
                long rightDist = (totalSum - prefixSum - currentIndex) - (long) (m - 1 - i) * currentIndex;
                
                result[currentIndex] = leftDist + rightDist;
                prefixSum += currentIndex;
            }
        }

        return result;
    }
}