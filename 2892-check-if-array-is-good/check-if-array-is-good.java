class Solution {
    public boolean isGood(int[] nums) {
        int n = nums.length - 1;
        int[] count = new int[n + 1];
        
        // 1. Tally up the frequencies
        for (int num : nums) {
            // If the number is out of the valid 1 to n range, it's instantly false
            if (num < 1 || num > n) {
                return false;
            }
            count[num]++;
        }
        
        // 2. Check the sequence rule (1 to n-1 must appear exactly once)
        for (int i = 1; i < n; i++) {
            if (count[i] != 1) {
                return false;
            }
        }
        
        // 3. Check the duplicate max rule (n must appear exactly twice)
        return count[n] == 2;
    }
}