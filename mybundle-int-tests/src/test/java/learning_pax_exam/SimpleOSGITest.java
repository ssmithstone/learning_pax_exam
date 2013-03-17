package learning_pax_exam;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.tinybundles.core.TinyBundles;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import uk.co.smithstone.osgi.Simple;

import javax.inject.Inject;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.ops4j.pax.exam.CoreOptions.*;

@RunWith(PaxExam.class)
public class SimpleOSGITest {

    @Inject
    private BundleContext bundleContext;

    @Inject
    Simple simple;

    @Configuration
    public Option[] configCxf() {


        InputStream build = TinyBundles.bundle().set(Constants.IMPORT_PACKAGE, "META-INF.cxf").build();
        return options(

                repositories("http://repository.springsource.com/maven/bundles/release", "http://repo.adobe.com/nexus/content/groups/public"),

//                wrappedBundle(mavenBundle("javax.xml.bind" , "jaxb-api" , "2.1")),
//                wrappedBundle(mavenBundle("com.sun.xml.bind" , "jaxb-impl" , "2.1.3")),
//                wrappedBundle(mavenBundle("org.jvnet.jaxb2_commons" , "jaxb2-basics-runtime" , "0.5.0")),


                mavenBundle("org.apache.cxf", "cxf-bundle", "2.3.2"),
                mavenBundle("org.codehaus.woodstox", "woodstox-core-asl", "4.0.7"),

                mavenBundle("org.apache.servicemix.bundles", "org.apache.servicemix.bundles.wsdl4j", "1.6.2_3"),
                mavenBundle("org.apache.servicemix.bundles", "org.apache.servicemix.bundles.jaxb-impl", "2.2.1.1_1"),
                mavenBundle("com.day.commons.osgi.wrapper" , "com.day.commons.osgi.wrapper.mail" , "1.4.0-0001"),
                mavenBundle("org.apache.geronimo.specs", "geronimo-jms_1.1_spec", "1.1.1"),
                mavenBundle("org.apache.geronimo.specs", "geronimo-ws-metadata_2.0_spec", "1.1.3"),
                mavenBundle("org.apache.aries.transaction" , "org.apache.aries.transaction.manager", "0.3.1-R1229964"),
                mavenBundle("javax.ws.rs", "jsr311-api", "1.1.1"),
                mavenBundle("org.apache.servicemix.specs", "org.apache.servicemix.specs.jaxb-api-2.2", "1.7.0"),
                mavenBundle("org.apache.servicemix.specs", "org.apache.servicemix.specs.stax-api-1.0", "1.7.0"),
                mavenBundle("org.apache.servicemix.specs", "org.apache.servicemix.specs.jaxws-api-2.2", "1.7.0"),
                mavenBundle("org.apache.servicemix.bundles" , "org.apache.servicemix.bundles.bcel" , "5.2_3"),
                mavenBundle("org.apache.servicemix.bundles", "org.apache.servicemix.bundles.xmlresolver-1.2", "4.0-m1"),
                mavenBundle("org.apache.servicemix.bundles", "org.apache.servicemix.bundles.xmlsec", "1.4.4_1"),
                mavenBundle("org.apache.servicemix.bundles", "org.apache.servicemix.bundles.asm", "3.3_1"),
                mavenBundle("org.apache.servicemix.bundles", "org.apache.servicemix.bundles.xalan", "2.7.1_3"),
                mavenBundle("org.apache.servicemix.bundles", "org.apache.servicemix.bundles.xerces", "2.9.1_4"),
                mavenBundle("org.apache.felix", "org.apache.felix.scr", "1.6.1-R1236132"),


                mavenBundle("org.apache.abdera" , "abdera-core", "1.0-R783018"),
                mavenBundle("org.apache.abdera" , "abdera-parser", "1.0-R783018"),

                mavenBundle("com.day.commons.osgi.wrapper" , "com.day.commons.osgi.wrapper.commons-lang2", "2.5-0001"),
                mavenBundle("org.slf4j", "jcl-over-slf4j", "1.6.4"),
                mavenBundle("org.slf4j", "log4j-over-slf4j", "1.6.4"),
                mavenBundle("org.apache.neethi", "neethi", "2.0.4"),
                mavenBundle("org.apache.ws.commons.axiom", "axiom-api", "1.2.7"),
                mavenBundle("org.apache.ws.commons.axiom", "axiom-impl", "1.2.7"),
                mavenBundle("org.apache.ws.commons.schema", "XmlSchema", "1.4.7"),
                mavenBundle("org.apache.ws.security", "wss4j", "1.5.11"),
                mavenBundle("org.codehaus.woodstox", "stax2-api", "3.0.2"),
                mavenBundle("org.apache.sling", "org.apache.sling.scripting.javascript", "2.0.12"),
                mavenBundle("org.slf4j", "slf4j-api", "1.6.4"),

//                mavenBundle("com.day.cq.wcm" , "cq-wcm-core" , "5.5.14"),
//                mavenBundle("com.day.cq.wcm" , "cq-wcm-commons" , "5.5.2"),
//                mavenBundle("com.day.cq", "cq-commons" , "5.5.0"),
//                mavenBundle("com.day.commons" , "day-commons-gfx" , "2.1.10"),
//                mavenBundle("com.day.cq.wcm" , "cq-wcm-api" , "5.5.0"),
//                mavenBundle("com.day.cq.dam" , "cq-dam-api" , "5.5.6"),
//                mavenBundle("com.day.cq.wcm" , "cq-wcm-foundation" , "5.5.2"),
//                mavenBundle("com.day.cq" , "cq-tagging" , "5.5.0"),
//                mavenBundle("com.day.cq.wcm" , "cq-wcm-taglib" , "5.5.0"),
//                mavenBundle("com.day.cq.workflow" , "cq-workflow-api" , "5.5.0"),
//                mavenBundle("com.day.commons" , "day-commons-io" , "1.1.6"),
//                mavenBundle("com.day.cq" , "cq-rewriter" , "5.5.2"),
//                mavenBundle("com.day.cq" , "cq-mailer", "5.5.0"),
//                mavenBundle("com.day.cq.wcm" , "cq-wcm-mobile-api" , "5.5.2"),
//                mavenBundle("com.day.cq" , "cq-search" , "5.5.4"),
//                mavenBundle("com.adobe.granite" , "com.adobe.granite.replication.core", "5.5.14"),


                mavenBundle("org.apache.sling" , "org.apache.sling.commons.log" , "2.1.3-R1232904"),
                mavenBundle("org.aopalliance", "com.springsource.org.aopalliance", "1.0.0"),
                mavenBundle("org.springframework", "org.springframework.aop", "3.2.1.RELEASE"),
                mavenBundle("org.springframework", "org.springframework.beans", "3.2.1.RELEASE"),
                mavenBundle("org.springframework", "org.springframework.context", "3.2.1.RELEASE"),
                mavenBundle("org.springframework", "org.springframework.context.support", "3.2.1.RELEASE"),
                mavenBundle("org.springframework", "org.springframework.core", "3.2.1.RELEASE"),
                mavenBundle("org.springframework", "org.springframework.expression", "3.2.1.RELEASE"),
                mavenBundle("org.springframework", "org.springframework.jms", "3.2.1.RELEASE"),
                mavenBundle("org.springframework", "org.springframework.transaction", "3.2.1.RELEASE"),


                mavenBundle("org.apache.aries", "org.apache.aries.util", "0.4"),
                mavenBundle("org.apache.servicemix.specs", "org.apache.servicemix.specs.saaj-api-1.3", "1.7.0"),
                bundle("http://repo.adobe.com/nexus/content/groups/public/org/apache/abdera/abdera-i18n/1.0-R783018/abdera-i18n-1.0-R783018.jar"),
                mavenBundle("commons-codec", "commons-codec", "1.5"),

                mavenBundle("javax.jcr", "jcr", "2.0"),
                mavenBundle("org.apache.sling", "org.apache.sling.api", "2.2.4"),
                mavenBundle("org.apache.sling" , "org.apache.sling.jcr.resource" , "2.0.11-R1239966"),
                mavenBundle("org.apache.sling", "org.apache.sling.scripting.api", "2.1.4"),
                mavenBundle("org.apache.sling" , "org.apache.sling.auth.core" , "1.0.7-R1239106"),
                mavenBundle("org.apache.felix", "org.apache.felix.http.bundle", "2.2.0"),

                mavenBundle("org.apache.sling", "org.apache.sling.commons.classloader", "1.3.0"),
                mavenBundle("org.apache.sling", "org.apache.sling.commons.json", "2.0.6"),
                mavenBundle("commons-collections", "commons-collections", "3.2.1"),
                mavenBundle("org.apache.sling", "org.apache.sling.adapter", "2.0.10"),
                mavenBundle("commons-io", "commons-io", "1.4"),
                mavenBundle("org.apache.sling", "org.apache.sling.commons.osgi", "2.1.0"),
                mavenBundle("org.apache.sling", "org.apache.sling.jcr.api", "2.1.0"),
                mavenBundle("org.eclipse.gemini.blueprint", "gemini-blueprint-core", "1.0.0.RELEASE"),
                mavenBundle("org.eclipse.gemini.blueprint", "gemini-blueprint-extender", "1.0.0.RELEASE"),
                mavenBundle("org.eclipse.gemini.blueprint", "gemini-blueprint-io", "1.0.0.RELEASE"),

                mavenBundle("org.apache.httpcomponents", "httpclient-osgi" , "4.0.3"),
                mavenBundle("org.apache.httpcomponents", "httpcore-osgi" , "4.0.1"),


                mavenBundle("learning_pax_exam", "mybundle", "1.0-SNAPSHOT"),
                keepCaches(),
                junitBundles());
    }


    @Test
    public void testBundleContextIsAvailable() throws Exception {

        assertThat(bundleContext, notNullValue());
        assertThat(simple, notNullValue());
        assertThat(simple.isAlive(), is(true));
    }

}


