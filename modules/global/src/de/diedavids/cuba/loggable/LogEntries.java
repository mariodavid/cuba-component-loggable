package de.diedavids.cuba.loggable;

import com.haulmont.cuba.core.entity.Entity;
import de.diedavids.cuba.loggable.LogEntriesBean.ExceptionLogEntryBuilder;
import de.diedavids.cuba.loggable.LogEntriesBean.MessageLogEntryBuilder;


/**
 * Factory for creating Log Entries via builders
 */
public interface LogEntries {

    String NAME = "ddcl_LogEntries";

    MessageLogEntryBuilder message(Entity loggable);

    ExceptionLogEntryBuilder exception(Entity loggable, Throwable throwable);


    interface LogEntrySourceBuilder {

        LogEntrySource build();
    }
}
