class Solution {
    public int maxDistance(int[] colors) {
        int n = colors.length;
        int maxdis = 0;
        for(int i = n-1;i >=0 ;i--){
            if( colors[0] != colors[i] ){
                maxdis = Math.max(maxdis,i);
                break;
            }
        }
        for(int i = 0; i < n;i++){
            if(colors[n-1] != colors[i]){
                maxdis = Math.max(maxdis,n-1-i);
                break;
            }
        }
        return maxdis;
    }
}