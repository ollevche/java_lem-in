
package input;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	AntFarmTests.class,
	AntGraphTests.class,
	ObjectInfoTests.class,
})

public class InputTestSuite {
  // the class remains empty,
  // used only as a holder for the above annotations
  // runs from gradle task
}
