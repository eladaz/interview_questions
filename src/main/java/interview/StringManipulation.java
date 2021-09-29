package interview;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Stack;

public class StringManipulation {

	public static boolean brackets (String str) {
		Pair<Character, Character> bracket = new MutablePair<>('(', ')');
		Pair<Character, Character> curlyBrackets = new MutablePair<>('{', '}');
		Pair<Character, Character> squareBrackets = new MutablePair<>('[', ']');

		Stack<Character> stack = new Stack<>();
		char[] chars = str.toCharArray();

		for (Character character : chars) {
			if (character == bracket.getKey() ||
						character == squareBrackets.getKey() ||
						character == curlyBrackets.getKey()) {
				stack.push(character);
			} else if (character == bracket.getValue() ||
							   character == squareBrackets.getValue() ||
							   character == curlyBrackets.getValue()) {

				if (stack.empty()) {
					return false;
				}
				Character pop = stack.pop();

				if (pop == bracket.getKey() && character != bracket.getValue()) {
					return false;
				}

				if (pop == curlyBrackets.getKey() && character != curlyBrackets.getValue()) {
					return false;
				}

				if (pop == squareBrackets.getKey() && character != squareBrackets.getValue()) {
					return false;
				}
			}
		}

		return stack.empty();
	}
}
