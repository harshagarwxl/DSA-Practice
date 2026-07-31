class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];
        for (char ch : word.toCharArray()) {
            freq[ch - 'a']++;
        }
        Arrays.sort(freq);
        int[] sortedFreq = new int[26];
        for (int i = 0; i < 26; i++) {
            sortedFreq[i] = freq[25 - i];
        }
        int totalPushes = 0;
        for (int i = 0; i < 26; i++) {
            if (sortedFreq[i] == 0) 
            break;
            int pushCost = (i / 8) + 1; 
            totalPushes += sortedFreq[i] * pushCost;
        }
        return totalPushes;
    }
}