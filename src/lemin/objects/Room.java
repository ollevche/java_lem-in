
package lemin.objects;

public class Room implements Comparable<Room>
{
	private String		name;
	private int			x, y;
	private ObjectInfo	info;

	public	Room (String name, int x, int y)
	{
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public	Room (String name, int x, int y, ObjectInfo info)
	{
		this(name, x, y);
		this.info = info;
	}

	public String	getName()
	{
		return name;
	}

	public ObjectInfo getInfo()
    {
        return info;
    }

	@Override
	public int	compareTo(Room r)
	{
		return name.compareTo(r.getName());
	}

	@Override
	public boolean	equals(Object obj)
	{
		Room someroom;

		if (obj == this)
			return true;
		if (obj == null || !(obj instanceof Room))
			return false;
		someroom = (Room)obj;
		if (this.compareTo(someroom) == 0)
			return true;
		return false;
	}

	@Override
	public int	hashCode()
	{
		return this.name.hashCode();
	}

	@Override
	public String	toString()
	{
		return String.format(info + "%s %d %d\n", name, x, y);
	}

}
