class Solution {
    public int mirrorDistance(int n) {
        int rev = 0;
        int temp = n;
        while( n>0){
            int d = n%10;
            rev = rev*10+d;
            n = n/10;
        }
        n = temp;
        return  (int) Math.abs(rev - n);
    }
}