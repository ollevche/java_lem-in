
package lemin.objects;

import java.util.LinkedList;

public abstract class InformationalObj
{
	private LinkedList<String> info;

	public void setInfo(LinkedList<String> info)
	{
		this.info = info;
	}

	public LinkedList<String> getInfo()
	{
		return (info);
	}
}