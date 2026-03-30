class Solution {
    public boolean isValidSudoku(char[][] board) {
        for(int i = 0;i <9 ;i++)
        {
            for(int j = 0; j < 9 ; j++)
            {
                if(board[i][j] != '.')
                {
                    char ch = board[i][j];
                    // rows & column check
                    for( int k = 0; k < 9; k++)
                    {
                        if(board[k][j] == ch && k != i)
                        return false;
                        if(board [i][k] == ch && k != j)
                        return false;
                    }
                    // block
                    // if( i >= 0 && i <= 2 )
                    // {
                    //     if(j >= 0 && j <= 2)
                    //     {
                    //         // block 1
                    //         for( int k = 0 ; k < 3; k++ )
                    //         {
                    //             for( int l = 0; l<3;l++)
                    //             {
                    //                 if(board[k][l] == ch && l != j)
                    //                 return false;
                    //             }
                    //         }
                    //     }
                    //     else if ( j > 2 && j <= 5)
                    //     {
                    //         // block 2
                    //         for( int k = 0 ; k < 3; k++ )
                    //         {
                    //             for( int l = 3; l<6;l++)
                    //             {
                    //                 if(board[k][l] == ch && l != j)
                    //                 return false;
                    //             }
                    //         }
                    //     }
                    //     else
                    //     {
                    //         // block 3
                    //         for( int k = 0 ; k < 3; k++ )
                    //         {
                    //             for( int l = 6; l<9;l++)
                    //             {
                    //                 if(board[k][l] == ch && l != j)
                    //                 return false;
                    //             }
                    //         }
                    //     }
                    // }
                    // else if ( i >= 3 && i <= 5 )
                    // {
                    //     if(j >= 0 && j <= 2)
                    //     {
                    //         // block 1
                    //         for( int k = 3 ; k < 6; k++ )
                    //         {
                    //             for( int l = 0; l<3;l++)
                    //             {
                    //                 if(board[k][l] == ch && l != j)
                    //                 return false;
                    //             }
                    //         }
                    //     }
                    //     else if ( j > 2 && j <= 5)
                    //     {
                    //         // block 2
                    //         for( int k = 3 ; k < 6; k++ )
                    //         {
                    //             for( int l = 3; l<6;l++)
                    //             {
                    //                 if(board[k][l] == ch && l != j)
                    //                 return false;
                    //             }
                    //         }
                    //     }
                    //     else
                    //     {
                    //         // block 3
                    //         for( int k = 3 ; k < 6; k++ )
                    //         {
                    //             for( int l = 6; l<9;l++)
                    //             {
                    //                 if(board[k][l] == ch && l != j)
                    //                 return false;
                    //             }
                    //         }
                    //     }
                    // }
                    // else
                    // {
                    //     if(j >= 0 && j <= 2)
                    //     {
                    //         // block 1
                    //         for( int k = 6 ; k < 9; k++ )
                    //         {
                    //             for( int l = 0; l<3;l++)
                    //             {
                    //                 if(board[k][l] == ch && l != j)
                    //                 return false;
                    //             }
                    //         }
                    //     }
                    //     else if ( j > 2 && j <= 5)
                    //     {
                    //         // block 2
                    //         for( int k = 6 ; k < 9; k++ )
                    //         {
                    //             for( int l = 3; l<6;l++)
                    //             {
                    //                 if(board[k][l] == ch && l != j)
                    //                 return false;
                    //             }
                    //         }
                    //     }
                    //     else
                    //     {
                    //         // block 3
                    //         for( int k = 6 ; k < 9; k++ )
                    //         {
                    //             for( int l = 6; l<9;l++)
                    //             {
                    //                 if(board[k][l] == ch && l != j)
                    //                 return false;
                    //             }
                    //         }
                    //     }
                    // }
                    // Block Check logic
                    int startRow = (i / 3) * 3;
                    int startCol = (j / 3) * 3;

                    for (int r = startRow; r < startRow + 3; r++) {
                        for (int c = startCol; c < startCol + 3; c++) {
                            // Only check other cells in the block, not the current cell itself
                            if (board[r][c] == ch && (r != i || c != j)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        // method - 2  using method - duplicate  3 blocks and 9 columns & 9 rows
        return true;
    }
}