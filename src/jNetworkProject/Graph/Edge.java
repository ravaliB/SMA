package jNetworkProject.Graph;

public class Edge {
	public GNode Node1;
	public GNode Node2;
	public int weight;
	public int num_car;
	
	//Une arrete relie deux noeuds et est pondérée
	public Edge(GNode node1, GNode node2, int weight)
	{
		this.Node1 = node1;
		this.Node2 = node2;
		this.weight = weight;
		this.num_car = 0;
	}


}	
