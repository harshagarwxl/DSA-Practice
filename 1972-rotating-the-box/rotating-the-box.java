class Solution {
    public char[][] rotateTheBox(char[][] box) {
        int rows = box.length;
        int cols = box[0].length;

        // 1. Apply Gravity for each row
        for (int i = 0; i < rows; i++) {
            int emptySpot = cols - 1; // Start tracking from the far right
            for (int j = cols - 1; j >= 0; j--) {
                if (box[i][j] == '*') {
                    // If we hit an obstacle, the next stone must stay above it
                    emptySpot = j - 1;
                } else if (box[i][j] == '#') {
                    // Move stone to the furthest available empty spot
                    char temp = box[i][j];
                    box[i][j] = '.';
                    box[i][emptySpot] = temp;
                    emptySpot--;
                }
            }
        }

        // 2. Rotate the box 90 degrees clockwise
        // New dimensions: rows -> cols, cols -> rows
        char[][] rotatedBox = new char[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // The element at [i][j] moves to [j][rows - 1 - i]
                rotatedBox[j][rows - 1 - i] = box[i][j];
            }
        }

        return rotatedBox;
    }
}