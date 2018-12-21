
package lemin.util;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lemin.logic.AntFarm;
import lemin.objects.Ants;
import lemin.objects.Link;
import lemin.objects.ObjectInfo;
import lemin.objects.Path;
import lemin.objects.PathSet;
import lemin.objects.Room;

public class IOHandler implements AutoCloseable
{
	public static final String ANTS_PATTERN = "^(\\d+)$";
	public static final String ROOM_PATTERN = "^([^-#L ]+)\\s(\\d+)\\s(\\d+)$";
	public static final String LINK_PATTERN = "^([^-#L ]+)-([^-#L ]+)$";
	public static final String COMMENT_PATTERN = "^(#.*)$";
	public static final String COMMAND_PATTERN = "^(##.*)$";

	private Scanner	scanner;
	private String	buffer;

	public	IOHandler(InputStream source)
	{
		scanner = new Scanner(source);
	}

	private ObjectInfo	readObjInfo()
	{
		ObjectInfo	info = new ObjectInfo();

		while (true)
		{
			if (buffer.matches(COMMAND_PATTERN))
				info.addCommand(buffer);
			else if (buffer.matches(COMMENT_PATTERN))
				info.addComment(buffer);
			else
				break ;
			buffer = scanner.nextLine();
		}
		return info;
	}

	public void	readAnts(AntFarm antFarm)
	{
		int			amount = 0;
		Matcher		matcher;
		Pattern		pattern = Pattern.compile(ANTS_PATTERN);

		buffer = scanner.nextLine();
		antFarm.addInfo(readObjInfo());
		matcher = pattern.matcher(buffer);
		if (matcher.matches())
			amount = Integer.valueOf(matcher.group(1));
		antFarm.setAnts(amount);
	}

	public void	readRooms(AntFarm antFarm)
	{
		Matcher 	matcher;
		Boolean 	isMatched;
		Pattern 	pattern = Pattern.compile(ROOM_PATTERN);

		do
		{
			buffer = scanner.nextLine();
			antFarm.addInfo(readObjInfo());
			matcher = pattern.matcher(buffer);
			isMatched = matcher.matches();
			if (isMatched)
				antFarm.addRoom(matcher);
		}
		while (isMatched);
	}

	public void	readLinks(AntFarm antFarm)
	{
		Matcher	matcher;
		Boolean	isMatched;
		Pattern pattern = Pattern.compile(LINK_PATTERN);

		do
		{
			antFarm.addInfo(readObjInfo());
			matcher = pattern.matcher(buffer);
			isMatched = matcher.matches();
			if (isMatched)
			{
				antFarm.addLink(matcher);
				buffer = scanner.nextLine();
			}
		}
		while (isMatched);
	}

	public void	printAntFarm(AntFarm antFarm)
	{
		Ants ants = antFarm.getAnts();

		System.out.println("\nReceived input:");
		System.out.printf("%s%s\n", ants.getInfo(), ants);
		for (Room r : antFarm.getRooms())
			System.out.printf("%s%s %d %d\n", r.getInfo(), r, r.getX(), r.getY());
		for (Link l : antFarm.getLinks())
			System.out.printf("%s%s\n", l.getInfo(), l);
	}

	public void printPaths(List<Path> paths)
	{
		System.out.printf("\nFound paths (%d):\n", paths.size());
		for (Path p : paths)
			System.out.println(p);
	}

	public void printSet(PathSet set)
	{
		System.out.printf("\nBest set (size = %d, eff = %d):\n", set.size(), set.getSteps());
		for (Path p : set.getPaths())
			System.out.println(p);
	}

	@Override
	public void	close()
	{
		scanner.close();
	}
}
