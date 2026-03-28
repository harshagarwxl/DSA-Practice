class Solution {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            // Move left pointer if not alphanumeric
            if (!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            } 
            // Move right pointer if not alphanumeric
            else if (!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            } 
            // Both are alphanumeric, so compare
            else {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }
}