package interview.search;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*
	Time Complexity O(|V| + |E|)
	Space Complexity O(|V|)
 */
public class BFS {

	/**
	 * Run Breadth Force Search (BFS) on the graph starting at Node start
	 * @param start is the Node at which we are starting our algorithm
	 * @param end is the Node we are searching for in the graph
	 * @return true if there is a path from Node start to Node end, false otherwise
	 */
	public static boolean existingPath(Node start, Node end) {
		HashSet<Node> visited = new HashSet<>();
		Queue<Node> adjacent = new LinkedList<>();

		adjacent.add(start);

		// Loop through all adjacent nodes
		while (!adjacent.isEmpty()) {
			Node current = adjacent.remove();
			if (current.value == end.value) {
				return true;
			}

			for (int i = 0; i < current.children.size() ;i++) {
				Node adjacentNode = current.children.get(i);
				if (!visited.contains(adjacentNode)) {
					adjacent.add(current.children.get(i));
				}
			}
			visited.add(current);
		}

		return false;
	}
}
