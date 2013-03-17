package com.lbi.vaa.tdd.flightstatus.tcs;

import com.lbi.vaa.tdd.flightstatus.DateRangeConfiguration;
import com.lbi.vaa.tdd.flightstatus.DepartureResponse;
import com.lbi.vaa.tdd.flightstatus.GregorianDepartureDate;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class TCSFlightStatusServiceTest {


    @Test
    public void testCallWebService() throws Exception {

//        WSFlyfoV3 v3 = new WSFlyfoV3(new URL("http://stephens-macbook-air.local:8080/?WSDL"));
//
//        WSFlyfoV3Soap soap = v3.getWSFlyfoV3Soap();



        final TCSFlightStatusService service = new TCSFlightStatusService("http://localhost:8080/", "CORELRO");
        DepartureResponse lhr = service.findDepartures("LHR", new DateRangeConfiguration(0, 0), new GregorianDepartureDate());
        assertThat(lhr.getDepartures().size() , equalTo(21));
    }
}
