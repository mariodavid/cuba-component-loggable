package de.diedavids.cuba.loggable;

import com.haulmont.cuba.core.entity.Entity;

public interface LogEntries {

    String NAME = "ddcl_LogEntries";

    LogEntrySourceBuilder message(Entity loggable);

    LogEntrySourceBuilder exception(Entity loggable, Throwable throwable);


    interface LogEntrySourceBuilder {

        LogEntrySource build();
    }
}
