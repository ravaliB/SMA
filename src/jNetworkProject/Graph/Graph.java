package jNetworkProject.Graph;

import java.util.ArrayList;
import java.util.HashSet;

public class Graph {
	public ArrayList<Edge> edges;
	public ArrayList<GNode> sommets;
	
	public Graph()
	{
		edges = new ArrayList<Edge>();
		sommets = new ArrayList<GNode>();
	}
	
	public void addNode(GNode node)
	{
		if (sommets.contains(node))
			System.err.println("ERREUR : addNode : Sommets déjà ajouté");
		
		sommets.add(node);
	}
	
	public void addArc(Edge arc)
	{
		if (!sommets.contains(arc.Node1) || !sommets.contains(arc.Node2))
			System.err.println("ERREUR : addArc : Sommets inconnus");
		
		edges.add(arc);
	}
	
	//Retourne tous les successeurs d'un arc
	public ArrayList<GNode> getSons(GNode src)
	{
		ArrayList<GNode> result = new ArrayList<GNode>();
		
		for (Edge edge : edges)
		{
			if (edge.Node1.id == src.id)
			{
				result.add(edge.Node2);
			}
			else if (edge.Node2.id == src.id)
			{
				result.add(edge.Node1);
			}
		}
		return result;
	}
	
	//Retourne le premier arc trouvé entre 2 nodes
	public Edge getArc(GNode src, GNode dst)
	{
		if (src == dst)
			return null;
		
		for (Edge edge : edges)
		{
			if ((edge.Node1 == src) && (edge.Node2 == dst))
			{
				return edge;
			}
			else if ((edge.Node1.id == dst.id) && (edge.Node2.id == src.id))
			{
				return edge;
			}
		}
		
		return null;
	}
	
	//Retourne tous arcs entre 2 nodes
	public ArrayList<Edge> getArcs(GNode src, GNode dst)
	{
		ArrayList<Edge> result = new ArrayList<Edge>();
		if (src == dst)
			return result;
		
		for (Edge edge : edges)
		{
			if ((edge.Node1 == src) && (edge.Node2 == dst))
			{
				result.add(edge);
			}
			else if ((edge.Node1 == dst) && (edge.Node2 == src))
			{
				result.add(edge);
			}
		}
		return result;
	}
	
}
