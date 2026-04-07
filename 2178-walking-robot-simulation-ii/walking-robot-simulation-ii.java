class Robot {
    private int width;
    private int height;
    private int pos; // 0 to (perimeter - 1)
    private int perimeter;
    private boolean moved;

    public Robot(int width, int height) {
        this.width = width;
        this.height = height;
        this.perimeter = 2 * (width + height) - 4;
        this.pos = 0;
        this.moved = false;
    }
    
    public void step(int num) {
        moved = true;
        pos = (pos + num) % perimeter;
    }
    public int [] getPos() {
        if(pos < width){
            return new int[] {pos , 0};
        } else if(pos < width + height - 1){
            return new int [] {width - 1, pos - (width - 1)};
        } else if(pos < 2*width + height - 2){
            return new int [] {width - 1 - (pos - (width + height - 2)), height -1};
        } else{
            return new int[]{ 0, height - 1 - (pos - (2*width + height - 3 ))};
        }
    }
    
    public String getDir() {
        // if(moved && pos == 0) return "South";
        // if(pos > 0 && pos < width)
        // return "East";
        // else if ( pos > width && pos < width + height - 1)
        // return "North";
        // else if( pos >= width + height- 1 && pos < 2 * width + height - 2)
        // return "West";
        // else if (pos >= 2*width + height - 2 || pos == 0)
        // return "East";

        // if (pos >= 1 && pos <= width - 1) return "East";
        // if(pos >= width && pos <= width + height - 2) return "North";
        // if (pos >= width + height - 1 && pos <= 2* width + height - 3) return "West";
        // return "South";
        if (!moved) return "East";
    
    // If pos is 0 AFTER moving, it means it completed a lap and is facing South.
    if (pos == 0) return "South";

    // 1. Bottom edge (excluding origin, including bottom-right corner)
    if (pos >= 1 && pos <= width - 1) return "East";
    
    // 2. Right edge (up to top-right corner)
    if (pos >= width && pos <= width + height - 2) return "North";
    
    // 3. Top edge (back to top-left corner)
    if (pos >= width + height - 1 && pos <= 2 * width + height - 3) return "West";
    
    // 4. Left edge (heading back to origin)
    return "South";
    }
}

/**
 * Your Robot object will be instantiated and called as such:
 * Robot obj = new Robot(width, height);
 * obj.step(num);
 * int[] param_2 = obj.getPos();
 * String param_3 = obj.getDir();
 */