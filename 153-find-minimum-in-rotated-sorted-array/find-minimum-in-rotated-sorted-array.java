class Solution {
    public int findMin(int[] nums) {
        int min = Integer.MAX_VALUE;
        int k = nums.length - 1;
        for(int i = 0; i <= k; i++){
            min = min > nums[i]? nums[i]: min;
            min = min > nums[k]? nums[k]: min;
            k--;
        }
        return min;
    }
}