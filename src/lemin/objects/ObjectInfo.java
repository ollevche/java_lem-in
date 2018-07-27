
package lemin.objects;

import java.util.LinkedList;

import lemin.util.InputDataMismatch;

public class ObjectInfo
{
	private LinkedList<String>	comments;
	private String				command;
	private boolean				hasStartCommand;
	private boolean				hasEndCommand;

	public boolean	addComment(String comment)
	{
		if (comments == null)
			comments = new LinkedList<>();
		comments.add(comment);
		return true;
	}

	public boolean	addCommand(String command)
	{
		if (this.command != null)
			throw (new InputDataMismatch("Cannot accept two commands"));
		this.command = command;
		if (command.equals("##start"))
			hasStartCommand = true;
		else if (command.equals("##end"))
			hasEndCommand = true;
		return true;
	}

	public boolean hasStartCommand()
	{
		return hasStartCommand;
	}

	public boolean hasEndCommand()
	{
		return hasEndCommand;
	}

	@Override
	public String toString()
	{
		String comments = (this.comments == null ? "" : this.comments.toString() + "\n");
		String command = (this.command == null ? "" : this.command + "\n");

		return (comments + command);
	}
}
