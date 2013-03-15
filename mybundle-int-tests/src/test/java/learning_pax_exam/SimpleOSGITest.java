package learning_pax_exam;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.osgi.framework.BundleContext;
import uk.co.smithstone.osgi.Simple;

import javax.inject.Inject;

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
    public Option[] config(){

        return options(
                mavenBundle("learning_pax_exam", "mybundle" , "1.0-SNAPSHOT"),
                junitBundles());
    }

    @Test
    public void testBundleContextIsAvailable() throws Exception {

        assertThat(bundleContext, notNullValue());
        assertThat(simple, notNullValue());
        assertThat(simple.isAlive(), is(true));
    }

}


