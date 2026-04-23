class Solution {
    public int myAtoi(String s) {
        //1. only leading whitespaces are ignored
        //2. check the sign
        //3. store the numbers in a string, until the pointer reaches the last character of the string or a non-didgit character is eencountered 
        //4. convert the string to integer or long, return the number
        //5. Check the sign of the string
        //making sure that if the number is >2^31 - 1 or less than -2^31, then return 2^31 -1 or - 2 ^ 31 respectfullye 
        StringBuilder str = new StringBuilder ();
        int number = 0;
        int i = 0;
        s = s.stripLeading();
        if(s.isEmpty())
            return 0;
        if(Character.isLetter(s.charAt(i))) {
            return 0;
        }
        // while( Character.isWhitespace(s.charAt(i)) ){
            
            // i++;
        // }
        int sign = 1;
        if (s.charAt(i) == '-') {
        sign = -1;
        i++;
        }else if (s.charAt(i) == '+') {
        i++;
        }
        while( i < s.length() ) {
            char ch = s.charAt(i);
            if(!Character.isDigit(ch))
            break;
            if (str.length() == 0 && ch == '0') {
                i++;
                continue;
            }
            
            str.append(ch);
            i++;
            if (str.length() > 12) break;
        }
        if (str.length() == 0) return 0;
        //4 done
        if(str.isEmpty())
        return 0;
        
        long result = Long.parseLong(str.toString());
        result *= sign;
        if(result > Integer.MAX_VALUE)
        return Integer.MAX_VALUE;
        else if ( result < Integer.MIN_VALUE)
        return Integer.MIN_VALUE;
        else 
        return (int) result;
    }
}