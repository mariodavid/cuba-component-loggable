package de.diedavids.cuba.loggable.service;

import com.haulmont.cuba.core.entity.Entity;
import de.diedavids.cuba.loggable.entity.LogEntry;
import de.diedavids.cuba.loggable.entity.LogEntryCategory;
import de.diedavids.cuba.loggable.entity.LogLevel;

import java.util.Collection;


public interface LogEntryService {
    String NAME = "ddcl_LogEntryService";

    int countLogEntries(Entity loggable);

    Collection<LogEntry> getLogEntries(Entity loggable);

    LogEntry createLogEntry(
            Entity loggable,
            String message,
            String detailedMessage,
            LogLevel level,
            LogEntryCategory category
    );
}
