package jNetworkProject;

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

		for (int i = 0; i < nNodes; i++) {
			Node node = new Node();
			node.setID(i);
			int nbdegrees = randGenerator.nextInt(3 - 1 + 1) + 1;
			node.setDegrees(nbdegrees);

			for (int j = 0; j < nbdegrees; j++) {
				int idSucc = randGenerator.nextInt(nNodes);
				double weight = randGenerator.nextDouble();
				boolean isdirected = randGenerator.nextBoolean();

				node.setSuccessor(idSucc, isdirected, weight);
			}

			context.add(node);
		}
	}
}