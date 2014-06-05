package jNetworkProject;

import java.util.Iterator;
import java.util.List;

import repast.simphony.engine.schedule.ScheduledMethod;
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
		int degs = node.getDegrees();
		double min = 9999;
		/*
		 * le successeur du noeud renvoie sur lui meme (ne bouge pas) si le
		 * degre est null
		 */
		Node succ = node;

		// verification si le degre du noeud est strict positif
		if (degs > 0) {
			// On recupere l'arc avec le plus petit cout
			for (int edge = 0; edge < degs; edge++) {
				if (node.getWeight(edge) < min) {
					min = node.getWeight(edge);
					succ = IdToNode(node.getSuccessor(edge));
				}
			}
		}

		moveTo(succ);
	}

	public void moveTo(Node n) {
		NdPoint ptAgent = space.getLocation(n);
		space.moveByDisplacement(this, 0.5, 0.5);
		space.moveTo(this, ptAgent.getX(), ptAgent.getY());
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	private Node IdToNode(int idsucc) {
		Iterator<Node> it = nodes.iterator();
		Node succ;

		do {
			succ = it.next();
		} while (it.hasNext() && !(succ.getID() == idsucc));

		return succ;
	}
}
