package org.matsim.contrib.carsharing.manager.supply.costs;

import java.util.HashMap;
import java.util.Map;

import org.matsim.contrib.carsharing.js.JavaScriptCalculator;
import org.matsim.contrib.carsharing.manager.PropertyManager;
import org.matsim.contrib.carsharing.manager.demand.RentalInfo;
/** 
 * @author balac
 */
public class CompanyCosts {	
	
	private Map<String, CostCalculation> perTypeCostCalculator = new HashMap<String, CostCalculation>();
	
	public CompanyCosts(Map<String, CostCalculation> perTypeCostCalculator) {
		
		this.perTypeCostCalculator = perTypeCostCalculator;
	}
	
	public double calcCost(String carsharingType, RentalInfo rentalInfo) {
		
		return this.perTypeCostCalculator.get(carsharingType).getCost(rentalInfo);
	}


	public double calcCost(String carsharingType, RentalInfo rentalInfo, PropertyManager propertyManager, JavaScriptCalculator javaScriptCalculator) {

		return this.perTypeCostCalculator.get(carsharingType).getCost(rentalInfo, propertyManager, javaScriptCalculator);
	}

}
