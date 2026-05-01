// class Solution {
//     //recursion function
//     public static int rotatefunction(int arr [], int rotation, long maxsum){
//         int n = arr.length;
//         if(rotation == n)
//         return (int) maxsum;
//         long currentsum = 0;
//         for(int i = 0; i < n;i++){
//             int rotatedindex = ( i + rotation) % n;
//             currentsum += arr[rotatedindex]*i;
//         }
//         maxsum = Math.max(currentsum,maxsum);
//         return rotatefunction( arr, rotation+1, maxsum);
//     }
//     public int maxRotateFunction(int[] nums) {
//         return rotatefunction(nums, 0, Long.MIN_VALUE);
//     }
// }
class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        long sum = 0;
        long f0 = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            f0 += (long) i * nums[i];
        }
        return (int) rotatefunction(nums, 1, sum, f0, f0);
    }

    public static long rotatefunction(int[] arr, int rotation, long totalSum, long currentSum, long maxVal) {
        int n = arr.length;
        
        // Base Case
        if (rotation == n) {
            return maxVal;
        }
        long nextSum = currentSum + totalSum - (long) n * arr[n - rotation];
        
        return rotatefunction(arr, rotation + 1, totalSum, nextSum, Math.max(maxVal, nextSum));
    }
}