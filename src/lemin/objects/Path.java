
package lemin.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Path implements Comparable<Path>
{
	private List<Room>	rooms;
	private int			id;

	public Path(List<Room> allRooms, List<Integer> roomsIds)
	{
		rooms = new ArrayList<>(roomsIds.size());
		for (Integer id : roomsIds)
			rooms.add(allRooms.get(id));
	}

	public boolean isIntersect(Path anotherOne, int roomsCount)
    {
		PathSet dummySet = new PathSet(1);

		dummySet.add(this);
		return dummySet.isIntersect(anotherOne, roomsCount);
	}

	public void	setId(int id)
	{
		this.id = id;
	}

	public int	getId()
	{
		return id;
	}

	public int	getLen()
	{
		return rooms.size();
	}

	public List<Room>	getRooms()
	{
		return Collections.unmodifiableList(rooms);
	}

	@Override
	public String toString()
	{
		return String.format("path_%d (%d) %s", id, rooms.size(), rooms.toString());
	}

	@Override
	public int compareTo(Path p)
	{
		return Integer.compare(getLen(), p.getLen());
	}

	@Override
	public boolean equals(Object obj)
	{
		Path somepath;

		if (obj == this)
			return true;
		if (obj == null || !(obj instanceof Path))
			return false;
		somepath = (Path)obj;
		if (somepath.getId() == getId())
			return true;
		return false;
	}
}
