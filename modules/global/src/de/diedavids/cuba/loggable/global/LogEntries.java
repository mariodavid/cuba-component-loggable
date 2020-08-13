package de.diedavids.cuba.loggable.global;

import com.haulmont.cuba.core.entity.Entity;
import de.diedavids.cuba.loggable.service.LogEntrySource;

public interface LogEntries {

    String NAME = "ddcl_LogEntries";

    LogEntrySourceBuilder message(Entity loggable);

    LogEntrySourceBuilder exception(Entity loggable, Throwable throwable);


    interface LogEntrySourceBuilder {

        LogEntrySource build();
    }
}
