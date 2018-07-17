
package lemin.objects;

import java.util.LinkedList;
import java.util.regex.Matcher;

public class Room
{
	public Room(String name, String x, String y)
	{
		
	}

	public Room(Matcher matcher)
	{
		System.out.printf("name = %s, ", matcher.group(1));
		System.out.printf("x = %s, ", matcher.group(2));
		System.out.printf("y = %s\n", matcher.group(3));
	}

	public void setLinks(LinkedList<String> links)
	{

	}

	public String getName()
	{
		return null;
	}
}
