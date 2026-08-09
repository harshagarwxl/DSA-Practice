class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] suffixSum = new int[n + 1];
        
        // Build suffix sum array
        for (int i = n - 1; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        
        // Memoization table
        Integer[][] memo = new Integer[n][n + 1];
        
        return helper(0, 1, piles, suffixSum, memo);
    }
    
    private int helper(int i, int M, int[] piles, int[] suffixSum, Integer[][] memo) {
        int n = piles.length;
        
        // If we can take all remaining piles
        if (i + 2 * M >= n) {
            return suffixSum[i];
        }
        
        if (memo[i][M] != null) {
            return memo[i][M];
        }
        
        int best = 0;
        
        // Try taking X piles (1 <= X <= 2M)
        for (int X = 1; X <= 2 * M; X++) {
            // Opponent's best after we take X
            int opponent = helper(i + X, Math.max(M, X), piles, suffixSum, memo);
            // Current player's gain = remaining stones - opponent's gain
            best = Math.max(best, suffixSum[i] - opponent);
        }
        
        memo[i][M] = best;
        return best;
    }
}
