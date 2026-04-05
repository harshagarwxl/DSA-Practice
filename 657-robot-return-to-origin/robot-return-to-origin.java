class Solution {
    public boolean judgeCircle(String moves) {
        int x = 0;
        int y = 0;
        // int l = 0;
        // int r = 0;
        int i = 0;
        if(moves.length() % 2 != 0)
        return false;
        while( i < moves.length()  )
        {
            if(moves.charAt(i) == 'U')
            x++;
            else if (moves.charAt(i) == 'D')
            x--;
            else if (moves.charAt(i) == 'R')
            y++;
            else
            y--;
            i++;
        }
        if( x == 0 && y == 0)
        return true;
        else
        return false;
    }
}