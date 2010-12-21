package withgod;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple JSONBenchTest.
 */
public class JSONBenchTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public JSONBenchTest(String testName)
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( JSONBench.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testJSONBench()
    {
        assertTrue( true );
    }
}
