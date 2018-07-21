
package lemin.objects;

import java.util.regex.Matcher;

public class Room implements Comparable<Room>
{
	private String	name;
	private int		x;
	private int		y;

	public Room(String name, int x, int y)
	{
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public Room(Matcher matcher)
	{
		this(matcher.group(1),
			Integer.parseInt(matcher.group(2)),
			Integer.parseInt(matcher.group(3)));
	}

	public String getName()
	{
		return (name);
	}

	@Override
	public int compareTo(Room r)
	{
		return (name.compareTo(r.getName()));
	}

	@Override
	public boolean equals(Object obj)
	{
		Room someroom;

		if (obj == this)
			return (true);
		if (obj == null || !(obj instanceof Room))
			return (false);
		someroom = (Room)obj;
		if (this.compareTo(someroom) == 0)
			return (true);
		return (false);
	}

	@Override
	public int hashCode()
	{
		return (this.name.hashCode());
	}

	@Override
	public String toString()
	{
		return (String.format("%s %d %d", name, x, y));
	}

}
