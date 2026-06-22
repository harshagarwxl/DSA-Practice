class Solution {
    public int maxNumberOfBalloons(String text) {
        int b = 0, a = 0, l = 0, o = 0, n = 0;
        for (char c : text.toCharArray()) {
            if (c == 'b') b++;
            else if (c == 'a') a++;
            else if (c == 'l') l++;
            else if (c == 'o') o++;
            else if (c == 'n') n++;
        } 
        l = l / 2;
        o = o / 2;
        return Math.min(b, Math.min(a, Math.min(l, Math.min(o, n))));
    }
}