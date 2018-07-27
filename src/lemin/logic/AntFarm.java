
package lemin.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.regex.Matcher;

import lemin.objects.Ants;
import lemin.objects.Link;
import lemin.objects.ObjectInfo;
import lemin.objects.Room;
import lemin.util.InputDataMismatch;

/*
**	AntFarm checks for:
**		duplicate rooms names
**		duplicate start/end commands
**		"No rooms" error
**		"No links" error
**		"No ants" error
*/

public class AntFarm
{
	private Ants 				ants;
	private ArrayList<Room>		rooms;
	private LinkedList<Link>	links;
	private	ObjectInfo			nextObjInfo;
	private int					lastInsertedId; // is it really needed?
	private	int					startRoomId;
	private int					endRoomId;

	public	AntFarm()
	{
		rooms = new ArrayList<>();
		links = new LinkedList<>();
		lastInsertedId = -1;
		startRoomId = -1;
		endRoomId = -1;
	}

	public boolean	setAnts(int amount)
	{
		if (amount < 1)
			throw (new InputDataMismatch("No ants found"));
		ants = new Ants(amount, nextObjInfo);
		nextObjInfo = null;
		return true;
	}

	public boolean	addRoom(Matcher roomMatcher)
	{
		String	name = roomMatcher.group(1);
		int	x = Integer.valueOf(roomMatcher.group(2));
		int	y = Integer.valueOf(roomMatcher.group(3));

		return addRoom(name, x, y);
	}

	private boolean	insertRoom(Room newOne)
	{
		int i, compRes;

		for(i = 0; i < rooms.size(); i++)
		{
			compRes = newOne.compareTo(rooms.get(i));
			if (compRes == 0)
				return false;
			if (compRes < 0)
				break ;
		}
		rooms.add(i, newOne); // optimize
		lastInsertedId = i;
		return true;
	}

	public boolean	addRoom(String name, int x, int y)
	{
		if (nextObjInfo.hasStartCommand() && startRoomId > -1)
			throw (new InputDataMismatch("Cannot accept two start rooms"));
		if (nextObjInfo.hasEndCommand() && endRoomId > -1)
			throw (new InputDataMismatch("Cannot accept two end rooms"));
		if (!insertRoom(new Room(name, x, y, nextObjInfo)))
			throw (new InputDataMismatch("Duplicate rooms found"));
		if (nextObjInfo.hasStartCommand())
			startRoomId = lastInsertedId;
		else if (nextObjInfo.hasEndCommand())
			endRoomId = lastInsertedId;
		nextObjInfo = null;
		return true;
	}

	public boolean	addLink(Matcher linkMatcher)
	{
		String	fromName = linkMatcher.group(1);
		String	toName = linkMatcher.group(2);

		return addLink(fromName, toName);
	}

	public boolean	addLink(String fromName, String toName)
	{
		Room	from = getRoom(fromName);
		Room	to = getRoom(toName);

		if (from == null || to == null)
			throw (new InputDataMismatch("Cannot link undefined rooms"));
		links.add(new Link(from, to, nextObjInfo));
		nextObjInfo = null;
		return true;
	}

	public boolean	addInfo(ObjectInfo info)
	{
		nextObjInfo = info;
		return true;
	}

	public int	getRoomId(String name)
	{
		Room target;
		int ind;

		target = new Room(name, 0, 0);
		ind = Collections.binarySearch(rooms, target);
		return ind;
	}

	public Room	getRoom(String name)
	{
		int ind;

		ind = getRoomId(name);
		if (ind < 0)
			return null;
		return rooms.get(ind);
	}

	public Ants	getAnts()
	{
		return ants;
	}

	public ArrayList<Room>	getRooms()
	{
		return rooms;
	}

	public LinkedList<Link>	getLinks()
	{
		return links;
	}

}
