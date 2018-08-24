
package input;

import org.junit.Test;

import lemin.logic.AntFarm;
import lemin.objects.ObjectInfo;
import lemin.util.InputDataMismatch;

public class AntFarmTests {

	@Test(expected = InputDataMismatch.class)
	public void setNegAntsCount() {
		AntFarm farm = new AntFarm();
		int ants = -1;

		farm.setAnts(ants);
	}

	@Test(expected = InputDataMismatch.class)
	public void addDuplicateRooms() {
		AntFarm farm = new AntFarm();

		farm.addInfo(new ObjectInfo());
		farm.addRoom("room", 0, 0);
		farm.addInfo(new ObjectInfo());
		farm.addRoom("room", 0, 0);
	}

	@Test(expected = InputDataMismatch.class)
	public void addTwoStartRooms() {
		AntFarm farm = new AntFarm();
		ObjectInfo info = new ObjectInfo();

		info.addCommand("##start");
		farm.addInfo(info);
		farm.addRoom("room", 0, 0);
		farm.addInfo(info);
		farm.addRoom("room", 0, 0);
	}

	@Test(expected = InputDataMismatch.class)
	public void addTwoEndRooms() {
		AntFarm farm = new AntFarm();
		ObjectInfo info = new ObjectInfo();

		info.addCommand("##end");
		farm.addInfo(info);
		farm.addRoom("room", 0, 0);
		farm.addInfo(info);
		farm.addRoom("room", 0, 0);
	}

	@Test(expected = InputDataMismatch.class)
	public void linkUndefinedRooms() {
		AntFarm farm = new AntFarm();
		ObjectInfo info = new ObjectInfo();

		farm.addLink("room1", "room2");
	}
}
