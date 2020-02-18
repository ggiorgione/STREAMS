package org.matsim.contrib.carsharing.config;

import org.matsim.core.config.ReflectiveConfigGroup;

/**
 * @author balac
 */

public class OneWayCarsharingConfigGroup extends ReflectiveConfigGroup{
	public static final String GROUP_NAME = "OneWayCarsharing";
	
	private String travelingOneWayCarsharing = null;

	private String constantCarsharing = null;
	
	private String vehiclelocationsInputFile = null;
	
	private Double searchDistance = null;
	
	private String rentalPriceTimeOneWayCarsharing = null;
	
	private String timeFeeOneWayCarsharing = null;
	
	private String timeParkingFeeOneWayCarsharing = null;

	private String distanceFeeOneWayCarsharing = null;
	
	private boolean useOneWayCarsharing = false;

	private String betaVotCarsharing = null;

	private String activateVot = "false";

	private String activateAvailCars = "false";

	private String pricing = null;

	private Double priceBaseDriving = null;

	private Double priceBaseStop = null;

	private boolean enableOplyPricePolicy;
	
	public OneWayCarsharingConfigGroup() {
		super(GROUP_NAME);
	}
		
	@StringGetter( "travelingOneWayCarsharing" )
	public String getUtilityOfTravelling() {
		return this.travelingOneWayCarsharing;
	}

	@StringSetter( "travelingOneWayCarsharing" )
	public void setUtilityOfTravelling(final String travelingOneWayCarsharing) {
		this.travelingOneWayCarsharing = travelingOneWayCarsharing;
	}

	@StringGetter( "constantCarsharing" )
	public String constantOneWayCarsharing() {
		return this.constantCarsharing;
	}

	@StringSetter( "constantCarsharing" )
	public void setConstantCarsharing(final String constantCarsharing) {
		this.constantCarsharing = constantCarsharing;
	}
	
	@StringGetter( "rentalPriceTimeOneWayCarsharing" )
	public String getRentalPriceTimeOneWayCarsharing() {
		return this.rentalPriceTimeOneWayCarsharing;
	}

	@StringSetter( "rentalPriceTimeOneWayCarsharing" )
	public void setRentalPriceTimeOneWayCarsharing(final String rentalPriceTimeOneWayCarsharing) {
		this.rentalPriceTimeOneWayCarsharing = rentalPriceTimeOneWayCarsharing;
	}
	
	@StringGetter( "vehiclelocationsOneWayCarsharing" )
	public String getvehiclelocations() {
		return this.vehiclelocationsInputFile;
	}

	@StringSetter( "vehiclelocationsOneWayCarsharing" )
	public void setvehiclelocations(final String vehiclelocationsInputFile) {
		this.vehiclelocationsInputFile = vehiclelocationsInputFile;
	}
	
	@StringGetter( "searchDistanceOneWayCarsharing" )
	public Double getsearchDistance() {
		return this.searchDistance;
	}

	@StringSetter( "searchDistanceOneWayCarsharing" )
	public void setsearchDistance(final String searchDistance) {
		this.searchDistance = Double.parseDouble(searchDistance);
	}
	
	@StringGetter( "timeFeeOneWayCarsharing" )
	public String timeFeeOneWayCarsharing() {
		return this.timeFeeOneWayCarsharing;
	}

	@StringSetter( "timeFeeOneWayCarsharing" )
	public void setTimeFeeOneWayCarsharing(final String timeFeeOneWayCarsharing) {
		this.timeFeeOneWayCarsharing = timeFeeOneWayCarsharing;
	}
	
	@StringGetter( "timeParkingFeeOneWayCarsharing" )
	public String timeParkingFeeOneWayCarsharing() {
		return this.timeParkingFeeOneWayCarsharing;
	}

	@StringSetter( "timeParkingFeeOneWayCarsharing" )
	public void setTimeParkingFeeOneWayCarsharing(final String timeParkingFeeOneWayCarsharing) {
		this.timeParkingFeeOneWayCarsharing = timeParkingFeeOneWayCarsharing;
	}
	
	@StringGetter( "distanceFeeOneWayCarsharing" )
	public String distanceFeeOneWayCarsharing() {
		return this.distanceFeeOneWayCarsharing;
	}

	@StringSetter( "distanceFeeOneWayCarsharing" )
	public void setDistanceFeeOneWayCarsharing(final String distanceFeeOneWayCarsharing) {
		this.distanceFeeOneWayCarsharing = distanceFeeOneWayCarsharing;
	}
	
	@StringGetter( "useOneWayCarsharing" )
	public boolean useOneWayCarsharing() {
		return this.useOneWayCarsharing;
	}

	@StringSetter( "useOneWayCarsharing" )
	public void setUseOneWayCarsharing(final boolean useOneWayCarsharing) {
		this.useOneWayCarsharing = useOneWayCarsharing;
	}

	@StringGetter( "betaVotCarsharing" )
	public String getBetaVotCarsharing() {
		return betaVotCarsharing;
	}

	@StringSetter( "betaVotCarsharing" )
	public void setBetaVotCarsharing(String betaVotCarsharing) {
		this.betaVotCarsharing = betaVotCarsharing;
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

	@StringGetter( "enableOplyPricePolicy" )
	public boolean enableOplyPricePolicy() {
		return this.enableOplyPricePolicy;
	}

	@StringSetter( "enableOplyPricePolicy" )
	public void setEnableOplyPricePolicy(boolean enableOplyPricePolicy) {
		this.enableOplyPricePolicy = enableOplyPricePolicy;
	}
}
