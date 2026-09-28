class Solution {
    public int maxDepth(String s) {
        int open = 0;
        int result = 0;
        for(char ch : s.toCharArray()) {
            if(ch == '(')
            open++;
            else if( ch == ')')
            open--;
            result = Math.max(result, open);

        }

        return result;
    }
}