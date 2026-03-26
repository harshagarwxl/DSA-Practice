// class Solution {
//     public int mySqrt(int x) {
//         if(x<2) return x;
//         long rt = 1;
//         // int resultant;
//         while( rt*rt <= x)
//         {
//             // resultant = rt*rt;
//             rt++;
//         }
//         // rt--;
//         return (int)rt-1;
//     }
// }
class Solution {
    public int mySqrt(int x) {
        if (x < 2) return x;

        int left = 2, right = x / 2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long num = (long) mid * mid;
            
            if (num > x) {
                right = mid - 1;
            } else if (num < x) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return right;
    }
}