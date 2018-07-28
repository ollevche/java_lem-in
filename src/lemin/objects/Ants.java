
package lemin.objects;

public class Ants
{
	private int         amount;
	private ObjectInfo  info;

	public	Ants(int amount, ObjectInfo info)
	{
		this.amount = amount;
		this.info = info;
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
