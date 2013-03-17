package com.lbi.vaa.tdd.flightstatus;

public class DateRangeConfiguration {

    private final int lower;
    private final int upper;

    public DateRangeConfiguration(int lower, int upper) {
        this.lower = lower;

        this.upper = upper;
    }

    public int getLower() {
        return lower;
    }

    public int getUpper() {
        return upper;
    }
}
