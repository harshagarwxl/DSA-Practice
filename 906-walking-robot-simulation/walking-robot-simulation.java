// import java.util.HashSet;
// import java.util.Set;
// class Solution {
//     public int robotSim(int[] commands, int[][] obstacles) {
//         // int i = 0;
//         int store = 0;
//         int [][] arr  = new int[][] {{0,1}, {1,0}, {0,-1}, {-1,0}};
//         Set<String> obsSet = new HashSet<>();
//         for( int [] obs : obstacles){
//             obsSet.add(obs[0] + "," + obs[1]);
//         }
//         int x = 0;
//         int y = 0;
//         int d = 0;
//         for(int cmd : commands)
//         {
//             if (cmd == -2) d = (d+3)%4;
//             else if (cmd == -1) d = (d+1) %4;
//             else
//             {
//                 for( int i = 0; i < cmd ; i++)
//                 {
//                     if(!obsSet.contains( (arr[d][0] + x) + "," + (arr[d][1] + y) ))
//                     {
//                         x = arr[d][0] + x;
//                         y = arr[d][1] + y;
//                         store = Math.max( store, x*x + y*y);
//                     }
//                     else
//                     break;
//                 }
//             }
//         }
//         return store;
//     }
// }
class Solution {
    // Make Coord immutable for safety
    private static record Coord(int x, int y) {
        public int getDist() { return x * x + y * y; }
    }

    private static final int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};

    public int robotSim(int[] commands, int[][] obstacles) {
        int dir = 0, max = 0;
        // Use the Coord record/class for the set
        Set<Coord> invalid = new HashSet<>();
        for (int[] obs : obstacles) {
            invalid.add(new Coord(obs[0], obs[1]));
        }

        int curX = 0, curY = 0;

        for (int command : commands) {
            if (command == -1) dir = (dir + 1) % 4;
            else if (command == -2) dir = (dir + 3) % 4;
            else {
                for (int i = 0; i < command; i++) {
                    int nextX = curX + dx[dir];
                    int nextY = curY + dy[dir];
                    
                    // Create a temporary Coord or use a different lookup method
                    if (invalid.contains(new Coord(nextX, nextY))) {
                        break;
                    }
                    curX = nextX;
                    curY = nextY;
                }
                max = Math.max(max, curX * curX + curY * curY);
            }
        }
        return max;
    }
}