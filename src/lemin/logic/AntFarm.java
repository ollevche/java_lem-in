
package lemin.logic;

import java.util.ArrayList;
import java.util.Collections;

import lemin.objects.Ants;
import lemin.objects.Room;
import lemin.util.DataLackException;

public class AntFarm
{
	private	Ants			ants;
	private ArrayList<Room>	rooms;

	public void	doTheMath()
	{

	}

	public void	setRooms(ArrayList<Room> rooms)
	{
		if (rooms.isEmpty())
			throw (new DataLackException("No rooms found"));
		Collections.sort(rooms);
		this.rooms = rooms;
	}

	public void	setAnts(Ants ants)
	{
		if (ants == null)
			throw (new DataLackException("No ants found"));
		this.ants = ants;
	}

	public Room	getRoom(String name)
	{
		Room target;
		int ind;

		target = new Room(name, 0, 0);
		ind = Collections.binarySearch(rooms, target);
		if (ind < 0)
			return (null);
		return (rooms.get(ind));
	}

	public void	linkTwoRooms(String fName, String sName)
	{
		System.out.printf("%s linked with %s\n", fName, sName);
	}

}
