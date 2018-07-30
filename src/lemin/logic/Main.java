
package lemin.logic;

import lemin.util.InputDataMismatch;
import lemin.util.FatalDataLack;
import lemin.util.IOHandler;

public class Main
{
	public static void	main(String[] args)
	{
		AntFarm	antFarm = new AntFarm();
		AntGraph antGraph;

		try(IOHandler ioHandler = new IOHandler())
		{
			ioHandler.readAnts(antFarm);
			ioHandler.readRooms(antFarm);
			ioHandler.readLinks(antFarm);
			ioHandler.printAntFarm(antFarm);
			antGraph = new AntGraph(antFarm);
			antGraph.findAllPaths();
			ioHandler.printPaths(antGraph.getPaths());
			antGraph.pickBestSet();
			ioHandler.printSet(antGraph.getBestSet());
		}
		catch (InputDataMismatch e)
		{
			System.err.println("Input data error: " + e.getMessage());
		}
		catch (FatalDataLack e)
		{
			System.err.println("Cannot proceed normally: " + e.getMessage());
		}
	}
}
