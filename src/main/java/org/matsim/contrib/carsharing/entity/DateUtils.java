package org.matsim.contrib.carsharing.entity;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static java.time.ZoneOffset.UTC;

public class DateUtils {

    public static final String DATE_TIME_PATTERN = "dd-MM-yyyy hh:mm:ss";

    public static final String DATE_TIME_MILLIS_PATTERN_1 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static final String DATE_TIME_MILLIS_PATTERN_2 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private static final DateTimeFormatter FORMATTER_MILLIS = DateTimeFormatter.ofPattern
            (DATE_TIME_MILLIS_PATTERN_1).withZone(ZoneId.systemDefault());

    public static long doubleTime2CurrentLongTime(long statingSimulationTime, double currentDoubleTime){
        return statingSimulationTime + ((long) (currentDoubleTime * 1000));
    }

    public static String instant2String(Instant instant) {
        return instant.atOffset(UTC).toLocalDateTime().format(FORMATTER_MILLIS);
    }

}
