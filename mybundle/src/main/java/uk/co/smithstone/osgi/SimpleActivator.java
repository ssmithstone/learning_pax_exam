package uk.co.smithstone.osgi;

import com.lbi.vaa.tdd.flightstatus.DateRangeConfiguration;
import com.lbi.vaa.tdd.flightstatus.DepartureResponse;
import com.lbi.vaa.tdd.flightstatus.GregorianDepartureDate;
import com.lbi.vaa.tdd.flightstatus.tcs.TCSFlightStatusService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.Properties;

public class SimpleActivator implements BundleActivator {
    @Override
    public void start(BundleContext bundleContext) throws Exception {



        Simple simple = new Simple() {
            @Override
            public boolean isAlive() {

                try {
                    final TCSFlightStatusService service = new TCSFlightStatusService("http://stephens-macbook-air.local:8080/", "CORELRO");
                    DepartureResponse lhr = service.findDepartures("LHR", new DateRangeConfiguration(0, 0), new GregorianDepartureDate());
                    System.out.println(lhr.getDepartures().size());
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
                return false;
            }
        };

        bundleContext.registerService(Simple.class.getName(), simple, new Properties());

    }

    /*private Simple createSimpleAnotherWay(){
        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        final Client client = factory.createClient("http://stephens-macbook-air.local:8088/mockRoomManagementServiceSoap11Binding?WSDL");

        return new Simple() {
            @Override
            public boolean isAlive() {

                return client != null;
            }
        };
    }*/

    @Override
    public void stop(BundleContext bundleContext) throws Exception {

    }
}
