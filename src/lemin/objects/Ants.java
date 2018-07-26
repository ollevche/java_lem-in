
package lemin.objects;

import java.util.LinkedList;

public class Ants extends InformationalObj
{
	private int	amount;

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

	public void setAmount(String amount)
	{
		this.amount = Integer.valueOf(amount);
	}

	public int	getAmount()
	{
		return (amount);
	}

	@Override
	public String toString()
	{
		String antsObj;

		antsObj = String.format("%s%d", info, amount);
		return (antsObj);
	}
}
