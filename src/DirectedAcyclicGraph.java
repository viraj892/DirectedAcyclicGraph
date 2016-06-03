import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author Viraj Shah
 *
 */
public class DirectedAcyclicGraph {
	private Map<String, ArrayList<String>> adj_list = new HashMap<String, ArrayList<String>>();

	public DirectedAcyclicGraph() {

	}

	/**
	 * Add an edge from {@code source} to {@code destination} and return
	 * {@code true} if the edge is added as a result of this function. If either
	 * {@code source} or {@code destination} does not exist, create them before
	 * adding the edge.
	 * 
	 * @param source
	 *            the name of the source node
	 * @param destination
	 *            the name of the destination edge
	 * @return {@code true} if the edge is added as a result of this method call
	 */
	public boolean addEdge(String source, String destination) {
		// If the edge is already present then return false
		if (source.equals(destination)) {
			System.out.println("edge +" + source + "," + destination + " forms a cycle");
			return false;
		}
		// add edge directly if graph is empty
		if (adj_list.isEmpty()) {
			adj_list.put(source, new ArrayList<String>());
			adj_list.get(source).add(destination);
			// Adding new vertex destination to hashmap with null Arraylist
			adj_list.put(destination, new ArrayList<String>());
			System.out.println("edge " + source + "," + destination + " added successfully");
			return true;
		} else if (adj_list.containsKey(source)) { // If source vertex exists in
													// graph

			if (adj_list.get(source).contains(destination)) {
				System.out.println("Edge already exists");
				return false;
			} else if (adj_list.containsKey(destination)) { // If both source &
															// destination
															// vertices exists
															// in graph

				// Check for cycle formation
				if (checkCycle(source, destination)) {
					System.out.println("edge " + source + "," + destination + " forms a cycle");
					return false;
				} else {
					adj_list.get(source).add(destination);
					System.out.println("edge " + source + "," + destination + " added successfully");
					return true;
				}

			} else {

				adj_list.get(source).add(destination);
				adj_list.put(destination, new ArrayList<String>());
				System.out.println("edge " + source + "," + destination + " added successfully");
				return true;
			}

		}
		// default return
		System.out.println("edge Not added ");
		return false;
	}

	/**
	 * 
	 * @param source
	 *            name of the source node of the edge that is to be checked
	 * @param destination
	 *            name of the destination node of the edge that is to be checked
	 * @return {@code true} if the edge {@code source}, {@code destination}
	 *         forms a cycle in the current graph
	 */
	public boolean checkCycle(String source, String destination) {
		Stack<String> fringe = new Stack<String>();
		Map<String, Boolean> visited = new HashMap<String, Boolean>();
		for (String key : adj_list.keySet())
			visited.put(key, false);

		fringe.push(destination);
		visited.put(destination, true);

		while (!fringe.isEmpty()) {
			String s = fringe.peek();
			System.out.println(s);
			ArrayList<String> neighbours = adj_list.get(s);
			if (neighbours.size() != 0) {
				for (int i = 0; i < neighbours.size(); i++) {
					if (!visited.get(neighbours.get(i))) {
						visited.put(neighbours.get(i), true);
						fringe.push(neighbours.get(i));
						if (neighbours.get(i).equals(source))
							return true;

					} else {
						fringe.pop();
					}
				}
			} else {
				fringe.pop();
			}
		}

		return false;
	}

	public static void main(String args[]) {
		DirectedAcyclicGraph graph = new DirectedAcyclicGraph();
		/**
		 * Test cases : method addedge(source, destination)
		 */
		graph.addEdge("a", "b");
		graph.addEdge("a", "c");
		graph.addEdge("b", "d");
		graph.addEdge("c", "f");
		graph.addEdge("c", "e");
		graph.addEdge("f", "a"); // cycle
	}
}
