class Solution {
    public boolean checkIfPangram(String sentence) {
    //     boolean [] arr = new boolean [26];
    //     Arrays.fill(arr, false);
    //     sentence.toLowerCase();
    //     for(char ch : sentence.toCharArray()) {
    //         if(!Character.isWhitespace(ch)) {
    //             int d = ch - 'a';
    //             arr[d] = true;
    //         }

    //     }
    //     for(boolean c : arr) {
    //         if( c == false)
    //         return false;
    //     }

    //     return true;
    // }
    if( sentence.length() < 26)
    return false;

    for( char ch = 'a' ; ch <= 'z'; ch++){
        if(sentence.indexOf(ch) <= -1)
        return false;
    }
    return true;


    }
}