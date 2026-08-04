class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        Arrays.sort(nums);

        List <Integer> ans = new ArrayList<>(); 

        for(int i = 0; i < nums.length - 1; i++) {

            if( nums[i] != nums[i+1] -1 )
            {
                while( nums[i] < nums[i+1] -1) {
                    ans.add(++nums[i]);
                }

            }

        }
        return ans;

    }
}