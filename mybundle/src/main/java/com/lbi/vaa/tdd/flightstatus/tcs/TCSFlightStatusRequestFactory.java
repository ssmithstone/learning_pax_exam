package com.lbi.vaa.tdd.flightstatus.tcs;

import com.lbi.vaa.tdd.flightstatus.DateRangeConfiguration;
import com.lbi.vaa.tdd.flightstatus.DepartureDate;
import com.virgin_atlantic.schemas.common._2011._05.AirportType;
import com.virgin_atlantic.schemas.common._2011._05.DateRangeType;
import com.virgin_atlantic.schemas.common._2011._05.DateTimeType;
import com.virgin_atlantic.schemas.common._2011._05.FlightType;
import com.virgin_atlantic.schemas.operations.flights.information._2011._05.StandardFlightInformationType;
import com.virgin_atlantic.schemas.operations.flights.services.flight._2011._05.SRequest;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.math.BigInteger;

public class TCSFlightStatusRequestFactory {
    private String airportCode;
    private DateRangeConfiguration dateRangeConfiguration;
    private DepartureDate departureDate;
    private StandardFlightInformationType flightInformation;

    public TCSFlightStatusRequestFactory(String airportCode, DateRangeConfiguration dateRangeConfiguration, DepartureDate departureDate) {
        this.airportCode = airportCode;
        this.dateRangeConfiguration = dateRangeConfiguration;
        this.departureDate = departureDate;
        this.flightInformation = new StandardFlightInformationType();
    }

    public SRequest create() throws DatatypeConfigurationException {
        SRequest sRequest = new SRequest();
        SRequest.StatusRQ statusRQ = new SRequest.StatusRQ();

        createAirport(airportCode);
        createDateRange(dateRangeConfiguration);
        createDepartureDate(departureDate);
        createFlightNumber();
        statusRQ.setFlightDetails(flightInformation);

        sRequest.getStatusRQ().add(statusRQ);
        return sRequest;
    }

    private void createAirport(String airportCode) {
        AirportType airportType = new AirportType();
        airportType.setIATAAirportCode(airportCode);
        flightInformation.getOriginAirport().add(airportType);
    }

    private void createDateRange(DateRangeConfiguration dateRangeConfiguration) {
        DateRangeType dateRange = new DateRangeType();
        dateRange.setMinusIndicator(BigInteger.valueOf(dateRangeConfiguration.getLower()));
        dateRange.setPlusIndicator(BigInteger.valueOf(dateRangeConfiguration.getUpper()));
        flightInformation.setDateRange(dateRange);
    }

    private void createDepartureDate(DepartureDate departureDate) throws DatatypeConfigurationException {
        DateTimeType dateTimeType = new DateTimeType();
        dateTimeType.setLocalDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(departureDate.getGregorianCalendar()));
        flightInformation.getScheduledDepartureDate().add(dateTimeType);
    }

    private void createFlightNumber() {
        FlightType type = new FlightType();
        type.setCarrierCode("VS");
        type.setFlightNumber(0);
        flightInformation.getFlight().add(type);
    }
}
