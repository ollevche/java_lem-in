
package input;

import org.junit.Before;
import org.junit.Test;

import lemin.logic.AntFarm;
import lemin.objects.ObjectInfo;
import lemin.util.InputDataMismatch;

public class AntFarmTests {

	private AntFarm farm;

	@Before
	public void initAntFarm() {
		farm = new AntFarm();
	}

	@Test(expected = InputDataMismatch.class)
	public void setNegAntsCount() {
		farm.setAnts(-1);
	}

	@Test(expected = InputDataMismatch.class)
	public void addDuplicateRooms() {
		farm.addInfo(new ObjectInfo());
		farm.addRoom("room", 0, 0);
		farm.addInfo(new ObjectInfo());
		farm.addRoom("room", 0, 0);
	}

	@Test(expected = InputDataMismatch.class)
	public void addTwoStartRooms() {
		ObjectInfo info = new ObjectInfo();

		info.addCommand("##start");
		farm.addInfo(info);
		farm.addRoom("room", 0, 0);
		farm.addInfo(info);
		farm.addRoom("room", 0, 0);
	}

	@Test(expected = InputDataMismatch.class)
	public void addTwoEndRooms() {
		ObjectInfo info = new ObjectInfo();

		info.addCommand("##end");
		farm.addInfo(info);
		farm.addRoom("room", 0, 0);
		farm.addInfo(info);
		farm.addRoom("room", 0, 0);
	}

	@Test(expected = InputDataMismatch.class)
	public void linkUndefinedRooms() {
		farm.addLink("room1", "room2");
	}
}
