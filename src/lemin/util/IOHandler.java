
package lemin.util;

import java.util.ArrayList;
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

	private Scanner scanner;
	private String buffer;

	public IOHandler()
	{
		scanner = new Scanner(System.in);
	}

	public Ants readAnts()
	{
		Ants ants = null;
		Matcher matcher;
		Pattern pattern = Pattern.compile(ANTS_PATTERN);

		buffer = scanner.nextLine();
		matcher = pattern.matcher(buffer);
		if (matcher.matches())
			ants = new Ants(matcher);
		return (ants);
	}

	public ArrayList<Room> readRooms()
	{
		Matcher matcher;
		Boolean isMatched;
		Pattern pattern = Pattern.compile(ROOM_PATTERN);
		ArrayList<Room> rooms = new ArrayList<>();

		do
		{
			buffer = scanner.nextLine();
			matcher = pattern.matcher(buffer);
			isMatched = matcher.matches();
			if (isMatched)
				rooms.add(new Room(matcher));
		}
		while (isMatched);
		return (rooms);
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

	}

	@Override
	public void	close()
	{
		scanner.close();
	}
}
