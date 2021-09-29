package interview.search;

import java.util.ArrayList;
import java.util.List;

/* Representation of a vertex */
public class Node {
	int value;
	List<Node> children; //Edges in the graph to other vertices

	public Node () {
		children = new ArrayList<>();
	}
}
