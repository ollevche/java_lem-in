
package lemin.logic;

import lemin.util.DataLackException;
import lemin.util.IOHandler;

public class Main
{
	public static void main(String[] args)
	{
		AntFarm	antFarm;

		antFarm = new AntFarm();
		try (IOHandler ioHandler = new IOHandler())
		{
			antFarm.setAnts(ioHandler.readAnts());
			antFarm.setRooms(ioHandler.readRooms());
			ioHandler.readLinks(antFarm);
			antFarm.doTheMath();
			ioHandler.printAntFarm(antFarm);
			// ioHandler.printOutput(antFarm);
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
