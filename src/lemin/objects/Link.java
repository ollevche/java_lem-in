
package lemin.objects;

public class Link
{
	private Room        from;
	private Room        to;
	private ObjectInfo  info;

	public	Link(Room from, Room to, ObjectInfo info)
	{
		this.from = from;
		this.to = to;
		this.info = info;
	}

	public Room	getFrom()
	{
		return from;
	}

	public Room	getTo()
	{
		return to;
	}

	public int	getFromId()
	{
		return from.getId();
	}

	public int	getToId()
	{
		return to.getId();
	}

	public ObjectInfo	getInfo()
	{
		return info;
	}

	@Override
	public String	toString()
	{
		return String.format("%s-%s", from.getName(), to.getName());
	}
}