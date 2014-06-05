package jNetworkProject;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private int id;
	private int degrees;
	private List<Integer> succs;
	private List<Double> weight;
	private List<Boolean> isdirected;
	
	
	public Node() {
		id = 0;
		degrees = 0;
		isdirected = new ArrayList<Boolean>(); 
		succs = new ArrayList<Integer>();
		weight = new ArrayList<Double>();
	}

	public void setID(int id)
	{
		this.id = id;
	}

	public int getID() {
		return id;
	}
	
	public void setDegrees(int degrees)
	{
		this.degrees = degrees;
	}
	
	public int getDegrees()
	{
		return degrees;
	}
	
	public void setSuccessor(int idTarg, boolean isDirTarg, double wTarg)
	{
		succs.add(idTarg);
		isdirected.add(isDirTarg);
		weight.add(wTarg);
	}
	
	public Integer getSuccessor(int i)
	{
		return succs.get(i);
	}
	
	public Boolean isDirected(int i)
	{
		return isdirected.get(i);
	}
	
	public Double getWeight(int i)
	{
		return weight.get(i);
	}
}
