package com.lbi.vaa.tdd.flightstatus;

import javax.xml.datatype.DatatypeConfigurationException;

public interface StatusService {

    Object[] findDefault();

    boolean isAlive();

    DepartureResponse findDepartures(String airportCode, DateRangeConfiguration dateRangeConfiguration, DepartureDate departureDate) throws DatatypeConfigurationException;
}
