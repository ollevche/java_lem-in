
package input;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

import lemin.objects.ObjectInfo;


public class ObjectInfoTests extends TestCase {

	private ObjectInfo info;

	@Before
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		info = new ObjectInfo();
	}

	// @Test(expected = InputDataMismatch.class)
	public void addStartCommand() {
		info.addCommand("##start");
		// assertEquals("##start");
	}

}