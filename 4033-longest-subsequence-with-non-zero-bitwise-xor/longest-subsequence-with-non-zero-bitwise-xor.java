class Solution {
    public int longestSubsequence(int[] nums) {
        // Set<Integer> set = Arrays.stream(nums)
        //                          .boxed()
        //                          .collect(Collectors.toSet());
        // if( set.size() == 1)
        // return 0;
        boolean allZero = true;
        int resultXor = 0;
        for( int num : nums){
            resultXor  = resultXor ^ num;
            if( num!= 0)
            allZero = false;
        }
        if(allZero)
        return 0;


        return (resultXor == 0) ? nums.length -1 : nums.length ;
    }
}