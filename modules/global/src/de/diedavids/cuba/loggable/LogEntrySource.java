package de.diedavids.cuba.loggable;

import com.haulmont.cuba.core.global.DataManager;
import de.diedavids.cuba.loggable.entity.LogEntry;

public interface LogEntrySource {

    LogEntry toLogEntry(DataManager dataManager);
}
