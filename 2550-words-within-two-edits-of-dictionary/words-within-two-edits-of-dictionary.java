import java.util.*;

class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> results = new ArrayList<>();
        
        for (String query : queries) {
            for (String word : dictionary) {
                if (canTransform(query, word, 2)) {
                    results.add(query);
                    break; 
                }
            }
        }
        return results;
    }

    private boolean canTransform(String s, String t, int k) {
        if (s.length() != t.length()) return false;
        
        int edits = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                edits++;
            }
            if (edits > k) return false;
        }
        return true;
    }
}