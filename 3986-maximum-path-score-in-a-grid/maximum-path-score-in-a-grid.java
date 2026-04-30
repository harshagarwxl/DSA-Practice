import java.util.Arrays;

class Solution {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        
        // dp[i][j][w] = max score at (i, j) with cost w
        // Initialize with -1 to represent unreachable states
        int[][][] dp = new int[m][n][k + 1];
        for (int[][] row : dp) {
            for (int[] col : row) {
                Arrays.fill(col, -1);
            }
        }

        // Starting point (0,0) - grid[0][0] is always 0 based on constraints
        dp[0][0][0] = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int w = 0; w <= k; w++) {
                    if (dp[i][j][w] == -1) continue;

                    // Try moving Right and Down
                    int[][] directions = {{0, 1}, {1, 0}};
                    for (int[] dir : directions) {
                        int ni = i + dir[0];
                        int nj = j + dir[1];

                        if (ni < m && nj < n) {
                            int cellVal = grid[ni][nj];
                            int nextScore = (cellVal == 0) ? 0 : cellVal;
                            int nextCost = (cellVal == 0) ? 0 : 1;

                            if (w + nextCost <= k) {
                                dp[ni][nj][w + nextCost] = Math.max(
                                    dp[ni][nj][w + nextCost], 
                                    dp[i][j][w] + nextScore
                                );
                            }
                        }
                    }
                }
            }
        }

        // Find the maximum score in the last cell across all valid costs <= k
        int maxScore = -1;
        for (int w = 0; w <= k; w++) {
            maxScore = Math.max(maxScore, dp[m - 1][n - 1][w]);
        }

        return maxScore;
    }
}