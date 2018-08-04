
package lemin.objects;

import java.util.List;

public class Ants
{
	private int         amount;
	private ObjectInfo  info;

	public	Ants(int amount, ObjectInfo info)
	{
		this.amount = amount;
		this.info = info;
	}

	public int runThrough(List<Path> curSet)
	{
		int		total, leftover, mergeVal, mergeSum = 0;
		Path	path;

		path = curSet.get(curSet.size() - 1);
		mergeVal = path.getLen();
		if (mergeVal == 2)
			return (1);
		for (Path p : curSet)
			mergeSum += mergeVal - (p.getLen() - 1) + 1;
		leftover = amount - mergeSum;
		total = mergeVal + (int)Math.ceil(leftover / (float)curSet.size());
		return (total);
	}

	public int	getAmount()
	{
		return amount;
	}

	public ObjectInfo	getInfo()
	{
		return info;
	}

	@Override
	public String	toString()
	{
		return String.valueOf(amount);
	}
}
