class Solution {
    public int missingNumber(int[] nums) {
        // Arrays.sort(nums);
        // for(int i = 0; i < nums.length ;i++)
        // {
        //     if( i != nums[i])
        //     return i;
        // }
        // return nums.length;
        int sum = nums.length * (nums.length + 1)/2;
        int array_sum = 0;
        for(int i : nums)
        {
            array_sum += i;
        }
        // if( array_sum == sum)
        // return nums.length + 1;
        // else 
        return sum - array_sum;
    }
}