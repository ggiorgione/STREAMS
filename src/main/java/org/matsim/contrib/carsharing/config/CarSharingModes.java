package org.matsim.contrib.carsharing.config;

import java.util.stream.Stream;

public enum CarSharingModes {
    ONEWAY("oneway", "OneWayCarsharing"),
    TWOWAY("twoway","TwoWayCarsharing"),
    FREEFLOATING("freefloating","FreeFloating");

    private final String legMode;
    private String carSharingType;

    CarSharingModes(String legMode, String carSharingType) {
        this.legMode = legMode;
        this.carSharingType = carSharingType;
    }

    public String getLegMode() {
        return legMode;
    }

    public String getCarSharingType() {
        return carSharingType;
    }

    public static String getCarShsringType(String mode) {
        return Stream.of(CarSharingModes.values()).filter(m -> m.getLegMode().equals(mode))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Unexpected value: " + mode))
                .getCarSharingType();
    }
}
