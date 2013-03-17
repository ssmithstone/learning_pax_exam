package com.lbi.vaa.tdd.flightstatus.tcs;

import com.lbi.vaa.tdd.flightstatus.Departure;
import com.lbi.vaa.tdd.flightstatus.DepartureResponse;
import com.virgin_atlantic.schemas.operations.flights.services.flight._2011._05.GetFlightStatusInformationResult;
import com.virgin_atlantic.webservices.GetFlightStatusInformationResponse;

import java.util.ArrayList;
import java.util.List;

public class TCSDepartureResponse implements DepartureResponse {

    private GetFlightStatusInformationResponse response;

    TCSDepartureResponse(GetFlightStatusInformationResponse response) {
        this.response = response;
    }

    @Override
    public boolean hasError() {
        return response.getGetFlightStatusInformationResult().getResult() != null;
    }

    @Override
    public List<Departure> getDepartures() {
        return new ArrayList<Departure>(){{
            for (final GetFlightStatusInformationResult.StatusRS rs : response.getGetFlightStatusInformationResult().getStatusRS()) {
                add(TCSDepartureFactory.create(rs));
            }
        }};
    }
}
