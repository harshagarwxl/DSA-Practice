class Solution {
    public int largestInteger(int[] nums, int k) {
         int n = nums.length;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        
        for (int i = 0; i <= n - k; i++) {
            for (int j = i; j < i + k; j++) {
                map.computeIfAbsent(nums[j], x -> new HashSet<>()).add(i);
            }
        }

        int ans = -1;
        for (Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() == 1) {
                ans = Math.max(ans, entry.getKey());
            }
        }

        return ans;
    }
}