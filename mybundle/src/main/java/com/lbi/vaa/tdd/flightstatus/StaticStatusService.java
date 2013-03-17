package com.lbi.vaa.tdd.flightstatus;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.ArrayList;
import java.util.List;

public class StaticStatusService implements StatusService {
    @Override
    public Object[] findDefault() {
        return new Object[] { new Object() };
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public DepartureResponse findDepartures(String airportCode, DateRangeConfiguration dateRangeConfiguration, DepartureDate departureDate) throws DatatypeConfigurationException {
        return new DepartureResponse() {
            @Override
            public boolean hasError() {
                return false;
            }

            @Override
            public List<Departure> getDepartures() {
                return new ArrayList<Departure>(){{

                }};
            }
        };
    }
}
