package interview;

import org.testng.annotations.Test;

import static interview.StringManipulation.brackets;
import static org.testng.Assert.*;

public class StringManipulationTest {

	@Test
	public void testBrackets () {
		boolean valid = brackets("{([])}");
		System.out.println(valid);
	}
}