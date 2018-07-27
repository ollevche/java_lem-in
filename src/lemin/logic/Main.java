
package lemin.logic;

import lemin.util.InputDataMismatch;
import lemin.util.IOHandler;

public class Main
{
	public static void	main(String[] args)
	{
		AntFarm	antFarm = new AntFarm();
		IOHandler ioHandler = new IOHandler();

		try
		{
			ioHandler.readAnts(antFarm);
			ioHandler.readRooms(antFarm);
			ioHandler.readLinks(antFarm);
		}
		catch (InputDataMismatch e)
		{
			System.err.println("Input data error: " + e.getMessage());
		}
		ioHandler.printAntFarm(antFarm);
		ioHandler.close();
	}
}
