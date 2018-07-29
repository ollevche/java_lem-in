
package lemin.objects;

import java.util.ArrayList;
import java.util.List;

import lemin.logic.AntFarm;

public class Path
{
	private List<Room>	rooms;

	public Path(AntFarm antFarm, List<Integer> roomsIds)
	{
		List<Room>  allRooms;

		allRooms = antFarm.getRooms();
		rooms = new ArrayList<>(roomsIds.size());
		for (Integer id : roomsIds)
			rooms.add(allRooms.get(id));
	}

	@Override
	public String toString()
	{
		return rooms.toString();
	}
}
