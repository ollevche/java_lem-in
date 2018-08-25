
package input;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import lemin.logic.AntFarm;
import lemin.logic.AntGraph;
import lemin.objects.ObjectInfo;
import lemin.util.FatalDataLack;

public class AntGraphTests {

    private AntGraph antGraph;
    private AntFarm antFarm;

	@Before
	public void initAntFarm() {
        antFarm = new AntFarm();
    }

    @Test(expected = FatalDataLack.class)
    public void initGraphWithEmptyFarm() {
        antGraph = new AntGraph(antFarm);
    }

    @Test(expected = FatalDataLack.class)
    public void initGraphWithRoomOnly() {
        antFarm.addInfo(new ObjectInfo());
        antFarm.addRoom("room", 0, 0);
        antGraph = new AntGraph(antFarm);
    }

    @Test(expected = FatalDataLack.class)
    public void initGraphWithUndefinedRoute() {
        antFarm.addInfo(new ObjectInfo());
        antFarm.addRoom("startRoom", 0, 0);
        antFarm.addInfo(new ObjectInfo());
        antFarm.addRoom("endRoom", 1, 1);
        antFarm.addLink("startRoom", "endRoom");
        antGraph = new AntGraph(antFarm);
    }

    @Test(expected = FatalDataLack.class)
    public void initGraphWithUnreachableEndRoom() {
        ObjectInfo info;

        info = new ObjectInfo();
        info.addCommand("##start");
        antFarm.addInfo(info);
        antFarm.addRoom("startRoom", 0, 0);
        info = new ObjectInfo();
        info.addCommand("##end");
        antFarm.addInfo(info);
        antFarm.addRoom("endRoom", 1, 1);
        antGraph = new AntGraph(antFarm);
    }

    @Test
    public void initGraphNormally() {
        ObjectInfo info;

        info = new ObjectInfo();
        info.addCommand("##start");
        antFarm.addInfo(info);
        antFarm.addRoom("startRoom", 0, 0);
        info = new ObjectInfo();
        info.addCommand("##end");
        antFarm.addInfo(info);
        antFarm.addRoom("endRoom", 1, 1);
        antFarm.addLink("startRoom", "endRoom");
        antGraph = new AntGraph(antFarm);
        assertNotNull("should contain a path", antGraph.getPaths());
        assertNotNull("should compose a set from one path", antGraph.getBestSet());
    }
}
