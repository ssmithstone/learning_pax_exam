package uk.co.smithstone.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.Properties;

public class SimpleActivator implements BundleActivator {
    @Override
    public void start(BundleContext bundleContext) throws Exception {

        System.out.println("Registering Service");
        bundleContext.registerService(Simple.class.getName() , new SimpleService(), new Properties());

    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {

    }
}
