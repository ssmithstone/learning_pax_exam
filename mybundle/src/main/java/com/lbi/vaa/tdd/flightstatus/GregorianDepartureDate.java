package com.lbi.vaa.tdd.flightstatus;

import java.util.GregorianCalendar;

public class GregorianDepartureDate implements DepartureDate {
    @Override
    public GregorianCalendar getGregorianCalendar() {
        return new GregorianCalendar();
    }
}
