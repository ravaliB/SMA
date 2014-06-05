package jNetworkProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import repast.simphony.context.Context;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;

public class RoadContext {
	private Context<Object> context;
	private ContinuousSpace<Object> space;
	private List<Node> nodes;

	public RoadContext(Context<Object> context, ContinuousSpace<Object> space) {
		this.context = context;
		this.space = space;
		nodes = new ArrayList<Node>();
		fillListAgent();
	}

	public void addCar(int numbercar) {
		for (int i = 0; i < numbercar; i++) {
			Node randomNode = generateLocationNode();
			NdPoint ptNode = space.getLocation(randomNode);
			Car car = new Car(space);
			context.add(car);
			space.moveTo(car, ptNode.getX(), ptNode.getY());
			car.setNode(randomNode);
			car.setNodes(nodes);
		}
	}

	private void fillListAgent()
	{
		for (Object obj : context) {
			if (obj instanceof Node)
			{
				Node node = (Node)obj;
				nodes.add(node);
			}
		}
	}

	private Node generateLocationNode() {
		Random randGenerator = new Random();
		int i = randGenerator.nextInt(10);
		
		return nodes.get(i);
	}
}
