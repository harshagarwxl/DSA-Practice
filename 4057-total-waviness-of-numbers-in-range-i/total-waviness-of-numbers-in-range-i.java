class Solution {
    public int totalWaviness(int num1, int num2) {
       int score = 0;
        for( int num = num1; num <= num2; num++){
            String s = Integer.toString(num);
            for(int i = 1;  i < s.length() -1; i++){
                //valley
                if( s.charAt(i) < s.charAt(i-1) && s.charAt(i) < s.charAt(i+1))
                score++;
                // peak
                if( s.charAt(i) > s.charAt(i-1) && s.charAt(i) > s.charAt(i+1))
                score++;
            }
        }
        return score;
    }
}