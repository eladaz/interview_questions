package interview;

import java.util.HashMap;

public class Sort {

	public static int[] findTwoElementWithSumsK (int[] unsortedArray, int k) {
		HashMap<Integer, Integer> map = new HashMap<>();

		for (int element : unsortedArray) {
			int value = k - element;
			if (map.containsKey(value)) {
				return new int[]{element, value};
			}
			map.put(element, value);
		}
		return new int[2];
	}
}
