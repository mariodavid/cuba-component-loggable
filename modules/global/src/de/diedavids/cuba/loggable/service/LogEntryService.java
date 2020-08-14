package de.diedavids.cuba.loggable.service;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.EntitySet;
import de.diedavids.cuba.loggable.LogEntrySource;
import de.diedavids.cuba.loggable.entity.LogEntry;

import java.util.Collection;


public interface LogEntryService {

    String NAME = "ddcl_LogEntryService";

    /**
     * count the log entries for the given loggable
     * @param loggable the entity to count the Log Entries for
     * @return count of the log entries
     */
    int countLogEntries(Entity loggable);

    /**
     * loads all Log Entries for a given loggable entity
     * @param loggable the entity to check for Log Entries
     * @return the Log Entries
     */
    Collection<LogEntry> getLogEntries(Entity loggable);


    /**
     * Creates and stores one Log Entry based on the given LogEntrySource
     * @param logEntryDescription the LogEntrySource which will be stored
     * @return the resulting saved LogEntry
     */
    LogEntry createLogEntry(
            LogEntrySource logEntryDescription
    );

    /**
     * Creates and stores multiple Log Entries based on the given LogEntrySource
     * @param logEntryDescriptions the LogEntrySources which will be stored
     * @return the resulting saved Log Entries as an EntitySet
     */
    EntitySet createLogEntries(
            Collection<LogEntrySource> logEntryDescriptions
    );

}
