package org.matsim.contrib.carsharing.manager.supply.costs;

public class Prices {

    private String pricing;

    private double priceBaseDriving, priceBaseStop, priceHighRateHorizontal, priceLowRateHorizontal;

    public Prices(String pricing, double priceBaseDriving, double priceBaseStop, double priceHighRateHorizontal, double priceLowRateHorizontal) {
        this.pricing = pricing;
        this.priceBaseDriving = priceBaseDriving;
        this.priceBaseStop = priceBaseStop;
        this.priceHighRateHorizontal = priceHighRateHorizontal;
        this.priceLowRateHorizontal = priceLowRateHorizontal;
    }

    public String getPricing() {
        return pricing;
    }

    public void setPricing(String pricing) {
        this.pricing = pricing;
    }

    public double getPriceBaseDriving() {
        return priceBaseDriving;
    }

    public void setPriceBaseDriving(double priceBaseDriving) {
        this.priceBaseDriving = priceBaseDriving;
    }

    public double getPriceBaseStop() {
        return priceBaseStop;
    }

    public void setPriceBaseStop(double priceBaseStop) {
        this.priceBaseStop = priceBaseStop;
    }

    public double getPriceHighRateHorizontal() {
        return priceHighRateHorizontal;
    }

    public void setPriceHighRateHorizontal(double priceHighRateHorizontal) {
        this.priceHighRateHorizontal = priceHighRateHorizontal;
    }

    public double getPriceLowRateHorizontal() {
        return priceLowRateHorizontal;
    }

    public void setPriceLowRateHorizontal(double priceLowRateHorizontal) {
        this.priceLowRateHorizontal = priceLowRateHorizontal;
    }
}



