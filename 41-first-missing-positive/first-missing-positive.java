class Solution {
    public int firstMissingPositive(int[] nums) {
        HashSet<Integer> hashSet = Arrays.stream(nums)
                                         .boxed()
                                         .collect(Collectors.toCollection(HashSet::new));
     for(int i = 1; ; i ++)
     {
        if(hashSet.contains(i))
        continue;
        return i;
     }
    }
}