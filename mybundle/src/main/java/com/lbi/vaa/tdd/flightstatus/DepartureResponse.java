package com.lbi.vaa.tdd.flightstatus;

import java.util.List;

public interface DepartureResponse {

    boolean hasError();

    List<Departure> getDepartures();

}
