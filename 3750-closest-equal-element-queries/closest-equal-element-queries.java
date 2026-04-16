import java.util.*;

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        Map<Integer, List<Integer>> posMap = new HashMap<>();
        int n = nums.length;

        // Step 1: Map each value to a sorted list of its indices
        for (int i = 0; i < n; i++) {
            posMap.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        List<Integer> finalResults = new ArrayList<>();

        for (int targetIdx : queries) {
            int val = nums[targetIdx];
            List<Integer> indices = posMap.get(val);

            // If the element appears only once, there is no other equal element
            if (indices == null || indices.size() <= 1) {
                finalResults.add(-1);
                continue;
            }

            int pos = Collections.binarySearch(indices, targetIdx);
            int size = indices.size();
            
            // Get immediate left neighbor (with wrap-around to the end of the list)
            int leftIdx = indices.get((pos - 1 + size) % size);
            
            // Get immediate right neighbor (with wrap-around to the start of the list)
            int rightIdx = indices.get((pos + 1) % size);

            // Calculate minimum circular distances
            int distLeft = getCircularDistance(targetIdx, leftIdx, n);
            int distRight = getCircularDistance(targetIdx, rightIdx, n);

            // Return the shortest distance (not the index)
            finalResults.add(Math.min(distLeft, distRight));
        }
        return finalResults;
    }
    
    // Helper method to compute shortest distance in a circular array
    private int getCircularDistance(int idx1, int idx2, int n) {
        int rawDistance = Math.abs(idx1 - idx2);
        return Math.min(rawDistance, n - rawDistance);
    }
}