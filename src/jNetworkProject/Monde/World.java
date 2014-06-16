package jNetworkProject.Monde;

import jNetworkProject.Graph.Edge;
import jNetworkProject.Graph.GNode;
import jNetworkProject.Graph.Graph;

import java.util.HashMap;
import java.util.HashSet;

import org.apache.batik.dom.util.HashTable;

public class World {
	public static Graph graph = null;
	public static HashMap<Edge, Integer> cars;
	
	public static void init(Graph g)
	{
		World.graph = g;
	}
	
	public static void update_leave_edge(GNode from, GNode now)
	{
		Edge e = World.graph.getArc(from, now);
		if (e != null)
			e.num_car--;
	}
	
	public static void update_enter_edge(GNode now, GNode to)
	{
		Edge e = World.graph.getArc(now, to);
		if (e != null)
			e.num_car--;
	}
	
}
