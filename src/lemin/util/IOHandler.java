
package lemin.util;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lemin.logic.AntFarm;
import lemin.objects.ObjectInfo;

public class IOHandler implements AutoCloseable
{
	public static final String ANTS_PATTERN = "^(\\d+)$";
	public static final String ROOM_PATTERN = "^([^-#L ]+)\\s(\\d+)\\s(\\d+)$";
	public static final String LINK_PATTERN = "^([^-#L ]+)-([^-#L ]+)$";
	public static final String COMMENT_PATTERN = "^(#.*)$";
	public static final String COMMAND_PATTERN = "^(##.*)$";

	private Scanner	scanner;
	private String	buffer;

	public	IOHandler()
	{
		scanner = new Scanner(System.in);
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
		System.out.println("\nReceived input:");
		System.out.print(antFarm.getAnts());
		antFarm.getRooms()
			.stream()
			.forEach(System.out::print);
		antFarm.getLinks()
			.stream()
			.forEach(System.out::print);
	}

	@Override
	public void	close()
	{
		scanner.close();
	}
}
