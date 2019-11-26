package org.matsim.contrib.carsharing.scoring;

import org.matsim.api.core.v01.events.Event;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.population.Leg;
import org.matsim.api.core.v01.population.Person;
import org.matsim.contrib.carsharing.js.JavaScriptCalculator;
import org.matsim.contrib.carsharing.manager.PropertyManager;
import org.matsim.contrib.carsharing.manager.demand.AgentRentals;
import org.matsim.contrib.carsharing.manager.demand.DemandHandler;
import org.matsim.contrib.carsharing.manager.demand.RentalInfo;
import org.matsim.contrib.carsharing.manager.supply.CarsharingSupplyInterface;
import org.matsim.contrib.carsharing.manager.supply.TwoWayContainer;
import org.matsim.contrib.carsharing.manager.supply.costs.CostsCalculatorContainer;
import org.matsim.contrib.carsharing.stations.CarsharingStation;
import org.matsim.contrib.carsharing.stations.TwoWayCarsharingStation;
import org.matsim.contrib.carsharing.vehicles.CSVehicle;
import org.matsim.contrib.carsharing.vehicles.StationBasedVehicle;
import org.matsim.core.config.Config;
import org.matsim.core.config.groups.PlanCalcScoreConfigGroup;
import org.matsim.core.scoring.functions.ScoringParameters;

import java.util.Properties;


public class CarsharingLegScoringFunction extends org.matsim.core.scoring.functions.CharyparNagelLegScoring {

	
	private Config config;	
	
	private CostsCalculatorContainer costsCalculatorContainer;
	private DemandHandler demandHandler;
	private Person person;
	private CarsharingSupplyInterface carsharingSupplyContainer;
	private PropertyManager propertyManager;
	private JavaScriptCalculator javaScriptCalculator;

	public CarsharingLegScoringFunction(ScoringParameters params, 
			Config config,  Network network, DemandHandler demandHandler,
			CostsCalculatorContainer costsCalculatorContainer, CarsharingSupplyInterface carsharingSupplyContainer,
			Person person)
	{
		super(params, network);
		this.config = config;
		this.demandHandler = demandHandler;
		this.carsharingSupplyContainer = carsharingSupplyContainer;
		this.costsCalculatorContainer = costsCalculatorContainer;
		this.person = person;		
	}

	public CarsharingLegScoringFunction(ScoringParameters params,
										Config config,  Network network, DemandHandler demandHandler,
										CostsCalculatorContainer costsCalculatorContainer, CarsharingSupplyInterface carsharingSupplyContainer,
										Person person,
										PropertyManager propertyManager,
										JavaScriptCalculator javaScriptCalculator)
	{
		super(params, network);
		this.config = config;
		this.demandHandler = demandHandler;
		this.carsharingSupplyContainer = carsharingSupplyContainer;
		this.costsCalculatorContainer = costsCalculatorContainer;
		this.person = person;
		this.propertyManager = propertyManager;
		this.javaScriptCalculator = javaScriptCalculator;

	}

	@Override
	public void handleEvent(Event event) {
		super.handleEvent(event);		
	}

	@Override
	public void finish() {
		super.finish();

		AgentRentals agentRentals = this.demandHandler.getAgentRentalsMap().get(person.getId());
		if (agentRentals != null) {
			double marginalUtilityOfMoney = ((PlanCalcScoreConfigGroup)this.config.getModules().get("planCalcScore")).getMarginalUtilityOfMoney();
			for(RentalInfo rentalInfo : agentRentals.getArr()) {
				CSVehicle vehicle = this.carsharingSupplyContainer.getAllVehicles().get(rentalInfo.getVehId().toString());

				//adds constant to score
				score += Double.parseDouble(this.config.getModules().get("TwoWayCarsharing").getParams().get("constantTwoWayCarsharing"));

				//Gets the configuration value of person VOT disabling/enabling
				boolean activateVot = Boolean.parseBoolean(this.config.getModules().get("TwoWayCarsharing").getParams().get("activateVot"));

				//Calculates person Vot is it is not disabled in config.xml
				if(activateVot) {

					double personVoT = 1;
					if( person.getAttributes().getAttribute("vot") != null) {
						personVoT = (double) person.getAttributes().getAttribute("vot");
					}

					//Beta VOT form config.xml
					double betaVot = Double.parseDouble(this.config.getModules().get("TwoWayCarsharing").getParams().get("votTwoWayCarsharing"));

					//adds personVot to score
					score +=  betaVot * personVoT;
				}



				int availCars = 0;

				//Gets the configuration value of available cars disabling/enabling
				boolean activateAvailCars = Boolean.parseBoolean(this.config.getModules().get("TwoWayCarsharing").getParams().get("activateAvailCars"));

				//Calculates value of available cars if it is not disabled in config.xml
				//if(activateAvailCars) {

				//get the station of the nearest vehicle to the person
				CarsharingStation nearestStation = ((TwoWayContainer) this.carsharingSupplyContainer
						.getCompany(vehicle.getCompanyId()).getVehicleContainer("twoway"))
						.getTwowaycarsharingstationsMap()
						.get(((StationBasedVehicle) vehicle).getStationId());

				//gets the number of available cars in the nearest station
				availCars = ((TwoWayCarsharingStation) nearestStation).getNumberOfVehicles(vehicle.getType());
				//}
				
				String pricing = this.config.getModules().get("TwoWayCarsharing").getParams().get("pricing");
				
				double cost = this.costsCalculatorContainer.getCost(vehicle.getCompanyId(), rentalInfo.getCarsharingType(), rentalInfo, propertyManager, javaScriptCalculator);
				
				if(pricing.equals("vertical") || (pricing.equals("horizontal") && activateAvailCars)) {
					Properties properties = propertyManager.getAppExaProperties();
					String script = properties.getProperty("jsFunc.availability.name");
					cost = (double) javaScriptCalculator.calculate(script, cost, availCars);
				}
				rentalInfo.setTripCost(cost);


				if (marginalUtilityOfMoney != 0.0) {
					//adds the cost per time and distance over number of available cars
					score += -1 * (cost * marginalUtilityOfMoney); //here the cost becomes negative for the scoring part

				}

			}
		}
	}
	
	@Override
	protected double calcLegScore(double departureTime, double arrivalTime, Leg leg) {
		
		
		double tmpScore = 0.0D;

		return tmpScore;
	}


}
