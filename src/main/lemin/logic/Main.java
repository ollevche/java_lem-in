
package lemin.logic;

import lemin.util.InputDataMismatch;
import lemin.visual.SwingVisualizer;

import java.io.InputStream;

import lemin.util.FatalDataLack;
import lemin.util.IOHandler;

/*
**	keywords: OPTIMIZE, REFACTOR, TO-DO
*/

public class Main
{

	public static final String VISUAL = "-visual";
	public static final String CLI = "-cli";

	public static AntGraph lemin(InputStream source) {
		AntFarm	antFarm = new AntFarm();
		AntGraph antGraph = null;

		try(IOHandler ioHandler = new IOHandler(source))
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

		return antGraph;
	}

	public static void visual() {
		SwingVisualizer swingVisualizer = new SwingVisualizer();
	}

	public static void	main(String[] args)
	{
		if (args.length != 1) {
			System.out.printf("Use these flags: [%s|%s]\n", VISUAL, CLI);
			return;
		}

		if (args[0].equals(VISUAL)) {
			visual();
			return;
		}

		if (args[0].equals(CLI)) {
			lemin(System.in);
			return;
		}

		System.out.printf("Use these flags: [%s|%s]\n", VISUAL, CLI);
	}
}
