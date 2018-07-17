
package lemin.logic;

import java.util.ArrayList;
import java.util.HashMap;

import lemin.objects.Ants;
import lemin.objects.Room;
import lemin.util.IOHandler;

public class AntsFarm
{
	private	Ants			ants;
	private ArrayList<Room>	rooms; // is it efficient enough?

	public void	readInput()
	{
		IOHandler ioHandler = new IOHandler();
		HashMap<String, ArrayList<String>> wholeLinks;
		ArrayList<String> roomLinks;

		ants = ioHandler.readAnts(); // throws DataLackException
		rooms = ioHandler.readRooms(); // throws DataLackException
		// wholeLinks = ioHandler.readLinks(); // throws DataLackException
		// for (Room r : rooms)
		// {
		// 	roomLinks = wholeLinks.get(r.getName());
		// 	r.setLinks(roomLinks);
		// }
	}

	public void	doTheMath()
	{

	}

	public void printInput()
	{

	}

	public void printOutput()
	{

	}
}
