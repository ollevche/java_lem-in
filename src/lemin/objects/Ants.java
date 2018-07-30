
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
		return (curSet.size() == 5 ? 0 : 1); // TODO: this
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
