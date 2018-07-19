
package lemin.objects;

import java.util.regex.Matcher;

public class Ants
{
	private int	amount;

	public Ants(int amount)
	{
		this.amount = amount;
	}

	public Ants(Matcher matcher)
	{
		this(Integer.parseInt(matcher.group(1)));
	}
}
