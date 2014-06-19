package jNetworkProject.Dijkstra;

import jNetworkProject.Graph.GNode;
import jNetworkProject.Monde.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dijkstra {
	

	
	public Dijkstra()
	{

	}

	public List<GNode> get_shortest_path(GNode src, GNode dst)
	{
		ArrayList<Dijkstra_node> nodes = new ArrayList<Dijkstra_node>();
		Dijkstra_node debut = new Dijkstra_node(src, 0);
		Dijkstra_node fin = new Dijkstra_node(dst);
		
		for (GNode n : World.graph.sommets)
		{
			if (n == src)
				nodes.add(debut);
			else if (n == dst)
				nodes.add(fin);
			else
				nodes.add(new Dijkstra_node(n));
		}
		
		while (!nodes.isEmpty())
		{
			Dijkstra_node n1 = getMinWeight(nodes);
			nodes.remove(n1);
			for (Dijkstra_node n2 : getSons(n1, nodes))
			{
				if (n2.parcouru > n1.parcouru + getDistance(n1, n2))
				{
					n2.parcouru = n1.parcouru + getDistance(n1, n2);
					n2.precedent = n1;
				}
			}
				
		}
		
		ArrayList<GNode> path = new ArrayList<GNode>();
		Dijkstra_node n = fin;
		while (n != debut)
		{
			path.add(n.node);
			n = n.precedent;
		}
		path.add(debut.node);
		Collections.reverse(path);
		
				
		
		return path;
		
	}
	
	private int getDistance(Dijkstra_node n1, Dijkstra_node n2)
	{
		int weight = World.graph.getArc(n1.node, n2.node).weight;
		int num_car = World.graph.getArc(n1.node, n2.node).num_car;
		
			
		//Si il y a plus de 20m par voiture : pas d'embouteillage
		if (num_car == 0 || weight / num_car > 7 )
		//if (num_car == 0 || weight * 1000 / num_car > 20)
		{
			return weight;
		}
		else
		{
			int car_per_meter = weight * 2 / num_car; // weight * 1000 / num_car;	
			return (weight + 10);
			//return weight + (20 - car_per_meter)*20;
		}
			
	}
	
	private Dijkstra_node getMinWeight(List<Dijkstra_node> nodes)
	{
		Dijkstra_node min = null;
		
		for (Dijkstra_node n : nodes)
		{
			if (min == null || min.parcouru > n.parcouru)
				min = n;
		}
		return min;
	}
	
	private List<Dijkstra_node> getSons(Dijkstra_node node, ArrayList<Dijkstra_node> nodes_list)
	{
		List<Dijkstra_node> fils = new ArrayList<Dijkstra_node>();
		List<GNode> fils_gnode = World.graph.getSons(node.node);
		for (Dijkstra_node n : nodes_list)
		{
			if (fils_gnode.contains(n.node))
				fils.add(n);
		}
		
		return fils;
	}
}
