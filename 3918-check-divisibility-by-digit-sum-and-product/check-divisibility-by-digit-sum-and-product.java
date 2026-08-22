class Solution {
    public boolean checkDivisibility(int n) {
        // boolean ans = true;
        int prod = n % 10;
        int sum = n % 10;
        int num = n/10;
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