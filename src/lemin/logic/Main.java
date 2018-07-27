
package lemin.logic;

import lemin.util.InputDataMismatch;
import lemin.util.IOHandler;

public class Main
{
	public static void	main(String[] args)
	{
		AntFarm	antFarm = new AntFarm();

		try (IOHandler ioHandler = new IOHandler())
		{
			ioHandler.readAnts(antFarm);
			ioHandler.readRooms(antFarm);
			ioHandler.readLinks(antFarm);
			ioHandler.printAntFarm(antFarm);
		}
		catch (InputDataMismatch e)
		{
			System.err.println("Input data error: " + e.getMessage());
		}
	}
}
