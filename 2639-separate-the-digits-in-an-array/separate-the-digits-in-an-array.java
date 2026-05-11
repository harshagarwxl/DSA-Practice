class Solution {
    public int[] separateDigits(int[] nums) {
        // int [] arr ;
        List <Integer> results = new ArrayList<>();
        // int t = 0;
        for(int i =0;i< nums.length;i++){
            int temp = nums[i];
            String str = String.valueOf(temp);
            int j = 0;
            while(j < str.length()){
                results.add(Character.getNumericValue(str.charAt(j)));
                j++;
                // t++;
            }
        }
        int [] arr = new int[results.size()];
        for(int i = 0; i < results.size();i++){
            arr[i] = results.get(i);
        }
        return arr;
    }
}