package org.matsim.contrib.carsharing.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OTANetworkInformation {

    private Integer systemMode;

    private Integer registration;

    private Integer rssi;

    private Integer bitErrorRate;


    public Integer getSystemMode() {
        return systemMode;
    }

    public void setSystemMode(Integer systemMode) {
        this.systemMode = systemMode;
    }

    public Integer getRegistration() {
        return registration;
    }

    public void setRegistration(Integer registration) {
        this.registration = registration;
    }

    public Integer getRssi() {
        return rssi;
    }

    public void setRssi(Integer rssi) {
        this.rssi = rssi;
    }

    public Integer getBitErrorRate() {
        return bitErrorRate;
    }

    public void setBitErrorRate(Integer bitErrorRate) {
        this.bitErrorRate = bitErrorRate;
    }
}
