class Solution {
    public boolean checkDivisibility(int n) {
        // boolean ans = true;
        int prod = 1;
        int sum = 0;
        int num = n;
        while ( num > 0){
            int d = num %10;
            sum += d;
            prod *= d;
            num /= 10;
        }
        if( n%(sum + prod) == 0 )
        return true;
        else 
        return false;
    }
}