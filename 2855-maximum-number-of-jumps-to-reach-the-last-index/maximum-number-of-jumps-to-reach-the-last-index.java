class Solution {
    public int maximumJumps(int[] nums, int target) {
    int n = nums.length;
    // Hint 2: Define dp array where dp[i] is max jumps from 0 to i
    int[] dp = new int[n];
    java.util.Arrays.fill(dp, -1); 
    
    dp[0] = 0; // Base case: 0 jumps to start at index 0

    // Hint 3: For each j, iterate over all i < j
    for (int j = 1; j < n; j++) {
        for (int i = 0; i < j; i++) {
            // Only consider jump if index i is reachable
            if (dp[i] != -1) {
                int diff = nums[j] - nums[i];
                
                // Condition: -target <= nums[j] - nums[i] <= target
                if (diff >= -target && diff <= target) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
        }
    }
    
    return dp[n - 1];
}
}