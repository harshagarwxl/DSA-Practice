class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[] numsSorted = nums.clone();
        Arrays.sort(numsSorted);

        // Step 1: Grouping
        int currGroup = 0;
        Map<Integer, Integer> numToGroup = new HashMap<>();
        Map<Integer, Queue<Integer>> groupToQueue = new HashMap<>();

        numToGroup.put(numsSorted[0], currGroup);
        groupToQueue.put(currGroup, new LinkedList<>(Arrays.asList(numsSorted[0])));

        for (int i = 1; i < n; i++) {
            if (numsSorted[i] - numsSorted[i - 1] > limit) {
                currGroup++;
            }
            numToGroup.put(numsSorted[i], currGroup);
            groupToQueue.computeIfAbsent(currGroup, k -> new LinkedList<>()).add(numsSorted[i]);
        }

        // Step 2: Rebuild original array
        for (int i = 0; i < n; i++) {
            int group = numToGroup.get(nums[i]);
            nums[i] = groupToQueue.get(group).poll();
        }

        return nums;
    }
}
