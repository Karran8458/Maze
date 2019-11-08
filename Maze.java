import java.io.File;	
import java.io.IOException;	
import java.util.Scanner;	

//********************************************************************
//  Maze.java       Author: Lewis/Loftus
//
//  Represents a maze of characters. The goal is to get from the
//  top left corner to the bottom right, following a path of 1s.
//********************************************************************

public class Maze
{
   // Sets private instance variables to be accessed by the different methods.
   private int tsum;		// Represents the number of tried cells.
   private int psum;		// Represents the number of cells that are on the final path.
   private int rowsize;		// Represents the total number of rows in the maze.
   private int colsize;		// Represents the total number of columns in the maze.
   private String filename = "maze1.txt";	// The maze text file is being assigned to String filename.
   private final int TRIED = 3;				// The TRIED variable is being assigned the number 3.
   private final int PATH = 7;				// The PATH variable is being assigned the number 7.
   private int endrow, endcolumn;			// Endrow and endcolumn are the destination points.
   
   private int[][] grid ;			// This grid is a two-dimensional array that contains the maze.
   
   //----------------------------------------------------------------------------------
   // Inserts special characters (0 or 1) to indicate what cell is blocked or clear respectively. 
   //----------------------------------------------------------------------------------
   public Maze() throws IOException		// The throws clause lists the IOException
   										// in case the filename is not writable.
   {
	   setSize(); // Calls the setSize method to get rowsize and colsize.
	   
	   Scanner scan1 = new Scanner (new File (filename)); // Scans from filename and assigns each cell 1 or 0.
	   
	   String lineofnumbers = null;		// Assigns lineofnumbers initially to be null.
	   
		   for (int index = 0; index < rowsize; index++)	// Index is incremented until it reaches rowsize.
		   {
			   lineofnumbers = scan1.nextLine();		// Lineofnumbers is assigned a string of characters in the input line.
			   
			   for (int i = 0; i < colsize; i++)		// i is incremented until it reaches colsize.
			   {
				   if (lineofnumbers.charAt(i) == '0')	// This if statement checks to see if the char at i has the same value as '0'.
				   {
					   grid[index][i] = 0;				// If the character at the certain index is '0', the cell is assigned to 0.
			  	   }
				   else 
				   {
					   grid[index][i] = 1;		// If the character at the certain index is not '0', the cell is assigned to 1.
				   }
				
			    }
		    }
   }
   
   //----------------------------------------------------------------------------
   // Sets tsum (sum of tried cells) and psum (sum of final path cells) to 0.
   //----------------------------------------------------------------------------
   public void setsumValues()
   {
	   tsum=0;		// Sets tsum (sum of tried cells) to 0.
	   psum=0;		// Sets psum (sum of final path cells) to 0.
   }

   //-----------------------------------------------------------------
   //  Attempts to recursively traverse the maze. Inserts special
   //  characters indicating locations that have been tried and that
   //  eventually become part of the solution.
   //-----------------------------------------------------------------
   public boolean traverse (int row, int column)
   {
      boolean done = false;		// Sets done to equal to false.
      
      if (valid (row, column))		// Calls the valid method and returns TRUE if the cell is 1.
      {
         grid[row][column] = TRIED;  // This cell has been tried so it is set to 3.
         
         tsum = tsum + 1;		// The sum of tried cells is being incremented 
         						// every time a cell is set to 3.
         if (row == endrow && column == endcolumn)	// The if statement checks to see if the destination point
        	                                        // is reached.
            done = true;  // the maze is solved
         else
         {
            done = traverse (row+1, column);     //  go down
            if (!done)
               done = traverse (row, column+1);  // go right
            if (!done)
               done = traverse (row-1, column);  // go up
            if (!done)
               done = traverse (row, column-1);  // go left
         }

         if (done)  // this location is part of the final path
         {
        	grid[row][column] = PATH;	// The cell is assigned to PATH which is 7.
         	psum = psum + 1;	// The sum of the cells that are part of the solution 
         	                    // is being incremented every time a cell is set to 7. 
         }
           
      }
      
      return done;	// Returns the value (true or false) of done.
   }
   
