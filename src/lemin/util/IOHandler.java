
package lemin.util;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lemin.objects.Ants;
import lemin.objects.Room;

public class IOHandler
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
		Ants ants;
		Matcher matcher;
		Pattern pattern = Pattern.compile(ANTS_PATTERN);

		buffer = scanner.nextLine();
		matcher = pattern.matcher(buffer);
		if (matcher.matches())
			ants = new Ants(matcher.group(1));
		else
			throw (new DataLackException("Ants not found"));
		return (ants);
	}

	public ArrayList<Room> readRooms()
	{
		Matcher matcher;
		Boolean isMatched;
		Pattern pattern = Pattern.compile(ROOM_PATTERN);
		ArrayList<Room> rooms = new ArrayList<>(); // optimize

		do
		{
			buffer = scanner.nextLine();
			matcher = pattern.matcher(buffer);
			isMatched = matcher.matches();
			if (isMatched)
				rooms.add(new Room(matcher));
		}
		while (isMatched);
		if (rooms.isEmpty())
			throw (new DataLackException("Rooms not found"));
		return (rooms);
	}
}
