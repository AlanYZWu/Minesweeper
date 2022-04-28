/*
 * Name: Alan Wu
 * Pennkey: wualan
 * Execution: None
 * 
 * Description: The MinesweeperBoard class and its associated methods
 */ 

public class MinesweeperBoard {
    private Tile[][] board; // Board represented by 2D array of Tiles
    
    /*
     * Constructor: Creates new instance of MinesweeperBoard object
     */ 
    public MinesweeperBoard() {
        this.board = new Tile[9][9]; // Defines dimensions of board
        double yCen = 17.0 / 18; // Center y coordinate of top left tile
        
        for (int i = 0; i < 9; i++) { // Iterates over rows
            double xCen = 1.0 / 18; // Center x coordiante of top left tile
            for (int j = 0; j < 9; j++) { // Iterates over columns
                this.board[i][j] = new Tile(xCen, yCen); // Initalizes tile
                xCen += 1.0 / 9; // Updates x coordinate
            }
            yCen -= 1.0 / 9; // Updates y coordinate
        }
    }
    
    /* Input: int row, int column
     * Output: Tile current
     * 
     * Descrpition: Returns the Tile at the specified row and column
     */ 
    public Tile getTile(int row, int col) {
        return this.board[row][col];
    }
    
    /* 
     * Input: None
     * Output: None
     * 
     * Description: Draws the board
     */ 
    public void draw() {
        for (int i = 0; i < 9; i++) { // Iterates over rows
            for (int j = 0; j < 9; j++) { // Iterates over columns
                this.board[i][j].draw(); // Draws tile at specified index
            }
        }
        
        double x = 0; // x coordinate of lines that form the grid
        double y = 0; // y coordinate of lines that form the grid
        PennDraw.setPenColor(PennDraw.BLACK); // Sets pen color to black
        
        for (int i = 0; i < 9; i++) { // Iterating loop
            PennDraw.line(x, 0.0, x, 1.0); // Draws horizontal line
            PennDraw.line(0.0, y, 1.0, y); // Draws vertical line
            x += 1.0 / 9; // Updates x coordinate
            y += 1.0 / 9; // Updates y coordinate
        }
    }
    
    /*
     * Input: int row, int color
     * Output: None
     * 
     * Description: Sets mines to random tiles except for the the tile at row, col
     */ 
    public void setMines(int row, int col) {       
        for (int mineCount = 10; mineCount > 0; mineCount--) { // Sets 10 Mines
            int randomVal = (int) (Math.random() * 81); // Random val from 0 to 80
            int count = 0; // Count that increases when a tile is passed 
            
            for (int i = 0; i < 9; i++) { // Iterates over rows
                for (int j = 0; j < 9; j++) { // Iterates over columns
                    /* Checks that current tile isn't the starting tile, count
                     * matches randomVal, and that the current Tile isn't a mine 
                     * already
                     */ 
                    if (this.board[row][col] != this.board[i][j] && 
                        count == randomVal && this.board[i][j].isMine() == false) {
                        this.board[i][j].setMine();
                    }
                    count++;
                }
            }
        }
    }
    
    /*
     * Input: None
     * Output: None
     * 
     * Description: Initalizes mineCount for all tiles
     */ 
    public void setMineCount() {
        for (int row = 0; row < 9; row++) { // Iterates over rows
            for (int col = 0; col < 9; col++) { // Iteartes over columns
                if (!this.board[row][col].isMine()) { // Checks Tile isn't a mine
                    int mineCount = 0; // Mine count for board[row][col]
                    
                    // Nested loops check adjavent tiles
                    for (int i = -1; i < 2; i++) { 
                        for (int j = -1; j < 2; j++) {
                            if (row + i > -1 && row + i < 9 && col + j > -1 && 
                                col + j < 9 && 
                                this.board[row + i][col + j].isMine()) {
                                mineCount++; 
                            }
                        }
                    }
                    this.board[row][col].setMineCount(mineCount); // Sets mine count
                }
            }
        }
    }
    
    /* 
     * Input: None
     * Output: None
     * 
     * Description: Reveals and draws the tile that is clicked out; recursively 
     *              calls this function on surrounding tiles
     */  
    public void revealTile(int row, int col) {
        // Checks if Tile isn't already revealed and that it has surrounding mines
        if (!this.board[row][col].isRevealed() && 
            this.board[row][col].getMineCount() != 0) {
            this.board[row][col].reveal(); // Reveals Tile
            this.board[row][col].draw(); // Draws Tile
        } else if (!this.board[row][col].isRevealed()) { // If mineCount is zero
            this.board[row][col].reveal(); // Reveals Tile
            this.board[row][col].draw(); // Draws Tile
            
            // Nested loops iterates over adjacent Tiles
            for (int i = -1; i < 2; i++) { 
                for (int j = -1; j < 2; j++) { 
                    /* Checks for out of bounds errors, that surrounding Tiles 
                     * aren't revealed, and that current Tile is not a mine
                     */ 
                    if (row + i >= 0 && row + i < 9 && col + j >= 0 && col + j < 9 &&
                        !this.board[row + i][col + j].isRevealed() && 
                        !this.board[row][col].isMine()) { 
                        revealTile(row + i, col + j); // Reveals adjacent Tile
                    }
                }
            }
        }
    }
}