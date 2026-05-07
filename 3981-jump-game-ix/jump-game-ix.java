class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;
        
        // 1. Calculate Prefix Maximums
        int[] prefixMax = new int[n];
        prefixMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
        }
        
        // 2. Calculate Suffix Minimums
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }
        
        int[] ans = new int[n];
        int start = 0; // Tracks the start of the current isolated block
        
        // 3. Find cuts and fill answers
        for (int i = 0; i < n; i++) {
            // A cut happens if max of left <= min of right (or we hit the end)
            if (i == n - 1 || prefixMax[i] <= suffixMin[i + 1]) {
                
                // The maximum of the current block is just the prefixMax at the cut
                int blockMax = prefixMax[i];
                
                // Every element in this block shares the same maximum reachable value
                for (int j = start; j <= i; j++) {
                    ans[j] = blockMax;
                }
                
                // Move start to the beginning of the next block
                start = i + 1;
            }
        }
        
        return ans;
    }
}