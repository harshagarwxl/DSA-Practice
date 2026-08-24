class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int sumLeft = 0, sumRight = 0;
        int qLeft = 0, qRight = 0;
        
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            if (i < n / 2) {
                if (c == '?') qLeft++;
                else sumLeft += c - '0';
            } else {
                if (c == '?') qRight++;
                else sumRight += c - '0';
            }
        }
        
        // Multiply by 2 to prevent integer division truncation
        return (sumLeft - sumRight) * 2 != (qRight - qLeft) * 9;
    }
}