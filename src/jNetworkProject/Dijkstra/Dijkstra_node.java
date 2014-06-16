package jNetworkProject.Dijkstra;

import jNetworkProject.Graph.GNode;

public class Dijkstra_node {

	public GNode node;
	public int parcouru;
	public Dijkstra_node precedent;
	
	public Dijkstra_node(GNode node)
	{
		this.node = node;
		parcouru = Integer.MAX_VALUE;
		precedent = null;
	}
	
	public Dijkstra_node(GNode node, int parcouru)
	{
		this.node = node;
		this.parcouru = parcouru;
		precedent = null;
	}
}
