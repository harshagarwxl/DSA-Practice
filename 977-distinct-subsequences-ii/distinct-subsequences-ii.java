class Solution {
    public int distinctSubseqII(String s) {
        // int MOD = 1000000007;
        int total = 0;
        int[] dp = new int[26];//char dp

        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 97;
            int add = (total - dp[c] + 1000000007) % 1000000007;

            dp[c] = 1 + total;
            total = (dp[c] + add) % 1000000007;
        }

        return total;
    }
}