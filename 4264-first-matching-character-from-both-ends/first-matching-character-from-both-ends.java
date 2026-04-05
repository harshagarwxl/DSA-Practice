class Solution {
    public int firstMatchingIndex(String s) {
        int i =0;
        while( i <= s.length()/2 )
        {
            if(s.charAt(i) == s.charAt(s.length() - i - 1))
            return i;
            i++;
        }
        return -1;
    }
}