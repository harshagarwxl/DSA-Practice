class Solution {
    public int minMirrorPairDistance(int[] nums) {
        Map<Integer,Integer> pos = new HashMap<>();
        int minDistance = nums.length + 1;
        for(int i = 0; i < nums.length; i++){
            if(pos.containsKey(nums[i]))
            minDistance = Math.min(minDistance, i - pos.get(nums[i]));
            pos.put(reverse(nums[i]),i);
        }
        return minDistance > nums.length ? -1 : minDistance;
    }
    private int reverse(int num) {
        int reversed = 0;
        while (num > 0) {
            reversed = reversed * 10 + num % 10;
            num /= 10;
        }
        return reversed;
    }
}
