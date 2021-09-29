package interview;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
	Set  = [a,b,c]
	power_set_size = pow(2, 3) = 8
	Run for binary counter = 000 to 111

	Value of Counter            Subset
		000                    -> Empty set
		001                    -> a
		010                    -> b
		011                    -> ab
		100                    -> c
		101                    -> ac
		110                    -> bc
		111                    -> abc
 */

// O(2^n)
public class PowerSet {

	public static void printPowerSet (char[] set, int setSize) {
		long powSetSize = (long) Math.pow(2, setSize);

		int counter, j;

		for (counter = 0; counter < powSetSize; counter++) {
			for (j = 0; j < setSize; j++) {
				int count = counter & (1 << j);
				if (count > 0) {
					System.out.print(set[j]);
				}
			}
			System.out.println();
		}
	}

	public static <T> Set<Set<T>> powerSet(Set<T> originalSet) {
		Set<Set<T>> sets = new HashSet<>();
		if (originalSet.isEmpty()) {
			sets.add(new HashSet<T>());
			return sets;
		}
		List<T> list = new ArrayList<T>(originalSet);
		T head = list.get(0);
		Set<T> rest = new HashSet<T>(list.subList(1, list.size()));
		for (Set<T> set : powerSet(rest)) {
			Set<T> newSet = new HashSet<T>();
			newSet.add(head);
			newSet.addAll(set);
			sets.add(newSet);
			sets.add(set);
		}
		return sets;
	}
}