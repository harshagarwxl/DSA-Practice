class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int count = 1;
        int sum = 0;
        while(n > 0){
            int d = n%8;
            if(n >= 8)
            sum = sum + 8*count;
            else
            sum = sum + d*count;
            count++;
            n = n - 8;
        }
        return sum;
    }
}