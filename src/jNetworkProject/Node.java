package jNetworkProject;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private int id;
	private int degrees;
	private List<Node> succs;
	private List<Double> weight;
	private List<Boolean> isdirected;
	private List<Integer> succsID;

	public Node() {
		id = 0;
		degrees = 0;
		isdirected = new ArrayList<Boolean>();
		succs = new ArrayList<Node>();
		weight = new ArrayList<Double>();
		succsID = new ArrayList<Integer>();
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}

	public void setDegrees(int degrees) {
		this.degrees = degrees;
	}

	public int getDegrees() {
		return degrees;
	}

	public void setSuccessor(Node targ, boolean isDirTarg, double wTarg) {
		succs.add(targ);
		isdirected.add(isDirTarg);
		weight.add(wTarg);
		succsID.add(targ.getID());
	}

	public Node getSuccessor(int i) {
		return succs.get(i);
	}

	public Boolean isDirected(int i) {
		return isdirected.get(i);
	}

	public Double getWeightSuccessor(int i) {
		return weight.get(i);
	}

	public List<Integer> getSuccessors() {
		return succsID;
	}
}
