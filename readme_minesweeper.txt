/**********************************************************************
 * Final Project - Minesweeper
 **********************************************************************/

Name: Alan Wu
PennKey: wualan
Hours to complete assignment (optional): ~10 - ~12 hours

/**********************************************************************
 *  Have you enter all help, collaboration, and outside resources
 *  in your help log?  If not, do so now.  (And in future, make sure
 *  you make your help log entries as you go, not at the end!)
 *
 *  If you did not get any help in outside of TA office hours,
 *  and did not use any materials outside of the standard
 *  course materials and piazza, write the following statement below:
 *  "I did not receive any help outside of TA office hours.  I
 *  did not collaborate with anyone, and I did not use any
 *  resources beyond the standard course materials."
 **********************************************************************/
     I did not receive any help outside of TA office hours. I did not 
     collaborate with anyone, and I did not use any resources beyond the 
     standard course materials.
     
/**********************************************************************
 *  How do you run the game?
 **********************************************************************/
    To run the game, you run the Minesweeper java file. No command line
    arguments are needed. 

/**********************************************************************
 *  Additional features
 **********************************************************************/
    I did not add any additional features beyond a single minor cosmetic
    change that wasn't in the required feature section. Normally in 
    minesweeper, if you click and mine and lose, all other mines are 
    revealed as well. That's the only thing I added.
    
/**********************************************************************
 *  Purpose of each file
 **********************************************************************/    
    I made three files. A file for a tile, a file for the board,
    and a file for the game itself. The tile file contains all the 
    fields and methods related to creating and working with a single
    tile. The file for the board contains the fields and methods and
    methods realted to creating and working with a set of tiles, 
    represented by a 2D array of tiles. The board file contains all the
    methods needed to play the game, such as setting where mines are and
    revealing tiles, but lacks the ability for the player to interact 
    with the board. The game file runs the game and lets the player 
    interact with the board. The game file also tracks the state of the 
    game i.e. whether the game is won, lost, or still going.