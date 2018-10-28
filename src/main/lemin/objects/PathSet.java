
package lemin.objects;

import java.util.ArrayList;
import java.util.List;

public class PathSet
{
	private List<Path>	paths;
	private int			capacity, steps, len;

	public PathSet(int capacity)
	{
		this.capacity = capacity;
		paths = new ArrayList<>(capacity);
	}

	public boolean	isIntersect(Path somePath, int roomsCount)
	{
		int[]		roomsCheckList = new int[roomsCount];
		int			id = paths.size() - 1;
		List<Room>	rooms = somePath.getRooms();

		while (id >= -1)
		{
			for (Room r : rooms)
				if (roomsCheckList[r.getId()] == 1)
					return (true);
				else if (!r.isStart() && !r.isEnd())
					roomsCheckList[r.getId()] = 1;
			if (id >= 0)
				rooms = paths.get(id).getRooms();
			id--;
		}
		return (false);
	}

	public Path getShortestDisjoint(List<Path> allPaths, int roomsCount)
	{
		int id = 0;
		Path shortest = allPaths.get(id);

		while (contains(shortest) || isIntersect(shortest, roomsCount))
		{
			shortest = allPaths.get(id);
			id++;
			if (id >= allPaths.size())
				return null;
		}
		return shortest;
	}

	public void evaluate(Ants ants)
	{
		steps = ants.runThrough(paths);
	}

	public boolean add(Path next)
	{
		if (paths.size() == capacity)
			return false;
		paths.add(next);
		len += next.getLen();
		return true;
	}

	public void delLast()
	{
		if (paths.size() > 0)
			paths.remove(paths.size() - 1);
	}

	private int	fullCheck(PathSet someSet, int checking)
	{
		int res = checking;

		if (!isFull() && !someSet.isFull())
			res = 0;
		else if (!someSet.isFull())
			res = -1;
		else if (!isFull())
			res = 1;
		return res;
	}

	public int compareLen(PathSet someSet) // TODO: it should be comparator
	{
		int comRes = Integer.compare(len, someSet.getLen());

		return fullCheck(someSet, comRes);
	}

	public int compareSteps(PathSet someSet) // TODO: it should be comparator
	{
		int comRes = Integer.compare(steps, someSet.getSteps());

		return fullCheck(someSet, comRes);
	}

	public boolean isFull()
	{
		return (paths.size() == capacity);
	}

	public int size()
	{
		return paths.size();
	}

	public void copyAll(PathSet someSet)
	{
		this.paths = new ArrayList<>(someSet.getPaths());
		this.capacity = someSet.getCapacity();
		this.len = someSet.getLen();
		this.steps = someSet.getSteps();
	}

	public boolean	contains(Path somePath)
	{
		return (paths.contains(somePath));
	}

	public int getLen()
	{
		return len;
	}

	public int getSteps()
	{
		return steps;
	}

	public List<Path>	getPaths()
	{
		return paths;
	}

	public int	getCapacity()
	{
		return capacity;
	}
}
