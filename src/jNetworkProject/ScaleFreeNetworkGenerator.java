package jNetworkProject;

import jNetworkProject.Graph.Edge;
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
				if (obj instanceof Edge) {
					Edge e = (Edge) obj;
						RepastEdge<Object> edges = 
								new RepastEdge<Object>(e.Node1,	e.Node2, false, e.weight);
						network.addEdge(edges);
					}

				
			}

			return network;
		}
	}
}