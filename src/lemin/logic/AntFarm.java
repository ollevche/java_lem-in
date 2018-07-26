
package lemin.logic;

import java.util.ArrayList;
import java.util.Collections;

import lemin.objects.Ants;
import lemin.objects.Room;
import lemin.util.InputDataMismatch;

public class AntFarm
{
	private	Ants			ants;
	private ArrayList<Room>	rooms;

	public void	doTheMath()
	{

	}

	public void	linkTwoRooms(String fName, String sName)
	{
		System.out.printf("%s linked with %s\n", fName, sName);
	}

	public void	setRooms(ArrayList<Room> rooms)
	{
		if (rooms.isEmpty())
			throw (new InputDataMismatch("No rooms found"));
		Collections.sort(rooms);
		this.rooms = rooms;
	}

	public void	setAnts(Ants ants)
	{
		if (ants.getAmount() <= 0)
			throw (new InputDataMismatch("No ants found"));
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

	public ArrayList<Room> getRooms()
	{
		return (rooms);
	}

	public Ants getAnts()
	{
		return (ants);
	}

}
