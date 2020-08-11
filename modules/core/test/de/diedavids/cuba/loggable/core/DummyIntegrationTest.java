package de.diedavids.cuba.loggable.core;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.testsupport.TestContainer;
import de.diedavids.cuba.loggable.DdclTestContainer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DummyIntegrationTest {

    @RegisterExtension
    static TestContainer container = DdclTestContainer.Common.INSTANCE;
    static DataManager dataManager;

    @BeforeAll
    static void beforeAll() {
        dataManager = AppBeans.get(DataManager.class);
    }

    @Test
    void myTestMethod() {

        assertThat(true)
                .isEqualTo(true);
    }
}