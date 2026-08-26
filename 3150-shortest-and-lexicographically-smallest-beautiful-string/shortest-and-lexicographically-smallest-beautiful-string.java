class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        char[] str = s.toCharArray();
        int ones = 0;
        
        // Count total '1's in string
        for (char c : str) {
            if (c == '1') ones++;
        }
        if (ones < k) return "";
        
        int l = 0, r = 0;
        String ans = "";
        int minLen = s.length() + 1;
        ones = 0;
        
        while (r < s.length()) {
            if (str[r] == '1') ones++;
            
            // Shrink window if ones count exceeds k
            while (l < r && ones > k) {
                if (str[l] == '1') ones--;
                l++;
            }
            
            // Trim leading '0's to minimize window length
            while (l < r && str[l] == '0') l++;
            
            // Track optimal window
            if (ones == k) {
                int currLen = r - l + 1;
                if (minLen > currLen) {
                    minLen = currLen;
                    ans = s.substring(l, r + 1);
                } else if (minLen == currLen) {
                    String curr = s.substring(l, r + 1);
                    if (compare(ans, curr)) {
                        ans = curr;
                    }
                }
            }
            r++;
        }
        return ans;
    }
    
    // Custom lexicographical comparison
    private boolean compare(String a, String b) {
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) > b.charAt(i)) return true;
            else if (a.charAt(i) < b.charAt(i)) return false;
        }
        return false;
    }
}