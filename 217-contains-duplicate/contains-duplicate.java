class Solution {
    public boolean containsDuplicate(int[] nums) {
        // for( int i = 0 ; i < nums.length; i++)
        // {
        //     for( int j =0; j < nums.length ; j++ )
        //     {
        //         if( nums[i] == nums[j] && i != j)
        //         return true;
        //     }
        // }
        // return false;
        // Set <Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        Set<Integer> set = new HashSet<>();
        for (int num : nums) 
        {
            set.add(num);
        }
        if( set.size() == nums.length)
        return false;
        else
        return true;
    }
}