package jNetworkProject.Monde;

import jNetworkProject.RoadContext;
import repast.simphony.engine.schedule.ScheduledMethod;

public class CarsCreator {

	private int count = 0;
	private RoadContext r;
	
	public CarsCreator(RoadContext road)
	{
		this.r = road;
	}
	
	@ScheduledMethod(start = 1, interval = 5)
	public void step()
	{
		this.r.addCar(4, 1);
	}
}
