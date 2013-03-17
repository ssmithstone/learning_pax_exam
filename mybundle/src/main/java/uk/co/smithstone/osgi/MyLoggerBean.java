package uk.co.smithstone.osgi;

public class MyLoggerBean {

    public MyLoggerBean() {
        System.out.println("Creating Bean using spring context");
        System.err.println("Creating Bean using spring context");
    }
}
