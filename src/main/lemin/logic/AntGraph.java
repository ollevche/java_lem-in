
package lemin.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import lemin.objects.Ants;
import lemin.objects.Link;
import lemin.objects.Path;
import lemin.objects.Room;
import lemin.objects.PathSet;
import lemin.util.FatalDataLack;

public class AntGraph
{
	private	AntFarm			antFarm;
	private List<Path>		paths;
	private PathSet			bestSet;

	public AntGraph(AntFarm antFarm)
	{
		if (antFarm.getRooms().isEmpty())
			throw (new FatalDataLack("No rooms found"));
		if (antFarm.getLinks().isEmpty())
			throw (new FatalDataLack("No links found"));
		if (antFarm.getStartId() < 0 || antFarm.getEndId() < 0)
			throw (new FatalDataLack("Route not defined"));
		this.antFarm = antFarm;
		paths = Collections.emptyList();
		bestSet = new PathSet(0);
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
			paths.add(new Path(antFarm.getRooms(), visited));
			return ;
		}
		for (int i = 0; i < adjMatrix.length; i++)
		{
			if (adjMatrix[curId][i] == 1 && !visited.contains(i))
			{
				visited.add(i);
				dfsearch(adjMatrix, visited, endId, paths);
				visited.remove(visited.size() - 1);
			}
		}
	}

	public void	findAllPaths()
	{
		List<Integer>	visited;
		int[][]			adjMatrix;
		List<Path>		paths = new LinkedList<>();

		antFarm.indexRooms();
		adjMatrix = createMatrix(antFarm.getRooms(), antFarm.getLinks()); // OPTIMIZE: adjacency list is a faster way
		visited = new ArrayList<>(antFarm.getRooms().size());
		visited.add(antFarm.getStartId());
		dfsearch(adjMatrix, visited, antFarm.getEndId(), paths); // OPTIMIZE: dfs + sorting should be replaced by just bfs
		if (paths.isEmpty())
			throw (new FatalDataLack("End room cannot be reached"));
		indexPath(paths);
	}

	private void	indexPath(List<Path> notIndexed)
	{
		int id = 0;

		paths = new ArrayList<>(notIndexed);
		Collections.sort(paths);
		for (Path p : paths)
			p.setId(id++);
	}

	private PathSet	buildSet(int size, PathSet progress) // REFACTOR: the func
	{
		PathSet best = new PathSet(size);
		int		roomsCount = antFarm.getRooms().size();
		Path	next;

		next = progress.getShortestDisjoint(paths, roomsCount);
		if (next == null)
			return best;
		if (progress.size() == size - 1)
		{
			progress.add(next);
			best.copyAll(progress);
			return best;
		}
		for (int i = next.getId(); i < paths.size(); i++)
		{
			Path p = paths.get(i);
			if (!progress.isIntersect(p, roomsCount)
				&& p.isIntersect(next, antFarm.getRooms().size()))
			{
				progress.add(p);
				PathSet someSet = buildSet(size, progress);
				if (best.compareLen(someSet) > 0)
					best.copyAll(someSet);
				progress.delLast();
			}
		}
		return best;
	}

	public PathSet	pickBestSet() // OPTIMIZE: it stops only when the last iteration fully bruteforced
	{
		Ants	ants = antFarm.getAnts();
		int		size;
		PathSet	sizeBest = bestSet = buildSet(1, new PathSet(1));

		bestSet.evaluate(ants);
		do
		{
			size = sizeBest.size() + 1;
			sizeBest = buildSet(size, new PathSet(size));
			if (!sizeBest.isFull())
				break ;
			sizeBest.evaluate(ants);
			if (bestSet.compareSteps(sizeBest) > 0)
				bestSet = sizeBest;
			else if (bestSet.compareSteps(sizeBest) < 0)
				break ;
		}
		while (sizeBest.size() <= ants.getAmount());
		return bestSet; // TODO: unmodifiable return
	}

	public PathSet	getBestSet()
	{
		return bestSet; // TODO: unmodifiable return
	}

	public List<Path>	getPaths()
	{
		return Collections.unmodifiableList(paths);
	}
}
