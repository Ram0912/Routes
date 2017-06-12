package com.sag.routes.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sag.routes.model.BusDetails;
import com.sag.routes.model.Route;
import com.sag.routes.model.RouteDTO;
import com.sag.routes.model.TrainDetails;
import com.sag.routes.service.ServiceI;

//RestController which contains all REST endpoints
@RestController
@RequestMapping("/bus")     //sample endpoint--- localhost:8080/rest/bus
public class RouteController {

	final static Logger logger = Logger.getLogger(RouteController.class);

	@Autowired
	private ServiceI serviceI;
	

	// Route Controller
	
	/*
	 * @GetMapping--specifies GET method
	 * @PostMapping--specifies POST method
	 * @PutMapping--specifies PUT method
	 * @DeleteMapping--specifies DELETE method
	 */
	@GetMapping("/route/{id}")   //sample endpoint---- localhost:8080/rest/bus/route/{id}
	public ResponseEntity<Route> getRouteById(@PathVariable("id") Integer id) {
		Route route = serviceI.getRouteById(id);
		return new ResponseEntity<Route>(route, HttpStatus.OK);
	}

	@GetMapping("/routes")
	public ResponseEntity<List<Route>> getAllRoutes() {
		List<Route> list = serviceI.getAllRoutes();
		return new ResponseEntity<List<Route>>(list, HttpStatus.OK);
	}

	@PostMapping("/route")
	public ResponseEntity<Void> addRoute(@RequestBody Route route, UriComponentsBuilder builder) {
		boolean flag = serviceI.addRoute(route);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/route/{id}").buildAndExpand(route.getRoute_id()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping("/route")
	public ResponseEntity<Route> updateRoute(@RequestBody Route route) {
		serviceI.updateRoute(route);
		return new ResponseEntity<Route>(route, HttpStatus.OK);
	}

	@DeleteMapping("/route/{id}")
	public ResponseEntity<Void> deleteRoute(@PathVariable("id") Integer id) {
		serviceI.deleteRoute(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/routenumber") // eg:/routenumber?source=velachery&destination=madipakkam
	public List<RouteDTO> getBusRoute(@RequestParam(value = "source", required = true) String source,
			@RequestParam(value = "destination", required = false) String destination) {
		List<RouteDTO> route = serviceI.getBusRoute(source, destination);
		logger.info(source + destination);
		return route;

	}

	// Bus Controller

	@GetMapping("/busdetails/{id}")   //sample endpoint---- localhost:8080/rest/bus/busdetails/{id}
	public ResponseEntity<BusDetails> getBusDetailsById(@PathVariable("id") Integer id) {
		BusDetails busDetails = serviceI.getBusDetailsById(id);
		return new ResponseEntity<BusDetails>(busDetails, HttpStatus.OK);
	}

	@GetMapping("/allbusdetails")
	public ResponseEntity<List<BusDetails>> getAllBusDetails() {
		List<BusDetails> list = serviceI.getAllBusDetails();
		return new ResponseEntity<List<BusDetails>>(list, HttpStatus.OK);
	}

	@PostMapping("/createbusdetails")
	public ResponseEntity<Void> addBusDetails(@RequestBody BusDetails busDetails, UriComponentsBuilder builder) {
		boolean flag = serviceI.addBusDetails(busDetails);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/busdetails/{id}").buildAndExpand(busDetails.getBusDetails_id()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<BusDetails> updateBusDetails(@RequestBody BusDetails busDetails) {
		serviceI.updateBusDetails(busDetails);
		return new ResponseEntity<BusDetails>(busDetails, HttpStatus.OK);
	}

	@DeleteMapping("/deletebusdetails/{id}")
	public ResponseEntity<Void> deleteBusDetails(@PathVariable("id") Integer id) {
		serviceI.deleteBusDetails(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	// Train Controller
	
	@GetMapping("/train/{id}")   //sample endpoint---- localhost:8080/rest/bus/route/{id}
	public ResponseEntity<TrainDetails> getTrainDetailsById(@PathVariable("id") Integer id) {
		TrainDetails train = serviceI.getTrainDetailsById(id);
		return new ResponseEntity<TrainDetails>(train, HttpStatus.OK);
	}

	@GetMapping("/Alltrains")
	public ResponseEntity<List<TrainDetails>> getAllTrainDetails() {
		List<TrainDetails> list = serviceI.getAllTrainDetails();
		return new ResponseEntity<List<TrainDetails>>(list, HttpStatus.OK);
	}

	@PostMapping("/createtrain")
	public ResponseEntity<Void> addTrainDetails(@RequestBody TrainDetails train, UriComponentsBuilder builder) {
		boolean flag = serviceI.addTrainDetails(train);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/train/{id}").buildAndExpand(train.getTrainId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping("/updatetrain")
	public ResponseEntity<TrainDetails> updateTrainDetails(@RequestBody TrainDetails train) {
		serviceI.updateTrainDetails(train);
		return new ResponseEntity<TrainDetails>(train, HttpStatus.OK);
	}

	@DeleteMapping("/deletetrain/{id}")
	public ResponseEntity<Void> deleteTrainDetails(@PathVariable("id") Integer id) {
		serviceI.deleteTrainDetails(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/trainnumber") // eg:/routenumber?source=velachery&destination=madipakkam
	public List<TrainDetails> getTrainRoute(@RequestParam(value = "source", required = true) String source,
			@RequestParam(value = "destination", required = false) String destination) {
		List<TrainDetails> train = serviceI.getTrainRoute(source, destination);
		logger.info(source + destination);
		return train;

	}

	
	}
