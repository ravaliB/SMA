package jNetworkProject;

import jNetworkProject.Graph.GNode;
import jNetworkProject.Graph.Graph;
import jNetworkProject.Monde.Car;
import jNetworkProject.Monde.CarsCreator;

import java.util.Random;

import repast.simphony.context.Context;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;

public class RoadContext {
	
	private Context<Object> context;
	private ContinuousSpace<Object> space;
	private Graph g;

	public RoadContext(Context<Object> context, ContinuousSpace<Object> space) {
		this.context = context;
		this.space = space;
		for (Object obj : context) {
			if (obj instanceof Graph)
				g = (Graph) obj;
		}
		
		context.add(new CarsCreator(this));
		
		/*nodes = new ArrayList<Node>();
		fillListAgent();*/
	}

	public void addCar(int in, int out)
	{
		GNode src = null;
		GNode dst = null;
		for (GNode n : g.sommets)
		{
			if (n.id == out)
				dst = n;
			if (n.id == in)
				src = n;
		}
		NdPoint ptNode = space.getLocation(src);
		Car car = new Car(space, src, dst);
		context.add(car);
		space.moveTo(car, ptNode.getX(), ptNode.getY());
	}
	
	public void addCar(int numbercar) {
		//World world = new World(nodes);

		for (int i = 0; i < numbercar; i++) {
			GNode src = generateLocationNode();
			GNode dst = generateLocationNode(src);
			NdPoint ptNode = space.getLocation(src);
			Car car = new Car(space, src, dst);
			context.add(car);
			space.moveTo(car, ptNode.getX(), ptNode.getY());
			//car.setNodes(nodes);
		}
	}

	private GNode generateLocationNode() {
		Random randGenerator = new Random();
		int i = randGenerator.nextInt(g.sommets.size());
		
		return g.sommets.get(i);
	}
	
	private GNode generateLocationNode(GNode different) {
		Random randGenerator = new Random();
		int i = randGenerator.nextInt(g.sommets.size());
		while (g.sommets.get(i) == different)
		{
			i = randGenerator.nextInt(g.sommets.size());
		}
		
		return g.sommets.get(i);
	}
}
