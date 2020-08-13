package de.diedavids.cuba.loggable.service;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.EntitySet;
import de.diedavids.cuba.loggable.entity.LogEntry;
import de.diedavids.cuba.loggable.entity.LogEntryCategory;
import de.diedavids.cuba.loggable.entity.LogLevel;

import java.util.Collection;
import java.util.Optional;


public interface LogEntryService {
    String NAME = "ddcl_LogEntryService";

    int countLogEntries(Entity loggable);

    Collection<LogEntry> getLogEntries(Entity loggable);


    LogEntry createLogEntry(
            LogEntrySource logEntryDescription
    );

    EntitySet createLogEntries(
            Collection<LogEntrySource> logEntryDescriptions
    );



}
