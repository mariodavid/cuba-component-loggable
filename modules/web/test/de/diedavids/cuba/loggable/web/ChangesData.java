package de.diedavids.cuba.loggable.web;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.web.testsupport.TestContainer;
import com.haulmont.cuba.web.testsupport.TestEntityFactory;
import com.haulmont.cuba.web.testsupport.TestEntityState;
import de.diedavids.cuba.loggable.entity.LogEntry;
import de.diedavids.cuba.loggable.entity.sample.SampleEntity;


public class ChangesData {

    private final TestContainer container;

    public ChangesData(TestContainer container) {
        this.container = container;
    }

    public SampleEntity sample(String customerName) {
        return sampleEntityFactory().create(
            "customerName", customerName
        );
    }

    private TestEntityFactory<SampleEntity> sampleEntityFactory() {
        return container.getEntityFactory(SampleEntity.class, TestEntityState.NEW);
    }

    public LogEntry logEntry(Entity loggable, String message) {
        return logEntryFactory().create(
            "loggable", loggable,
            "message", message
        );
    }

    private TestEntityFactory<LogEntry> logEntryFactory() {
        return container.getEntityFactory(LogEntry.class, TestEntityState.DETACHED);
    }
}