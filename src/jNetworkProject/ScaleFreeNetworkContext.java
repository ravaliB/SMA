package jNetworkProject;

import jNetworkProject.Graph.Edge;
import jNetworkProject.Graph.GNode;
import jNetworkProject.Graph.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import repast.simphony.context.Context;
import repast.simphony.context.space.graph.NetworkBuilder;

public class ScaleFreeNetworkContext {
	private Context<Object> context;
	private Graph g = null;

	public ScaleFreeNetworkContext(Context<Object> context) {
		this.context = context;
		BuildGraph();
	}
	
	private void BuildGraph()
	{
		this.g = new Graph();
		
		GNode A = new GNode(1);
		GNode B = new GNode(2);
		GNode C = new GNode(3);
		GNode D = new GNode(4);
		GNode E = new GNode(5);
		GNode F = new GNode(6);

		Edge AB = new Edge(A, B, 20);
		Edge BC = new Edge(B, C, 30);
		Edge AC = new Edge(A, C, 100);
		Edge CD = new Edge(C, D, 1);
		Edge CE = new Edge(C, E, 10);
		Edge EF = new Edge(E, F, 55);
		Edge CF = new Edge(C, F, 60);
		

		g.addNode(A);
		g.addNode(B);
		g.addNode(C);
		g.addNode(D);
		g.addNode(E);
		g.addNode(F);
		
		g.addArc(AB);
		g.addArc(BC);
		g.addArc(AC);
		g.addArc(CD);
		g.addArc(CE);
		g.addArc(EF);
		g.addArc(CF);
		

		context.add(A);
		context.add(B);
		context.add(C);
		context.add(D);
		context.add(E);
		context.add(F);
		
		context.add(AB);
		context.add(BC);
		context.add(AC);
		context.add(CD);
		context.add(CE);
		context.add(EF);
		context.add(CF);
		
		context.add(g);
	}

	public Graph getGraph()
	{
		return g;
	}
	
	public void buildNetwork() {
		NetworkBuilder<Object> builder = new NetworkBuilder<Object>(
				"NetworkTest", context, true);
		builder.setGenerator(new ScaleFreeNetworkGenerator(context));
		builder.buildNetwork();
	}

	
}