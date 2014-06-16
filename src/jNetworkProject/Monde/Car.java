package jNetworkProject.Monde;

import jNetworkProject.Dijkstra.Dijkstra;
import jNetworkProject.Graph.GNode;

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
	
	private GNode position = null; 
	private GNode destination = null; 
	private GNode next = null;
	
	private NdPoint vect_move = null;
	private int dist_pos_to_next = -1;
	
	private int count = 0;
	private int speed_ = 50;
	
	Dijkstra dijkstra;
	
	public Car(ContinuousSpace<Object> space, GNode pos, GNode dst) {
		this.space = space;
		this.position = pos;
		this.next = pos;
		this.destination = dst;
		this.dijkstra = new Dijkstra();
	}
	

	@ScheduledMethod(start = 1, interval = 1)
	public void step() {
	
		//Si en mouvement
		if (count > 0)
		{
			count--;
			
			NdPoint p1 = space.getLocation(this);
			//On avance 
			NdPoint res = new NdPoint(p1.getX() + 1.0/(float)dist_pos_to_next * vect_move.getX(),
									  p1.getY() + 1.0/(float)dist_pos_to_next * vect_move.getY());
			space.moveTo(this, res.toDoubleArray(null));
			return;
		}
		
	
		if (next == destination)
		{
			System.out.println("VOITURE ARRIVEE");
			return;
		}
		
		World.update_leave_edge(position, next);	
		position = next;
		next = dijkstra.get_shortest_path(position, destination).get(1);
		World.update_enter_edge(position, next);
		
		NdPoint p1 = space.getLocation(position);
		NdPoint p2 = space.getLocation(next);
		vect_move = new NdPoint(p2.getX() - p1.getX(), p2.getY() - p1.getY());
		dist_pos_to_next = World.graph.getArc(position, next).weight;
		count = dist_pos_to_next;
		
		
	}

	
}
