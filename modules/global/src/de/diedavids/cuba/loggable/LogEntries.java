package de.diedavids.cuba.loggable;

import com.haulmont.cuba.core.entity.Entity;
import de.diedavids.cuba.loggable.LogEntriesBean.ExceptionLogEntryBuilder;
import de.diedavids.cuba.loggable.LogEntriesBean.MessageLogEntryBuilder;


/**
 * Factory for creating Log Entries via builders
 */
public interface LogEntries {

    String NAME = "ddcl_LogEntries";

    /**
     * creates a LogEntryBuilder for a regular log message
     * @param loggable the loggable to be based on
     * @return a MessageLogEntryBuilder instance
     */
    MessageLogEntryBuilder message(Entity loggable);

    /**
     * creates a LogEntryBuilder for an log message that is based on an exception
     * @param loggable the loggable to be based on
     * @return a ExceptionLogEntryBuilder instance
     */
    ExceptionLogEntryBuilder exception(Entity loggable, Throwable throwable);


    interface LogEntrySourceBuilder {

        LogEntrySource build();
    }
}
