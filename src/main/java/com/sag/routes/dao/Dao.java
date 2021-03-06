package com.sag.routes.dao;

import java.util.List;

import com.sag.routes.model.BusDetails;
import com.sag.routes.model.Route;
import com.sag.routes.model.TrainDetails;

//CRUD Operations

public interface Dao {

	// Route DAO

	// Get all available routes
	List<Route> getAllRoutes();

	// Get Bus route number for given source and destination
	public List<Object> getBusRoute(String source, String destination);

	// Get routes based on ID
	Route getRouteById(int routeId);

	void addRoute(Route route);

	void updateRoute(Route route);

	void deleteRoute(int routeId);

	// Check whether route ID exists or not to create a new route to avoid
	// duplication
	boolean routeExists(String source, String destination);

	// Bus DAO
	// Get all available bus number
	List<BusDetails> getAllBusDetails();

	// Get bus number based on ID
	BusDetails getBusDetailsById(int busDetailsId);

	void addBusDetails(BusDetails busDetails);

	void updateBusDetails(BusDetails busDetails);

	void deleteBusDetails(int busDetailsId);

	// Check whether bus ID exists or not to create a new bus number to avoid
	// duplication
	boolean busDetailsExists(String route_Num);
	
	
	
	// Train DAO

		// Get all available routes
		public List<Object> getAllTrainDetails();

		// Get Train timings for given source and destination
		public List<Object> getTrainRoute(String source, String destination);

		// Get routes based on ID
		TrainDetails getTrainDetailsById(int trainId);

		void addTrainDetails(TrainDetails trainDetails);

		void updateTrainDetails(TrainDetails trainDetails);

		void deleteTrainDetails(int trainDetailsId);
		// Check whether route ID exists or not to create a new route to avoid
		// duplication
		boolean trainDetailsExists(String source, String destination,String route,long time,String type);


}
