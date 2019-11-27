package org.matsim.contrib.carsharing.manager.supply.costs;

public class Prices {

    private String pricing;

    private double priceBaseDriving, priceBaseStop;

    public Prices(String pricing, double priceBaseDriving, double priceBaseStop) {
        this.pricing = pricing;
        this.priceBaseDriving = priceBaseDriving;
        this.priceBaseStop = priceBaseStop;
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

}



