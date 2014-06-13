package jNetworkProject;

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
					Node node = (Node) obj;

					for (int i = 0; i < node.getDegrees(); i++) {

						RepastEdge<Object> edges = new RepastEdge<Object>(node,
								node.getSuccessor(i), node.isDirected(i),
								node.getWeightSuccessor(i));
						network.addEdge(edges);
					}
				}
			}

			return network;
		}
	}
}