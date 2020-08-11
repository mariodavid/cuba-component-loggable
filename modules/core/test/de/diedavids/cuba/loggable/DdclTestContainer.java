package de.diedavids.cuba.loggable;

import com.haulmont.cuba.testsupport.TestContainer;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.ArrayList;
import java.util.Arrays;

public class DdclTestContainer extends TestContainer {

    public DdclTestContainer() {
        super();
        //noinspection ArraysAsListWithZeroOrOneArgument
        appComponents = Arrays.asList(
                // list add-ons here: "com.haulmont.reports", "com.haulmont.addon.bproc", etc.
                "com.haulmont.cuba",
                "de.diedavids.cuba.entitysoftreference"
        );
        appPropertiesFiles = Arrays.asList(
                // List the files defined in your web.xml
                // in appPropertiesConfig context parameter of the core module
                "de/diedavids/cuba/loggable/app.properties",
                // Add this file which is located in CUBA and defines some properties
                // specifically for test environment. You can replace it with your own
                // or add another one in the end.
                "com/haulmont/cuba/testsupport/test-app.properties");
        autoConfigureDataSource();
    }


    public static class Common extends DdclTestContainer {

        // A common singleton instance of the test container which is initialized once for all tests
        public static final DdclTestContainer.Common INSTANCE = new DdclTestContainer.Common();

        private static volatile boolean initialized;

        private Common() {
        }

        @Override
        public void beforeAll(ExtensionContext extensionContext) throws Exception {
            if (!initialized) {
                super.beforeAll(extensionContext);
                initialized = true;
            }
            setupContext();
        }


        @SuppressWarnings("RedundantThrows")
        @Override
        public void afterAll(ExtensionContext extensionContext) throws Exception {
            cleanupContext();
            // never stops - do not call super
        }
    }
}