package de.diedavids.cuba.loggable.service;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.EntitySet;
import de.diedavids.cuba.entitysoftreference.SoftReferenceService;
import de.diedavids.cuba.loggable.LogEntrySource;
import de.diedavids.cuba.loggable.entity.LogEntry;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service(LogEntryService.NAME)
public class LogEntryServiceBean implements LogEntryService {

    private static final String LOGGABLE_COLUMN_NAME = "loggable";

    @Inject
    private SoftReferenceService softReferenceService;

    @Inject
    private DataManager dataManager;

    @Override
    public int countLogEntries(Entity loggable) {
        return softReferenceService.countEntitiesForSoftReference(LogEntry.class, loggable, LOGGABLE_COLUMN_NAME);
    }

    @Override
    public Collection<LogEntry> getLogEntries(Entity loggable) {
        return softReferenceService.loadEntitiesForSoftReference(LogEntry.class, loggable, LOGGABLE_COLUMN_NAME, "logEntry-view");
    }

    @Override
    public LogEntry createLogEntry(LogEntrySource logEntryDescription) {
        return convertToLogEntry(logEntryDescription);
    }

    @Override
    public LogEntry saveLogEntry(LogEntrySource description) {

        final LogEntry logEntry = createLogEntry(description);

        EntitySet persistedEntries = persistLogEntries(Collections.singletonList(logEntry));

        return persistedEntries.get(LogEntry.class, logEntry.getId());

    }


    @Override
    public EntitySet saveLogEntries(Collection<LogEntrySource> logEntryDescriptions) {
        return persistLogEntries(createLogEntries(logEntryDescriptions));

    }

    @Override
    public Collection<LogEntry> createLogEntries(Collection<LogEntrySource> logEntryDescriptions) {
        return logEntryDescriptions.stream()
            .map(this::convertToLogEntry)
            .collect(Collectors.toList());
    }

    private LogEntry convertToLogEntry(LogEntrySource description) {
        return description.toLogEntry(dataManager);
    }

    private EntitySet persistLogEntries(Collection<LogEntry> logEntries) {
        CommitContext commitContext = new CommitContext();
        logEntries.forEach(commitContext::addInstanceToCommit);
        return dataManager.commit(commitContext);
    }

}
