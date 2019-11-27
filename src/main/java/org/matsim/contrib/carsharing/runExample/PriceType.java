package org.matsim.contrib.carsharing.runExample;

public enum PriceType {
    TIME_BASE("timeBase"),
    AVAIL_BASE("availabilityBase"),
    FIXED("fixed");

    private String priceType;

    PriceType(String priceType){
        this.priceType = priceType;
    }

    public String getPriceType() {
        return priceType;
    }
}
