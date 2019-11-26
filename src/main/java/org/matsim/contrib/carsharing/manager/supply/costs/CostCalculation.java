package org.matsim.contrib.carsharing.manager.supply.costs;

import org.matsim.contrib.carsharing.js.JavaScriptCalculator;
import org.matsim.contrib.carsharing.manager.PropertyManager;
import org.matsim.contrib.carsharing.manager.demand.RentalInfo;
/** 
 * @author balac
 */
public interface CostCalculation {
	
	default double getCost(RentalInfo rentalInfo){return 0d;}

	public double getCost(RentalInfo rentalInfo, PropertyManager propertyManager, JavaScriptCalculator javaScriptCalculator);

}
