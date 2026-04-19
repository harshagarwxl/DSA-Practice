class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        if( n == 0)
        return -1;
        int suffix []  = new int [n];
        int [] arr = nums ;
        suffix [n-1] = arr[n-1];
        for(int i = n-2; i >= 0; i--)
        suffix[i] = Math.min(arr[i], suffix[i+1]);
        int prefixMax = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++){
            prefixMax = Math.max( prefixMax , arr[i] );
            long instability = (long) prefixMax - suffix[i];
            if(instability <= k)
            return i;
        }
        return -1;
    }
}