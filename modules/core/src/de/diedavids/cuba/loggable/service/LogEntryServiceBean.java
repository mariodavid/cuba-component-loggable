package de.diedavids.cuba.loggable.service;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.EntitySet;
import de.diedavids.cuba.entitysoftreference.SoftReferenceService;
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
    public LogEntry createLogEntry(LogEntrySource description) {

        LogEntry logEntry = description.toLogEntry(dataManager);
        EntitySet persistedEntries = persistLogEntries(Collections.singletonList(logEntry));
        return persistedEntries.get(LogEntry.class, logEntry.getId());

    }

    private EntitySet persistLogEntries(Collection<LogEntry> logEntries) {
        CommitContext commitContext = new CommitContext();
        logEntries.forEach(commitContext::addInstanceToCommit);
        return dataManager.commit(commitContext);
    }

    @Override
    public EntitySet createLogEntries(Collection<LogEntrySource> logEntryDescriptions) {
        List<LogEntry> logEntries = logEntryDescriptions.stream()
                .map(description -> description.toLogEntry(dataManager))
                .collect(Collectors.toList());

        return persistLogEntries(logEntries);

    }


}
