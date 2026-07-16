class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int[] prefixGcd = new int[n];
        
        int maxSoFar = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxSoFar = Math.max(maxSoFar, nums[i]);
            prefixGcd[i] = gcd(nums[i], maxSoFar); // ✅ Correct step
        }
        
        Arrays.sort(prefixGcd);
        
        long sum = 0;
        int i = 0, j = n - 1;
        while (i < j) { // ✅ ensures middle element is ignored
            sum += gcd(prefixGcd[i], prefixGcd[j]);
            i++;
            j--;
        }
        
        return sum;
    }
    
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
