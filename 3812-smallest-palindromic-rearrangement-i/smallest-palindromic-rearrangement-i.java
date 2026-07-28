class Solution {
    public String smallestPalindrome(String s) {
        int count[] = new int[ 26];
        //counts the number of alphabets in
        for( char c : s.toCharArray())
        count[c-'a']++;

        StringBuilder firsthalf = new StringBuilder();
        String middle = "";

        //each character's appended to the optimal position as per the number of character and order
        for(int i = 0; i < 26; i++){
            char ch = (char) (i + 'a');
            if( count[i] %2 != 0){
                middle = String.valueOf(ch);
            }
            int halfcount = count[i]/2;
            for( int j = 0; j < halfcount; j++)
            firsthalf.append(ch);
        }
        
            String leftside = firsthalf.toString();
            String rightside = firsthalf.reverse().toString();
        
        return leftside + middle + rightside;
    }
}