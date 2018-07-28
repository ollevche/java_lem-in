
package lemin.logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import lemin.objects.Link;
import lemin.objects.Path;
import lemin.objects.Room;
import lemin.util.FatalDataLack;

public class AntGraph
{
	private	AntFarm			antFarm;
	private ArrayList<Path>	paths;

	public AntGraph(AntFarm antFarm)
	{
		if (antFarm.getRooms().isEmpty())
			throw (new FatalDataLack("No rooms found"));
		if (antFarm.getLinks().isEmpty())
			throw (new FatalDataLack("No links found"));
		if (antFarm.getStartId() < 0 || antFarm.getEndId() < 0)
			throw (new FatalDataLack("Route not defined"));
		this.antFarm = antFarm;
	}

	private int[][]	createMatrix(List<Room> rooms, List<Link> links)
	{
		int		size = rooms.size();
		int[][]	matrix = new int[size][size];

		for (Link l : links)
		{
			matrix[l.getFromId()][l.getToId()] = 1;
			matrix[l.getToId()][l.getFromId()] = 1;
		}
		return matrix;
	}

	private void	dfsearch(int[][] adjMatrix, List<Integer> visited, int endId, List<Path> paths)
	{
		int curId;

		curId = visited.get(visited.size() - 1);
		if (curId == endId)
		{
			paths.add(new Path(antFarm, visited));
			return ;
		}
		for (int i = 0; i < adjMatrix.length; i++)
		{
			if (adjMatrix[curId][i] == 1 && !visited.contains(i))
			{
				visited.add(i);
				dfsearch(adjMatrix, visited, endId, paths);
				visited.remove(visited.size() - 1); // check it
			}
		}
	}

	public void	findAllPaths()
	{
		ArrayList<Integer>	visited;
		int[][]				adjMatrix;
		LinkedList<Path>	paths = new LinkedList<>();

		antFarm.indexRooms();
		adjMatrix = createMatrix(antFarm.getRooms(), antFarm.getLinks());
		visited = new ArrayList<>(antFarm.getRooms().size());
		visited.add(antFarm.getStartId());
		dfsearch(adjMatrix, visited, antFarm.getEndId(), paths);
		if (paths.isEmpty())
			throw (new FatalDataLack("End room cannot be reached"));
		this.paths = new ArrayList<>(paths);
	}

	public List<Path>	getPaths()
	{
		return paths;
	}

}