package com.lbi.vaa.tdd.flightstatus.tcs;

import com.lbi.vaa.tdd.flightstatus.*;
import com.virgin_atlantic.schemas.operations.flights.services.flight._2011._05.GetFlightStatusInformationResult;
import com.virgin_atlantic.schemas.services.servicecallingcontext._2011.*;
import com.virgin_atlantic.webservices.GetFlightStatusInformation;
import com.virgin_atlantic.webservices.WSFlyfoV3Soap;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

public class TCSFlightStatusService implements StatusService {

    private String endpointAddress;
    private String databaseName;

    public TCSFlightStatusService() {
    }

    public void setEndpointAddress(String endpointAddress) {
        this.endpointAddress = endpointAddress;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public TCSFlightStatusService(String endpointAddress, String databaseName) {
        this.endpointAddress = endpointAddress;
        this.databaseName = databaseName;
    }

    @Override
    public DepartureResponse findDepartures(String airportCode, DateRangeConfiguration dateRangeConfiguration, DepartureDate departureDate) throws DatatypeConfigurationException {
        GetFlightStatusInformation parameters = new GetFlightStatusInformation();

        parameters.setSRequest(new TCSFlightStatusRequestFactory(airportCode, dateRangeConfiguration, departureDate).create());
        WSFlyfoV3Soap client = createClient(endpointAddress, databaseName);
        TCSDepartureResponse tcsDepartureResponse = new TCSDepartureResponse(client.getFlightStatusInformation(parameters));
        return tcsDepartureResponse;
    }


    private WSFlyfoV3Soap createClient(String endpointAddress, String databaseName) {

        try {
            JaxWsProxyFactoryBean bean = new JaxWsProxyFactoryBean();
            bean.setServiceClass(WSFlyfoV3Soap.class);
            bean.setAddress(endpointAddress);
            bean.setDataBinding(new JAXBDataBinding());
            final ServiceCallingContext context = createCallingContext(databaseName);
            bean.setOutInterceptors(new ArrayList<Interceptor<? extends Message>>() {{
                add(new TCSFlightStatusRequestInterceptor(context));
            }});

            Object o = bean.create();
            return (WSFlyfoV3Soap) o;
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return null;

    }

    private ServiceCallingContext createCallingContext(String databaseName) {
        final ServiceCallingContext context = new ServiceCallingContext();

        ApplicationNameType applicationNameType = new ApplicationNameType();
        applicationNameType.setGUID("52C155166448684C8F0D0D80D433621A");
        applicationNameType.setGroupId("3");
        applicationNameType.setVersionId("1");
        context.setApplicationName(applicationNameType);

        CallingContextType callingContextType = new CallingContextType();
        context.setCallingContext(callingContextType);

        HeaderVersionType headerVersionType = new HeaderVersionType();
        headerVersionType.setHeaderVersion(new HeaderVersionType());
        context.setHeaderVersion(headerVersionType);

        UserContextType userContext = new UserContextType();
        userContext.setDatabaseName(databaseName);
        context.setUserContext(userContext);
        return context;
    }

    @Override
    public Object[] findDefault() {
        return new Object[0];
    }

    @Override
    public boolean isAlive() {
        return true;
    }

    private static class TCSFlightStatusRequestInterceptor extends AbstractSoapInterceptor {

//        private static final Log log = LogFactory.getLog(TCSFlightStatusRequestInterceptor.class);

        private final ServiceCallingContext context;

        public TCSFlightStatusRequestInterceptor(ServiceCallingContext context) {
            super(Phase.SETUP);
            this.context = context;
        }

        @Override
        public void handleMessage(SoapMessage soapMessage) throws Fault {
            List<Header> headers = soapMessage.getHeaders();
            try {
                headers.add(new Header(new QName("http://schemas.virgin-atlantic.com/Services/ServiceCallingContext/2011", "ServiceCallingContext"), context, new JAXBDataBinding(ServiceCallingContext.class)));
            } catch (JAXBException e) {
                e.printStackTrace(System.err);
//                log.error(e);
            }
        }
    }
}

class TCSDepartureFactory {

    public static final int DEFAULT_INDEX = 0;

    public static Departure create(final GetFlightStatusInformationResult.StatusRS response) {
        return new Departure() {
            @Override
            public String getFromAirport() {
                return response.getFlightDetails().get(DEFAULT_INDEX).getOriginAirport().get(DEFAULT_INDEX).getName();
            }

            @Override
            public String getToAirport() {
                return response.getFlightDetails().get(DEFAULT_INDEX).getDestinationAirport().get(DEFAULT_INDEX).getName();
            }

            @Override
            public String getTerminal() {
                return response.getFlightDetails().get(DEFAULT_INDEX).getOriginAirport().get(DEFAULT_INDEX).getTerminal();
            }

            @Override
            public String getFlightNumber() {
                return String.format("%s%03d", response.getFlightDetails().get(DEFAULT_INDEX).getFlight().get(DEFAULT_INDEX).getCarrierCode(),
                        response.getFlightDetails().get(DEFAULT_INDEX).getFlight().get(DEFAULT_INDEX).getFlightNumber());
            }

            @Override
            public String getStatus() {
                return response.getFlightDetails().get(DEFAULT_INDEX).getFlight().get(DEFAULT_INDEX).getFlightStatus();
            }
        };
    }
}

