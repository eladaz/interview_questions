package interview;

import java.util.HashSet;
import java.util.Set;

import static interview.PowerSet.powerSet;
import static interview.PowerSet.printPowerSet;


public class Main {
	public static void main (String[] args) {
		char []set = {'a', 'b', 'c'};
		printPowerSet(set, 3);

		Set<Character> mySet = new HashSet<Character>();
		mySet.add('a');
		mySet.add('b');
		mySet.add('c');
		Set<Set<Character>> sets = powerSet(mySet);

		System.out.print("PowerSet of {'a', 'b', 'c'}: { ");
		for (Set<Character> s : sets) {
			if (s == sets.toArray()[sets.size() -1]) {
				System.out.print(s);
			} else {
				System.out.print(s + ", ");
			}

		}

		System.out.print(" }");
	}
}
