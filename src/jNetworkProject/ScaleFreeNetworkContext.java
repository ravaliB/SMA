package jNetworkProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import repast.simphony.context.Context;
import repast.simphony.context.space.graph.NetworkBuilder;

public class ScaleFreeNetworkContext {
	private Context<Object> context;

	public ScaleFreeNetworkContext(Context<Object> context) {
		this.context = context;
		addNodes(10);
	}

	public void buildNetwork() {
		NetworkBuilder<Object> builder = new NetworkBuilder<Object>(
				"NetworkTest", context, true);
		builder.setGenerator(new ScaleFreeNetworkGenerator(context));
		builder.buildNetwork();
	}

	private void addNodes(int nNodes) {
		Random randGenerator = new Random();
		List<Node> nodes = new ArrayList<Node>();
		int nextSuccId = -1;
		int succId = -1;
		int IdItself = -1;
		boolean isdir;
		double weight;

		// declaration des noeuds
		for (int i = 0; i < nNodes; i++) {
			Node node = new Node();
			int nbdegrees = (int)(Math.random() * (nNodes-2)) + 2;
			
			node.setID(i);
			node.setDegrees(nbdegrees);
			nodes.add(node);
		}

		// declaration des successeurs (aleatoires)
		for (Node nod : nodes) {
			for (int i = 0; i < nod.getDegrees(); i++) {

				// boucle pour eviter d'avoir 2 fois le même successeur
				while ((nod.getSuccessors().contains(nextSuccId))
						|| (IdItself == succId)) {
					succId = randGenerator.nextInt(nNodes);
					nextSuccId = succId;
				}

				isdir = randGenerator.nextBoolean();
				weight = randGenerator.nextDouble();
				Node succ = nodes.get(succId);
				nod.setSuccessor(succ, isdir, weight);
			}

			context.add(nod);
		}
	}
}