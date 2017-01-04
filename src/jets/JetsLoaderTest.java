package jets;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class JetsLoaderTest {
	JetsLoader loader;
	String fileName = "";
	
	@Before
	public void setUp() throws Exception {
		//Instantiate new object
		loader = new JetsLoader(null, null);
	}

	@After
	public void tearDown() throws Exception {
		loader = null;
	}

	@Test
	public void test_string_to_pilot_to_string() {
		String inString = "PILOT:99:Jack \"Launchpad\" McQuack:M:33";
		JetsLoader.Pair<Pilot, Integer> pair = loader.pilotFromString(inString);
		Pilot p = pair.first();
		int pid = pair.second();
		String outString = loader.pilotToString(p, pid);
		
		assertEquals(inString, outString);
	}

	@Test
	public void test_string_to_jet_to_string() {
		String inString = "JET:33:Boeing 666:200:400:99.95:99";
		JetsLoader.Pair<Jet, Integer> pair = loader.jetFromString(inString);
		Jet j = pair.first();
		int jid = pair.second();
		String outString = loader.jetToString(j, jid, 0);
		
		assertEquals(inString, outString);
	}
	
//	@Test
//	public void test() {
//		assertNotNull(scores);
//		assertArrayEquals(new Integer[]{5, 9, 10}, scores.toArray(new Integer[0]));
//		fail("Not yet implemented");
//	}

}
