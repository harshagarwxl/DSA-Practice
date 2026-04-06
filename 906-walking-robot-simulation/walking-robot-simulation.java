import java.util.HashSet;
import java.util.Set;
class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        // int i = 0;
        int store = 0;
        int [][] arr  = new int[][] {{0,1}, {1,0}, {0,-1}, {-1,0}};
        Set<String> obsSet = new HashSet<>();
        for( int [] obs : obstacles){
            obsSet.add(obs[0] + "," + obs[1]);
        }
        int x = 0;
        int y = 0;
        int d = 0;
        for(int cmd : commands)
        {
            if (cmd == -2) d = (d+3)%4;
            else if (cmd == -1) d = (d+1) %4;
            else
            {
                for( int i = 0; i < cmd ; i++)
                {
                    if(!obsSet.contains( (arr[d][0] + x) + "," + (arr[d][1] + y) ))
                    {
                        x = arr[d][0] + x;
                        y = arr[d][1] + y;
                        store = Math.max( store, x*x + y*y);
                    }
                    else
                    break;
                }
            }
        }
        return store;
    }
}