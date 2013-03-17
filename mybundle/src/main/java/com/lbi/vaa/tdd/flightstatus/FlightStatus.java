package com.lbi.vaa.tdd.flightstatus;

public class FlightStatus {

    public enum Type {
        ARRIVAL,DEPARTURE
    }

    private Type type;

    public FlightStatus() {
    }

    public FlightStatus(Type type) {

        this.type = type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
