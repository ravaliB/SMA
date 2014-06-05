package jNetworkProject;

import java.util.Iterator;

import repast.simphony.context.Context;
import repast.simphony.context.space.graph.NetworkGenerator;
import repast.simphony.space.graph.Network;
import repast.simphony.space.graph.RepastEdge;

public class ScaleFreeNetworkGenerator implements NetworkGenerator<Object> {

	private Context<Object> context;

	public ScaleFreeNetworkGenerator(Context<Object> context) {
		this.context = context;
	}

	public Network<Object> createNetwork(Network<Object> network) {

		if (context.isEmpty())
			return null;
		else {
			for (Object obj : context) {
				if (obj instanceof Node) {
					Node node = (Node)obj;
					
					for (int i = 0; i < node.getDegrees(); i++) {

						RepastEdge<Object> edges = new RepastEdge<Object>(
								node, IdToNode(node.getSuccessor(i)),
								node.isDirected(i), node.getWeight(i));
						network.addEdge(edges);
					}
				}
			}

			return network;
		}
	}

	private Node IdToNode(int idSucc) {
		Iterable<Object> nodes = context.getObjects(Node.class);
		Iterator<Object> it = nodes.iterator();
		Node succ;

		do {
			succ = (Node)it.next();
		} while (it.hasNext() && !(succ.getID() == idSucc));

		return succ;
	}

}