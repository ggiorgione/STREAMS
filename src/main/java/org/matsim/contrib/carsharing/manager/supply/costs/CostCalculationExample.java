package org.matsim.contrib.carsharing.manager.supply.costs;

import java.text.DecimalFormat;

import org.matsim.contrib.carsharing.manager.demand.RentalInfo;
import org.matsim.contrib.carsharing.manager.supply.CarsharingSupplyInterface;
import org.matsim.contrib.carsharing.manager.supply.TwoWayContainer;
import org.matsim.contrib.carsharing.stations.CarsharingStation;
import org.matsim.contrib.carsharing.stations.TwoWayCarsharingStation;
import org.matsim.contrib.carsharing.vehicles.CSVehicle;
import org.matsim.contrib.carsharing.vehicles.StationBasedVehicle;
import org.matsim.core.config.Config;

import com.google.inject.Inject;

/**
 * @author balac
 */
public class CostCalculationExample implements CostCalculation {

	

	private final static double scaleTOMatchCar = 1.0; // 0.325; SCENDERE E VEDERE CHE SUCCEDE
	private final static double distCost = 0;
	private static double priceBaseDriving = 0.3;
	private static double priceBaseStop = 0.3;
	@Inject private CarsharingSupplyInterface carsharingSupplyContainer;

	Config config;
	String pricing = "base";
	double priceHighRateHorizontal = 0.3;
	double priceLowRateHorizontal = 0.15;

	DecimalFormat df = new DecimalFormat("#.##");   

	public CostCalculationExample(Config config) {
		super();
		this.config = config;
		//gets  dynamicPricing, priceHighRateHorizontal and priceLowRateHorizontal values from configration file
		pricing = this.config.getModules().get("TwoWayCarsharing").getParams().get("pricing");
		priceBaseDriving = Double.parseDouble(this.config.getModules().get("TwoWayCarsharing").getParams().get("priceBaseDriving"));
		priceBaseStop = Double.parseDouble(this.config.getModules().get("TwoWayCarsharing").getParams().get("priceBaseStop"));
		priceHighRateHorizontal = Double.parseDouble(this.config.getModules().get("TwoWayCarsharing").getParams().get("priceHighRateHorizontal"));
		priceLowRateHorizontal = Double.parseDouble(this.config.getModules().get("TwoWayCarsharing").getParams().get("priceLowRateHorizontal"));
	}
	// COST here ct = 0.20 and cd = 0

	@Override
	public double getCost(RentalInfo rentalInfo) {
		
		double ST = rentalInfo.getStartTime();
		double ET = rentalInfo.getEndTime();

		double costLu = 0;
		
		//If horizontal pricing
		if (pricing.equals("horizontal")) {
			//Start and end time from seconds to hours
			double ST_Hour = ST / 3600;
			double ET_Hour = ET / 3600;

			// StartTime slot upper bound
			double ST_UB = getTimeSlotBound(ST_Hour, "upper");

			// Time until next slot in hour
			double LB = ST_UB - ST_Hour;

			//Start time slot cost
			double ST_SlotCost = getTimeSlotCost(ST_Hour);
			
			// Cost until the end of the first time slot
			double Cb = LB * 60 * ST_SlotCost;

			// EndTime slot Lower bound
			double ET_LB = getTimeSlotBound(ET_Hour, "lower");

			// Time until next slot in hour
			double UB = ET_Hour - ET_LB;

			//End time slot cost
			double ET_SlotCost = getTimeSlotCost(ET_Hour);
			
			// Cost until the end of the first time slot
			double Ce = UB * 60 * ET_SlotCost;

			// Remaining time in hours
			int TI = (int) Math.abs(ET_LB - ST_UB);
			
			//Ci
			double Ci = 0.0;
			if((TI/3) % 2 == 0) {
				Ci = ((TI/2)*60)*priceHighRateHorizontal+((TI/2)*60)*priceLowRateHorizontal;
			}else if((TI/3) == 1.0) {
				if(ST_SlotCost == priceHighRateHorizontal) {
					Ci = TI*60*priceLowRateHorizontal;
				}else {
					Ci = TI*60*priceLowRateHorizontal;
				}
			}else if(TI == 0){
				Ci = 0;
			}else {
				Ci = (((TI-1)/2)*60)*priceHighRateHorizontal+(((TI-1)/2)*60)*priceLowRateHorizontal+(1*60*ST_SlotCost);
			}
			costLu = Double.valueOf(df.format(Cb + Ce + Ci));
			
		}//If horizontal pricing is disabled
		else {

			double rentalTime = ET - ST;
			double inVehicleTime = rentalInfo.getInVehicleTime();
			double distance = rentalInfo.getDistance();

			double evalTime = (priceBaseDriving * (inVehicleTime / 60)) + (((rentalTime - inVehicleTime) / 60.0) * priceBaseStop);

			double evalDist = distCost * (distance / 1000);

			costLu = Double.valueOf(df.format(scaleTOMatchCar * (evalTime + evalDist)));

		}

		System.out.println("---------------------------> Cost "+costLu);
		//rentalInfo.setTripCost(costLu);
		//System.out.println(rentalInfo.toString());
		return costLu;

	}


	//get the upper/lower bound of the timeSlot of the input time
	public double getTimeSlotBound(double time, String bound) {

		if (time > 3.0 && time <= 6.0) {
			if (bound == "lower") {
				return 6.1;
			} else {
				return 9.0;
			}
		}else if (time > 6.0 && time <= 9.0) {
			if (bound == "lower") {
				return 6.1;
			} else {
				return 9.0;
			}
		} else if (time > 9.0 && time <= 12.0) {
			if (bound == "lower") {
				return 9.1;
			} else {
				return 12.0;
			}
		} else if (time > 12.0 && time <= 15.0) {
			if (bound == "lower") {
				return 12.1;
			} else {
				return 15.0;
			}
		} else if (time > 15.0 && time <= 18.0) {
			if (bound == "lower") {
				return 15.1;
			} else {
				return 18.0;
			}
		} else if (time > 18.0 && time <= 21.0) {
			if (bound == "lower") {
				return 18.1;
			} else {
				return 21.0;
			}
		}else if (time > 21.0 && time <= 24.0) {
			if (bound == "lower") {
				return 21.1;
			} else {
				return 24.0;
			}
		} else {
			if (bound == "lower") {
				return 0.1;
			} else {
				return 3.0;
			}
		}
	}

	//gets the timeSlot cost 
	public double getTimeSlotCost(double time) {
		if ((time > 18 && time <= 21) || (time > 12 && time <= 15) || (time > 6 && time <= 9)) {
			return priceHighRateHorizontal;
		} else {
			return priceLowRateHorizontal;
		}
	}

}

/*
 * public class CostCalculationExample_new implements CostCalculation {
 * 
 * private final static double betaTT = 1.0; private final static double
 * betaRentalTIme = 1.0; private final static double scaleTOMatchCar = 4.0;
 * 
 * @Override public double getCost(RentalInfo rentalInfo) {
 * 
 * double rentalTIme = rentalInfo.getEndTime() - rentalInfo.getStartTime();
 * double inVehicleTime = rentalInfo.getInVehicleTime();
 * 
 * 
 * return CostCalculationExample_new.scaleTOMatchCar * (inVehicleTime /60.0 *
 * 0.3 + (rentalTIme - inVehicleTime) / 60.0 * 0.15); }
 * 
 * }
 * 
 */
