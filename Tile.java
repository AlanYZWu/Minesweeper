/*
 * Name: Alan Wu
 * Pennkey: wualan
 * Execution: None
 * 
 * Description: The Tile class and its associated methods
 */ 

public class Tile {
    private boolean mine; // Whether or not a Tile is a mine
    private int mineCount; // How many mines surround a Tile
    private boolean revealed; // Whether or not a Tile has been revealed
    private double xCenter; // The horizontal coordinate for the center of the Tile
    private double yCenter; // The vertical coordiante for the cetner of the Tile
    
    /*
     * Constructor: Creates new instance of Tile object
     */ 
    public Tile(double xCen, double yCen) {
        // Checks for coodinates that would be off canvas
        if (xCen < 0 || xCen > 1 || yCen < 0 || yCen > 1) { 
            throw new IllegalArgumentException("Illegal Coordinates");
        }
        
        this.mine = false; // By default Tiles are not mines
        this.mineCount = 0; // By default there are no mines around a given Tile
        this.revealed = false; // By default Tiles are not revealed
        this.xCenter = xCen; 
        this.yCenter = yCen;
    }
    
    /* 
     * Input: None
     * Output: boolean mine
     * 
     * Descrption: Returns a boolean representing if the tile is a mine
     */ 
    public boolean isMine() {
        return this.mine;
    }
    
    /*
     * Input: None
     * Output: boolean revealed
     * 
     * Descrption: Returns a boolean representing if the tile is revealed
     */  
    public boolean isRevealed() {
        return this.revealed;
    }
    
    public int getMineCount() {
        return this.mineCount;
    }
    
    /*
     * Input: None
     * Output: None
     * 
     * Description: Changes revealed from false to true
     */ 
    public void reveal() {
        this.revealed = true;
    }
    
    /*
     * Input: int num
     * Output: None
     * 
     * Description: Sets the number of surrounding mines equal to number
     */ 
    public void setMineCount(int num) {
        if (num > 8 || num < 0) {
            throw new IllegalArgumentException("Illegal number of mines");
        }
        
        this.mineCount = num;
    }
    
    /* 
     * Input: None
     * Output: None
     * 
     * Description: Makes the Tile a mine
     */ 
    public void setMine() {
        this.mine = true;
    }
    
    /*
     * Input: None
     * Output: None
     * 
     * Description: Draws the Tile using PennDraw
     */ 
    public void draw() {
        if (this.revealed) { // Checks if tile is revealed
            if (this.mine) { // Checks if tile is a mine
                PennDraw.setPenColor(PennDraw.RED); // Makes Tile red
                // Draws Tile
                PennDraw.filledSquare(this.xCenter, this.yCenter, 1.0 / 18); 
                // Draws mine image on top of Tile
                PennDraw.picture(this.xCenter, this.yCenter, "Mine.jpg", 51, 51); 
            } else { // Draws Tiles of various mine counts
                PennDraw.setPenColor(PennDraw.GRAY); // Makes Tile gray
                // Draws Tile
                PennDraw.filledSquare(this.xCenter, this.yCenter, 1.0 / 18);
                PennDraw.setFontSize(40); // Sets font size
                
                // Writes mine count in varying colors
                if (this.mineCount == 1) { 
                    PennDraw.setPenColor(PennDraw.BLUE);
                    PennDraw.text(this.xCenter, this.yCenter, "1");
                } else if (this.mineCount == 2) {
                    PennDraw.setPenColor(PennDraw.GREEN);
                    PennDraw.text(this.xCenter, this.yCenter, "2");
                } else if (this.mineCount == 3) {
                    PennDraw.setPenColor(PennDraw.RED);
                    PennDraw.text(this.xCenter, this.yCenter, "3");
                } else if (this.mineCount == 4) {
                    PennDraw.setPenColor(0, 20, 87);
                    PennDraw.text(this.xCenter, this.yCenter, "4");
                } else if (this.mineCount == 5) {
                    PennDraw.setPenColor(168, 0, 2);
                    PennDraw.text(this.xCenter, this.yCenter, "5");
                } else if (this.mineCount == 6) {
                    PennDraw.setPenColor(0, 153, 148);
                    PennDraw.text(this.xCenter, this.yCenter, "6");
                } else if (this.mineCount == 7) {
                    PennDraw.setPenColor(PennDraw.BLACK);
                    PennDraw.text(this.xCenter, this.yCenter, "7");
                } else {
                    PennDraw.text(this.xCenter, this.yCenter, "8");
                }
            }
        } else { // If Tile is not revealed
            PennDraw.setPenColor(PennDraw.LIGHT_GRAY); // Makes Tile light gray
            // Draws Tile
            PennDraw.filledSquare(this.xCenter, this.yCenter, 1.0 / 18); 
        }
    }
}