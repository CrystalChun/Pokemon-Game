import java.awt.Point;
import java.io.*;
import java.util.Scanner;
/**
 * Map class which holds 3 maps of the pokemon world.
 * @author Crystal Chun		ID#012680952
 *
 */
public class Map 
{
	/**A 2D array of all the characters in a map*/
	private char [][] map;
	
	/**A 2D array corresponding to the map that tells 
	 * the user whether or not the point has been revealed.
	 */
	private boolean [][] revealed;
	
	/**
	 * The map constructor that initializes both the character
	 * map and the boolean map.
	 */
	public Map()
	{
		map = new char [5][5];
		revealed = new boolean [5][5];
	}
	
	/**
	 * Generates the specific area map by reading in 
	 * the characters of that map and storing them into the
	 * map 2D character array. 
	 * @param areaNum The area map where the user is.
	 */
	public void generateArea(int areaNum)
	{
		//Array's indexes initialized to zero
		int row = 0;
		int column = 0;
		
		//Specifies which text file to read from
		String area = "Area" + (areaNum % 3) + ".txt";
		
		//Reads the character map in and stores into array
		try 
		{
			Scanner read = new Scanner(new File(area));
			
			//Reads until reaches end of file
			while(read.hasNext())
			{
				map[row][column] = read.next().charAt(0);
				
				//If the point is a city (c) or the start (s), then reveals location
				if(map[row][column] == 'c' || map[row][column] == 's')
				{
					revealed[row][column] = true;
				}
				//Otherwise sets all points to hidden
				else
				{
					revealed[row][column] = false;
				}
				//Changes the index to the next row and column 0 after reaching the last column
				if(column >= 4)
				{
					column = 0;
					row ++;
				}
				else
				{
					column ++;
				}
			}
			read.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("File was not found.");
		}
	}
	
	/**
	 * Gets the character at the specified location and returns it
	 * if the location exists, otherwise returns 'G'.
	 * @param p The point to be evaluated
	 * @return The character at that location or 'G' if the location is invalid
	 */
	public char getCharAtLoc(Point p)
	{
		char location = 'G';
		
		try
		{
			//Tries to get char at this location
			location = map[(int) p.getX()][(int) p.getY()]; 
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("You cannot go that way");
		}
		return location;
	}

	/**
	 * Prints out the whole map, and tests each point to see if
	 * it's revealed, if it is, then prints that character.
	 * If it's not, then prints an 'X'. Also prints the user's
	 * location as an '@' sign.
	 * @param p The user's location
	 */
	public void displayMap(Point p)
	{
		//Iterates through whole 2D map array
		for(int i = 0; i < map.length; i++)
		{
			for(int j = 0; j < map[0].length; j++)
			{
				//Signifies the user's location
				if((int) p.getX() == i && (int) p.getY() == j)
				{
					System.out.print("@ ");
				}
				//Checks if character is revealed
				else if(revealed[i][j])
				{
					System.out.print(map[i][j] + " ");
				}
				//Otherwise hidden
				else
				{
					System.out.print("X ");
				}
			}
			System.out.println();
		}
	}
	
	/**
	 * Finds the start location in the map and returns the point.
	 * @return The start location (point)
	 */
	public Point findStartLocation()
	{
		Point p = null;
		for(int i = 0; i < map.length; i++)
		{
			for(int j = 0; j < map[0].length; j++)
			{
				if(map[i][j] == 's')
				{
					p = new Point(i,j);
				}
			}
		}
		return p;
	}
	
	/**
	 * Reveals the character at the point passed in by setting
	 * its location in the revealed 2D array to true.
	 * @param p The point to be revealed
	 */
	public void reveal(Point p)
	{
		revealed[(int) p.getX()][(int) p.getY()] = true;
	}
	
	/**
	 * Removes the opponent at a certain location and replaces the 
	 * character with an 'n'.
	 * @param p The point to remove the opponent
	 */
	public void removeOppAtLoc(Point p)
	{
		map[(int) p.getX()][(int) p.getY()] = 'n';
	}
}
