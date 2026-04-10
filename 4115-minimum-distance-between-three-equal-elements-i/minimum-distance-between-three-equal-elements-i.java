class Solution {
    public int minimumDistance(int[] nums) {
        Map <Integer , List<Integer>> map = new HashMap<>();
        int minDist = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++)
        {
            map.putIfAbsent(nums[i], new ArrayList<>());
            List<Integer> list = map.get(nums[i]);
            list.add(i);
            int size = list.size();
            if(size >= 3)
            {
                int dist = 2* (list.get(size - 1) - list.get(size - 3));
                minDist = Math.min(minDist, dist);
            }
        }
        return minDist == Integer.MAX_VALUE ? -1: minDist;
    }
}