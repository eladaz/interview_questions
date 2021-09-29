package interview;

import org.testng.annotations.Test;

import static interview.Sort.findTwoElementWithSumsK;

public class SortTest {

	@Test
	public void testFindTwoElementWithSumsK () {
		int[] array = { 8, 4, 10, 3, 2 };
		int k = 5;
		int[] result = findTwoElementWithSumsK(array, k);

		System.out.print("The two elements are: " + result[0] + ", " + result[1]);
	}
}