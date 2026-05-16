class Solution {
    public int findMin(int[] nums) {
        int n = nums.length - 1;
        int min = Integer.MAX_VALUE;

        for(int i = 0;i <= n; i++){
            min = min > nums[i] ? nums[i]: min;
            min = min > nums[n] ? nums[n]: min;
            n--;
        }
        return min;
    }
}