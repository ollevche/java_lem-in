
package input;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import lemin.objects.ObjectInfo;
import lemin.util.InputDataMismatch;

public class ObjectInfoTests {

	private ObjectInfo info;

	@Before
	public void initObjInfo() {
		info = new ObjectInfo();
	}

	@Test
	public void addStartCommand() {
		info.addCommand("##start");
		assertTrue("start command should be added", info.hasStartCommand());
		assertEquals("toString should return command", info.toString(), "##start\n");
	}

	@Test
	public void addEndCommand() {
		info.addCommand("##end");
		assertTrue("end command should be added", info.hasEndCommand());
		assertEquals("toString should return command", info.toString(), "##end\n");
	}

	@Test
	public void addUndefinedCommand() {
		info.addCommand("##command");
		assertTrue("undefined command should be added", info.hasCommand());
		assertEquals("toString should return command", info.toString(), "##command\n");
	}

	@Test(expected = InputDataMismatch.class)
	public void addStartNEndCommands() {
		info.addCommand("##start");
		info.addCommand("##end");
	}

	@Test(expected = InputDataMismatch.class)
	public void addCommentAfterCommand() {
		info.addCommand("##start");
		info.addComment("#comment");
	}

	@Test
	public void addComments() {
		info.addComment("#comment");
		info.addComment("#comment1");
		info.addComment("#comment2");
		info.addComment("#comment3");
		info.addComment("#comment4");
		assertNotEquals("comments should be added", info.toString(), "");
	}
}
