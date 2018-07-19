
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

}
