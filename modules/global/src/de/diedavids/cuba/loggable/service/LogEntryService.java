package de.diedavids.cuba.loggable.service;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.EntitySet;
import de.diedavids.cuba.loggable.LogEntrySource;
import de.diedavids.cuba.loggable.entity.LogEntry;

import java.util.Collection;


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
