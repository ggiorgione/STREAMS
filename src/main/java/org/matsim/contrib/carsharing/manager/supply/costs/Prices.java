package org.matsim.contrib.carsharing.manager.supply.costs;

public class Prices {

    private String pricing;

    private double priceBaseDriving, priceBaseStop;

    private boolean enableOplyPricePolicy;

    public Prices(String pricing, double priceBaseDriving, double priceBaseStop, boolean enableOplyPricePolicy) {
        this.pricing = pricing;
        this.priceBaseDriving = priceBaseDriving;
        this.priceBaseStop = priceBaseStop;
        this.enableOplyPricePolicy = enableOplyPricePolicy;
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

    public boolean isEnableOplyPricePolicy() {
        return enableOplyPricePolicy;
    }

    public void setEnableOplyPricePolicy(boolean enableOplyPricePolicy) {
        this.enableOplyPricePolicy = enableOplyPricePolicy;
    }
}



