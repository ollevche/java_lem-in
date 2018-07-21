
package lemin.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lemin.logic.AntFarm;
import lemin.objects.Ants;
import lemin.objects.Room;

public class IOHandler implements AutoCloseable
{
	public static final String ANTS_PATTERN = "^(\\d+)$";
	public static final String ROOM_PATTERN = "^([^-#L ]+)\\s(\\d+)\\s(\\d+)$";
	public static final String LINK_PATTERN = "^([^-#L ]+)-([^-#L ]+)$";
	public static final String COMMENT_PATTERN = "^(#.*)$";
	public static final String COMMAND_PATTERN = "^(##.*)$";

	private Scanner scanner;
	private String buffer;

	public IOHandler()
	{
		scanner = new Scanner(System.in);
	}

	/*
	**	reads all of the comments and a command
	**	checks if the command is last line before the obj (ants or room or link)
	*/

	private LinkedList<String>	readObjInfo()
	{
		LinkedList<String> info = new LinkedList<>();
		Boolean isMatched;

		do
		{
			isMatched = buffer.matches(COMMENT_PATTERN);
			if (isMatched)
			{
				info.add(buffer);
				buffer = scanner.nextLine();				
				if (buffer.matches(COMMAND_PATTERN))
					break;
			}
		}
		while (isMatched);
		return (info);
	}

	public Ants	readAnts()
	{
		Ants ants = new Ants();
		Matcher matcher;
		Pattern pattern = Pattern.compile(ANTS_PATTERN);

		buffer = scanner.nextLine();
		ants.setInfo(readObjInfo());
		matcher = pattern.matcher(buffer);
		if (matcher.matches())
			ants.setAmount(matcher.group(1));;
		return (ants);
	}

	public ArrayList<Room>	readRooms()
	{
		Matcher matcher;
		Boolean isMatched;
		Pattern pattern = Pattern.compile(ROOM_PATTERN);
		HashSet<Room> rooms = new HashSet<>();

		do
		{
			buffer = scanner.nextLine();
			matcher = pattern.matcher(buffer);
			isMatched = matcher.matches();
			if (isMatched)
				if (!rooms.add(new Room(matcher)))
					throw (new InputDataMismatch("Duplicate rooms found"));
		}
		while (isMatched);
		return (new ArrayList<Room>(rooms));
	}

	public void	readLinks(AntFarm antFarm)
	{
		Matcher	matcher;
		Boolean	isMatched;
		Pattern pattern = Pattern.compile(LINK_PATTERN);

		do
		{
			matcher = pattern.matcher(buffer);
			isMatched = matcher.matches();
			if (isMatched)
				antFarm.linkTwoRooms(matcher.group(1), matcher.group(2));
			buffer = scanner.nextLine();
		}
		while (isMatched);
	}

	public void	printAntFarm(AntFarm antFarm)
	{
		System.out.println("Received input:\n" + antFarm.getAnts());
		antFarm.getRooms()
			.stream()
			.forEach(System.out::println);
	}

	@Override
	public void	close()
	{
		scanner.close();
	}
}
