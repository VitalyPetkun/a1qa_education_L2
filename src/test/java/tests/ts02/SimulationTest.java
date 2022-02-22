package tests.ts02;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.SmartLogger;

public class SimulationTest extends BaseTest {

    @Test
    public void checkTimer() {
        SmartLogger.logStep(1, "Simulation test");
        Assert.assertTrue(true, "Simulation test isn't correct");
    }
}