   //-----------------------------------------------------------------
   //  Determines if a specific location is valid.
   //-----------------------------------------------------------------
   private boolean valid (int row, int column)
   {
      boolean result = false;	// Sets the value of result to false.
 
      // Checks to see if the cell is in the bounds of the matrix
      if (row >= 0 && row < grid.length &&
          column >= 0 && column < grid[row].length)

         //  Checks to see if the cell is not blocked and not previously tried
         if (grid[row][column] == 1)
            result = true;

      return result;	// Returns true or false whether the cell can be set to 3 or not.
   }
   
   //---------------------------------------------------------------
   // Assigns endrow and endcolumn (the destination points) the values of row2 and col2
   // which was passed from the main method user input.
   //---------------------------------------------------------------
   public void setdestination (int row2, int col2) 
   {

	  {
	   endrow=row2;	// Passes the value of row2 to endrow (the destination row).
	   endcolumn=col2;	//Passes the value of col2 to endcolumn (the destination column).
	  }
   }
   
   
   //--------------------------------------------------------------------------
   // Returns tsum (sum of the tried cells) back to the main method.
   //--------------------------------------------------------------------------
   public int gettriedsum()
   {
	   return tsum;
   }
   
   //---------------------------------------------------------------------------
   // Returns psum (sum of the cells on the final path) back to the main method.
   //---------------------------------------------------------------------------
   public int getpathsum()
   {
	   return psum;	// Returns psum (sum of the cells on the final path) back to the main method.
   }
   
   //-----------------------------------------------------
   // Sets the size of the maze
   //-----------------------------------------------------
   private void setSize () throws IOException		
   {
	   String lineofnumbers = null;		// Sets the value of String lineofnumbers to be null.
	   
	   Scanner scan2 = new Scanner (new File (filename));	// Assigns scan2 to read from the text file (filename).
	   
	   int index=0;
	   while (scan2.hasNext())	// The while loop returns true if there is another token in its input.
	   {
		   lineofnumbers = scan2.nextLine();	// Lineofnumbers is assigned a string of characters from the current line.
		   index++;	// Index is incremented every time there is another token.
	   }
	   
	   rowsize = index;						// Assigns rowsize to equal the number of rows.
	   colsize = lineofnumbers.length();	// Assigns colsize to the length of String lineofnumbers.
	   
	   grid = new int [rowsize][colsize];	// Create grid to be a two-dimensional array with rowsize and colsize.
   }
   
   //-----------------------------------------------------------------------
   // Returns the variable rowsize from the setSize method to the main method
   //-----------------------------------------------------------------------
   public int getrowsize()
   {
	 return rowsize;	// Returns rowsize as the maximum number of rows.
   }
   
   //-----------------------------------------------------------------------
   // Returns the variable colsize from the setSize method to the main method
   //-----------------------------------------------------------------------
   public int getcolsize()
   {
	 return colsize;	// Returns colsize as the maximum number of columns.
   }
   
   //--------------------------------------------------------------------
   // Gets int row to output the solution as a string
   //--------------------------------------------------------------------
   public String getrowString (int row)
   {
	   String result = "";	// Sets result to space.
	   for (int col = 0; col < grid[row].length; col++)	// Increments the columns until grid[row].length.
	   {
		   result = result + grid[row][col] + "";	// Increments result and creates a space.
	   }
		   
		   return result;	// Returns result as a String for file output.
	}

   //-----------------------------------------------------------------
   //  Returns the maze as a string.
   //-----------------------------------------------------------------
   public String toString ()
   {
      String result = "\n";	// Sets String result to be new line.

      for (int row=0; row < grid.length; row++)	// Increments the row until it equals grid.length.
      {
         for (int column=0; column < grid[row].length; column++)	// Increments the column until it equals grid[row].length
            result += grid[row][column] + "";	// The grid is being read as String result to separate the cells with a space.
         result += "\n";	// Result is written on a new line.
      }

      return result;	// Returns result as a String for printing the maze.
   }
}



