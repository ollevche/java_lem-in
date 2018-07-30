
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
		adjMatrix = createMatrix(antFarm.getRooms(), antFarm.getLinks());
		visited = new ArrayList<>(antFarm.getRooms().size());
		visited.add(antFarm.getStartId());
		dfsearch(adjMatrix, visited, antFarm.getEndId(), paths);
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

	private PathSet	buildSet(int size, PathSet progress) // TODO: review and rewrite it (the whole algo)
	{
		PathSet best = new PathSet(size);
		Path	next;

		next = progress.getShortestDisjoint(paths, antFarm.getRooms().size());
		if (next == null)
			return best;
		if (progress.size() == size - 1)
		{
			progress.add(next);
			best.copyAll(progress);
			return best;
		}
		paths.stream()
			.skip(next.getId() + 1) // (id == 0) -> skip one (current) element
			.filter(p -> !progress.contains(p) // leaves intersectors of next path
				&& p.isIntersect(next, antFarm.getRooms().size()))
			.limit(size - progress.size() - 1) // -1 because of 'next' (will be in progress)
			.forEach(p -> { // applies buildSet() with extended progress, then shrinks it
				if (!progress.add(next)) // it's for safety only
					return ; // del it  if limit() works properly
				PathSet someSet = buildSet(size, progress);
				if (best.compareLen(someSet) > 0)
					best.copyAll(someSet);
				progress.delLast(); });
		return best;
	}

	public PathSet	pickBestSet()
	{
		Ants	ants = antFarm.getAnts();
		PathSet	sizeBest = bestSet = buildSet(1, new PathSet(1));

		bestSet.evaluate(ants);
		do
		{
			int size = sizeBest.size() + 1;
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
		return bestSet; // modifiable
	}

	public PathSet	getBestSet()
	{
		return bestSet;
	}

	public List<Path>	getPaths()
	{
		return Collections.unmodifiableList(paths);
	}
}
