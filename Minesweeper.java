/*
 * Name: Alan Wu
 * Pennkey: wualan
 * Execution: java Minesweeper
 * 
 * Description: The Minesweeper class and its associated methods
 */ 

public class Minesweeper {
    private MinesweeperBoard board; // The board the game is played on
    private boolean started; // If the game has been started 
    private boolean win; // If the game has been won
    private boolean loss; // If the game has been lost
    
    /* 
     * Constructor: Creates a new istance the Minesweeper object
     */ 
    public Minesweeper(MinesweeperBoard board) {
        this.board = board;
        this.started = false; // Initally no game is started
        this.win = false; // Initially no game is won
        this.loss = false; // Initally no game is lost
    }
    
    /*
     * Input: None
     * Output: Boolean win
     * 
     * Description: Returns a boolean for whether or not the game has been won
     */ 
    public boolean getWin() {
        return this.win;
    }
    
    /* 
     * Input: None 
     * Output: boolean win
     * 
     * Description: Returns a boolean for whether or not the game has been lost
     */
    public boolean getLoss() {
        return this.loss;
    }
    
    /*
     * Input: None
     * Output: None
     * 
     * Description: Checks if the game has been won
     */ 
    public void checkWin() {
        boolean allRevealed = true; // If all tiles have been revealed
        for (int row = 0; row < 9; row++) { // Iterates over rows
            for (int col = 0; col < 9; col++) { // Iterates over columns
                // Checks if current tile hasn't been revealed and also isn't a mine
                if (!this.board.getTile(row, col).isRevealed() && 
                    !this.board.getTile(row, col).isMine()) { 
                    allRevealed = false; // Sets allRevealed to false
                    break;
                }
            }
        }
        
        this.win = allRevealed; // Game is won when all non-mine tiles are revealed
    }
    
    /*
     * Input: None
     * Output: None
     * 
     * Description: Checks if the game has been won or lost and ends the game if it
     *              it has
     */ 
    public void endGame() {
        if (this.win || this.loss) { // Checks if the game has been won or lost
            // Draws a white box
            PennDraw.setPenColor(PennDraw.WHITE);
            PennDraw.filledRectangle(0.5, 0.5, 0.3, 0.15); 

            // Outlines box in black
            PennDraw.setPenColor(PennDraw.BLACK); 
            PennDraw.line(0.2, 0.35, 0.8, 0.35);
            PennDraw.line(0.2, 0.65, 0.8, 0.65);
            PennDraw.line(0.2, 0.35, 0.2, 0.65);
            PennDraw.line(0.8, 0.35, 0.8, 0.65);
            
            // Writes text in box
            PennDraw.setFontSize(28);
            PennDraw.text(0.5, 0.46, "Press the Space Bar");
            PennDraw.text(0.5, 0.4, "to Play Again");

            
            // Writes text based on whether the game has been won or lost
            PennDraw.setFontSize(40);
            if (this.loss) {
                PennDraw.text(0.5, 0.55, "Game Over!");
            } else {
                PennDraw.text(0.5, 0.55, "You Win!");
            }
        }
    }
    
    /*
     * Input: None
     * Output: None
     * 
     * Description: Stops code from proceeding till spacebar is clicked
     *              
     */
    public void stall() {
        boolean notClicked = true;
        while (notClicked) {
            if (PennDraw.hasNextKeyTyped() && PennDraw.nextKeyTyped() == ' ') {
                notClicked = false;
            } 
        }
    }
    
    /*
     * Input: None
     * Output: None
     * 
     * Description: Draws the current state of the game
     */ 
    public void draw() {
        this.board.draw();
    }
    
    /*
     * Input: None
     * Output: None
     * 
     * Description: Reveals the Tile the mouse is currently over. Starts the game if
     *              the game hasn't been started. Sets the game state to loss if the
     *              revealed Tile is a mine.
     */ 
    public void click() {
        int row = 8 - (int) (PennDraw.mouseY() * 9); // Which row the mouse is in
        int col = (int) (PennDraw.mouseX() * 9); // Which column the mouse is in
        if (!this.started) { // Checks if the game has been started
            this.board.setMines(row, col); // Sets position of mines
            this.board.setMineCount(); // Sets mine count of all non-mine Tiles
            this.started = true;
        }
        
        this.board.revealTile(row, col); // Reveals Tile mouse is over
        
        // Sets state to loss if revealed Tile is a mine and reveals all mines
        if (this.board.getTile(row, col).isMine()) { 
            this.loss = true;
            
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (this.board.getTile(i, j).isMine()) {
                        this.board.getTile(i, j).reveal();
                        this.board.getTile(i, j).draw();
                    }
                }
            }
        }
    }
        
    public static void main(String[] args) {
        while (true) { // Infinte loop
            Minesweeper game = new Minesweeper(new MinesweeperBoard()); // New game
            PennDraw.enableAnimation(60); // Enables animation
            
            // Continues game is game has not been won or lost
            while (!game.getWin() && !game.getLoss()) { //
                if (PennDraw.mousePressed()) { // Clicks 
                    game.click();
                }
                
                game.checkWin(); // Checks if game has been won or lost
                game.draw(); // Draws new game state
                game.endGame(); // Ends loop if game has been won or lost
                PennDraw.advance(); // Advance to next frame
            }

            game.stall(); // Lets outmost loop repeat when space bar is clicked
        }
    }
}