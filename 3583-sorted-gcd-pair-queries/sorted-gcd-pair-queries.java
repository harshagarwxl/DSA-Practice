// class Solution {
//     public int[] gcdValues(int[] nums, long[] queries) {
//     int n = nums.length;
//     int total_pairs = n * (n-1)/2;
//     // n = 9 , 8
//     // n*n = 81
//     int gcdpairs [] = new int[total_pairs];
//     int counter = 0;
//      for(int i = 0; i < n; i++){
//         int a = nums[i];
//         for( int j = i +1; j < n; j++){
//             int b = nums[j];
//             gcdpairs[counter++] = gcd( a,b);
//         }
//      } 

//      Arrays.sort(gcdpairs);
//     //  return ans;
//     int ans[] = new int [ queries.length];
//     for( int i = 0; i < queries.length; i++){
//         int temp = (int) queries[i];
//         if( temp >= 0 && temp < total_pairs )
//             ans[i] = gcdpairs[temp];
//             else 
//             ans[i] = -1;
//         }
//         return ans;
//     }
    
//     private int gcd(int a , int b){
//         while(b != 0){
//             int temp = b;
//              b = a % b;
//              a = temp;
//         }
//         return a;
//     }
// }

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        // Step 1: Count frequency of each number
        int[] count = new int[maxVal + 1];
        for (int num : nums) {
            count[num]++;
        }

        // Step 2 & 3: Count pairs for each exact GCD using inclusion-exclusion
        long[] gcdPairs = new long[maxVal + 1];
        for (int i = maxVal; i >= 1; i--) {
            // Count how many numbers are multiples of i
            long multiples = 0;
            for (int j = i; j <= maxVal; j += i) {
                multiples += count[j];
            }
            
            // Total pairs that share 'i' as a common divisor
            long totalPairsWithDivisor = multiples * (multiples - 1) / 2;
            
            // Subtract pairs that have a larger multiple as their actual GCD
            for (int j = 2 * i; j <= maxVal; j += i) {
                totalPairsWithDivisor -= gcdPairs[j];
            }
            
            gcdPairs[i] = totalPairsWithDivisor;
        }

        // Step 4: Build prefix sums of the pairs
        long[] prefixSums = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefixSums[i] = prefixSums[i - 1] + gcdPairs[i];
        }

        // Step 5: Answer each query using binary search
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = findGcdIndex(prefixSums, queries[i]);
        }

        return ans;
    }

    // Finds the smallest GCD value whose prefix sum is strictly greater than the query index
    private int findGcdIndex(long[] prefixSums, long target) {
        int low = 1, high = prefixSums.length - 1;
        int ans = high;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (prefixSums[mid] > target) {
                ans = mid;
                high = mid - 1; // Look for a smaller valid GCD
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}