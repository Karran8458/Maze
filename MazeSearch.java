import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

//********************************************************************
//  MazeSearch.java       Author: Lewis/Loftus
//  by Karran Gowda
//
//  Demonstrates recursion.
//********************************************************************

public class MazeSearch
{
   //-----------------------------------------------------------------
   //  Creates a new maze, prints its original form, attempts to
   //  solve it, and prints out its final form.
   //-----------------------------------------------------------------
   public static void main (String[] args) throws IOException
   {
	  //-------------------------------------------------------------
	  // Sets Scanner method that reads input from the keyboard.
	  //-------------------------------------------------------------
	  Scanner scan = new Scanner(System.in);
	  //-------------------------------------------------------------
	  // Sets instance variables that are passed into the methods 
	  // in Maze.java.
	  //-------------------------------------------------------------
	  int row, column, row2, col2;	// The starting and destination points.
	  int triedsum;	// Represents the sum of the TRIED cells.
	  int pathsum;	// Represents the sum of the cells on the final path.
	  String file = "output-maze1.txt";	// Sets file to "output-maze1.txt".
	  
	  FileWriter fw = new FileWriter (file);	// Represents a text output file.
	  BufferedWriter bw = new BufferedWriter (fw);	// Gives the output stream buffering capabilities.
	  PrintWriter outFile = new PrintWriter (bw);	// Includes print and println methods.
	  //---------------------------------------------------------------
	  // Calls the Maze constructor to set the values of the cells 
	  // to either 0 or 1 ( a blocked or clear path respectively).
	  //---------------------------------------------------------------
      Maze labyrinth = new Maze();
      //---------------------------------------------------------------
      // Prints the original maze constructed from the toString method.
      //---------------------------------------------------------------
      System.out.println(labyrinth);
      //---------------------------------------------------------------
      // Reads the user input for the starting row and column.
      //---------------------------------------------------------------
      System.out.println();
      System.out.print("Enter row where you want to start (between 0 and " + (labyrinth.getrowsize()-1) + "):");	// Gets the value of rowsize and prints it.
      row=scan.nextInt();	//Enables the user to put any value from 0 to the rowsize.
      while (row<0 || row>(labyrinth.getrowsize()-1))	// Keeps printing Invalid row until the user types a valid number within the range.
      {
    	  
    	  System.out.println();
    	  System.out.println("Invalid row");
    	  System.out.print("Enter row where you want to start (between 0 and " + (labyrinth.getrowsize()-1) + "):"); // Gets the value of rowsize and prints it.
          row=scan.nextInt();	// Enables the user to put any value from 0 to the rowsize.
      }
      
      System.out.println();
      System.out.print("Enter column where you want to start (between 0 and " + (labyrinth.getcolsize()-1) + "):");	// Gets the value of colsize and prints it.
      column=scan.nextInt();	// Enables the user to put any value from 0 to the colsize.
      while (column<0 || column>(labyrinth.getcolsize()-1))	// Keeps printing Invalid column until the user types a valid number within the range.
      {
    	  System.out.println();
    	  System.out.println("Invalid column");
    	  System.out.print("Enter column where you want to start (between 0 and " + (labyrinth.getcolsize()-1) + "):");	// Gets the value of colsize and prints it.
          column=scan.nextInt();	// Enables the user to put any value from 0 to the colsize.
      }
      //---------------------------------------------------------
      // Reads the user input for the destination row and column.
      //---------------------------------------------------------
      System.out.println();
      System.out.print("Enter row of your destination(between 0 and " + (labyrinth.getrowsize()-1) + "):");				// Gets the value of rowsize and prints it.
      row2=scan.nextInt();	// Enables the user to put any value from 0 to the rowsize.
      System.out.println();
      while (row2<0 || row2>(labyrinth.getrowsize()-1))	// Keeps printing Invalid row until the user types a valid number within the range.
      {
    	  System.out.println("Invalid row");
    	  System.out.print("Enter row of your destination(between 0 and " + (labyrinth.getrowsize()-1) + "):");			// Gets the value of rowsize and prints it.
          row2=scan.nextInt();	// Enables the user to put any value from 0 to the rowsize.
      }
      
      System.out.print("Enter column of your destination (between 0 and " + (labyrinth.getcolsize()-1) + "):");			// Gets the value of colsize and prints it.
      col2=scan.nextInt();	// Enables the user to put any value from 0 to the colsize.
      System.out.println();
      
      while (col2<0 || col2>(labyrinth.getcolsize()-1))	// Keeps printing Invalid column until the user types a valid number within the range.
      {
    	  System.out.println("Invalid column");
    	  System.out.print("Enter column of your destination (between 0 and " + (labyrinth.getcolsize()-1) + "):");		// Gets the value of colsize and prints it.
          col2=scan.nextInt();	// Enables the user to put any value from 0 to the rowsize.
      }
      //-----------------------------------------------------
      // Invokes the setdestination method to set endrow 
      // and endcolumn the values of row2 and col2.
      //-----------------------------------------------------
      labyrinth.setdestination(row2,col2);
      //-----------------------------------------------------
      // Invokes the setsumValues method to set 
      // the values of tsum (sum of tried cells) 
      // and psum (number of cells on the final path).
      //-----------------------------------------------------
      labyrinth.setsumValues();
      //
      //--------------------------------------------------------------------------
      // Invokes the traverse method that recursively traverses through the maze
      // and prints the number of cells tried and the number of cells on the final
      // path.
      //--------------------------------------------------------------------------
      if (labyrinth.traverse (row, column))
         {
    	  System.out.println ("The maze was successfully traversed!");	// Prints the statement.
    	  pathsum = labyrinth.getpathsum();	// Passes the value of returned sum of path cells to the pathsum variable.
      	  System.out.println ("Number of cells on solution path:" + pathsum);	// Prints the statement and the sum of path cells.
      	  triedsum = labyrinth.gettriedsum();	// Passes the value of returned sum of tried cells to the triedsum variable.
      	  System.out.println ("Number of cells tried:" + triedsum);	// Prints the statement and the sum of tried cells.
         }
      else
      {
         System.out.println ("There is no possible path.");	// Prints the statement.
         triedsum = labyrinth.gettriedsum();	// Passes the value of returned sum of tried cells to the triedsum variable.
  	     System.out.println ("Number of cells tried:" + triedsum);	// Prints the statement and the sum of tried cells.
      }
      //---------------------------------------------------------------------------
      // Prints the solution of the maze.
      //---------------------------------------------------------------------------
      System.out.println (labyrinth);
      
      //---------------------------------------------------------------------------
      // Creates an output file of the solution by going through every row and column 
      // to send every cell to the file.
      //---------------------------------------------------------------------------
      for (int row1 = 0; row1 < labyrinth.getrowsize(); row1++)	// Increments the row until the maximum number of rows.
      {
    	  outFile.println(labyrinth.getrowString(row1));	// The method getrowString gets back a row as a String.
      }
      
      //----------------------------------------------------------------------------
      // Closes the output file.
      //----------------------------------------------------------------------------
      outFile.close();
   }
}



