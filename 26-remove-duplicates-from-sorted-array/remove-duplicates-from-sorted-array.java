class Solution {
    public int removeDuplicates(int[] nums) {
        // Set<Integer> set = Arrays.stream(nums) 
        //                  .boxed()      
        //                  .collect(Collectors.toSet());       
        //                   return set.size();
        // Arrays.sort(nums);
        int j = 1;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] != nums[j - 1]) {
            nums[j] = nums[i];
            j++;
            }
        }
        return j;
        // if(nums[i] != nums[j]);
    }
}