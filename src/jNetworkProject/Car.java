package jNetworkProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.SpatialMath;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;

public class Car {
	private ContinuousSpace<Object> space;
	private Node node;
	private List<Node> nodes;

	public Car(ContinuousSpace<Object> space) {
		this.space = space;
	}

	@ScheduledMethod(start = 1, interval = 1)
	public void step() {
		Random randGenerator = new Random();
		int randomIdNode = randGenerator.nextInt(nodes.size());
		Node n = node;
		List<Node> path;

		if (randomIdNode != n.getID()) {
			path = compute(n, nodes.get(randomIdNode));

			if (null != path) {
				for (Node nod : path) {
					moveTo(nod);
				}
			} else
				moveTo(n);
		} else
			randomIdNode = randGenerator.nextInt(nodes.size());
	}

	public void moveTo(Node n) {
		NdPoint myPoint = space.getLocation(this);
		NdPoint otherPoint = space.getLocation(n);
		double angle = SpatialMath.calcAngleFor2DMovement(space, myPoint,
				otherPoint);
		space.moveByVector(this, 0.1, angle, 0);
		// myPoint = space.getLocation(this);
		space.moveTo(this, otherPoint.getX(), otherPoint.getY());
		node = n;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	private List<Node> compute(Node source, Node dst) {
		Node it = source;
		Double weight = 0.0;
		HashMap<Node, Double> marked = new HashMap<Node, Double>();
		HashMap<Node, Double> cumulwsuccs;
		List<Node> precs = new ArrayList<Node>();
		List<Node> res = new ArrayList<Node>();
		List<Node> res2;
		res.add(it);

		while ((null != it) && !it.equals(dst) && (it.getDegrees() > 0)) {
			cumulwsuccs = new HashMap<Node, Double>();

			for (int i = 0; i < it.getDegrees(); i++) {
				Node succ = it.getSuccessor(i);

				if (!precs.contains(succ)) {
					if (it.equals(source))
						weight = it.getWeightSuccessor(i);
					else
						weight = it.getWeightSuccessor(i)
								+ marked.get(it).doubleValue();

					if (marked.containsKey(succ)) {
						if (weight < marked.get(succ).doubleValue())
							marked.put(succ, weight);
						else {
							weight = marked.get(succ).doubleValue();
							res.remove(it);
						}
					} else {
						marked.put(succ, weight);
					}

					cumulwsuccs.put(succ, weight);
				}
			}

			precs.add(it);
			it = getMin(cumulwsuccs);
			res.add(it);
			cumulwsuccs.clear();
		}

		if ((null == it) || (it.getDegrees() == 0)) {
			res2 = getBackList(res, dst);

			return res2;
		} else
			return res;
	}

	private List<Node> getBackList(List<Node> l, Node dst) {
		List<Node> res = new ArrayList<Node>();
		List<Node> tmp = l;
		HashMap<Node, Double> hash = new HashMap<Node, Double>();
		Node min;

		for (Node node : tmp) {
			if (node != null) {
				for (int i = 0; i < node.getDegrees(); i++) {
					if (node.getSuccessor(i).equals(dst))
						hash.put(node, node.getWeightSuccessor(i));
				}
			}
		}

		if (!hash.isEmpty()) {
			min = getMin(hash);
			Iterator<Node> it = l.iterator();
			Node n;

			do {
				n = it.next();
				res.add(n);
			} while (!n.equals(min) && it.hasNext());

			if (n.equals(min)) {
				res.add(dst);

				return res;
			} else
				return null;
		} else
			return null;
	}

	private Node getMin(HashMap<Node, Double> list) {
		double min = 9999999;
		Node minNode = null;

		for (Map.Entry<Node, Double> entry : list.entrySet()) {
			double weight = entry.getValue();

			if (weight < min) {
				min = weight;
				minNode = entry.getKey();
			}
		}

		return minNode;
	}
}
