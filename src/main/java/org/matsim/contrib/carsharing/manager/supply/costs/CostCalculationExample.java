package org.matsim.contrib.carsharing.manager.supply.costs;

import org.matsim.contrib.carsharing.js.JavaScriptCalculator;
import org.matsim.contrib.carsharing.manager.PropertyManager;
import org.matsim.contrib.carsharing.manager.demand.RentalInfo;
import org.matsim.core.config.Config;

import java.util.Properties;

/**
 * @author balac
 */
public class CostCalculationExample implements CostCalculation {


	private double priceBaseDriving;
	private double priceBaseStop;
	private Config config;
	private String pricing;



	public CostCalculationExample(Config config) {
		super();
		this.config = config;
		//gets  dynamicPricing, priceHighRateHorizontal and priceLowRateHorizontal values from configration file
		pricing = this.config.getModules().get("TwoWayCarsharing").getParams().get("pricing");
		priceBaseDriving = Double.parseDouble(this.config.getModules().get("TwoWayCarsharing").getParams().get("priceBaseDriving"));
		priceBaseStop = Double.parseDouble(this.config.getModules().get("TwoWayCarsharing").getParams().get("priceBaseStop"));
	}


	@Override
	public double getCost(RentalInfo rentalInfo, PropertyManager propertyManager, JavaScriptCalculator javaScriptCalculator) {
		Properties properties = propertyManager.getAppExaProperties();
		String script = properties.getProperty("jsFunc.price.name");
		Prices prices = new Prices(pricing, priceBaseDriving, priceBaseStop);
		return (double) javaScriptCalculator.calculate(script, rentalInfo, prices);

	}

}
