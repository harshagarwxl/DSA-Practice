class Solution {
    int n, k;
    int MOD = 1000000007;
    Integer[][][] dp;

    public int numberOfSets(int n, int k) {
        this.n = n;
        this.k = k;

        dp = new Integer[n + 1][k + 1][2];

        return fn(0, 0, 0);
    }

    private int fn(int i, int segments, int open) {

        if (i == n) {
            return (segments == k && open == 0) ? 1 : 0;
        }

        if (segments > k)
            return 0;

        if (dp[i][segments][open] != null)
            return dp[i][segments][open];

        long ans = 0;

        if (open == 0) {

            // Don't use i
            ans += fn(i + 1, segments, 0);

            // Start segment at i
            ans += fn(i + 1, segments, 1);

        } else {

            // Continue current segment
            ans += fn(i + 1, segments, 1);

            // End current segment at i
            ans += fn(i + 1, segments + 1, 0);

            // End current segment at i
            // and start another at i
            ans += fn(i + 1, segments + 1, 1);
        }

        return dp[i][segments][open] = (int)(ans % MOD);
    }
}