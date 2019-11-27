package org.matsim.contrib.carsharing.config;

import org.matsim.core.config.ReflectiveConfigGroup;
/** 
 * @author balac
 */

public class TwoWayCarsharingConfigGroup extends ReflectiveConfigGroup {
	public static final String GROUP_NAME = "TwoWayCarsharing";
	
	private String travelingTwoWayCarsharing = null;
	
	private String constantTwoWayCarsharing = null;
	
	private String vehiclelocationsInputFile = null;
	
	private Double searchDistance = null;
	
	private String rentalPriceTimeTwoWayCarsharing = null;
	
	private String timeFeeTwoWayCarsharing = null;
	
	private String distanceFeeTwoWayCarsharing = null;
	
	private boolean useTwoWayCarsharing = false;
	
	private String votTwoWayCarsharing = null;

	private String activateVot = "false";

	private String activateAvailCars = "false";

	private String pricing = null;

	private Double priceBaseDriving = null;

	private Double priceBaseStop = null;


	public TwoWayCarsharingConfigGroup() {
		super(GROUP_NAME);
	}

	@StringGetter( "travelingTwoWayCarsharing" )
	public String getUtilityOfTravelling() {
		return this.travelingTwoWayCarsharing;
	}

	@StringSetter( "travelingTwoWayCarsharing" )
	public void setUtilityOfTravelling(final String travelingTwoWayCarsharing) {
		this.travelingTwoWayCarsharing = travelingTwoWayCarsharing;
	}

	@StringGetter( "constantTwoWayCarsharing" )
	public String constantTwoWayCarsharing() {
		return this.constantTwoWayCarsharing;
	}

	@StringSetter( "constantTwoWayCarsharing" )
	public void setConstantTwoWayCarsharing(final String constantTwoWayCarsharing) {
		this.constantTwoWayCarsharing = constantTwoWayCarsharing;
	}
	
	@StringGetter( "rentalPriceTimeTwoWayCarsharing" )
	public String getRentalPriceTimeTwoWayCarsharing() {
		return this.rentalPriceTimeTwoWayCarsharing;
	}

	@StringSetter( "rentalPriceTimeTwoWayCarsharing" )
	public void setRentalPriceTimeTwoWayCarsharing(final String rentalPriceTimeTwoWayCarsharing) {
		this.rentalPriceTimeTwoWayCarsharing = rentalPriceTimeTwoWayCarsharing;
	}
	
	@StringGetter( "vehiclelocationsTwoWayCarsharing" )
	public String getvehiclelocations() {
		return this.vehiclelocationsInputFile;
	}

	@StringSetter( "vehiclelocationsTwoWayCarsharing" )
	public void setvehiclelocations(final String vehiclelocationsInputFile) {
		this.vehiclelocationsInputFile = vehiclelocationsInputFile;
	}
	
	@StringGetter( "searchDistanceTwoWayCarsharing" )
	public Double getsearchDistance() {
		return this.searchDistance;
	}

	@StringSetter( "searchDistanceTwoWayCarsharing" )
	public void setsearchDistance(final String searchDistance) {
		this.searchDistance = Double.parseDouble(searchDistance);
	}
	
	@StringGetter( "timeFeeTwoWayCarsharing" )
	public String timeFeeTwoWayCarsharing() {
		return this.timeFeeTwoWayCarsharing;
	}

	@StringSetter( "timeFeeTwoWayCarsharing" )
	public void setTimeFeeTwoWayCarsharing(final String timeFeeTwoWayCarsharing) {
		this.timeFeeTwoWayCarsharing = timeFeeTwoWayCarsharing;
	}
	
	@StringGetter( "distanceFeeTwoWayCarsharing" )
	public String distanceFeeTwoWayCarsharing() {
		return this.distanceFeeTwoWayCarsharing;
	}

	@StringSetter( "distanceFeeTwoWayCarsharing" )
	public void setDistanceFeeTwoWayCarsharing(final String distanceFeeTwoWayCarsharing) {
		this.distanceFeeTwoWayCarsharing = distanceFeeTwoWayCarsharing;
	}
	
	@StringGetter( "useTwoWayCarsharing" )
	public boolean useTwoWayCarsharing() {
		return this.useTwoWayCarsharing;
	}

	@StringSetter( "useTwoWayCarsharing" )
	public void setUseTwoWayCarsharing(final boolean useTwoWayCarsharing) {
		this.useTwoWayCarsharing = useTwoWayCarsharing;
	}

	@StringGetter( "votTwoWayCarsharing" )
	public String getVotTwoWayCarsharing() {
		return votTwoWayCarsharing;
	}

	@StringSetter( "votTwoWayCarsharing" )
	public void setVotTwoWayCarsharing(String votTwoWayCarsharing) {
		this.votTwoWayCarsharing = votTwoWayCarsharing;
	}

	@StringGetter( "activateVot" )
	public String getActivateVot() {
		return activateVot;
	}
	@StringSetter( "activateVot" )
	public void setActivateVot(String activateVot) {
		this.activateVot = activateVot;
	}
	
	@StringGetter( "activateAvailCars" )
	public String getActivateAvailCars() {
		return activateAvailCars;
	}
	@StringSetter( "activateAvailCars" )
	public void setActivateAvailCars(String activateAvailCars) {
		this.activateAvailCars = activateAvailCars;
	}

	@StringGetter( "pricing" )
	public String getPricing() {
		return pricing;
	}

	@StringSetter( "pricing" )
	public void setPricing(String pricing) {
		this.pricing = pricing;
	}

	@StringGetter( "priceBaseDriving" )
	public Double getPriceBaseDriving() {
		return priceBaseDriving;
	}

	@StringSetter( "priceBaseDriving" )
	public void setPriceBaseDriving(Double priceBaseDriving) {
		this.priceBaseDriving = priceBaseDriving;
	}

	@StringGetter( "priceBaseStop" )
	public Double getPriceBaseStop() {
		return priceBaseStop;
	}

	@StringSetter( "priceBaseStop" )
	public void setPriceBaseStop(Double priceBaseStop) {
		this.priceBaseStop = priceBaseStop;
	}



}

