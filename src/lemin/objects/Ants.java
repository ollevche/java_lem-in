
package lemin.objects;

import java.util.LinkedList;

public class Ants
{
	private int	amount;
	private LinkedList<String> info; // optimize

	public Ants()
	{

	}

	public Ants(int amount)
	{
		setAmount(amount);
	}

	public void setAmount(int amount)
	{
		this.amount = amount;
	}

	public int	getAmount()
	{
		return (amount);
	}

	public void setAmount(String amount)
	{
		this.amount = Integer.valueOf(amount);
	}

	public void setInfo(LinkedList<String> info)
	{
		this.info = info;
	}

	@Override
	public String toString()
	{
		String antsObj;

		antsObj = String.format("%s%d", info, amount);
		return (antsObj);
	}
}
