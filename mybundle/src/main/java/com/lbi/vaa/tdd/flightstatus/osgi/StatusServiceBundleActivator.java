package com.lbi.vaa.tdd.flightstatus.osgi;

import com.lbi.vaa.tdd.flightstatus.StatusService;
import com.lbi.vaa.tdd.flightstatus.tcs.TCSFlightStatusService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.Properties;

public class StatusServiceBundleActivator implements BundleActivator {

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        StatusService service = new TCSFlightStatusService("http://10.201.59.104/WS_FLYFOV3/WS_FLYFOV3_1.asmx", "CORELRO");
        bundleContext.registerService(StatusService.class.getName(),
                service,
                new Properties());

    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
    }
}
