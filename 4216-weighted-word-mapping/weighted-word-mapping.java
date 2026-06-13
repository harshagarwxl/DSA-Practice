// import java.util.*;
public class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        /*
        0 - z
        1 - y
        .
        .
        .
        .
        .
        .
        .
        24 - b
        25 - a
        */
       StringBuilder sb = new StringBuilder();
       for( String word : words)
        {
            int sum = 0;
            for(char ch : word.toCharArray()){
                sum += weights[ch-'a'];
            }
            sb.append( (char) ('z' - sum%26));
        }
        return sb.toString();
    }
}
