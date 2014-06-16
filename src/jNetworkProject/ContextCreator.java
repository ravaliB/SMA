package jNetworkProject;

import jNetworkProject.Monde.World;
import repast.simphony.context.Context;
import repast.simphony.context.space.continuous.ContinuousSpaceFactory;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.RandomCartesianAdder;

public class ContextCreator implements ContextBuilder<Object> {

	@Override
	public Context<Object> build(Context<Object> context) {
		context.setId("JNetworkProject");


		ContinuousSpaceFactory spaceFactory = 
				ContinuousSpaceFactoryFinder.createContinuousSpaceFactory(null);
		
		ContinuousSpace<Object> space =
				spaceFactory.createContinuousSpace("space", context,
						new RandomCartesianAdder<Object>(),
						new repast.simphony.space.continuous.WrapAroundBorders(),
						50,50);
	
		ScaleFreeNetworkContext sfnc = new ScaleFreeNetworkContext(context);
		sfnc.buildNetwork();
		
		World.init(sfnc.getGraph());
		
		RoadContext rctxt = new RoadContext(context, space);
		//rctxt.addCar(2);
		rctxt.addCar(1, 6);
		rctxt.addCar(3, 1);
		System.out.println("return context");
		return context;
	}
}
