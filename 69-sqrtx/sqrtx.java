class Solution {
    public int mySqrt(int x) {
        if(x<2) return x;
        long rt = 1;
        // int resultant;
        while( rt*rt <= x)
        {
            // resultant = rt*rt;
            rt++;
        }
        // rt--;
        return (int)rt-1;
    }
}