package de.diedavids.cuba.loggable.service;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import de.diedavids.cuba.entitysoftreference.SoftReferenceService;
import de.diedavids.cuba.loggable.entity.LogEntry;
import de.diedavids.cuba.loggable.entity.LogEntryCategory;
import de.diedavids.cuba.loggable.entity.LogLevel;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;

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
    public LogEntry createLogEntry(
            Entity loggable,
            String message,
            String detailedMessage,
            LogLevel level,
            LogEntryCategory category
    ) {

        LogEntry logEntry = dataManager.create(LogEntry.class);

        logEntry.setLoggable(loggable);
        logEntry.setMessage(message);
        logEntry.setDetailedMessage(detailedMessage);
        logEntry.setLevel(level);
        logEntry.setCategory(category);

        return dataManager.commit(logEntry);

    }

}
