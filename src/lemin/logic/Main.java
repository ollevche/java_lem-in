
package lemin.logic;

import lemin.util.DataLackException;

public class Main
{
	public static void main(String[] args)
	{
		AntsFarm	antsFarm;

		antsFarm = new AntsFarm();
		try
		{
			antsFarm.readInput();
			antsFarm.doTheMath();
			antsFarm.printInput(); // move after try-catch?
			antsFarm.printOutput(); // move after try-catch?
		}
		catch (DataLackException e)
		{
			System.err.println("Not enough input data: " + e.getMessage());
		}
		catch (Exception e)
		{
			System.err.println("Unexpected exception: " + e);
		}
	}
}
