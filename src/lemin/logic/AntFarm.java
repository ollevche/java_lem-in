
package lemin.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

import lemin.objects.Ants;
import lemin.objects.Link;
import lemin.objects.ObjectInfo;
import lemin.objects.Room;
import lemin.util.InputDataMismatch;

public class AntFarm
{
	private Ants 		ants;
	private List<Room>	rooms;
	private List<Link>	links;
	private	ObjectInfo	nextObjInfo;
	private	int			startRoomId;
	private int			endRoomId;

	public	AntFarm()
	{
		rooms = new ArrayList<>();
		links = new LinkedList<>();
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
			startRoomId = 0;
		else if (nextObjInfo.hasEndCommand())
			endRoomId = 0;
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

	/*
	**	Needed for all path finding.
	**	It should be done after all addRoom operations,
	**	because rooms are inserted in their "right" places
	*/

	public void	indexRooms()
	{
		int id = 0;

		for (Room r : rooms)
		{
			r.setId(id);
			if (r.getInfo().hasStartCommand())
				startRoomId = id;
			if (r.getInfo().hasEndCommand())
				endRoomId = id;
			id++;
		}
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

	public List<Room>	getRooms()
	{
		return rooms;
	}

	public List<Link>	getLinks()
	{
		return links;
	}

	public int getStartId()
	{
		return startRoomId;
	}

	public int getEndId()
	{
		return endRoomId;
	}
}
